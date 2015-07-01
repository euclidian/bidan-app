package org.ei.bidan.view.controller;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.flurry.android.FlurryAgent;

import org.ei.bidan.bidan.view.activity.NativeKBSmartRegisterActivity;
import org.ei.bidan.bidan.view.activity.NativeKIANCSmartRegisterActivity;
import org.ei.bidan.bidan.view.activity.NativeKIAnakSmartRegisterActivity;
import org.ei.bidan.bidan.view.activity.NativeKIPNCSmartRegisterActivity;
import org.ei.bidan.bidan.view.activity.NativeReportingActivity;
import org.ei.bidan.view.activity.NativeANCSmartRegisterActivity;
import org.ei.bidan.view.activity.NativeChildSmartRegisterActivity;
import org.ei.bidan.view.activity.NativeECSmartRegisterActivity;
import org.ei.bidan.view.activity.NativePNCSmartRegisterActivity;
import org.ei.bidan.view.activity.VideosActivity;
import org.ei.bidan.bidan.view.activity.NativeKISmartRegisterActivity;
import org.ei.bidan.view.activity.*;

import static org.ei.bidan.view.controller.ProfileNavigationController.*;

public class NavigationController {
    private Activity activity;
    private ANMController anmController;

    public NavigationController(Activity activity, ANMController anmController) {
        this.activity = activity;
        this.anmController = anmController;
    }

    @JavascriptInterface
    public void startReports() {
        FlurryAgent.logEvent("start_reports");
        activity.startActivity(new Intent(activity, NativeReportingActivity.class));
    }

    @JavascriptInterface
    public void startVideos() {
        FlurryAgent.logEvent("start_videos");
        activity.startActivity(new Intent(activity, VideosActivity.class));
    }

    @JavascriptInterface
    public void startECSmartRegistry() {
        activity.startActivity(new Intent(activity, NativeECSmartRegisterActivity.class));
    }

    @JavascriptInterface
    public void startFPSmartRegistry() {
        activity.startActivity(new Intent(activity, NativeFPSmartRegisterActivity.class));
    }

    @JavascriptInterface
    public void startANCSmartRegistry() {
        activity.startActivity(new Intent(activity, NativeANCSmartRegisterActivity.class));
    }

    @JavascriptInterface
    public void startPNCSmartRegistry() {
        activity.startActivity(new Intent(activity, NativePNCSmartRegisterActivity.class));
    }

    @JavascriptInterface
    public void startChildSmartRegistry() {
        activity.startActivity(new Intent(activity, NativeChildSmartRegisterActivity.class));
    }

    @JavascriptInterface
    public void startKartuIbuRegistry() {
        activity.startActivity(new Intent(activity, NativeKISmartRegisterActivity.class));
    }

    @JavascriptInterface
    public void startKartuIbuANCRegistry() {
        activity.startActivity(new Intent(activity, NativeKIANCSmartRegisterActivity.class));
    }

    @JavascriptInterface
    public void startKartuIbuPNCRegistry() {
        activity.startActivity(new Intent(activity, NativeKIPNCSmartRegisterActivity.class));
    }

    @JavascriptInterface
    public void startAnakBayiRegistry() {
        activity.startActivity(new Intent(activity, NativeKIAnakSmartRegisterActivity.class));
    }

    @JavascriptInterface
    public void startKBRegistry() {
        activity.startActivity(new Intent(activity, NativeKBSmartRegisterActivity.class));
    }

    @JavascriptInterface
    public String get() {
        return anmController.get();
    }

    @JavascriptInterface
    public void goBack() {
        activity.finish();
    }

    @JavascriptInterface
    public void startEC(String entityId) {
        navigateToECProfile(activity, entityId);
    }

    @JavascriptInterface
    public void startKI(String entityId) {
        navigateToKIProfile(activity, entityId);
    }

    @JavascriptInterface
    public void startAnakDetail(String entityId) {
        navigateToAnakProfile(activity, entityId);
    }

    @JavascriptInterface
    public void startANC(String entityId) {
        navigateToANCProfile(activity, entityId);
    }

    @JavascriptInterface
    public void startPNC(String entityId) {
        navigateToPNCProfile(activity, entityId);
    }

    @JavascriptInterface
    public void startChild(String entityId) {
        navigateToChildProfile(activity, entityId);
    }

}
