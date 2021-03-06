package org.ei.bidan.service;

import org.ei.bidan.repository.FormDataRepository;

public class PendingFormSubmissionService {

    private FormDataRepository formDataRepository;

    public PendingFormSubmissionService(FormDataRepository formDataRepository) {
        this.formDataRepository = formDataRepository;
    }


    public long pendingFormSubmissionCount() {
        return formDataRepository.getPendingFormSubmissionsCount();
    }
}