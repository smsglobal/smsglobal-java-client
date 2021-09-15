package com.smsglobal.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "optout")
public class Optout {

    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    @XmlElement(name = "date")
    protected LocalDate date;

    @XmlElement(name = "number")
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
