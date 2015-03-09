package org.ei.drishti.bidan.view.contract;

import android.util.Log;

import com.google.common.base.Strings;

import org.apache.commons.lang3.StringUtils;
import org.ei.drishti.util.DateUtil;
import org.ei.drishti.util.IntegerUtil;
import org.ei.drishti.view.contract.SmartRegisterClient;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

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
    private String posyandu;
    private String householdAddress;
    private String noIbu;
    private String wifeName;
    private String wifeAge;
    private String golonganDarah;
    private String riwayatKomplikasi;
    private String husbandName;
    private String tglPeriksa;
    private String edd;
    private String village;
    private String dateOfBirth;
    private Map<String, String> status = new HashMap<String, String>();

    public KartuIbuClient(String entityId,String puskesmas, String province, String kabupaten, String posyandu, String householdAddress, String noIbu, String wifeName, String wifeAge, String golonganDarah, String riwayatKomplikasi, String husbandName, String tglPeriksa, String edd, String village) {
        this.entityId = entityId;
        this.puskesmas = puskesmas;
        this.province = province;
        this.kabupaten = kabupaten;
        this.posyandu = posyandu;
        this.householdAddress = householdAddress;
        this.noIbu = noIbu;
        this.wifeName = wifeName;
        this.wifeAge = wifeAge;
        this.golonganDarah = golonganDarah;
        this.riwayatKomplikasi = riwayatKomplikasi;
        this.husbandName = husbandName;
        this.tglPeriksa = tglPeriksa;
        this.edd = edd;
        this.village = village;
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

    public String getPosyandu() {
        return posyandu;
    }

    public String getHouseholdAddress() {
        return householdAddress;
    }

    public String getNoIbu() {
        return noIbu;
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

    public String getTglPeriksa() { return tglPeriksa; }

    public String getEdd() { return edd; }

    public String getVillage() {
        return village;
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

    public void setPosyandu(String posyandu) {
        this.posyandu = posyandu;
    }

    public void setHouseholdAddress(String householdAddress) {
        this.householdAddress = householdAddress;
    }

    public void setNoIbu(String noIbu) {
        this.noIbu = noIbu;
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

    public void setTglPeriksa(String tglPeriksa) {
        this.tglPeriksa = tglPeriksa;
    }

    public void setEdd(String edd) { this.edd = edd; }

    public void setVillage(String village) {
        this.village = village;
    }

    @Override
    public String entityId() {
        return entityId;
    }

    @Override
    public String name() {
        return getWifeName();
    }

    @Override
    public String displayName() {
        return getWifeName();
    }

    @Override
    public String village() {
        return getVillage();
    }

    @Override
    public String wifeName() {
        return Strings.isNullOrEmpty(this.wifeName) ? "" : humanize(this.wifeName);
    }

    @Override
    public String husbandName() {
        return Strings.isNullOrEmpty(this.husbandName) ? "" : humanize(this.husbandName);
    }

    @Override
    public int age() {
        return Integer.parseInt(wifeAge);
    }

    @Override
    public int ageInDays() {
        return StringUtils.isBlank(dateOfBirth) ? 0 : Days.daysBetween(LocalDate.parse(dateOfBirth), DateUtil.today()).getDays();
    }

    @Override
    public String ageInString() {
        return "(" + age() + ")";
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

    public Map<String, String> status() {
        return status;
    }

    public KartuIbuClient withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public KartuIbuClient withStatus(Map<String, String> status) {
        this.status = status;
        return this;
    }
}
