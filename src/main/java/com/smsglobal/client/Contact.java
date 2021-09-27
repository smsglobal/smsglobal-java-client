package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {

    @JsonProperty("id")
    protected String id;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("phone")
    protected String phone;

    @JsonProperty("email")
    protected String email;

    @JsonProperty("address")
    protected String address;

    @JsonProperty("city")
    protected String city;

    @JsonProperty("state")
    protected String state;

    @JsonProperty("postcode")
    protected String postcode;

    @JsonProperty("country")
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
