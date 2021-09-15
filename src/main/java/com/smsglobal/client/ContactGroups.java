package com.smsglobal.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "result")
public class ContactGroups extends AbstractHasOffsetLimitTotal {

    @XmlElementWrapper(name = "groups")
    @XmlElement(name = "group")
    protected List<ContactGroup> contactGroups;

    public List<ContactGroup> getGroups() {
        return this.contactGroups;
    }

    public void setGroups(final List<ContactGroup> contactGroups) {
        this.contactGroups = contactGroups;
    }

    @Override
    public String toString() {
        return "ContactGroups{" +
            "offset=" + this.offset +
            ", limit=" + this.limit +
            ", total=" + this.total +
            ", contactGroups=" + this.contactGroups +
            '}';
    }
}
