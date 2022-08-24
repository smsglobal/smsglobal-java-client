package com.smsglobal.client;

public class OutgoingMessages extends AbstractMessages {

    @Override
    public String toString() {
        return "OutgoingMessages{" +
            "offset=" + this.offset +
            ", limit=" + this.limit +
            ", total=" + this.total +
            ", messages=" + this.messages +
            '}';
    }
}
