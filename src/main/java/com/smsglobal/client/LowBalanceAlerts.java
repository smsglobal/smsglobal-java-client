package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class LowBalanceAlerts {

    @JsonProperty("enabled")
    protected Boolean enabled;

    @JsonProperty("threshold")
    protected BigDecimal threshold;

    @JsonProperty("sendto")
    protected String sendto;

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public BigDecimal getThreshold() {
        return this.threshold;
    }

    public void setThreshold(final BigDecimal threshold) {
        this.threshold = threshold;
    }

    public String getSendto() {
        return this.sendto;
    }

    public void setSendto(final String sendto) {
        this.sendto = sendto;
    }

    @Override
    public String toString() {
        return "LowBalanceAlerts{" +
            "enabled=" + this.enabled +
            ", threshold=" + this.threshold +
            ", sendto='" + this.sendto + '\'' +
            '}';
    }
}
