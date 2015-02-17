package org.ei.drishti.bidan.kartuibu;

import org.ei.drishti.view.contract.SmartRegisterClient;

/**
 * Created by Dimas Ciputra on 2/17/15.
 */
public class KartuIbuClient implements SmartRegisterClient {

    private String entityId;
    private String caseId;
    private String name;
    private String district;
    private String Province;
    private String Kabupaten;
    private String phc;
    private String householdAddress;
    private String ecNumber;
    private String wifeName;
    private String wifeAge;
    private String Golongan_darah;
    private String Riwayat_komplikasi;

    public KartuIbuClient(String entityId, String caseId, String name, String district, String province, String kabupaten, String phc, String householdAddress, String ecNumber, String wifeName, String wifeAge, String golongan_darah, String riwayat_komplikasi) {
        this.entityId = entityId;
        this.caseId = caseId;
        this.name = name;
        this.district = district;
        Province = province;
        Kabupaten = kabupaten;
        this.phc = phc;
        this.householdAddress = householdAddress;
        this.ecNumber = ecNumber;
        this.wifeName = wifeName;
        this.wifeAge = wifeAge;
        this.Golongan_darah = golongan_darah;
        this.Riwayat_komplikasi = riwayat_komplikasi;
    }

    // Getter
    public String getEntityId() {
        return entityId;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getName() {
        return name != null ? name : "";
    }

    public String getDistrict() {
        return district;
    }

    public String getProvince() {
        return Province;
    }

    public String getKabupaten() {
        return Kabupaten;
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
        return Golongan_darah;
    }

    public String getRiwayat_komplikasi() {
        return Riwayat_komplikasi;
    }

    // Setter
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public void setKabupaten(String kabupaten) {
        Kabupaten = kabupaten;
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

    public void setGolongan_darah(String golongan_darah) {
        Golongan_darah = golongan_darah;
    }

    public void setRiwayat_komplikasi(String riwayat_komplikasi) {
        Riwayat_komplikasi = riwayat_komplikasi;
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
        return null;
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
        return 0;
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
        return 0;
    }
}
