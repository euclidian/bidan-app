package org.ei.bidan.bidan.view.controller;

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

/**
 * Created by Dimas Ciputra on 4/8/15.
 */
public class AnakRegisterController {
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
                            a.getDetail("birthWeight"))
                            .withMotherName(a.getKartuIbu().getDetails().get("Namalengkap"))
                            .withMotherAge(a.getKartuIbu().getDetails().get("Umur"))
                            .withFatherName(a.getKartuIbu().getDetails().get("Namasuami"))
                            .withDOB(a.getDateOfBirth())
                            .withName(a.getDetail("name"))
                            .withKINumber(a.getKartuIbu().getDetails().get("NoIbu"))
                            .withBirthCondition(a.getDetail("birthCondition"))
                            .withServiceAtBirth(a.getDetail("serviceAtBirth"))
                            .withPhotoPath(photoPath);

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
}
