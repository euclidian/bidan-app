package org.ei.bidan.view.controller;

import android.app.Activity;
import android.content.Intent;

import org.ei.bidan.bidan.view.activity.NativeKIANCSmartRegisterActivity;
import org.ei.bidan.bidan.view.activity.NativeKIAnakSmartRegisterActivity;
import org.ei.bidan.bidan.view.activity.NativeKIPNCSmartRegisterActivity;
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

    public void startReports() {
        activity.startActivity(new Intent(activity, ReportsActivity.class));
    }

    public void startVideos() {
        activity.startActivity(new Intent(activity, VideosActivity.class));
    }

    public void startECSmartRegistry() {
        activity.startActivity(new Intent(activity, NativeECSmartRegisterActivity.class));
    }

    public void startFPSmartRegistry() {
        activity.startActivity(new Intent(activity, NativeFPSmartRegisterActivity.class));
    }

    public void startANCSmartRegistry() {
        activity.startActivity(new Intent(activity, NativeANCSmartRegisterActivity.class));
    }

    public void startPNCSmartRegistry() {
        activity.startActivity(new Intent(activity, NativePNCSmartRegisterActivity.class));
    }

    public void startChildSmartRegistry() {
        activity.startActivity(new Intent(activity, NativeChildSmartRegisterActivity.class));
    }

    public void startKartuIbuRegistry() {
        activity.startActivity(new Intent(activity, NativeKISmartRegisterActivity.class));
    }

    public void startKartuIbuANCRegistry() {
        activity.startActivity(new Intent(activity, NativeKIANCSmartRegisterActivity.class));
    }

    public void startKartuIbuPNCRegistry() {
        activity.startActivity(new Intent(activity, NativeKIPNCSmartRegisterActivity.class));
    }

    public void startAnakBayiRegistry() {
        activity.startActivity(new Intent(activity, NativeKIAnakSmartRegisterActivity.class));
    }

    public String get() {
        return anmController.get();
    }

    public void goBack() {
        activity.finish();
    }

    public void startEC(String entityId) {
        navigateToECProfile(activity, entityId);
    }

    public void startANC(String entityId) {
        navigateToANCProfile(activity, entityId);
    }

    public void startPNC(String entityId) {
        navigateToPNCProfile(activity, entityId);
    }

    public void startChild(String entityId) {
        navigateToChildProfile(activity, entityId);
    }
}
