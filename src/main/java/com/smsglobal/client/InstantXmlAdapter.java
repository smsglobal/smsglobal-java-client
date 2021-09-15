package com.smsglobal.client;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class InstantXmlAdapter extends XmlAdapter<String, Instant> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss Z");

    @Override
    public Instant unmarshal(final String value) {
        return DATE_TIME_FORMATTER.parse(value, Instant::from);
    }

    @Override
    public String marshal(final Instant value) {
        return value.toString();
    }
}