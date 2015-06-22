package org.ei.bidan.bidan.view.controller;

import android.content.Context;
import com.google.gson.Gson;

import org.ei.bidan.AllConstants;
import org.ei.bidan.bidan.domain.Ibu;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.repository.AllKartuIbus;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;

import static org.ei.bidan.AllConstants.KartuIbuFields.*;

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

        getMotherInformation(kartuIbu, kartuIbuClient);

        return kartuIbuClient;
    }

    public void getMotherInformation(KartuIbu kartuIbu, KartuIbuClient kartuIbuClient) {
        Ibu ibu = allKohort.findIbuWithOpenStatusByKIId(kartuIbu.getCaseId());

        if(ibu!=null) {

            kartuIbuClient.setChronicDisease(ibu.getDetail(CHRONIC_DISEASE));
            kartuIbuClient.setrLila(ibu.getDetail(AllConstants.KartuANCFields.LILA_CHECK_RESULT));
            kartuIbuClient.setrHbLevels(ibu.getDetail(AllConstants.KartuANCFields.HB_RESULT));
            kartuIbuClient.setrTdDiastolik(ibu.getDetail(AllConstants.KartuPNCFields.VITAL_SIGNS_TD_DIASTOLIC));
            kartuIbuClient.setrTdSistolik(ibu.getDetail(AllConstants.KartuPNCFields.VITAL_SIGNS_TD_SISTOLIC));
            kartuIbuClient.setrBloodSugar(ibu.getDetail(AllConstants.KartuANCFields.SUGAR_BLOOD_LEVEL));
            kartuIbuClient.setrAbortus(kartuIbu.getDetail(NUMBER_ABORTIONS));
            kartuIbuClient.setrPartus(kartuIbu.getDetail(NUMBER_PARTUS));
            kartuIbuClient.setrPregnancyComplications(ibu.getDetail(AllConstants.KartuANCFields.COMPLICATION_HISTORY));
            kartuIbuClient.setrFetusNumber(ibu.getDetail(AllConstants.KartuANCFields.FETUS_NUMBER));
            kartuIbuClient.setrFetusSize(ibu.getDetail(AllConstants.KartuANCFields.FETUS_SIZE));
            kartuIbuClient.setrFetusPosition(ibu.getDetail(AllConstants.KartuANCFields.FETUS_POSITION));
            kartuIbuClient.setrHeight(ibu.getDetail(AllConstants.KartuANCFields.HEIGHT));
            kartuIbuClient.setrPelvicDeformity(ibu.getDetail(AllConstants.KartuANCFields.PELVIC_DEFORMITY));
            kartuIbuClient.setrDeliveryMethod(ibu.getDetail(AllConstants.KartuPNCFields.DELIVERY_METHOD));
            kartuIbuClient.setLaborComplication(ibu.getDetail(AllConstants.KartuPNCFields.COMPLICATION));

            kartuIbuClient.setIsInPNCorANC(true);
            if(ibu.isANC()) {
                kartuIbuClient.setrLila(ibu.getDetail(AllConstants.KartuANCFields.LILA_CHECK_RESULT));
                kartuIbuClient.setrHbLevels(ibu.getDetail(AllConstants.KartuANCFields.HB_RESULT));
                kartuIbuClient.setIsPregnant(true);
            } else if(ibu.isPNC()) {
                kartuIbuClient.setIsPregnant(false);
            }
        } else {
            kartuIbuClient.setIsInPNCorANC(false);
            kartuIbuClient.setIsPregnant(false);
        }
    }

    public String getClientJson() {
        return new Gson().toJson(get());
    }

}
