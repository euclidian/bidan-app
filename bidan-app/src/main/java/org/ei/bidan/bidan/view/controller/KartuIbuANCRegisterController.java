package org.ei.bidan.bidan.view.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.apache.commons.lang3.tuple.Pair;
import org.ei.bidan.AllConstants;
import org.ei.bidan.bidan.domain.Ibu;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.bidan.view.contract.KartuIbuANCClient;
import org.ei.bidan.bidan.view.contract.KartuIbuANCClients;
import org.ei.bidan.util.Cache;
import org.ei.bidan.util.CacheableData;
import org.ei.bidan.view.contract.SmartRegisterClient;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;
import static org.ei.bidan.AllConstants.KartuIbuFields.*;
import static org.ei.bidan.AllConstants.KartuANCFields.*;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class KartuIbuANCRegisterController extends CommonController {

    private static final String KI_ANC_CLIENTS_LIST = "KIANCClientsList";

    private final AllKohort allKohort;
    private final Cache<String> cache;
    private final Cache<KartuIbuANCClients> kartuIbuANCClientsCache;

    public KartuIbuANCRegisterController(AllKohort allKohort, Cache<String> cache, Cache<KartuIbuANCClients> kartuIbuANCClientsCache) {
        this.allKohort = allKohort;
        this.cache = cache;
        this.kartuIbuANCClientsCache = kartuIbuANCClientsCache;
    }

    public String get() {
        return cache.get(KI_ANC_CLIENTS_LIST, new CacheableData<String>() {
            @Override
            public String fetch() {
                KartuIbuANCClients ancClients = new KartuIbuANCClients();
                List<Pair<Ibu, KartuIbu>> ancsWithKis = allKohort.allANCsWithKartuIbu();

                for (Pair<Ibu, KartuIbu> ancsWithKi : ancsWithKis) {
                    Ibu anc = ancsWithKi.getLeft();
                    KartuIbu ki = ancsWithKi.getRight();

                    KartuIbuANCClient kartuIbuClient = new KartuIbuANCClient(anc.getId(),
                            ki.dusun(),
                            ki.getDetail(PUSKESMAS_NAME),
                            ki.getDetail(MOTHER_NAME),
                            ki.getDetail(MOTHER_DOB));
                    ancClients.add(kartuIbuClient);
                }
                return new Gson().toJson(ancClients);
            }
        });
    }

    public KartuIbuANCClients getKartuIbuANCClients() {
        return kartuIbuANCClientsCache.get(KI_ANC_CLIENTS_LIST, new CacheableData<KartuIbuANCClients>() {
            @Override
            public KartuIbuANCClients fetch() {
                KartuIbuANCClients ancClients = new KartuIbuANCClients();
                List<Pair<Ibu, KartuIbu>> ancsWithKis = allKohort.allANCsWithKartuIbu();

                for (Pair<Ibu, KartuIbu> ancsWithKi : ancsWithKis) {
                    Ibu anc = ancsWithKi.getLeft();
                    KartuIbu ki = ancsWithKi.getRight();

                    KartuIbuANCClient kartuIbuClient = new KartuIbuANCClient(anc.getId(),
                            ki.dusun(),
                            ki.getDetail(PUSKESMAS_NAME),
                            ki.getDetail(MOTHER_NAME),
                            ki.getDetail(MOTHER_DOB))
                            .withHusband(ki.getDetail(HUSBAND_NAME))
                            .withKINumber(ki.getDetail(MOTHER_NUMBER))
                            .withEDD(ki.getDetail(EDD))
                            .withANCStatus(anc.getDetail(MOTHER_NUTRITION_STATUS))
                            .withKunjunganData(anc.getDetail(TRIMESTER))
                            .withTTImunisasiData(anc.getDetail(IMMUNIZATION_TT_STATUS))
                            .withTanggalHPHT(anc.getDetail(HPHT_DATE));

                    kartuIbuClient.setKartuIbuCaseId(anc.getKartuIbuId());
                    kartuIbuClient.setBB(anc.getDetail(WEIGHT_BEFORE));
                    kartuIbuClient.setTB(anc.getDetail(HEIGHT));
                    kartuIbuClient.setLILA(anc.getDetail(LILA_CHECK_RESULT));
                    kartuIbuClient.setBeratBadan(anc.getDetail(WEIGHT_CHECK_RESULT));
                    kartuIbuClient.setPenyakitKronis(anc.getDetail(CHRONIC_DISEASE));
                    kartuIbuClient.setAlergi(anc.getDetail(ALLERGY));
                    kartuIbuClient.setHighRiskLabour(ki.getDetail(IS_HIGH_RISK_LABOUR));
                    kartuIbuClient.setHigRiskPregnancyReason(ki.getDetail(HIGH_RISK_PREGNANCY_REASON));
                    kartuIbuClient.setRiwayatKomplikasiKebidanan(anc.getDetail(COMPLICATION_HISTORY));

                    kartuIbuClient.setIsInPNCorANC(true);
                    kartuIbuClient.setIsPregnant(true);

                    kartuIbuClient.setChronicDisease(anc.getDetail(CHRONIC_DISEASE));
                    kartuIbuClient.setrLila(anc.getDetail(AllConstants.KartuANCFields.LILA_CHECK_RESULT));
                    kartuIbuClient.setrHbLevels(anc.getDetail(AllConstants.KartuANCFields.HB_RESULT));
                    kartuIbuClient.setrTdDiastolik(anc.getDetail(AllConstants.KartuPNCFields.VITAL_SIGNS_TD_DIASTOLIC));
                    kartuIbuClient.setrTdSistolik(anc.getDetail(AllConstants.KartuPNCFields.VITAL_SIGNS_TD_SISTOLIC));
                    kartuIbuClient.setrBloodSugar(anc.getDetail(AllConstants.KartuANCFields.SUGAR_BLOOD_LEVEL));
                    kartuIbuClient.setrAbortus(ki.getDetail(NUMBER_ABORTIONS));
                    kartuIbuClient.setrPartus(ki.getDetail(NUMBER_PARTUS));
                    kartuIbuClient.setrPregnancyComplications(anc.getDetail(AllConstants.KartuANCFields.COMPLICATION_HISTORY));
                    kartuIbuClient.setrFetusNumber(anc.getDetail(AllConstants.KartuANCFields.FETUS_NUMBER));
                    kartuIbuClient.setrFetusSize(anc.getDetail(AllConstants.KartuANCFields.FETUS_SIZE));
                    kartuIbuClient.setrFetusPosition(anc.getDetail(AllConstants.KartuANCFields.FETUS_POSITION));
                    kartuIbuClient.setrPelvicDeformity(anc.getDetail(AllConstants.KartuANCFields.PELVIC_DEFORMITY));
                    kartuIbuClient.setrHeight(anc.getDetail(AllConstants.KartuANCFields.HEIGHT));
                    kartuIbuClient.setrDeliveryMethod(anc.getDetail(AllConstants.KartuPNCFields.DELIVERY_METHOD));
                    kartuIbuClient.setLaborComplication(anc.getDetail(AllConstants.KartuPNCFields.COMPLICATION));

                    ancClients.add(kartuIbuClient);
                }
                sortByName(ancClients);
                return ancClients;
            }
        });
    }

    private void sortByName(List<? extends SmartRegisterClient> kiClients) {
        sort(kiClients, new Comparator<SmartRegisterClient>() {
            @Override
            public int compare(SmartRegisterClient o1, SmartRegisterClient o2) {
                return o1.name().compareToIgnoreCase(o2.name());
            }
        });
    }

    public CharSequence[] getRandomNameChars(final SmartRegisterClient client) {
        String clients = get();
        List<SmartRegisterClient> ancClients = new Gson().fromJson(clients, new TypeToken<List<KartuIbuANCClient>>() {
        }.getType());
        return onRandomNameChars(
                client,
                ancClients,
                allKohort.randomDummyANCName(),
                AllConstants.DIALOG_DOUBLE_SELECTION_NUM);
    }

}
