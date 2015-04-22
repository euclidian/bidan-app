package org.ei.bidan.bidan.view.contract;

import com.google.common.base.Strings;

import org.ei.bidan.domain.ANCServiceType;
import org.ei.bidan.util.IntegerUtil;
import org.ei.bidan.view.contract.AlertDTO;
import org.ei.bidan.view.contract.ServiceProvidedDTO;
import org.ei.bidan.view.contract.SmartRegisterClient;
import org.ei.bidan.view.contract.Visits;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import static org.ei.bidan.util.StringUtil.humanize;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.ei.bidan.AllConstants.FORM_DATE_TIME_FORMAT;
import static org.joda.time.LocalDateTime.parse;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class KartuIbuANCClient implements KartuIbuANCSmartRegisterClient {

    public static final String CATEGORY_ANC = "anc";
    public static final String CATEGORY_TT = "tt";
    public static final String CATEGORY_HB = "hb";

    private static final String[] SERVICE_CATEGORIES = {CATEGORY_ANC, CATEGORY_TT,
            CATEGORY_HB};

    private String entityId;
    private String kiNumber;
    private String puskesmas;
    private String name;
    private String ancNumber;
    private String age;
    private String husbandName;
    private String photo_path;
    private boolean isHighPriority;
    private boolean isHighRisk;
    private String riskFactors;
    private String locationStatus;
    private String edd;
    private String village;
    private String ancStatus;
    private String kunjungan;
    private String ttImunisasi;
    private String usiaKlinis;

    private List<AlertDTO> alerts;
    private List<ServiceProvidedDTO> services_provided;
    private String entityIdToSavePhoto;
    private Map<String, Visits> serviceToVisitsMap;

    public KartuIbuANCClient(String entityId, String village, String puskesmas, String name, String age) {
        this.entityId = entityId;
        this.puskesmas = puskesmas;
        this.name = name;
        this.age = age;
        this.village = village;
        this.serviceToVisitsMap = new HashMap<String, Visits>();
    }

    @Override
    public String eddForDisplay() {
        if(Strings.isNullOrEmpty(edd)) return "-";

        DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormat.forPattern("dd MMM YYYY");
        LocalDateTime date = parse(edd, formatter);

        return "" + date.toString(formatter2);
    }

    @Override
    public LocalDateTime edd() {
        return parse(edd);
    }

    @Override
    public String pastDueInDays() {
        return null;
    }

    @Override
    public AlertDTO getAlert(ANCServiceType type) {
        return null;
    }

    @Override
    public boolean isVisitsDone() {
        return false;
    }

    @Override
    public boolean isTTDone() {
        return false;
    }

    @Override
    public String visitDoneDateWithVisitName() {
        return null;
    }

    @Override
    public String ttDoneDate() {
        return null;
    }

    @Override
    public String ifaDoneDate() {
        return null;
    }

    @Override
    public String ancNumber() {
        return null;
    }

    @Override
    public String riskFactors() {
        return riskFactors == null ? "-" : humanize(riskFactors);
    }

    @Override
    public ServiceProvidedDTO serviceProvidedToACategory(String category) {
        return null;
    }

    @Override
    public String getHyperTension(ServiceProvidedDTO ancServiceProvided) {
        return null;
    }

    @Override
    public ServiceProvidedDTO getServiceProvidedDTO(String serviceName) {
        return null;
    }

    @Override
    public List<ServiceProvidedDTO> allServicesProvidedForAServiceType(String serviceType) {
        return null;
    }

    @Override
    public String entityId() {
        return entityId;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String displayName() {
        return name;
    }

    @Override
    public String village() {
        return village;
    }

    @Override
    public String wifeName() {
        return name;
    }

    @Override
    public String husbandName() {
        return husbandName;
    }

    @Override
    public int age() {
        return Integer.parseInt(age);
    }

    @Override
    public int ageInDays() {
        return Integer.parseInt(age) * 365;
    }

    @Override
    public String ageInString() {
        return "(" + age + ")";
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
        return name.toLowerCase(Locale.getDefault()).startsWith(filterCriterion.toLowerCase())
                || String.valueOf(kiNumber).startsWith(filterCriterion)
                || String.valueOf(puskesmas).startsWith(filterCriterion);
    }

    @Override
    public int compareName(SmartRegisterClient client) {
        return 0;
    }

    public String getPuskesmas() {
        return puskesmas;
    }

    public String kiNumber() {
        return kiNumber;
    }

    public String ancStatus() { return humanize(ancStatus); }

    public String ttImunisasi() { return ttImunisasi == null ? "-" : humanize(ttImunisasi); }

    public String kunjungan() { return kunjungan == null ? "-" : humanize(kunjungan); }

    public String usiaKlinis() { return usiaKlinis == null ? "-" : humanize(usiaKlinis); }

    public KartuIbuANCClient withHusband(String husbandName) {
        this.husbandName = husbandName;
        return this;
    }

    public KartuIbuANCClient withKINumber(String kiNumber) {
        this.kiNumber = kiNumber;
        return this;
    }

    public KartuIbuANCClient withEDD(String edd) {
        this.edd = edd;
        return this;
    }

    public KartuIbuANCClient withANCStatus(String ancStatus) {
        this.ancStatus = ancStatus;
        return this;
    }

    public KartuIbuANCClient withRiskFactors(String riskFactors) {
        this.riskFactors = riskFactors;
        return this;
    }

    public KartuIbuANCClient withKunjunganData(String kunjungan) {
        this.kunjungan = kunjungan;
        return this;
    }

    public KartuIbuANCClient withTTImunisasiData(String ttImunisasi) {
        this.ttImunisasi = ttImunisasi;
        return this;
    }

    public KartuIbuANCClient withUsiaKlinisData(String usiaKlinis) {
        this.usiaKlinis = usiaKlinis;
        return this;
    }

}
