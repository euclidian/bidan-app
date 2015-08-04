package org.ei.bidan.view.controller;

import android.webkit.JavascriptInterface;

import org.ei.bidan.view.activity.SecuredActivity;

public class FormController {
    private SecuredActivity activity;

    public FormController(SecuredActivity activity) {
        this.activity = activity;
    }

    @JavascriptInterface
    public void startFormActivity(String formName, String entityId, String metaData) {
        activity.startFormActivity(formName, entityId, metaData);
    }

    @JavascriptInterface
    public void startMicroFormActivity(String formName, String entityId, String metaData) {
        activity.startMicroFormActivity(formName, entityId, metaData);
    }
}
