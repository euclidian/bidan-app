package org.ei.drishti.bidan.service;

import org.ei.drishti.bidan.repository.AllKartuIbus;
import org.ei.drishti.domain.form.FormSubmission;
import org.ei.drishti.repository.AllTimelineEvents;
import org.ei.drishti.service.ServiceProvidedService;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class IbuService {
    public static final String IBU_ID = "ibuId";
    public AllKartuIbus allKartuIbus;
    public AllTimelineEvents allTimelineEvents;
    private ServiceProvidedService serviceProvidedService;
    public static String submissionDate = "submissionDate";

    public IbuService(AllKartuIbus allKartuIbus, AllTimelineEvents allTimelineEvents, ServiceProvidedService serviceProvidedService) {
        this.allKartuIbus = allKartuIbus;
        this.allTimelineEvents = allTimelineEvents;
        this.serviceProvidedService = serviceProvidedService;
    }

    public void registerANC(FormSubmission submission) {
        addTimelineEventsForIbuRegistration(submission);
    }

    public void ancVisit(FormSubmission submission) {}

    public void close(FormSubmission submission) {}

    public void close(String entityId, String reason) {}

    public void ttProvided(FormSubmission submission) {}

    public void hbTest(FormSubmission submission) {}

    private void addTimelineEventsForIbuRegistration(FormSubmission submission) {

    }

}
