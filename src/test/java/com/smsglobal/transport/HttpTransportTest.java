package com.smsglobal.transport;

import com.smsglobal.client.Message;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

/**
 * Test HTTP Transport
 */
public class HttpTransportTest {

    @Test
    public void baseParameters() throws URISyntaxException {
        final HttpTransport httpTransport = new HttpTransport("jsiuwmkd", "kjas98sk", "https://www.smsglobal.com/http-api.php");
        assertEquals("jsiuwmkd", httpTransport.getUsername());
        assertEquals("kjas98sk", httpTransport.getPassword());
        assertEquals("https://www.smsglobal.com/http-api.php", httpTransport.getBaseUrl());
    }

    @Test(expected = URISyntaxException.class)
    public void badUrl() throws URISyntaxException {
        final HttpTransport httpTransport = new HttpTransport("jsiuwmkd", "kjas98sk", "https://www.smsglobal.com/^^http-api.php");
    }

    @Test
    public void buildUrl() throws Exception {
        final Message message = new Message("SGTest", "61400000000", "Build url test");
        final HttpTransport httpTransport = new HttpTransport("jsiuwmkd", "kjas98sk", "https://www.smsglobal.com/http-api.php");
        assertEquals(
            "https://www.smsglobal.com/http-api.php?action=sendsms&user=jsiuwmkd&password=kjas98sk&from=SGTest&to=61400000000&text=Build+url+test",
            httpTransport.buildUrl(message));
    }
}