package org.ei.bidan.view.activity;

import android.os.Bundle;
import org.ei.bidan.domain.Report;
import org.ei.bidan.view.controller.ReportIndicatorDetailViewController;

import static org.ei.bidan.AllConstants.*;

public class ReportIndicatorDetailActivity extends SecuredWebActivity {

    @Override
    protected void onInitialization() {
        Bundle extras = getIntent().getExtras();
        Report indicatorDetails = (Report) extras.get(INDICATOR_DETAIL);
        String categoryDescription = extras.getString(CATEGORY_DESCRIPTION);

        webView.addJavascriptInterface(new ReportIndicatorDetailViewController(this, indicatorDetails, categoryDescription), "context");
        webView.loadUrl("file:///android_asset/www/report_indicator_detail.html");
    }
}
