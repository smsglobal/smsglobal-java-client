package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VerifiedNumbers {

    @JsonProperty("numbers")
    protected List<String> numbers;

    public List<String> getNumbers() {
        return this.numbers;
    }

    public void setNumbers(final List<String> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "VerifiedNumbers{" +
            "numbers=" + this.numbers +
            '}';
    }
}
