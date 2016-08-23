package com.smsglobal.examples;

import com.smsglobal.client.Client;
import com.smsglobal.client.Message;
import com.smsglobal.transport.HttpTransport;

/**
 * Send message via the HTTP transport
 */
public class HttpExample {

    public static void main(String [ ] args) throws Exception {
        Message message = new Message("SGTest", "614xx", "Build url test");
        HttpTransport httpTransport = new HttpTransport("xx", "xx", "https://www.smsglobal.com/http-api.php");
        System.out.println(httpTransport.buildUrl(message));
        Client client = new Client(httpTransport);
        String response = client.sendMessage(message);
        System.out.println(response);
    }

}
