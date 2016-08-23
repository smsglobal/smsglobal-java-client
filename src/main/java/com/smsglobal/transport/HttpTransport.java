package com.smsglobal.transport;

import com.smsglobal.client.Message;
import com.smsglobal.client.Transport;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * HTTP Transport
 */
public class HttpTransport implements Transport {

    private String action = "sendsms";
    private String username;
    private String password;
    private String baseUrl;
    private URI uri;

    public HttpTransport(String username, String password, String baseUrl) throws URISyntaxException {
        this.username = username;
        this.password = password;
        this.baseUrl = baseUrl;
        this.uri = new URI(baseUrl);
    }


    public String sendMessage(Message message) throws Exception {
        String url = buildUrl(message);
        Content response = Request.Get(url).execute().returnContent();
        return response.asString();
    }

    public String buildUrl(Message message) {
        URIBuilder builder = new URIBuilder();
        builder.setScheme(uri.getScheme());
        builder.setHost(uri.getHost());
        builder.setPath(uri.getPath());
        builder.addParameter("action", action);
        builder.addParameter("user", username);
        builder.addParameter("password", password);
        builder.addParameter("from", message.getOrigin());
        builder.addParameter("to", message.getDestination());
        builder.addParameter("text", message.getMessage());
        // TODO: 16/08/2016 scheduled date and time
        return builder.toString();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
