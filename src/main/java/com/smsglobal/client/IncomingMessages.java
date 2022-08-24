package com.smsglobal.client;

public class IncomingMessages extends AbstractMessages {

    @Override
    public String toString() {
        return "IncomingMessages{" +
            "offset=" + this.offset +
            ", limit=" + this.limit +
            ", total=" + this.total +
            ", messages=" + this.messages +
            '}';
    }
}
