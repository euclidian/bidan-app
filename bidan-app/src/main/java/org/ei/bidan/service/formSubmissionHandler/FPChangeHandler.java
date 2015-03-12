package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.EligibleCoupleService;

public class FPChangeHandler implements FormSubmissionHandler {
    private EligibleCoupleService ecService;

    public FPChangeHandler(EligibleCoupleService ecService) {
        this.ecService = ecService;
    }

    @Override
    public void handle(FormSubmission submission) {
        ecService.fpChange(submission);
    }
}
