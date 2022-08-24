package com.smsglobal.client;

public class HttpStatusCodeException extends Exception {

    protected final int statusCode;
    protected final String reasonPhrase;
    protected final String body;

    public HttpStatusCodeException(final int statusCode, final String reasonPhrase, final String body) {
        super(statusCode + " " + reasonPhrase + " " + body);

        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
        this.body = body;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public String getBody() {
        return this.body;
    }
}
