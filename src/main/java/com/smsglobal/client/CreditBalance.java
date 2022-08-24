package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CreditBalance {

    @JsonProperty("balance")
    protected BigDecimal balance;

    @JsonProperty("currency")
    protected String currency;

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "CreditBalance{" +
            "balance=" + this.balance +
            ", currency='" + this.currency + '\'' +
            '}';
    }
}
