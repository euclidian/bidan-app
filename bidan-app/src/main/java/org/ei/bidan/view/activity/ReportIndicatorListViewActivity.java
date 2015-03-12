package org.ei.bidan.view.activity;

import org.ei.bidan.view.controller.ReportIndicatorListViewController;

import static org.ei.bidan.AllConstants.REPORT_CATEGORY;

public class ReportIndicatorListViewActivity extends SecuredWebActivity {
    @Override
    protected void onInitialization() {
        String category = getIntent().getExtras().getString(REPORT_CATEGORY);

        webView.addJavascriptInterface(new ReportIndicatorListViewController(this, context.allReports(), category), "context");
        webView.loadUrl("file:///android_asset/www/report_indicator_list.html");
    }
}
