package org.ei.bidan.view.activity;

import org.ei.bidan.view.controller.ReportsController;

public class ReportsActivity extends SecuredWebActivity {
    @Override
    protected void onInitialization() {
        webView.addJavascriptInterface(new ReportsController(this), "context");
        webView.loadUrl("file:///android_asset/www/reports.html");
    }
}
