package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.ChildService;

public class ChildCloseHandler implements FormSubmissionHandler {
    private final ChildService childService;

    public ChildCloseHandler(ChildService childService) {
        this.childService = childService;
    }

    @Override
    public void handle(FormSubmission submission) {
        childService.close(submission);
    }
}
