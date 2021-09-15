package com.smsglobal.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.Instant;

/**
 * SMSGlobal Message
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "message")
public class Message {

    @XmlElement(name = "id")
    protected String id;

    @XmlElement(name = "outgoing_id")
    protected String outgoingId;

    @XmlElement(name = "origin")
    protected String origin;

    @XmlElement(name = "destination")
    protected String destination;

    @XmlElement(name = "message")
    protected String message;

    @XmlElement(name = "isUnicode")
    protected Boolean unicode;

    @XmlElement(name = "status")
    protected MessageStatus status;

    @XmlJavaTypeAdapter(InstantXmlAdapter.class)
    @XmlElement(name = "dateTime")
    protected Instant dateTime;

    @XmlElement(name = "isMultipart")
    protected Boolean multipart;

    @XmlElement(name = "partNumber")
    protected Integer partNumber;

    @XmlElement(name = "totalParts")
    protected Integer totalParts;

    @XmlElement(name = "notifyUrl")
    protected String notifyUrl;

    @XmlElement(name = "incomingUrl")
    protected String incomingUrl;

    public Message() {
    }

    public Message(final String origin, final String destination, final String message) {
        this.origin = origin;
        this.destination = destination;
        this.message = message;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getOutgoingId() {
        return this.outgoingId;
    }

    public void setOutgoingId(final String outgoingId) {
        this.outgoingId = outgoingId;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Boolean getUnicode() {
        return this.unicode;
    }

    public void setUnicode(final Boolean unicode) {
        this.unicode = unicode;
    }

    public MessageStatus getStatus() {
        return this.status;
    }

    public void setStatus(final MessageStatus status) {
        this.status = status;
    }

    public Instant getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(final Instant dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getMultipart() {
        return this.multipart;
    }

    public void setMultipart(final Boolean multipart) {
        this.multipart = multipart;
    }

    public Integer getPartNumber() {
        return this.partNumber;
    }

    public void setPartNumber(final Integer partNumber) {
        this.partNumber = partNumber;
    }

    public Integer getTotalParts() {
        return this.totalParts;
    }

    public void setTotalParts(final Integer totalParts) {
        this.totalParts = totalParts;
    }

    public String getNotifyUrl() {
        return this.notifyUrl;
    }

    public void setNotifyUrl(final String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getIncomingUrl() {
        return this.incomingUrl;
    }

    public void setIncomingUrl(final String incomingUrl) {
        this.incomingUrl = incomingUrl;
    }

    @Override
    public String toString() {
        return "Message{" +
            "id=" + this.id +
            ", outgoingId=" + this.outgoingId +
            ", origin='" + this.origin + '\'' +
            ", destination='" + this.destination + '\'' +
            ", message='" + this.message + '\'' +
            ", unicode=" + this.unicode +
            ", status=" + this.status +
            ", dateTime=" + this.dateTime +
            ", multipart=" + this.multipart +
            ", partNumber=" + this.partNumber +
            ", totalParts=" + this.totalParts +
            ", notifyUrl='" + this.notifyUrl + '\'' +
            ", incomingUrl='" + this.incomingUrl + '\'' +
            '}';
    }
}
