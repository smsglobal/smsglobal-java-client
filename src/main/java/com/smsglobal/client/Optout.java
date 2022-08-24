package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Optout {

    @JsonProperty("date")
    protected LocalDate date;

    @JsonProperty("number")
    protected String number;

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Optout{" +
            "date=" + this.date +
            ", number='" + this.number + '\'' +
            '}';
    }
}
