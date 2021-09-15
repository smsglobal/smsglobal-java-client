package com.smsglobal.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "outgoing")
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
