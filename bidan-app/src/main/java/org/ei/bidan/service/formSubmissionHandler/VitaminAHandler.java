package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.ChildService;

public class VitaminAHandler implements FormSubmissionHandler {
    private final ChildService childService;

    public VitaminAHandler(ChildService childService) {
        this.childService = childService;
    }

    @Override
    public void handle(FormSubmission submission) {
        childService.updateVitaminAProvided(submission);
    }
}
