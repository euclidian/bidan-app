package org.ei.bidan.convertor;

import org.ei.bidan.domain.form.FormSubmission;
import org.ei.drishti.dto.form.FormSubmissionDTO;

import java.util.ArrayList;
import java.util.List;

import static org.ei.bidan.domain.SyncStatus.SYNCED;

public class FormSubmissionConvertor {
    public static List<FormSubmission> toDomain(List<FormSubmissionDTO> formSubmissionsDto) {
        List<FormSubmission> submissions = new ArrayList<FormSubmission>();
        for (FormSubmissionDTO formSubmission : formSubmissionsDto) {
            submissions.add(new FormSubmission(
                    formSubmission.instanceId(),
                    formSubmission.entityId(),
                    formSubmission.formName(),
                    formSubmission.instance(),
                    formSubmission.clientVersion(),
                    SYNCED,
                    formSubmission.formDataDefinitionVersion(),
                    formSubmission.serverVersion()
            ));
        }
        return submissions;
    }
}
