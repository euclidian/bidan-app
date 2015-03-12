package org.ei.bidan.view.activity;

import org.ei.bidan.view.controller.ECSmartRegisterController;

public class ECSmartRegisterActivity extends SmartRegisterActivity {
    @Override
    protected void onSmartRegisterInitialization() {
        webView.addJavascriptInterface(new ECSmartRegisterController(context.allEligibleCouples(),
                context.allBeneficiaries(), context.listCache(),
                context.ecClientsCache()), "context");
        webView.loadUrl("file:///android_asset/www/smart_registry/ec_register.html");
    }
}
