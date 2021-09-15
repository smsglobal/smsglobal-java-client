package com.smsglobal.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractHasOffsetLimitTotal {

    @XmlElement(name = "offset")
    protected Integer offset;

    @XmlElement(name = "limit")
    protected Integer limit;

    @XmlElement(name = "total")
    protected Integer total;

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(final Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(final Integer total) {
        this.total = total;
    }
}
