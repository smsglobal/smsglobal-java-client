package com.smsglobal.transport;

import com.smsglobal.client.Message;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.HttpHost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.protocol.HttpContext;

import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * REST transport test suite
 */
public class RestTransportTest {

    SSLContext sslcontext = SSLContexts.createSystemDefault();
    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext) {
        @Override
        public Socket connectSocket(
                int connectTimeout,
                Socket socket,
                HttpHost host,
                InetSocketAddress remoteAddress,
                InetSocketAddress localAddress,
                HttpContext context) throws IOException, ConnectTimeoutException {
            if (socket instanceof SSLSocket) {
                try {
                    ((SSLSocket) socket).setEnabledProtocols(new String[] {"TLSv1.2"});
                    PropertyUtils.setProperty(socket, "host", host.getHostName());
                } catch (NoSuchMethodException ex) {
                } catch (IllegalAccessException ex) {
                } catch (InvocationTargetException ex) {
                }
            }
            return super.connectSocket(connectTimeout, socket, host, remoteAddress,
                    localAddress, context);
        }
    };

    @Test
    public void baseParameters() throws URISyntaxException, IOException {
        RestTransport restTransport = new RestTransport("aaaa", "bbbb", "https://api.smsglobal.com/v2", 443,sslsf);
        assertEquals("base url should be https://api.smsglobal.com/v2", "https://api.smsglobal.com/v2", restTransport.getBaseUrl());
        assertEquals("base scheme should be https", "https", restTransport.getUri().getScheme());
        assertEquals("base host should be api.smsglobal.com", "api.smsglobal.com", restTransport.getUri().getHost());
        assertEquals("base version should be v2", "v2", restTransport.getVersion());
        restTransport.setVersion("v1.1");
        assertEquals("base version should be v1.1", "v1.1", restTransport.getVersion());
    }

    @Test
    public void version() throws URISyntaxException, IOException {
        RestTransport restTransport = new RestTransport("aaaa", "bbbb", "https://api.smsglobal.com/v2", 443,sslsf);
        assertEquals("base version should be v2", "v2", restTransport.getVersion());
        restTransport.setVersion("v1.1");
        assertEquals("base version should be v1.1", "v1.1", restTransport.getVersion());
        restTransport.setBaseUrl("https://api.smsglobal.com/v2/some/other.stuff");
        assertEquals("base version should be v2", "v2", restTransport.getVersion());
    }

    @Test
    public void getMac() throws Exception {
        RestTransport restTransport = new RestTransport("945a7aca9f05a78a194b77f76c6bb653", "a8dfa0dc19c7439914c74c6c92a95d25", "https://api.smsglobal.com/v2", 443,sslsf);
        String mac = restTransport.getMac("GET", "/sms/", 1471398353, 1174249);
        assertEquals("iX34qsNi2hqcoeNVtA/W5D+Hj6eMO3mIThfcYNJPaPM=", mac);
    }

    @Test
    public void getAuthHeader() throws Exception {
        RestTransport restTransport = new RestTransport("945a7aca9f05a78a194b77f76c6bb653", "a8dfa0dc19c7439914c74c6c92a95d25", "https://api.smsglobal.com/v2", 443,sslsf);
        String mac = restTransport.getMac("GET", "/sms/", 1471398353, 1174249);
        String authHeader = restTransport.getAuthHeader(mac, 1471398353, 1174249);
        assertEquals("MAC id=\"945a7aca9f05a78a194b77f76c6bb653\", ts=\"1471398353\", nonce=\"1174249\", mac=\"iX34qsNi2hqcoeNVtA/W5D+Hj6eMO3mIThfcYNJPaPM=\"", authHeader);
    }

    @Test
    public void toXml() throws Exception {
        RestTransport transport = new RestTransport();
        Message message = new Message("1111", "2222", "Test message");
        String actual = transport.toXml(message);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<message>\n" +
                "    <destination>2222</destination>\n" +
                "    <message>Test message</message>\n" +
                "    <origin>1111</origin>\n" +
                "</message>\n";
        assertEquals(expected, actual);
    }

    @Test
    public void toJson() throws Exception {
        RestTransport transport = new RestTransport();
        Message message = new Message("1111", "2222", "Test message");
        String actual = transport.toJson(message);
        String expected = "{\"origin\":\"1111\",\"destination\":\"2222\",\"message\":\"Test message\"}";
        assertEquals(expected, actual);
    }

}