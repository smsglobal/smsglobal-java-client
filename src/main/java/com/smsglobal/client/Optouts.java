package com.smsglobal.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "optouts")
public class Optouts extends AbstractHasOffsetLimitTotal {

    @XmlElement(name = "optout")
    protected List<Optout> optouts;

    public List<Optout> getOptouts() {
        return this.optouts;
    }

    public void setOptouts(final List<Optout> optouts) {
        this.optouts = optouts;
    }

    @Override
    public String toString() {
        return "Optouts{" +
            "offset=" + this.offset +
            ", limit=" + this.limit +
            ", total=" + this.total +
            ", optouts=" + this.optouts +
            '}';
    }
}
