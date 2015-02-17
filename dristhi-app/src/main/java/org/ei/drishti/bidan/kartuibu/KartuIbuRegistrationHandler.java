package org.ei.drishti.bidan.kartuibu;

import org.ei.drishti.domain.form.FormSubmission;
import org.ei.drishti.service.formSubmissionHandler.FormSubmissionHandler;

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
