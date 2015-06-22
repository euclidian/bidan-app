package org.ei.bidan.bidan.view.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.apache.commons.lang3.ArrayUtils;
import org.ei.bidan.AllConstants;
import org.ei.bidan.bidan.domain.Anak;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.bidan.view.contract.AnakClient;
import org.ei.bidan.domain.Alert;
import org.ei.bidan.domain.ServiceProvided;
import org.ei.bidan.service.AlertService;
import org.ei.bidan.service.ServiceProvidedService;
import org.ei.bidan.util.Cache;
import org.ei.bidan.util.CacheableData;
import org.ei.bidan.view.contract.AlertDTO;
import org.ei.bidan.view.contract.ServiceProvidedDTO;
import org.ei.bidan.view.contract.SmartRegisterClient;
import org.ei.bidan.view.contract.SmartRegisterClients;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.String.valueOf;
import static java.util.Collections.sort;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.ei.bidan.domain.ServiceProvided.CHILD_ILLNESS_SERVICE_PROVIDED_NAME;
import static org.ei.bidan.domain.ServiceProvided.PNC_SERVICE_PROVIDED_NAME;
import static org.ei.bidan.domain.ServiceProvided.VITAMIN_A_SERVICE_PROVIDED_NAME;

import static org.ei.bidan.AllConstants.KartuAnakFields.*;
import static org.ei.bidan.AllConstants.KartuIbuFields.*;

/**
 * Created by Dimas Ciputra on 4/8/15.
 */
public class AnakRegisterController extends CommonController {
    private static final String ANAK_CLIENTS_LIST = "anakClientsList";

    private final AllKohort allKohort;
    private final AlertService alertService;
    private final ServiceProvidedService serviceProvidedService;
    private final Cache<String> cache;
    private final Cache<SmartRegisterClients> smartRegisterClientsCache;

    public AnakRegisterController(AllKohort allKohort, AlertService alertService, ServiceProvidedService serviceProvidedService, Cache<String> cache, Cache<SmartRegisterClients> smartRegisterClientsCache) {
        this.allKohort = allKohort;
        this.alertService = alertService;
        this.serviceProvidedService = serviceProvidedService;
        this.cache = cache;
        this.smartRegisterClientsCache = smartRegisterClientsCache;
    }

    public String get() {
        return cache.get(ANAK_CLIENTS_LIST, new CacheableData<String>() {
            @Override
            public String fetch() {
                List<Anak> anakList = allKohort.allAnakWithIbuAndKI();
                SmartRegisterClients anakClients = new SmartRegisterClients();

                for(Anak a : anakList) {
                    AnakClient anakClient = new AnakClient(a.getCaseId(),
                            a.getGender(),
                            a.getDetail(BIRTH_WEIGHT),
                            a.getDetails())
                            .withName(a.getDetail(CHILD_NAME));
                    anakClients.add(anakClient);
                }
                return new Gson().toJson(anakClients);
            }
        });
    }

    public SmartRegisterClients getClient() {
        return  smartRegisterClientsCache.get(ANAK_CLIENTS_LIST, new CacheableData<SmartRegisterClients>() {
            @Override
            public SmartRegisterClients fetch() {
                List<Anak> anakList = allKohort.allAnakWithIbuAndKI();
                SmartRegisterClients anakSmartClient = new SmartRegisterClients();

                for(Anak a : anakList) {
                    String photoPath = isBlank(a.getPhotoPath()) ?
                            (AllConstants.FEMALE_GENDER.equalsIgnoreCase(a.getGender()) ?
                                    AllConstants.DEFAULT_GIRL_INFANT_IMAGE_PLACEHOLDER_PATH :
                                    AllConstants.DEFAULT_BOY_INFANT_IMAGE_PLACEHOLDER_PATH) :
                            a.getPhotoPath();

                    // List<AlertDTO> alerts = getAlerts(a.getCaseId());
                    // List<ServiceProvidedDTO> services = getServicesProvided(a.getCaseId());

                    AnakClient anakClient = new AnakClient(a.getCaseId(), a.getGender(),
                            a.getDetail(BIRTH_WEIGHT),
                            a.getDetails())
                            .withMotherName(a.getKartuIbu().getDetail(MOTHER_NAME))
                            .withMotherAge(a.getKartuIbu().getDetail(MOTHER_AGE))
                            .withFatherName(a.getKartuIbu().getDetail(HUSBAND_NAME))
                            .withDOB(a.getDateOfBirth())
                            .withName(a.getDetail(CHILD_NAME))
                            .withKINumber(a.getKartuIbu().getDetail(MOTHER_NUMBER))
                            .withBirthCondition(a.getDetail(BIRTH_CONDITION))
                            .withServiceAtBirth(a.getDetail(SERVICE_AT_BIRTH))
                            .withPhotoPath(photoPath)
                            .withIsHighRisk(a.getDetail(IS_HIGH_RISK_CHILD));
                    anakClient.setHb07(a.getDetail(IMMUNIZATION_HB_0_7_DATES));
                    anakClient.setBcgPol1(a.getDetail(IMMUNIZATION_BCG_AND_POLIO1));
                    anakClient.setDptHb1Pol2(a.getDetail(IMMUNIZATION_DPT_HB1_POLIO2));
                    anakClient.setDptHb2Pol3(a.getDetail(IMMUNIZATION_DPT_HB2_POLIO3));
                    anakClient.setDptHb3Pol4(a.getDetail(IMMUNIZATION_DPT_HB3_POLIO4));
                    anakClient.setCampak(a.getDetail(IMMUNIZATION_MEASLES));
                    anakClient.setBirthPlace(a.getIbu().getDetail(BIRTH_PLACE));
                    anakClient.setHbGiven(a.getDetail(HB_GIVEN));
                    anakClient.setVisitDate(a.getDetail(CHILD_VISIT_DATE));
                    anakClient.setCurrentWeight(a.getDetail(CHILD_CURRENT_WEIGTH));
                    anakClient.setBabyNo(a.getDetail(BABY_NO));
                    anakSmartClient.add(anakClient);
                }
                sortByName(anakSmartClient);
                return anakSmartClient;
            }
        });
    }

    private List<AlertDTO> getAlerts(String entityId) {
        List<Alert> alerts = alertService.findByEntityIdAndAlertNames(entityId, AllConstants.Immunizations.ALL);
        List<AlertDTO> alertDTOs = new ArrayList<AlertDTO>();
        for (Alert alert : alerts) {
            alertDTOs.add(new AlertDTO(alert.visitCode(), valueOf(alert.status()), alert.startDate()));
        }
        return alertDTOs;
    }

    private List<ServiceProvidedDTO> getServicesProvided(String entityId) {
        List<ServiceProvided> servicesProvided = serviceProvidedService.findByEntityIdAndServiceNames(entityId,
                ArrayUtils.addAll(AllConstants.Immunizations.ALL, VITAMIN_A_SERVICE_PROVIDED_NAME,
                        CHILD_ILLNESS_SERVICE_PROVIDED_NAME, PNC_SERVICE_PROVIDED_NAME));
        List<ServiceProvidedDTO> serviceProvidedDTOs = new ArrayList<ServiceProvidedDTO>();
        for (ServiceProvided serviceProvided : servicesProvided) {
            serviceProvidedDTOs.add(new ServiceProvidedDTO(serviceProvided.name(), serviceProvided.date(), serviceProvided.data()));
        }
        return serviceProvidedDTOs;
    }

    private void sortByName(List<SmartRegisterClient> childrenClient) {
        sort(childrenClient, new Comparator<SmartRegisterClient>() {
            @Override
            public int compare(SmartRegisterClient oneChild, SmartRegisterClient anotherChild) {
                return oneChild.compareName(anotherChild);
            }
        });
    }

    private void sortByMotherName(List<AnakClient> childrenClient) {
        sort(childrenClient, new Comparator<AnakClient>() {
            @Override
            public int compare(AnakClient oneChild, AnakClient anotherChild) {
                return oneChild.motherName().compareToIgnoreCase(anotherChild.motherName());
            }
        });
    }

    public CharSequence[] getRandomNameChars(final SmartRegisterClient client) {
        String clients = get();
        List<SmartRegisterClient> anakClients = new Gson().fromJson(clients, new TypeToken<List<AnakClient>>() {
        }.getType());

        return onRandomNameChars(
                client,
                anakClients,
                allKohort.randomDummyAnakName(),
                AllConstants.DIALOG_DOUBLE_SELECTION_NUM);
    }
}
