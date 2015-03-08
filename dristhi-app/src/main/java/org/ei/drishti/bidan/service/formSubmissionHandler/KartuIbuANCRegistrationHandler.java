package org.ei.drishti.bidan.service.formSubmissionHandler;

import org.ei.drishti.bidan.service.IbuService;
import org.ei.drishti.domain.form.FormSubmission;
import org.ei.drishti.service.formSubmissionHandler.FormSubmissionHandler;

/**
 * Created by Dimas Ciputra on 3/8/15.
 */
public class KartuIbuANCRegistrationHandler implements FormSubmissionHandler {

    private IbuService ibuService;

    public KartuIbuANCRegistrationHandler(IbuService ibuService) {
        this.ibuService = ibuService;
    }

    @Override
    public void handle(FormSubmission submission) {
        ibuService.registerANC(submission);
    }
}
