package com.smsglobal.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "verifiedNumbers")
public class VerifiedNumbers {

    @XmlElementWrapper(name = "numbers")
    @XmlElement(name = "entry")
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
