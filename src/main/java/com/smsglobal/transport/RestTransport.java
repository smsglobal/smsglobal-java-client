package com.smsglobal.transport;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.smsglobal.client.AutoTopup;
import com.smsglobal.client.Contact;
import com.smsglobal.client.ContactGroups;
import com.smsglobal.client.CreditBalance;
import com.smsglobal.client.DedicatedNumbers;
import com.smsglobal.client.HttpStatusCodeException;
import com.smsglobal.client.IncomingMessages;
import com.smsglobal.client.LowBalanceAlerts;
import com.smsglobal.client.Message;
import com.smsglobal.client.Optouts;
import com.smsglobal.client.OutgoingMessages;
import com.smsglobal.client.SharedPools;
import com.smsglobal.client.VerifiedNumbers;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.Closeable;
import java.io.IOException;
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
 * <a href="https://www.smsglobal.com/rest-api/">https://www.smsglobal.com/rest-api/</a>
 */
public class RestTransport implements Closeable {

    public static final String PRODUCTION_BASE_URL = "https://api.smsglobal.com/v2";

    public static final int DEFAULT_TIMEOUT_MS = 60 * 1000;

    public static CloseableHttpClient createHttpClient(final int timeoutMs) {
        final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(timeoutMs)
            .setConnectTimeout(timeoutMs)
            .setSocketTimeout(timeoutMs)
            .build();
        return HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
    }

    public static ObjectMapper createObjectMapper() {
        return new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
    }

    private final String key;
    private final String secret;
    private final String baseUrl;
    private final String host;
    private final int port;
    private final String version;
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final Random random = new Random();

    public RestTransport(
        final String key, final String secret, final String baseUrl, final CloseableHttpClient httpClient,
        final ObjectMapper objectMapper) throws URISyntaxException {

        this.key = key;
        this.secret = secret;
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;

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

    public RestTransport(
        final String key, final String secret, final CloseableHttpClient httpClient, final ObjectMapper objectMapper) throws URISyntaxException {

        this(key, secret, PRODUCTION_BASE_URL, httpClient, objectMapper);
    }

    public RestTransport(final String key, final String secret, final String baseUrl, final CloseableHttpClient httpClient) throws URISyntaxException {
        this(key, secret, baseUrl, httpClient, createObjectMapper());
    }

    public RestTransport(final String key, final String secret, final CloseableHttpClient httpClient) throws URISyntaxException {
        this(key, secret, PRODUCTION_BASE_URL, httpClient, createObjectMapper());
    }

    public RestTransport(final String key, final String secret, final String baseUrl, final int timeoutMs) throws URISyntaxException {
        this(key, secret, baseUrl, createHttpClient(timeoutMs));
    }

    public RestTransport(final String key, final String secret, final int timeoutMs) throws URISyntaxException {
        this(key, secret, PRODUCTION_BASE_URL, createHttpClient(timeoutMs));
    }

    public RestTransport(final String key, final String secret, final String baseUrl) throws URISyntaxException {
        this(key, secret, baseUrl, DEFAULT_TIMEOUT_MS);
    }

    public RestTransport(final String key, final String secret) throws URISyntaxException {
        this(key, secret, PRODUCTION_BASE_URL);
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

    private static void checkResponse(final CloseableHttpResponse httpResponse) throws HttpStatusCodeException {
        final StatusLine statusLine = httpResponse.getStatusLine();
        final int statusCode = statusLine.getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            return;
        }

        final String reasonPhrase = statusLine.getReasonPhrase();
        String body = null;
        try {
            body = EntityUtils.toString(httpResponse.getEntity());
        } catch (final IOException ignored) {
        }

        throw new HttpStatusCodeException(statusCode, reasonPhrase, body);
    }

    private <T> T get(
        final String path, final List<NameValuePair> query,
        final Class<T> responseType) throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {

        final String pathAndQuery = query != null && !query.isEmpty() ? new URIBuilder(path).addParameters(query).toString() : path;
        final HttpGet httpRequest = new HttpGet(new URI(this.baseUrl + pathAndQuery));
        httpRequest.setHeader(HttpHeaders.ACCEPT, "application/json");
        httpRequest.setHeader(HttpHeaders.AUTHORIZATION, getAuthHeader(httpRequest.getMethod(), pathAndQuery));
        try (final CloseableHttpResponse httpResponse = this.httpClient.execute(httpRequest)) {
            checkResponse(httpResponse);

            return this.objectMapper.readValue(httpResponse.getEntity().getContent(), responseType);
        }
    }

    private <T> T get(
        final String path,
        final Class<T> responseType) throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {

        return get(path, null, responseType);
    }

    private <T, U> T post(
        final String path, final List<NameValuePair> query, final U body,
        final Class<T> responseType) throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {

        final String bodyJson = this.objectMapper.writeValueAsString(body);
        final String pathAndQuery = query != null && !query.isEmpty() ? new URIBuilder(path).addParameters(query).toString() : path;
        final HttpPost httpRequest = new HttpPost(new URI(this.baseUrl + pathAndQuery));
        httpRequest.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        httpRequest.setHeader(HttpHeaders.AUTHORIZATION, getAuthHeader(httpRequest.getMethod(), pathAndQuery));
        final StringEntity entity = new StringEntity(bodyJson, ContentType.APPLICATION_JSON);
        httpRequest.setEntity(entity);
        try (final CloseableHttpResponse httpResponse = this.httpClient.execute(httpRequest)) {
            checkResponse(httpResponse);

            return this.objectMapper.readValue(httpResponse.getEntity().getContent(), responseType);
        }
    }

    private <T, U> T post(
        final String path, final U body,
        final Class<T> responseType) throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {

        return post(path, null, body, responseType);
    }

    public AutoTopup getAutoTopup() throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        return get("/auto-topup", AutoTopup.class);
    }

    public ContactGroups getContactGroups(
        final Integer offset,
        final Integer limit) throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {

        final List<NameValuePair> query = offset == null && limit == null ? null : new ArrayList<>(2);
        if (offset != null) {
            query.add(new BasicNameValuePair("offset", offset.toString()));
        }
        if (limit != null) {
            query.add(new BasicNameValuePair("limit", limit.toString()));
        }
        return get("/group", query, ContactGroups.class);
    }

    public DedicatedNumbers getDedicatedNumbers() throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        return get("/dedicated-number", DedicatedNumbers.class);
    }

    public Optouts getOptOuts(
        final Integer offset,
        final Integer limit) throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {

        final List<NameValuePair> query = offset == null && limit == null ? null : new ArrayList<>(2);
        if (offset != null) {
            query.add(new BasicNameValuePair("offset", offset.toString()));
        }
        if (limit != null) {
            query.add(new BasicNameValuePair("limit", limit.toString()));
        }
        return get("/opt-outs", query, Optouts.class);
    }

    public SharedPools getSharedPools() throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        return get("/sharedpool", SharedPools.class);
    }

    public OutgoingMessages getOutgoingMessages(
        final Integer offset,
        final Integer limit) throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {

        final List<NameValuePair> query = offset == null && limit == null ? null : new ArrayList<>(2);
        if (offset != null) {
            query.add(new BasicNameValuePair("offset", offset.toString()));
        }
        if (limit != null) {
            query.add(new BasicNameValuePair("limit", limit.toString()));
        }
        return get("/sms", query, OutgoingMessages.class);
    }

    public OutgoingMessages sendMessage(
        final Message message) throws IOException, NoSuchAlgorithmException, InvalidKeyException, HttpStatusCodeException, URISyntaxException {

        return post("/sms", message, OutgoingMessages.class);
    }

    public IncomingMessages getIncomingMessages(
        final Integer offset,
        final Integer limit) throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {

        final List<NameValuePair> query = offset == null && limit == null ? null : new ArrayList<>(2);
        if (offset != null) {
            query.add(new BasicNameValuePair("offset", offset.toString()));
        }
        if (limit != null) {
            query.add(new BasicNameValuePair("limit", limit.toString()));
        }
        return get("/sms-incoming", query, IncomingMessages.class);
    }

    public Contact getUserBillingDetails() throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        return get("/user/billing-details", Contact.class);
    }

    public Contact getUserContactDetails() throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        return get("/user/contact-details", Contact.class);
    }

    public CreditBalance getUserCreditBalance() throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        return get("/user/credit-balance", CreditBalance.class);
    }

    public LowBalanceAlerts getUserLowBalanceAlerts() throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        return get("/user/low-balance-alerts", LowBalanceAlerts.class);
    }

    public VerifiedNumbers getUserVerifiedNumbers() throws HttpStatusCodeException, IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        return get("/user/verified-numbers", VerifiedNumbers.class);
    }
}
