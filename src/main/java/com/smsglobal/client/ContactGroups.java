package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ContactGroups extends AbstractHasOffsetLimitTotal {

    @JsonProperty("group")
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
