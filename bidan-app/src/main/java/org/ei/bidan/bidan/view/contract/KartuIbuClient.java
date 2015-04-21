package org.ei.bidan.bidan.view.contract;

import com.google.common.base.Strings;

import org.apache.commons.lang3.StringUtils;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.util.DateUtil;
import org.ei.bidan.util.IntegerUtil;
import org.ei.bidan.view.contract.SmartRegisterClient;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.ei.bidan.util.StringUtil.humanize;

/**
 * Created by Dimas Ciputra on 2/17/15.
 */
public class KartuIbuClient implements KISmartRegisterClient {

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
    private String numberOfPregnancies;
    private String parity;
    private String numberLivingChildren;
    private String numberOfAbortions;
    private String rtRw;
    private Map<String, String> status = new HashMap<String, String>();
    private List<KIChildClient> children;

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
        this.children = new ArrayList<KIChildClient>();
    }

    // Getter
    public String getEntityId() {
        return entityId;
    }

    public String getPuskesmas() {
        return humanize(puskesmas);
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

    @Override
    public String numberOfPregnancies() {
        return Strings.isNullOrEmpty(numberOfPregnancies) ? "-" : numberOfPregnancies;
    }

    @Override
    public String parity() {
        return Strings.isNullOrEmpty(parity) ? "-" : parity;
    }

    @Override
    public String numberOfLivingChildren() {
        return Strings.isNullOrEmpty(numberLivingChildren) ? "-" : numberLivingChildren;
    }

    @Override
    public String numberOfAbortions() {
        return Strings.isNullOrEmpty(numberOfAbortions) ? "-" : numberOfAbortions;
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

    public void setRtRw(String rtRw) { this.rtRw = rtRw; }

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getRtRw() { return rtRw; }

    @Override
    public boolean satisfiesFilter(String filterCriterion) {
        return wifeName.toLowerCase(Locale.getDefault()).startsWith(filterCriterion.toLowerCase())
                || String.valueOf(noIbu).startsWith(filterCriterion)
                || String.valueOf(puskesmas).startsWith(filterCriterion);
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

    public KartuIbuClient withNumberOfPregnancies(String numberOfPregnancies) {
        this.numberOfPregnancies = numberOfPregnancies;
        return this;
    }

    public KartuIbuClient withParity(String parity) {
        this.parity = parity;
        return this;
    }

    public KartuIbuClient withNumberOfLivingChildren(String numberLivingChildren) {
        this.numberLivingChildren = numberLivingChildren;
        return this;
    }

    public KartuIbuClient withNumberOfAbortions(String numberOfAbortions) {
        this.numberOfAbortions = numberOfAbortions;
        return this;
    }

    public KartuIbuClient addChild(KIChildClient childClient) {
        children.add(childClient);
        return this;
    }

    public List<KIChildClient> children() {
        return children;
    }
}
