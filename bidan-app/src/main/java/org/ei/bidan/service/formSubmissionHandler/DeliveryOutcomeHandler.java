package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.ChildService;
import org.ei.bidan.service.MotherService;

public class DeliveryOutcomeHandler implements FormSubmissionHandler {
    private final MotherService motherService;
    private ChildService childService;

    public DeliveryOutcomeHandler(MotherService motherService, ChildService childService) {
        this.motherService = motherService;
        this.childService = childService;
    }

    @Override
    public void handle(FormSubmission submission) {
        motherService.deliveryOutcome(submission);
        childService.register(submission);
    }
}
