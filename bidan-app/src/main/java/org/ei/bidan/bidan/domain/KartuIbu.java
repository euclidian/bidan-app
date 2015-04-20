package org.ei.bidan.bidan.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

/**
 * Created by Dimas Ciputra on 2/16/15.
 */
public class KartuIbu {
    private String caseId;
    private Map<String, String> details;
    private boolean isClosed;

    public KartuIbu(String caseId, Map<String, String> details) {
        this.caseId = caseId;
        this.details = details;
        this.isClosed = false;
    }

    // Getter
    public String getCaseId() {
        return caseId;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public boolean isClosed() {
        return isClosed;
    }

    // Setter
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    // Tools
    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}