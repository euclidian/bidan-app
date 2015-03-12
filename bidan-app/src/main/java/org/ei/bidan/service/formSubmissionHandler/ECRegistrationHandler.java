package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.EligibleCoupleService;

public class ECRegistrationHandler implements FormSubmissionHandler {
    private EligibleCoupleService ecService;

    public ECRegistrationHandler(EligibleCoupleService ecService) {
        this.ecService = ecService;
    }

    @Override
    public void handle(FormSubmission submission) {
        ecService.register(submission);
    }
}
