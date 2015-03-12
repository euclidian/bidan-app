package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.MotherService;

public class HBTestHandler implements FormSubmissionHandler {
    private final MotherService motherService;

    public HBTestHandler(MotherService motherService) {
        this.motherService = motherService;
    }

    @Override
    public void handle(FormSubmission submission) {
        motherService.hbTest(submission);
    }
}
