package org.ei.bidan.bidan.service.formSubmissionHandler;

import org.ei.bidan.bidan.service.KartuIbuService;
import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.formSubmissionHandler.FormSubmissionHandler;

/**
 * Created by Dimas Ciputra on 2/16/15.
 */
public class KartuIbuRegistrationHandler implements FormSubmissionHandler{
    private KartuIbuService kartuIbuService;

    public KartuIbuRegistrationHandler(KartuIbuService kartuIbuService) {
        this.kartuIbuService = kartuIbuService;
    }

    @Override
    public void handle(FormSubmission submission) {
        kartuIbuService.register(submission);
    }
}
