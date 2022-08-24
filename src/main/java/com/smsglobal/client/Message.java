package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
 * SMSGlobal Message
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    @JsonProperty("origin")
    protected String origin;

    @JsonProperty("destination")
    protected String destination;

    @JsonProperty("message")
    protected String message;

    @JsonProperty("notifyUrl")
    protected String notifyUrl;

    @JsonProperty("incomingUrl")
    protected String incomingUrl;

    @JsonProperty("id")
    protected String id;

    @JsonProperty("outgoing_id")
    protected String outgoingId;

    @JsonProperty("isUnicode")
    protected Boolean unicode;

    @JsonProperty("status")
    protected MessageStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd HH:mm:ss Z")
    @JsonProperty("dateTime")
    protected Instant dateTime;

    @JsonProperty("isMultipart")
    protected Boolean multipart;

    @JsonProperty("partNumber")
    protected Integer partNumber;

    @JsonProperty("totalParts")
    protected Integer totalParts;

    public Message() {
    }

    public Message(final String origin, final String destination, final String message) {
        this.origin = origin;
        this.destination = destination;
        this.message = message;
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

    @Override
    public String toString() {
        return "Message{" +
            "origin='" + this.origin + '\'' +
            ", destination='" + this.destination + '\'' +
            ", message='" + this.message + '\'' +
            ", notifyUrl='" + this.notifyUrl + '\'' +
            ", incomingUrl='" + this.incomingUrl + '\'' +
            ", id='" + this.id + '\'' +
            ", outgoingId='" + this.outgoingId + '\'' +
            ", unicode=" + this.unicode +
            ", status=" + this.status +
            ", dateTime=" + this.dateTime +
            ", multipart=" + this.multipart +
            ", partNumber=" + this.partNumber +
            ", totalParts=" + this.totalParts +
            '}';
    }
}
