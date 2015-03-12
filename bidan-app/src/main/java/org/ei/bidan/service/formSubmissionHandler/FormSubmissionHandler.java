package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;

public interface FormSubmissionHandler {
    public void handle(FormSubmission submission);
}
