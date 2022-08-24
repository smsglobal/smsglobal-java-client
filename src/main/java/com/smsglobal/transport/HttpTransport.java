package com.smsglobal.transport;

import com.smsglobal.client.Message;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * HTTP Transport
 * <a href="https://www.smsglobal.com/http-api/">https://www.smsglobal.com/http-api/</a>
 */
public class HttpTransport {

    public static final String PRODUCTION_URL = "https://www.smsglobal.com/http-api.php";

    private static final String SEND_SMS_ACTION = "sendsms";

    private final String username;
    private final String password;
    private final String url;
    private final URI uri;

    public HttpTransport(final String username, final String password, final String url) throws URISyntaxException {
        this.username = username;
        this.password = password;
        this.url = url;
        this.uri = new URI(url);
    }

    public HttpTransport(final String username, final String password) throws URISyntaxException {
        this(username, password, PRODUCTION_URL);
    }

    public String sendMessage(final Message message) throws IOException {
        final String url = buildUrl(message);
        final Content response = Request.get(url).execute().returnContent();
        return response.asString();
    }

    public String buildUrl(final Message message) {
        final URIBuilder builder = new URIBuilder();
        builder.setScheme(this.uri.getScheme());
        builder.setHost(this.uri.getHost());
        builder.setPath(this.uri.getPath());
        builder.addParameter("action", SEND_SMS_ACTION);
        builder.addParameter("user", this.username);
        builder.addParameter("password", this.password);
        builder.addParameter("from", message.getOrigin());
        builder.addParameter("to", message.getDestination());
        builder.addParameter("text", message.getMessage());
        // TODO: 16/08/2016 scheduled date and time
        return builder.toString();
    }

    public String getUrl() {
        return this.url;
    }

    public URI getUri() {
        return this.uri;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
