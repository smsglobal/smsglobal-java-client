package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MessageStatus {

    @JsonProperty("delivered") DELIVERED,
    @JsonProperty("sent") SENT,
    @JsonProperty("scheduled") SCHEDULED,
    @JsonProperty("noCredits") NO_CREDITS,
    @JsonProperty("invalidNumber") INVALID_NUMBER,
    @JsonProperty("undelivered") UNDELIVERED,
    @JsonProperty("rejected") REJECTED,
    @JsonProperty("Error 1002") ERROR_1002
}