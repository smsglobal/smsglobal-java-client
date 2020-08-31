package com.smsglobal.examples;

import com.smsglobal.client.Client;
import com.smsglobal.client.Message;
import com.smsglobal.transport.RestTransport;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.HttpHost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Send message via the HTTP transport
 */
public class RestExample {

    public static void main(String [ ] args) throws Exception {
        Message message = new Message("SGTest", "61426203571", "Build url test 33");

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


        RestTransport restTransport = new RestTransport("5d8b1fd934a10e45d8b0476e5e9776da", "3a826f541af41127353d7f87ec73d36b", "https://api.smsglobal.com/v2", 443,sslsf);
        restTransport.setPath("/sms/");
        System.out.println(restTransport.getBaseUrl() + restTransport.getPath());
        System.out.println(restTransport.toXml(message));
        Client client = new Client(restTransport);
        String response = client.sendMessage(message);
        System.out.println(response);
    }

}
