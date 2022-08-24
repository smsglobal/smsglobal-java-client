package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Optouts extends AbstractHasOffsetLimitTotal {

    @JsonProperty("optouts")
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
