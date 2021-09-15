package com.smsglobal.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "auto-topup")
public class AutoTopup {

    @XmlElement(name = "disabled")
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
