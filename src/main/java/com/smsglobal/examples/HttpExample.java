package com.smsglobal.examples;

import com.smsglobal.client.Message;
import com.smsglobal.transport.HttpTransport;

/**
 * Send message via the HTTP transport
 */
public class HttpExample {

    public static void main(final String[] args) throws Exception {
        final Message message = new Message("SGTest", "614xx", "Build url test");
        final HttpTransport httpTransport = new HttpTransport("xx", "xx", "https://www.smsglobal.com/http-api.php");
        System.out.println(httpTransport.buildUrl(message));
        final String response = httpTransport.sendMessage(message);
        System.out.println(response);
    }
}
