package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public abstract class AbstractMessages extends AbstractHasOffsetLimitTotal {

    @JsonProperty("messages")
    protected List<Message> messages;

    public List<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(final List<Message> messages) {
        this.messages = messages;
    }
}
