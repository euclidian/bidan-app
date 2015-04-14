package org.ei.bidan.bidan.view.contract;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.ei.bidan.util.DateUtil;
import org.ei.bidan.util.StringUtil;
import org.ei.bidan.view.contract.AlertDTO;
import org.ei.bidan.view.contract.SmartRegisterClient;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.ei.bidan.AllConstants.ECRegistrationFields.BPL_VALUE;
import static org.ei.bidan.AllConstants.FEMALE_GENDER;
import static org.ei.bidan.AllConstants.IN_AREA;
import static org.ei.bidan.AllConstants.OUT_OF_AREA;
import static org.ei.bidan.util.DateUtil.formatDate;

/**
 * Created by Dimas Ciputra on 4/8/15.
 */
public class AnakClient implements SmartRegisterClient{

    private final String entityId;
    private String gender;
    private String weight;
    private String name;
    private String motherName;
    private String dob;
    private String motherAge;
    private String fatherName;
    private String village;
    private String locationStatus;
    private String economicStatus;
    private boolean isHighRisk;
    private String photo_path;
    private String kiNumber;
    private String birthCondition;
    private String serviceAtBirth;

    public AnakClient(String entityId, String gender, String weight) {
        this.entityId = entityId;
        this.gender = gender;
        this.weight = weight;
    }

    public String motherName() {
        return StringUtil.humanize(motherName);
    }

    public String fatherName() {
        return StringUtil.humanize(fatherName);
    }

    public String gender() { return StringUtil.humanize(gender); }

    public String weight() {
        return weight;
    }

    public String motherKiNumber() {
        return kiNumber;
    }

    public String dateOfBirth() {
        return isBlank(dob) ? "" : formatDate(dob);
    }

    public String getBirthCondition() { return StringUtil.humanize(birthCondition); }

    public String getServiceAtBirth() { return StringUtil.humanize(serviceAtBirth); }

    @Override
    public String entityId() {
        return entityId;
    }

    @Override
    public String name() {
        return isBlank(name) ? "" : StringUtil.humanize(name);
    }

    @Override
    public String displayName() {
        return isBlank(name) ? "B/o " + motherName() : StringUtil.humanize(name);
    }

    @Override
    public String village() {
        return null;
    }

    @Override
    public int age() {
        return isBlank(dob) ? 0 : Years.yearsBetween(LocalDate.parse(dob), LocalDate.now()).getYears();
    }

    @Override
    public int ageInDays() {
        return isBlank(dob) ? 0 : Days.daysBetween(LocalDate.parse(dob), DateUtil.today()).getDays();
    }

    @Override
    public String ageInString() {
        return "(" + format(ageInDays()) + ", " + formatGender(gender()) + ")";
    }

    private String formatGender(String gender) {
        return FEMALE_GENDER.equalsIgnoreCase(gender) ? "F" : "M";
    }


    @Override
    public boolean isSC() {
        return false;
    }

    @Override
    public boolean isST() {
        return false;
    }

    @Override
    public boolean isHighRisk() {
        return isHighRisk;
    }


    @Override
    public boolean isHighPriority() { return false; }

    @Override
    public boolean isBPL() {
        return economicStatus != null && economicStatus.equalsIgnoreCase(BPL_VALUE);
    }

    @Override
    public String profilePhotoPath() {
        return photo_path;
    }

    @Override
    public String locationStatus() {
        return locationStatus;
    }

    @Override
    public boolean satisfiesFilter(String filterCriterion) {
        return (!isBlank(name) && name.toLowerCase().startsWith(filterCriterion.toLowerCase()))
                || (!isBlank(motherName) && motherName.toLowerCase().startsWith(filterCriterion.toLowerCase()))
                || String.valueOf(kiNumber).startsWith(filterCriterion);
    }

    @Override
    public int compareName(SmartRegisterClient anotherClient) {
        /*
        ChildSmartRegisterClient anotherChildClient = (ChildSmartRegisterClient) anotherClient;
        return this.motherName().compareTo(anotherChildClient.motherName());
        */
        return 0;
    }

    @Override
    public String wifeName() {
        return name();
    }

    @Override
    public String husbandName() {
        return motherName() + "," + fatherName();
    }

    public String format(int days_since) {
        int DAYS_THRESHOLD = 28;
        int WEEKS_THRESHOLD = 119;
        int MONTHS_THRESHOLD = 720;
        if (days_since < DAYS_THRESHOLD) {
            return (int) Math.floor(days_since) + "d";
        } else if (days_since < WEEKS_THRESHOLD) {
            return (int) Math.floor(days_since / 7) + "w";
        } else if (days_since < MONTHS_THRESHOLD) {
            return (int) Math.floor(days_since / 30) + "m";
        } else {
            return (int) Math.floor(days_since / 365) + "y";
        }
    }

    public AnakClient withName(String name) {
        this.name = name;
        return this;
    }

    public AnakClient withMotherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public AnakClient withDOB(String dob) {
        this.dob = dob;
        return this;
    }

    public AnakClient withMotherAge(String motherAge) {
        this.motherAge = motherAge;
        return this;
    }

    public AnakClient withFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public AnakClient withVillage(String village) {
        this.village = village;
        return this;
    }

    public AnakClient withOutOfArea(boolean outOfArea) {
        this.locationStatus = outOfArea ? OUT_OF_AREA : IN_AREA;
        return this;
    }

    public AnakClient withEconomicStatus(String economicStatus) {
        this.economicStatus = economicStatus;
        return this;
    }

    public AnakClient withIsHighRisk(boolean isHighRisk) {
        this.isHighRisk = isHighRisk;
        return this;
    }

    public AnakClient withPhotoPath(String photoPath) {
        this.photo_path = photoPath;
        return this;
    }

    public AnakClient withKINumber(String kiNumber) {
        this.kiNumber = kiNumber;
        return this;
    }

    public AnakClient withBirthCondition(String birthCondition) {
        this.birthCondition = birthCondition;
        return this;
    }

    public AnakClient withServiceAtBirth(String serviceAtBirth) {
        this.serviceAtBirth = serviceAtBirth;
        return this;
    }

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
