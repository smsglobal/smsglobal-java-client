package com.smsglobal.examples;

import com.smsglobal.client.Message;
import com.smsglobal.transport.RestTransport;

/**
 * Send message via the HTTP transport
 */
public class RestExample {

    public static void main(final String[] args) throws Exception {
        final Message message = new Message("SGTest", "61426203571", "Build url test 33");
        final RestTransport restTransport = new RestTransport(
            "5d8b1fd934a10e45d8b0476e5e9776da", "3a826f541af41127353d7f87ec73d36b", "https://api.smsglobal.com/v2");
        System.out.println(restTransport.getBaseUrl());
        System.out.println(message);

        System.out.println("sendMessage");
        System.out.println(restTransport.sendMessage(message));

        System.out.println("getOutgoingMessages");
        System.out.println(restTransport.getOutgoingMessages(null, 1000));

        System.out.println("getIncomingMessages");
        System.out.println(restTransport.getIncomingMessages(null, null));

        System.out.println("getUserCreditBalance");
        System.out.println(restTransport.getUserCreditBalance());
    }
}