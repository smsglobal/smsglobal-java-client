package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactGroup {

    @JsonProperty("id")
    protected String id;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("keyword")
    protected String keyword;

    @JsonProperty("isGlobal")
    protected Boolean global;

    @JsonProperty("contactCount")
    protected Integer contactCount;

    @JsonProperty("defaultOrigin")
    protected String defaultOrigin;

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(final String keyword) {
        this.keyword = keyword;
    }

    public Boolean getGlobal() {
        return this.global;
    }

    public void setGlobal(final Boolean global) {
        this.global = global;
    }

    public Integer getContactCount() {
        return this.contactCount;
    }

    public void setContactCount(final Integer contactCount) {
        this.contactCount = contactCount;
    }

    public String getDefaultOrigin() {
        return this.defaultOrigin;
    }

    public void setDefaultOrigin(final String defaultOrigin) {
        this.defaultOrigin = defaultOrigin;
    }

    @Override
    public String toString() {
        return "ContactGroup{" +
            "id=" + this.id +
            ", name='" + this.name + '\'' +
            ", keyword='" + this.keyword + '\'' +
            ", global=" + this.global +
            ", contactCount=" + this.contactCount +
            ", defaultOrigin='" + this.defaultOrigin + '\'' +
            '}';
    }
}
