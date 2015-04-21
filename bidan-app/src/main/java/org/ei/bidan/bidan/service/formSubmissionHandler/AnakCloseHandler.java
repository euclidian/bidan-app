package org.ei.bidan.bidan.service.formSubmissionHandler;

import org.ei.bidan.bidan.service.AnakService;
import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.ChildService;
import org.ei.bidan.service.formSubmissionHandler.FormSubmissionHandler;

public class AnakCloseHandler implements FormSubmissionHandler {
    private final AnakService childService;

    public AnakCloseHandler(AnakService childService) {
        this.childService = childService;
    }

    @Override
    public void handle(FormSubmission submission) {
        childService.close(submission);
    }
}
