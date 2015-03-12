package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.EligibleCoupleService;

public class FPComplicationsHandler implements FormSubmissionHandler {
    private EligibleCoupleService ecService;

    public FPComplicationsHandler(EligibleCoupleService ecService) {
        this.ecService = ecService;
    }

    @Override
    public void handle(FormSubmission submission) {
        ecService.fpComplications(submission);
    }
}
