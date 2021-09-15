package com.smsglobal.transport;

import com.smsglobal.client.AutoTopup;
import com.smsglobal.client.Contact;
import com.smsglobal.client.ContactGroups;
import com.smsglobal.client.CreditBalance;
import com.smsglobal.client.DedicatedNumbers;
import com.smsglobal.client.IncomingMessages;
import com.smsglobal.client.LowBalanceAlerts;
import com.smsglobal.client.Message;
import com.smsglobal.client.Optouts;
import com.smsglobal.client.OutgoingMessages;
import com.smsglobal.client.SharedPools;
import com.smsglobal.client.VerifiedNumbers;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

/**
 * REST Transport
 */
public class RestTransport implements Closeable {

    private final String key;
    private final String secret;
    private final String baseUrl;
    private final String host;
    private final int port;
    private final String version;
    private final CloseableHttpClient httpClient;
    private final Random random = new Random();

    public RestTransport(final String key, final String secret, final String baseUrl, final CloseableHttpClient httpClient) throws URISyntaxException {
        this.key = key;
        this.secret = secret;
        this.httpClient = httpClient;
        this.baseUrl = baseUrl;
        final URI uri = new URI(baseUrl);
        this.host = uri.getHost();
        int port = uri.getPort();
        if (port <= 0) {
            final String scheme = uri.getScheme();
            switch (scheme) {
                case "http":
                    port = 80;
                    break;
                case "https":
                    port = 443;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
        this.port = port;
        final String[] paths = uri.getPath().split("/");
        this.version = paths[1];
    }

    public RestTransport(final String key, final String secret, final String baseUrl) throws URISyntaxException {
        this(key, secret, baseUrl, HttpClients.createDefault());
    }

    public static String toXml(final Message message) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(message.getClass());
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        final StringWriter stringWriter = new StringWriter();
        marshaller.marshal(message, stringWriter);
        return stringWriter.toString();
    }

    public static <T> T fromXml(final InputStream inputStream, final Class<T> elementClass) throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(elementClass);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(new StreamSource(inputStream), elementClass).getValue();
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getVersion() {
        return this.version;
    }

    @Override
    public void close() throws IOException {
        this.httpClient.close();
    }

    public String getMac(
        final String method, final String pathAndQuery, final long timestamp, final int nonce) throws NoSuchAlgorithmException, InvalidKeyException {
        final Mac mac = Mac.getInstance("HmacSHA256");
        final SecretKeySpec secretHash = new SecretKeySpec(this.secret.getBytes(), "HmacSHA256");
        mac.init(secretHash);
        final String message = timestamp + "\n" +
            nonce + "\n" +
            method + "\n/" +
            this.version + pathAndQuery + "\n" +
            this.host + "\n" +
            this.port + "\n\n";
        return Base64.getEncoder().encodeToString(mac.doFinal(message.getBytes()));
    }

    public String getAuthHeader(final String mac, final long timestamp, final int nonce) {
        return "MAC id=\"" + this.key + "\", ts=\"" + timestamp + "\", nonce=\"" + nonce + "\", mac=\"" + mac + "\"";
    }

    public String getAuthHeader(final String method, final String pathAndQuery) throws NoSuchAlgorithmException, InvalidKeyException {
        final long timestamp = System.currentTimeMillis() / 1000L;
        final int nonce = this.random.nextInt();
        final String mac = getMac(method, pathAndQuery, timestamp, nonce);
        return getAuthHeader(mac, timestamp, nonce);
    }

    private <T> T get(final String path, final List<NameValuePair> query, final Class<T> responseType) throws Exception {
        final String pathAndQuery = query != null && !query.isEmpty() ? new URIBuilder(path).addParameters(query).toString() : path;
        final HttpGet httpRequest = new HttpGet(this.baseUrl + pathAndQuery);
        httpRequest.setHeader(HttpHeaders.ACCEPT, "application/xml");
        httpRequest.setHeader(HttpHeaders.AUTHORIZATION, getAuthHeader(httpRequest.getMethod(), pathAndQuery));
        try (final CloseableHttpResponse httpResponse = this.httpClient.execute(httpRequest)) {
            final StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                throw new RuntimeException(
                    statusLine.getStatusCode() + " " + statusLine.getReasonPhrase() + " " + EntityUtils.toString(httpResponse.getEntity()));
            }

            return fromXml(httpResponse.getEntity().getContent(), responseType);
        }
    }

    private <T> T get(final String path, final Class<T> responseType) throws Exception {
        return get(path, null, responseType);
    }

    public AutoTopup getAutoTopup() throws Exception {
        return get("/auto-topup", AutoTopup.class);
    }

    public ContactGroups getContactGroups(final Integer offset, final Integer limit) throws Exception {
        final List<NameValuePair> query = offset == null && limit == null ? null : new ArrayList<>(2);
        if (offset != null) {
            query.add(new BasicNameValuePair("offset", offset.toString()));
        }
        if (limit != null) {
            query.add(new BasicNameValuePair("limit", limit.toString()));
        }
        return get("/group", query, ContactGroups.class);
    }

    public DedicatedNumbers getDedicatedNumbers() throws Exception {
        return get("/dedicated-number", DedicatedNumbers.class);
    }

    public Optouts getOptOuts(final Integer offset, final Integer limit) throws Exception {
        final List<NameValuePair> query = offset == null && limit == null ? null : new ArrayList<>(2);
        if (offset != null) {
            query.add(new BasicNameValuePair("offset", offset.toString()));
        }
        if (limit != null) {
            query.add(new BasicNameValuePair("limit", limit.toString()));
        }
        return get("/opt-outs", query, Optouts.class);
    }

    public SharedPools getSharedPools() throws Exception {
        return get("/sharedpool", SharedPools.class);
    }

    public OutgoingMessages getOutgoingMessages(final Integer offset, final Integer limit) throws Exception {
        final List<NameValuePair> query = offset == null && limit == null ? null : new ArrayList<>(2);
        if (offset != null) {
            query.add(new BasicNameValuePair("offset", offset.toString()));
        }
        if (limit != null) {
            query.add(new BasicNameValuePair("limit", limit.toString()));
        }
        return get("/sms", query, OutgoingMessages.class);
    }

    public OutgoingMessages sendMessage(final Message message) throws Exception {
        final String messageXml = toXml(message);
        final String path = "/sms";
        final HttpPost httpRequest = new HttpPost(this.baseUrl + path);
        httpRequest.setHeader(HttpHeaders.ACCEPT, "application/xml");
        httpRequest.setHeader(HttpHeaders.AUTHORIZATION, getAuthHeader(httpRequest.getMethod(), path));
        final StringEntity entity = new StringEntity(messageXml);
        entity.setContentType("application/xml");
        httpRequest.setEntity(entity);
        try (final CloseableHttpResponse httpResponse = this.httpClient.execute(httpRequest)) {
            final StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                throw new RuntimeException(
                    statusLine.getStatusCode() + " " + statusLine.getReasonPhrase() + " " + EntityUtils.toString(httpResponse.getEntity()));
            }

            return fromXml(httpResponse.getEntity().getContent(), OutgoingMessages.class);
        }
    }

    public IncomingMessages getIncomingMessages(final Integer offset, final Integer limit) throws Exception {
        final List<NameValuePair> query = offset == null && limit == null ? null : new ArrayList<>(2);
        if (offset != null) {
            query.add(new BasicNameValuePair("offset", offset.toString()));
        }
        if (limit != null) {
            query.add(new BasicNameValuePair("limit", limit.toString()));
        }
        return get("/sms-incoming", query, IncomingMessages.class);
    }

    public Contact getUserBillingDetails() throws Exception {
        return get("/user/billing-details", Contact.class);
    }

    public Contact getUserContactDetails() throws Exception {
        return get("/user/contact-details", Contact.class);
    }

    public CreditBalance getUserCreditBalance() throws Exception {
        return get("/user/credit-balance", CreditBalance.class);
    }

    public LowBalanceAlerts getUserLowBalanceAlerts() throws Exception {
        return get("/user/low-balance-alerts", LowBalanceAlerts.class);
    }

    public VerifiedNumbers getUserVerifiedNumbers() throws Exception {
        return get("/user/verified-numbers", VerifiedNumbers.class);
    }
}
