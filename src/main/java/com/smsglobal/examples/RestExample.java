package com.smsglobal.examples;

import com.smsglobal.client.Client;
import com.smsglobal.client.Message;
import com.smsglobal.transport.RestTransport;

/**
 * Send message via the HTTP transport
 */
public class RestExample {

    public static void main(String [ ] args) throws Exception {
        Message message = new Message("SGTest", "614ccc", "Build url test 33");
        RestTransport restTransport = new RestTransport("aaa", "bbb", "https://api.smsglobal.com/v2", 443);
        restTransport.setPath("/sms/");
        System.out.println(restTransport.getBaseUrl() + restTransport.getPath());
        System.out.println(restTransport.toXml(message));
        Client client = new Client(restTransport);
        String response = client.sendMessage(message);
        System.out.println(response);
    }

}
