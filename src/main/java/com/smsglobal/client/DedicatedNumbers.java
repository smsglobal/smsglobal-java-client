package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DedicatedNumbers {

    @JsonProperty("dedicatedNumbers")
    protected List<String> dedicatedNumbers;

    public List<String> getDedicatedNumbers() {
        return this.dedicatedNumbers;
    }

    public void setDedicatedNumbers(final List<String> dedicatedNumbers) {
        this.dedicatedNumbers = dedicatedNumbers;
    }

    @Override
    public String toString() {
        return "DedicatedNumbers{" +
            "dedicatedNumbers=" + this.dedicatedNumbers +
            '}';
    }
}
