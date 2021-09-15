package com.smsglobal.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum MessageStatus {

    @XmlEnumValue("delivered") delivered,
    @XmlEnumValue("sent") sent,
    @XmlEnumValue("scheduled") scheduled,
    @XmlEnumValue("noCredits") noCredits,
    @XmlEnumValue("invalidNumber") invalidNumber,
    @XmlEnumValue("undelivered") undelivered
}
