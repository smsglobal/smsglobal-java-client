package com.smsglobal.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractMessages extends AbstractHasOffsetLimitTotal {

    @XmlElement(name = "message")
    protected List<Message> messages;

    public List<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(final List<Message> messages) {
        this.messages = messages;
    }
}
