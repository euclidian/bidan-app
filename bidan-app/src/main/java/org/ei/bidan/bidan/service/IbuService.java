package org.ei.bidan.bidan.service;

import org.ei.bidan.AllConstants;
import org.ei.bidan.bidan.domain.Ibu;
import org.ei.bidan.bidan.repository.AllKartuIbus;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.repository.AllTimelineEvents;
import org.ei.bidan.service.ServiceProvidedService;

import static org.ei.bidan.AllConstants.ANCCloseFields.CLOSE_REASON_FIELD_NAME;
import static org.ei.bidan.AllConstants.ANCCloseFields.DEATH_OF_WOMAN_FIELD_VALUE;
import static org.ei.bidan.AllConstants.PNCCloseFields.DEATH_OF_MOTHER_FIELD_VALUE;
import static org.ei.bidan.AllConstants.PNCCloseFields.WRONG_ENTRY;
import static org.ei.bidan.util.Log.logWarn;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class IbuService {
    public static final String IBU_ID = "ibuId";
    public AllKartuIbus allKartuIbus;
    public AllTimelineEvents allTimelineEvents;
    private ServiceProvidedService serviceProvidedService;
    public AllKohort allKohort;
    public static String submissionDate = "submissionDate";

    public IbuService(AllKartuIbus allKartuIbus, AllTimelineEvents allTimelineEvents, ServiceProvidedService serviceProvidedService,
                      AllKohort allKohort) {
        this.allKartuIbus = allKartuIbus;
        this.allTimelineEvents = allTimelineEvents;
        this.serviceProvidedService = serviceProvidedService;
        this.allKohort = allKohort;
    }

    public void registerANC(FormSubmission submission) {
        addTimelineEventsForIbuRegistration(submission);
    }

    public void ancVisit(FormSubmission submission) {}

    public void close(FormSubmission submission) {
        close(submission.entityId(), submission.getFieldValue(CLOSE_REASON_FIELD_NAME));
    }

    public void close(String entityId, String reason) {
        Ibu mother = allKohort.findIbuWithOpenStatus(entityId);
        if (mother == null) {
            logWarn("Tried to close non-existent mother. Entity ID: " + entityId);
            return;
        }

        if(WRONG_ENTRY.equalsIgnoreCase(reason) && mother.isPNC()) {
            allKohort.switchIbuToANC(entityId);
            return;
        }

        allKohort.closeIbu(entityId);
        if (DEATH_OF_WOMAN_FIELD_VALUE.equalsIgnoreCase(reason)
                || DEATH_OF_MOTHER_FIELD_VALUE.equalsIgnoreCase(reason)
                || AllConstants.ANCCloseFields.PERMANENT_RELOCATION_FIELD_VALUE.equalsIgnoreCase(reason)
                || AllConstants.PNCCloseFields.PERMANENT_RELOCATION_FIELD_VALUE.equalsIgnoreCase(reason)) {
            allKartuIbus.close(mother.getKartuIbuId());
        }
    }

    public void ttProvided(FormSubmission submission) {}

    public void hbTest(FormSubmission submission) {}

    private void addTimelineEventsForIbuRegistration(FormSubmission submission) {

    }

}
