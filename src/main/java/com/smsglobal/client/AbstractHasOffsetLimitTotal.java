package com.smsglobal.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractHasOffsetLimitTotal {

    @JsonProperty("offset")
    protected Integer offset;

    @JsonProperty("limit")
    protected Integer limit;

    @JsonProperty("total")
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
