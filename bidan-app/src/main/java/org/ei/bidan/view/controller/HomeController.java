package org.ei.bidan.view.controller;

import android.webkit.JavascriptInterface;

import org.ei.bidan.util.Log;

public class HomeController {
    private UpdateController updateController;

    public HomeController(UpdateController updateController) {
        this.updateController = updateController;
    }

    @JavascriptInterface
    public void pageHasFinishedLoading() {
        updateController.pageHasFinishedLoading();
    }

    @JavascriptInterface
    public void log(String text) {
        Log.logInfo(text);
    }
}

