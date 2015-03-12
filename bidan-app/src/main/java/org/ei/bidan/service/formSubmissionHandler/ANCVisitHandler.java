package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.MotherService;

public class ANCVisitHandler implements FormSubmissionHandler {
    private MotherService motherService;

    public ANCVisitHandler(MotherService motherService) {
        this.motherService = motherService;
    }

    @Override
    public void handle(FormSubmission submission) {
        motherService.ancVisit(submission);
    }
}
