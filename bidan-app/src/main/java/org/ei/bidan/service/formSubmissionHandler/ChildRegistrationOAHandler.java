package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.ChildService;

public class ChildRegistrationOAHandler implements FormSubmissionHandler {
    private final ChildService childService;

    public ChildRegistrationOAHandler(ChildService childService) {
        this.childService = childService;
    }

    @Override
    public void handle(FormSubmission submission) {
        childService.registerForOA(submission);
    }
}
