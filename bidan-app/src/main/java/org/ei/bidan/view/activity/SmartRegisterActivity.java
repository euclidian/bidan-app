package org.ei.bidan.view.activity;

import org.ei.bidan.AllConstants;
import org.ei.bidan.Context;
import org.ei.bidan.event.CapturedPhotoInformation;
import org.ei.bidan.event.Listener;

import static org.ei.bidan.AllConstants.*;
import static org.ei.bidan.event.Event.ON_PHOTO_CAPTURED;
import static org.ei.bidan.util.Log.logInfo;

public abstract class SmartRegisterActivity extends SecuredWebActivity {
    protected Listener<CapturedPhotoInformation> photoCaptureListener;

    @Override
    protected void onInitialization() {
        onSmartRegisterInitialization();

        photoCaptureListener = new Listener<CapturedPhotoInformation>() {
            @Override
            public void onEvent(CapturedPhotoInformation data) {
                if (webView != null) {
                    webView.loadUrl("javascript:pageView.reloadPhoto('" + data.entityId() + "', '" + data.photoPath() + "')");
                }
            }
        };
        ON_PHOTO_CAPTURED.addListener(photoCaptureListener);
    }

    protected abstract void onSmartRegisterInitialization();

    @Override
    protected void onResumption() {
        webView.loadUrl("javascript:if(window.pageView) {window.pageView.reload();}");
    }
}
