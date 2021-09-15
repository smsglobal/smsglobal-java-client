package com.smsglobal.transport;

import com.smsglobal.client.Message;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * HTTP Transport
 */
public class HttpTransport {

    private final String action = "sendsms";
    private String username;
    private String password;
    private String baseUrl;
    private URI uri;

    public HttpTransport(final String username, final String password, final String baseUrl) throws URISyntaxException {
        this.username = username;
        this.password = password;
        this.baseUrl = baseUrl;
        this.uri = new URI(baseUrl);
    }


    public String sendMessage(final Message message) throws Exception {
        final String url = buildUrl(message);
        final Content response = Request.Get(url).execute().returnContent();
        return response.asString();
    }

    public String buildUrl(final Message message) {
        final URIBuilder builder = new URIBuilder();
        builder.setScheme(this.uri.getScheme());
        builder.setHost(this.uri.getHost());
        builder.setPath(this.uri.getPath());
        builder.addParameter("action", this.action);
        builder.addParameter("user", this.username);
        builder.addParameter("password", this.password);
        builder.addParameter("from", message.getOrigin());
        builder.addParameter("to", message.getDestination());
        builder.addParameter("text", message.getMessage());
        // TODO: 16/08/2016 scheduled date and time
        return builder.toString();
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public void setBaseUrl(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public URI getUri() {
        return this.uri;
    }

    public void setUri(final URI uri) {
        this.uri = uri;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
