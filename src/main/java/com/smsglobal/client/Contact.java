package com.smsglobal.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "contact")
public class Contact {

    @XmlElement(name = "id")
    protected String id;

    @XmlElement(name = "name")
    protected String name;

    @XmlElement(name = "phone")
    protected String phone;

    @XmlElement(name = "email")
    protected String email;

    @XmlElement(name = "address")
    protected String address;

    @XmlElement(name = "city")
    protected String city;

    @XmlElement(name = "state")
    protected String state;

    @XmlElement(name = "postcode")
    protected String postcode;

    @XmlElement(name = "country")
    protected String country;

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(final String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Contact{" +
            "id=" + this.id +
            ", name='" + this.name + '\'' +
            ", phone='" + this.phone + '\'' +
            ", email='" + this.email + '\'' +
            ", address='" + this.address + '\'' +
            ", city='" + this.city + '\'' +
            ", state='" + this.state + '\'' +
            ", postcode='" + this.postcode + '\'' +
            ", country='" + this.country + '\'' +
            '}';
    }
}
