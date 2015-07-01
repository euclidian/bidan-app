package org.ei.bidan.bidan.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.ei.bidan.AllConstants;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.ei.bidan.AllConstants.ECRegistrationFields.CURRENT_FP_METHOD;

/**
 * Created by Dimas Ciputra on 2/16/15.
 */
public class KartuIbu {
    private String caseId;
    private Map<String, String> details;
    private String dusun;
    private boolean isClosed;

    public KartuIbu(String caseId, Map<String, String> details, String dusun) {
        this.caseId = caseId;
        this.details = details;
        this.isClosed = false;
        this.dusun = dusun;
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

    public boolean hasKBMethod() {
        String kbMethod = getDetail(AllConstants.KeluargaBerencanaFields.CONTRACEPTION_METHOD);
        return isNotBlank(kbMethod);
    }

    public String getDetail(String name) {
        return details.get(name);
    }

    public String dusun() { return this.dusun; }

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
