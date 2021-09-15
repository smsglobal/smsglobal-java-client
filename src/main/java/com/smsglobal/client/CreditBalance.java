package com.smsglobal.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
public class CreditBalance {

    @XmlElement(name = "balance")
    protected BigDecimal balance;

    @XmlElement(name = "currency")
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
