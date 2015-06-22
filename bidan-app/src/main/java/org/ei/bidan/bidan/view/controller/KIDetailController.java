package org.ei.bidan.bidan.view.controller;

import android.content.Context;

import com.google.common.base.Strings;
import com.google.gson.Gson;

import org.ei.bidan.AllConstants;
import org.ei.bidan.bidan.domain.Ibu;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.repository.AllKartuIbus;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;
import org.ei.bidan.util.EasyMap;

import java.util.Map;

import static org.ei.bidan.AllConstants.KartuIbuFields.CHRONIC_DISEASE;
import static org.ei.bidan.AllConstants.KartuIbuFields.EDD;
import static org.ei.bidan.AllConstants.KartuIbuFields.HUSBAND_NAME;
import static org.ei.bidan.AllConstants.KartuIbuFields.IS_HIGH_PRIORITY;
import static org.ei.bidan.AllConstants.KartuIbuFields.IS_HIGH_RISK;
import static org.ei.bidan.AllConstants.KartuIbuFields.IS_HIGH_RISK_LABOUR;
import static org.ei.bidan.AllConstants.KartuIbuFields.IS_HIGH_RISK_PREGNANCY;
import static org.ei.bidan.AllConstants.KartuIbuFields.KABUPATEN;
import static org.ei.bidan.AllConstants.KartuIbuFields.MOTHER_ADDRESS;
import static org.ei.bidan.AllConstants.KartuIbuFields.MOTHER_AGE;
import static org.ei.bidan.AllConstants.KartuIbuFields.MOTHER_BLOOD_TYPE;
import static org.ei.bidan.AllConstants.KartuIbuFields.MOTHER_DOB;
import static org.ei.bidan.AllConstants.KartuIbuFields.MOTHER_NAME;
import static org.ei.bidan.AllConstants.KartuIbuFields.MOTHER_NUMBER;
import static org.ei.bidan.AllConstants.KartuIbuFields.NUMBER_ABORTIONS;
import static org.ei.bidan.AllConstants.KartuIbuFields.NUMBER_OF_LIVING_CHILDREN;
import static org.ei.bidan.AllConstants.KartuIbuFields.NUMBER_OF_PREGNANCIES;
import static org.ei.bidan.AllConstants.KartuIbuFields.NUMBER_PARTUS;
import static org.ei.bidan.AllConstants.KartuIbuFields.POSYANDU_NAME;
import static org.ei.bidan.AllConstants.KartuIbuFields.PROPINSI;
import static org.ei.bidan.AllConstants.KartuIbuFields.PUSKESMAS_NAME;

/**
 * Created by Dimas Ciputra on 4/17/15.
 */
public class KIDetailController {
    private final Context context;
    private final String caseId;
    private final AllKartuIbus allKartuIbus;
    private final AllKohort allKohort;

    public KIDetailController(Context context, String caseId, AllKartuIbus allKartuIbus, AllKohort allKohort) {
        this.context = context;
        this.caseId = caseId;
        this.allKartuIbus = allKartuIbus;
        this.allKohort = allKohort;
    }

    public KartuIbuClient get() {
        KartuIbu kartuIbu = allKartuIbus.findByCaseID(caseId);
        KartuIbuClient kartuIbuClient = new KartuIbuClient(kartuIbu.getCaseId(),
                kartuIbu.getDetail(PUSKESMAS_NAME), kartuIbu.getDetail(PROPINSI),
                kartuIbu.getDetail(KABUPATEN), kartuIbu.getDetail(POSYANDU_NAME),
                kartuIbu.getDetail(MOTHER_ADDRESS), kartuIbu.getDetail(MOTHER_NUMBER),
                kartuIbu.getDetail(MOTHER_NAME), kartuIbu.getDetail(MOTHER_AGE),
                kartuIbu.getDetail(MOTHER_BLOOD_TYPE),
                kartuIbu.getDetail(HUSBAND_NAME),
                kartuIbu.dusun())
                .withIsHighRiskPregnancy(kartuIbu.getDetail(IS_HIGH_RISK_PREGNANCY))
                .withDateOfBirth(kartuIbu.getDetail(MOTHER_DOB))
                .withParity(kartuIbu.getDetail(NUMBER_PARTUS))
                .withNumberOfAbortions(kartuIbu.getDetail(NUMBER_ABORTIONS))
                .withNumberOfPregnancies(kartuIbu.getDetail(NUMBER_OF_PREGNANCIES))
                .withNumberOfLivingChildren(kartuIbu.getDetail(NUMBER_OF_LIVING_CHILDREN))
                .withHighPriority(kartuIbu.getDetail(IS_HIGH_PRIORITY))
                .withIsHighRisk(kartuIbu.getDetail(IS_HIGH_RISK))
                .withEdd(kartuIbu.getDetail(EDD))
                .withHighRiskLabour(kartuIbu.getDetail(IS_HIGH_RISK_LABOUR));

        kartuIbuClient.setReligion(kartuIbu.getDetail("agama"));
        kartuIbuClient.setJob(kartuIbu.getDetail("pekerjaan"));
        kartuIbuClient.setEducation(kartuIbu.getDetail("pendidikan"));
        kartuIbuClient.setInsurance(kartuIbu.getDetail("asuransiJiwa"));
        kartuIbuClient.setPhoneNumber(kartuIbu.getDetail("NomorTelponHp"));

        Ibu ibu = allKohort.findIbuWithOpenStatusByKIId(kartuIbu.getCaseId());

        if(ibu!=null) {
            kartuIbuClient.setChronicDisease(ibu.getDetail(CHRONIC_DISEASE));
            kartuIbuClient.withHighRiskFromANC(!Strings.isNullOrEmpty(ibu.getDetail(CHRONIC_DISEASE)));
        }
        if (ibu != null && ibu.isANC()) {
            kartuIbuClient.setLila(ibu.getDetail(AllConstants.KartuANCFields.LILA_CHECK_RESULT));
        }
        return kartuIbuClient;
    }

    public String getClientJson() {
        return new Gson().toJson(get());
    }

}
