package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SharedPools {

    @JsonProperty("SharedPools")
    protected List<String> sharedPools;

    public List<String> getSharedPools() {
        return this.sharedPools;
    }

    public void setSharedPools(final List<String> sharedPools) {
        this.sharedPools = sharedPools;
    }

    @Override
    public String toString() {
        return "SharedPools{" +
            "sharedPools=" + this.sharedPools +
            '}';
    }
}
