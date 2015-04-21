package org.ei.bidan.bidan.service;

import org.ei.bidan.AllConstants;
import org.ei.bidan.bidan.repository.AllKartuIbus;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.repository.AllTimelineEvents;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by Dimas Ciputra on 2/16/15.
 */
public class KartuIbuService {
    private final AllKartuIbus allKartuIbus;
    private final AllTimelineEvents allTimelineEvents;
    private final AllKohort allKohort;

    public KartuIbuService(AllKartuIbus allKartuIbus, AllTimelineEvents allTimelineEvents, AllKohort allKohort) {
        this.allKartuIbus = allKartuIbus;
        this.allTimelineEvents = allTimelineEvents;
        this.allKohort = allKohort;
    }

    public void register(FormSubmission submission) {
        if (isNotBlank(submission.getFieldValue(AllConstants.CommonFormFields.SUBMISSION_DATE))) {
            // TODO : add to timeline event repository
            // allTimelineEvents.add(TimelineEvent.forECRegistered(submission.entityId(), submission.getFieldValue(AllConstants.CommonFormFields.SUBMISSION_DATE)));
        }
    }

    public void closeKartuIbu(FormSubmission formSubmission) {
        allKartuIbus.close(formSubmission.entityId());
        allKohort.closeAllIbuForKartuIbu(formSubmission.entityId());
    }
}
