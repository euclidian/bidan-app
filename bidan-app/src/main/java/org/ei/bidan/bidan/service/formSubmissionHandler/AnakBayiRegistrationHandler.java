package org.ei.bidan.bidan.service.formSubmissionHandler;

import org.ei.bidan.bidan.service.AnakService;
import org.ei.bidan.bidan.service.IbuService;
import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.formSubmissionHandler.FormSubmissionHandler;

/**
 * Created by Dimas Ciputra on 3/8/15.
 */
public class AnakBayiRegistrationHandler implements FormSubmissionHandler {

    private AnakService anakService;

    public AnakBayiRegistrationHandler(AnakService anakService) {
        this.anakService = anakService;
    }

    @Override
    public void handle(FormSubmission submission) {
        anakService.register(submission);
    }
}
