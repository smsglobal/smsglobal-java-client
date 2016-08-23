package com.smsglobal.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * SMSGlobal Message
 */
@XmlRootElement
public class Message {

    private String origin;
    private String destination;
    private String message;
    private Integer maxSplit;
    private Date scheduled;

    public Message() {
    }

    public Message(String origin, String destination, String message) {
        this.origin = origin;
        this.destination = destination;
        this.message = message;
    }

    public String getOrigin() {
        return origin;
    }

    @XmlElement
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    @XmlElement
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMessage() {
        return message;
    }

    @XmlElement
    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getMaxSplit() {
        return maxSplit;
    }

    public void setMaxSplit(Integer maxSplit) {
        this.maxSplit = maxSplit;
    }

    public Date getScheduled() {
        return scheduled;
    }

    public void setScheduled(Date scheduled) {
        this.scheduled = scheduled;
    }
}
