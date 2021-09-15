package com.smsglobal.client;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(final String value) {
        return LocalDate.parse(value);
    }

    @Override
    public String marshal(final LocalDate value) {
        return value.toString();
    }
}