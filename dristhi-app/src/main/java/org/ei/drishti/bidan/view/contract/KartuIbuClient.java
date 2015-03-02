package org.ei.drishti.bidan.view.contract;

import android.util.Log;

import com.google.common.base.Strings;

import org.ei.drishti.util.DateUtil;
import org.ei.drishti.util.IntegerUtil;
import org.ei.drishti.view.contract.SmartRegisterClient;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.ei.drishti.util.StringUtil.humanize;

/**
 * Created by Dimas Ciputra on 2/17/15.
 */
public class KartuIbuClient implements SmartRegisterClient {

    private String entityId;
    private String puskesmas;
    private String province;
    private String kabupaten;
    private String phc;
    private String householdAddress;
    private String ecNumber;
    private String wifeName;
    private String wifeAge;
    private String golonganDarah;
    private String riwayatKomplikasi;

    public KartuIbuClient(String entityId,String puskesmas, String province, String kabupaten, String phc, String householdAddress, String ecNumber, String wifeName, String wifeAge, String golonganDarah, String riwayatKomplikasi) {
        this.entityId = entityId;
        this.puskesmas = puskesmas;
        this.province = province;
        this.kabupaten = kabupaten;
        this.phc = phc;
        this.householdAddress = householdAddress;
        this.ecNumber = ecNumber;
        this.wifeName = wifeName;
        this.wifeAge = wifeAge;
        this.golonganDarah = golonganDarah;
        this.riwayatKomplikasi = riwayatKomplikasi;
    }

    // Getter
    public String getEntityId() {
        return entityId;
    }

    public String getPuskesmas() {
        return puskesmas;
    }

    public String getProvince() {
        return province;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public String getPhc() {
        return phc;
    }

    public String getHouseholdAddress() {
        return householdAddress;
    }

    public String getEcNumber() {
        return ecNumber;
    }

    public String getWifeName() {
        return wifeName;
    }

    public String getWifeAge() {
        return wifeAge;
    }

    public String getGolongan_darah() {
        return golonganDarah;
    }

    public String getRiwayat_komplikasi() {
        return riwayatKomplikasi;
    }

    // Setter
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public void setPuskesmas(String district) {
        this.puskesmas = district;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public void setPhc(String phc) {
        this.phc = phc;
    }

    public void setHouseholdAddress(String householdAddress) {
        this.householdAddress = householdAddress;
    }

    public void setEcNumber(String ecNumber) {
        this.ecNumber = ecNumber;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
    }

    public void setWifeAge(String wifeAge) {
        this.wifeAge = wifeAge;
    }

    public void setGolonganDarah(String golonganDarah) {
       this.golonganDarah = golonganDarah;
    }

    public void setRiwayatKomplikasi(String riwayatKomplikasi) {
        this.riwayatKomplikasi = riwayatKomplikasi;
    }

    @Override
    public String entityId() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String displayName() {
        return null;
    }

    @Override
    public String village() {
        return null;
    }

    @Override
    public String wifeName() {
        return Strings.isNullOrEmpty(this.wifeName) ? "" : humanize(this.wifeName);
    }

    @Override
    public String husbandName() {
        return null;
    }

    @Override
    public int age() {
        return 0;
    }

    @Override
    public int ageInDays() {
        return Strings.isNullOrEmpty(this.wifeAge) ? 0 : Integer.parseInt(this.wifeAge);
    }

    @Override
    public String ageInString() {
        return null;
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
        return false;
    }

    @Override
    public boolean isHighPriority() {
        return false;
    }

    @Override
    public boolean isBPL() {
        return false;
    }

    @Override
    public String profilePhotoPath() {
        return null;
    }

    @Override
    public String locationStatus() {
        return null;
    }

    @Override
    public boolean satisfiesFilter(String filterCriterion) {
        return false;
    }

    @Override
    public int compareName(SmartRegisterClient client) {
        return this.wifeName().compareToIgnoreCase(client.wifeName());
    }
}
