package com.smsglobal.transport;

import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

/**
 * REST transport test suite
 */
public class RestTransportTest {

    @Test
    public void baseParameters() throws URISyntaxException {
        final RestTransport restTransport = new RestTransport("aaaa", "bbbb", "https://api.smsglobal.com/v2");
        assertEquals("base url should be https://api.smsglobal.com/v2", "https://api.smsglobal.com/v2", restTransport.getBaseUrl());
        assertEquals("base host should be api.smsglobal.com", "api.smsglobal.com", restTransport.getHost());
        assertEquals("base port should be 443", 443, restTransport.getPort());
        assertEquals("base version should be v2", "v2", restTransport.getVersion());
    }

    @Test
    public void version() throws URISyntaxException {
        final RestTransport restTransport = new RestTransport("aaaa", "bbbb", "https://api.smsglobal.com/v2");
        assertEquals("base version should be v2", "v2", restTransport.getVersion());
    }

    @Test
    public void getMac() throws Exception {
        final RestTransport restTransport = new RestTransport(
            "945a7aca9f05a78a194b77f76c6bb653", "a8dfa0dc19c7439914c74c6c92a95d25", "https://api.smsglobal.com/v2");
        final String mac = restTransport.getMac("GET", "/sms/", 1471398353, 1174249);
        assertEquals("iX34qsNi2hqcoeNVtA/W5D+Hj6eMO3mIThfcYNJPaPM=", mac);
    }

    @Test
    public void getAuthHeader() throws Exception {
        final RestTransport restTransport = new RestTransport(
            "945a7aca9f05a78a194b77f76c6bb653", "a8dfa0dc19c7439914c74c6c92a95d25", "https://api.smsglobal.com/v2");
        final String mac = restTransport.getMac("GET", "/sms/", 1471398353, 1174249);
        final String authHeader = restTransport.getAuthHeader(mac, 1471398353, 1174249);
        assertEquals(
            "MAC id=\"945a7aca9f05a78a194b77f76c6bb653\", ts=\"1471398353\", nonce=\"1174249\", mac=\"iX34qsNi2hqcoeNVtA/W5D+Hj6eMO3mIThfcYNJPaPM=\"",
            authHeader);
    }
}