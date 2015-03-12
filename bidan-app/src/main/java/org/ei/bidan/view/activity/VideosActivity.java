package org.ei.bidan.view.activity;

import org.ei.bidan.view.controller.VideosController;

public class VideosActivity extends SecuredWebActivity {
    @Override
    protected void onInitialization() {
        webView.addJavascriptInterface(new VideosController(this), "context");
        webView.loadUrl("file:///android_asset/www/smart_registry/videos.html");
    }
}
