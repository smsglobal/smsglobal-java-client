package com.smsglobal.client;

/**
 * SMSGlobal Transport
 */
public interface Transport {

    String sendMessage(Message message) throws Exception;

}
