package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.EligibleCoupleService;

public class ECCloseHandler implements FormSubmissionHandler {
    private EligibleCoupleService ecService;

    public ECCloseHandler(EligibleCoupleService ecService) {
        this.ecService = ecService;
    }

    @Override
    public void handle(FormSubmission submission) {
        ecService.closeEligibleCouple(submission);
    }
}
