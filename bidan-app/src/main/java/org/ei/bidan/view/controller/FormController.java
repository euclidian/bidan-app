package org.ei.bidan.view.controller;

import org.ei.bidan.view.activity.SecuredActivity;

public class FormController {
    private SecuredActivity activity;

    public FormController(SecuredActivity activity) {
        this.activity = activity;
    }

    public void startFormActivity(String formName, String entityId, String metaData) {
        activity.startFormActivity(formName, entityId, metaData);
    }

    public void startMicroFormActivity(String formName, String entityId, String metaData) {
        activity.startMicroFormActivity(formName, entityId, metaData);
    }
}
