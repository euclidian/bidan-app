package org.ei.bidan.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.webkit.WebSettings;

import com.flurry.android.FlurryAgent;

import org.apache.commons.io.IOUtils;
import org.ei.bidan.Context;
import org.ei.bidan.R;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;

import static java.util.UUID.randomUUID;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.ei.bidan.AllConstants.ENTITY_ID_PARAM;
import static org.ei.bidan.AllConstants.FIELD_OVERRIDES_PARAM;
import static org.ei.bidan.AllConstants.FORM_NAME_PARAM;
import static org.ei.bidan.AllConstants.FORM_SUBMISSION_ROUTER;
import static org.ei.bidan.AllConstants.INSTANCE_ID_PARAM;
import static org.ei.bidan.AllConstants.REPOSITORY;
import static org.ei.bidan.AllConstants.ZIGGY_FILE_LOADER;
import static org.ei.bidan.util.Log.logError;

public abstract class SecuredFormActivity extends SecuredWebActivity {
    public static final String ANDROID_CONTEXT_FIELD = "androidContext";
    private String model;
    private String form;
    private String formName;
    private String entityId;
    private String fieldOverrides;

    public SecuredFormActivity() {
        super();
        shouldDismissProgressBarOnProgressComplete = true;
    }

    @Override
    protected void onInitialization() {
        try {
            getIntentData();
        } catch (IOException e) {
            logError(e.toString());
            finish();
        }
        webViewInitialization();
    }

    private void getIntentData() throws IOException {
        Intent intent = getIntent();
        formName = intent.getStringExtra(FORM_NAME_PARAM);
        entityId = intent.getStringExtra(ENTITY_ID_PARAM);
        fieldOverrides = intent.getStringExtra(FIELD_OVERRIDES_PARAM);
        model = IOUtils.toString(getAssets().open("www/form/" + formName + "/model.xml"));
        form = IOUtils.toString(getAssets().open("www/form/" + formName + "/form.xml"));
    }

    private void webViewInitialization() {
        WebSettings webViewSettings = webView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setDatabaseEnabled(true);
        webViewSettings.setDomStorageEnabled(true);
        webView.addJavascriptInterface(new FormWebInterface(model, form, this), ANDROID_CONTEXT_FIELD);
        webView.addJavascriptInterface(Context.getInstance().formDataRepository(), REPOSITORY);
        webView.addJavascriptInterface(Context.getInstance().ziggyFileLoader(), ZIGGY_FILE_LOADER);
        webView.addJavascriptInterface(Context.getInstance().formSubmissionRouter(), FORM_SUBMISSION_ROUTER);
        String encodedFieldOverrides = null;
        try {
            if (isNotBlank(this.fieldOverrides)) {
                encodedFieldOverrides = URLEncoder.encode(this.fieldOverrides, "utf-8");
            }
        } catch (Exception e) {
            logError(MessageFormat.format("Cannot encode field overrides: {0} due to : {1}", fieldOverrides, e));
        }
        webView.loadUrl(MessageFormat.format("file:///android_asset/www/enketo/template.html?{0}={1}&{2}={3}&{4}={5}&{6}={7}&touch=true",
                FORM_NAME_PARAM, formName,
                ENTITY_ID_PARAM, entityId,
                INSTANCE_ID_PARAM, randomUUID(),
                FIELD_OVERRIDES_PARAM, encodedFieldOverrides));

    }

    @Override
    protected void reportException(String message) {

    }

    @Override
    public void onBackPressed() {
        FlurryAgent.logEvent("on_back_button_pressed");
        new AlertDialog.Builder(this)
                .setMessage(R.string.form_back_confirm_dialog_message)
                .setTitle(R.string.form_back_confirm_dialog_title)
                .setCancelable(false)
                .setPositiveButton(R.string.yes_button_label,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                goBack();
                            }
                        })
                .setNegativeButton(R.string.no_button_label,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                FlurryAgent.logEvent("canceled_back_to_dashboard");
                            }
                        })
                .show();
    }

    private void goBack() {
        FlurryAgent.logEvent("back_to_dashboard");
        super.onBackPressed();
    }
}
