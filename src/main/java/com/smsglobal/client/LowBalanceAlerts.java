package com.smsglobal.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "settings")
public class LowBalanceAlerts {

    @XmlElement(name = "enabled")
    protected Boolean enabled;

    @XmlElement(name = "threshold")
    protected BigDecimal threshold;

    @XmlElement(name = "sendto")
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
