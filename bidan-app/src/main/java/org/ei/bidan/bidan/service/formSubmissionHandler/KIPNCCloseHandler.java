package org.ei.bidan.bidan.service.formSubmissionHandler;

import org.ei.bidan.bidan.service.IbuService;
import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.formSubmissionHandler.FormSubmissionHandler;

public class KIPNCCloseHandler implements FormSubmissionHandler {
    private final IbuService motherService;

    public KIPNCCloseHandler(IbuService motherService) {
        this.motherService = motherService;
    }

    @Override
    public void handle(FormSubmission submission) {
        motherService.close(submission);
    }
}
