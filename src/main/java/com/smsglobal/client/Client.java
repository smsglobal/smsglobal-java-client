package com.smsglobal.client;

/**
 * SMSGlobal Client
 */
public class Client {

    private Transport transport;

    public Client(Transport transport) {
        this.transport = transport;
    }

    public String sendMessage(Message message) throws Exception {
        return transport.sendMessage(message);
    }
}
