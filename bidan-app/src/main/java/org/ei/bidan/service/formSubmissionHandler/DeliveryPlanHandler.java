package org.ei.bidan.service.formSubmissionHandler;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.bidan.service.MotherService;

public class DeliveryPlanHandler implements FormSubmissionHandler {
    private final MotherService motherService;

    public DeliveryPlanHandler(MotherService motherService) {
        this.motherService = motherService;
    }

    @Override
    public void handle(FormSubmission submission) {
        motherService.deliveryPlan(submission);
    }
}
