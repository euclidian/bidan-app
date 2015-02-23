package org.ei.drishti.bidan.service;

import org.ei.drishti.AllConstants;
import org.ei.drishti.bidan.repository.AllKartuIbus;
import org.ei.drishti.domain.form.FormSubmission;
import org.ei.drishti.repository.AllTimelineEvents;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by Dimas Ciputra on 2/16/15.
 */
public class KartuIbuService {
    private final AllKartuIbus allKartuIbus;
    private final AllTimelineEvents allTimelineEvents;

    public KartuIbuService(AllKartuIbus allKartuIbus, AllTimelineEvents allTimelineEvents) {
        this.allKartuIbus = allKartuIbus;
        this.allTimelineEvents = allTimelineEvents;
    }

    public void register(FormSubmission submission) {
        if (isNotBlank(submission.getFieldValue(AllConstants.CommonFormFields.SUBMISSION_DATE))) {
            // allTimelineEvents.add(TimelineEvent.forECRegistered(submission.entityId(), submission.getFieldValue(AllConstants.CommonFormFields.SUBMISSION_DATE)));
        }
    }

    public void closeKartuIbu(FormSubmission formSubmission) {
        allKartuIbus.close(formSubmission.entityId());
    }
}
