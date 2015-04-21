package org.ei.bidan.bidan.service.formSubmissionHandler;

import org.ei.bidan.bidan.service.IbuService;
import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.MotherService;
import org.ei.bidan.service.formSubmissionHandler.FormSubmissionHandler;

public class KIANCCloseHandler implements FormSubmissionHandler {
    private final IbuService motherService;

    public KIANCCloseHandler(IbuService motherService) {
        this.motherService = motherService;
    }

    @Override
    public void handle(FormSubmission submission) {
        motherService.close(submission);
    }
}
