package org.ei.bidan.bidan.service;

import org.ei.bidan.AllConstants;
import org.ei.bidan.bidan.domain.Ibu;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.bidan.repository.AnakRepository;
import org.ei.bidan.bidan.repository.IbuRepository;
import org.ei.bidan.domain.Child;
import org.ei.bidan.domain.Mother;
import org.ei.bidan.domain.ServiceProvided;
import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.domain.form.SubForm;
import org.ei.bidan.repository.AllAlerts;
import org.ei.bidan.repository.AllBeneficiaries;
import org.ei.bidan.repository.AllTimelineEvents;
import org.ei.bidan.repository.ChildRepository;
import org.ei.bidan.repository.MotherRepository;
import org.ei.bidan.service.ServiceProvidedService;
import org.ei.bidan.util.EasyMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.ei.bidan.AllConstants.ChildIllnessFields.CHILD_REFERRAL;
import static org.ei.bidan.AllConstants.ChildIllnessFields.CHILD_SIGNS;
import static org.ei.bidan.AllConstants.ChildIllnessFields.CHILD_SIGNS_OTHER;
import static org.ei.bidan.AllConstants.ChildIllnessFields.REPORT_CHILD_DISEASE;
import static org.ei.bidan.AllConstants.ChildIllnessFields.REPORT_CHILD_DISEASE_DATE;
import static org.ei.bidan.AllConstants.ChildIllnessFields.REPORT_CHILD_DISEASE_OTHER;
import static org.ei.bidan.AllConstants.ChildIllnessFields.REPORT_CHILD_DISEASE_PLACE;
import static org.ei.bidan.AllConstants.ChildIllnessFields.SICK_VISIT_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.BCG_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.DPTBOOSTER_1_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.DPTBOOSTER_2_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.HEPB_BIRTH_DOSE_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.JE_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.MEASLESBOOSTER_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.MEASLES_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.MMR_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.OPVBOOSTER_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.OPV_0_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.OPV_1_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.OPV_2_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.OPV_3_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.PENTAVALENT_1_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.PENTAVALENT_2_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.PENTAVALENT_3_DATE;
import static org.ei.bidan.AllConstants.ChildRegistrationECFields.SHOULD_CLOSE_MOTHER;
import static org.ei.bidan.AllConstants.ChildRegistrationOAFields.CHILD_ID;
import static org.ei.bidan.AllConstants.ChildRegistrationOAFields.THAYI_CARD_NUMBER;
import static org.ei.bidan.AllConstants.ENTITY_ID_FIELD_NAME;
import static org.ei.bidan.AllConstants.Immunizations.BCG;
import static org.ei.bidan.AllConstants.Immunizations.DPT_BOOSTER_1;
import static org.ei.bidan.AllConstants.Immunizations.DPT_BOOSTER_2;
import static org.ei.bidan.AllConstants.Immunizations.HEPATITIS_BIRTH_DOSE;
import static org.ei.bidan.AllConstants.Immunizations.JE;
import static org.ei.bidan.AllConstants.Immunizations.MEASLES;
import static org.ei.bidan.AllConstants.Immunizations.MEASLES_BOOSTER;
import static org.ei.bidan.AllConstants.Immunizations.MMR;
import static org.ei.bidan.AllConstants.Immunizations.OPV_0;
import static org.ei.bidan.AllConstants.Immunizations.OPV_1;
import static org.ei.bidan.AllConstants.Immunizations.OPV_2;
import static org.ei.bidan.AllConstants.Immunizations.OPV_3;
import static org.ei.bidan.AllConstants.Immunizations.OPV_BOOSTER;
import static org.ei.bidan.AllConstants.Immunizations.PENTAVALENT_1;
import static org.ei.bidan.AllConstants.Immunizations.PENTAVALENT_2;
import static org.ei.bidan.AllConstants.Immunizations.PENTAVALENT_3;
import static org.ei.bidan.AllConstants.SPACE;
import static org.ei.bidan.domain.TimelineEvent.forChildBirthInChildProfile;
import static org.ei.bidan.domain.TimelineEvent.forChildBirthInECProfile;
import static org.ei.bidan.domain.TimelineEvent.forChildBirthInMotherProfile;
import static org.ei.bidan.domain.TimelineEvent.forChildImmunization;
import static org.ei.bidan.domain.TimelineEvent.forChildPNCVisit;

public class AnakService {
    private AllKohort allKohort;
    private AnakRepository anakRepository;
    private IbuRepository ibuRepository;
    private AllTimelineEvents allTimelines;
    private ServiceProvidedService serviceProvidedService;
    private AllAlerts allAlerts;

    public AnakService(AllKohort allKohort, IbuRepository ibuRepository, AnakRepository anakRepository,
                       AllTimelineEvents allTimelineEvents, ServiceProvidedService serviceProvidedService, AllAlerts allAlerts) {
        this.allKohort = allKohort;
        this.anakRepository = anakRepository;
        this.ibuRepository = ibuRepository;
        this.allTimelines = allTimelineEvents;
        this.serviceProvidedService = serviceProvidedService;
        this.allAlerts = allAlerts;
    }

    public void register(FormSubmission submission) {

    }

    private boolean isDeliveryOutcomeStillBirth(FormSubmission submission) {
        return AllConstants.DeliveryOutcomeFields.STILL_BIRTH_VALUE
                .equalsIgnoreCase(submission.getFieldValue(AllConstants.DeliveryOutcomeFields.DELIVERY_OUTCOME));
    }

    private void closeIbu(String id) {
        Ibu ibu = allKohort.findIbu(id);
        ibu.setIsClosed(true);
        allKohort.updateIbu(ibu);
    }

    private boolean shouldCloseMother(String shouldCloseMother) {
        return isBlank(shouldCloseMother) || Boolean.parseBoolean(shouldCloseMother);
    }

    public void updateImmunizations(FormSubmission submission) {
        String immunizationDate = submission.getFieldValue(AllConstants.ChildImmunizationsFields.IMMUNIZATION_DATE);
        List<String> immunizationsGivenList = splitFieldValueBySpace(submission, AllConstants.ChildImmunizationsFields.IMMUNIZATIONS_GIVEN);
        List<String> previousImmunizationsList = splitFieldValueBySpace(submission, AllConstants.ChildImmunizationsFields.PREVIOUS_IMMUNIZATIONS_GIVEN);
        immunizationsGivenList.removeAll(previousImmunizationsList);
        for (String immunization : immunizationsGivenList) {
            allTimelines.add(forChildImmunization(submission.entityId(), immunization, immunizationDate));
            serviceProvidedService.add(ServiceProvided.forChildImmunization(submission.entityId(), immunization, immunizationDate));
            allAlerts.changeAlertStatusToInProcess(submission.entityId(), immunization);
        }
    }

    private ArrayList<String> splitFieldValueBySpace(FormSubmission submission, String fieldName) {
        return new ArrayList<String>(Arrays.asList(submission.getFieldValue(fieldName).split(SPACE)));
    }

    public void close(FormSubmission submission) {
        allKohort.closeAnak(submission.entityId());
    }

    public void updatePhotoPath(String entityId, String imagePath) {
        // childRepository.updatePhotoPath(entityId, imagePath);
    }

    public void updateIllnessStatus(FormSubmission submission) {
        String sickVisitDate = submission.getFieldValue(SICK_VISIT_DATE);
        String date = sickVisitDate != null ?
                sickVisitDate : submission.getFieldValue(REPORT_CHILD_DISEASE_DATE);
        serviceProvidedService.add(
                ServiceProvided.forChildIllnessVisit(submission.entityId(),
                        date,
                        createChildIllnessMap(submission))
        );
    }

    private Map<String, String> createChildIllnessMap(FormSubmission submission) {
        return EasyMap.create(CHILD_SIGNS, submission.getFieldValue(CHILD_SIGNS))
                .put(CHILD_SIGNS_OTHER, submission.getFieldValue(CHILD_SIGNS_OTHER))
                .put(SICK_VISIT_DATE, submission.getFieldValue(SICK_VISIT_DATE))
                .put(REPORT_CHILD_DISEASE, submission.getFieldValue(REPORT_CHILD_DISEASE))
                .put(REPORT_CHILD_DISEASE_OTHER, submission.getFieldValue(REPORT_CHILD_DISEASE_OTHER))
                .put(REPORT_CHILD_DISEASE_DATE, submission.getFieldValue(REPORT_CHILD_DISEASE_DATE))
                .put(REPORT_CHILD_DISEASE_PLACE, submission.getFieldValue(REPORT_CHILD_DISEASE_PLACE))
                .put(CHILD_REFERRAL, submission.getFieldValue(CHILD_REFERRAL))
                .map();
    }

    public void updateVitaminAProvided(FormSubmission submission) {
        serviceProvidedService.add(
                ServiceProvided.forVitaminAProvided(submission.entityId(),
                        submission.getFieldValue(AllConstants.VitaminAFields.VITAMIN_A_DATE),
                        submission.getFieldValue(AllConstants.VitaminAFields.VITAMIN_A_DOSE),
                        submission.getFieldValue(AllConstants.VitaminAFields.VITAMIN_A_PLACE)));
    }

}
