package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.MotherService;

public class ANCRegistrationOAHandler implements FormSubmissionHandler {
    private MotherService motherService;

    public ANCRegistrationOAHandler(MotherService motherService) {
        this.motherService = motherService;
    }

    @Override
    public void handle(FormSubmission submission) {
        motherService.registerOutOfAreaANC(submission);
    }
}
