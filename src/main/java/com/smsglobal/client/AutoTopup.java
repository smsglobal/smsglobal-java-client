package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AutoTopup {

    @JsonProperty("disabled")
    protected Boolean disabled;

    public Boolean getDisabled() {
        return this.disabled;
    }

    public void setDisabled(final Boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "AutoTopup{" +
            "disabled=" + this.disabled +
            '}';
    }
}
