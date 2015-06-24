package org.ei.bidan.view.activity;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.flurry.android.FlurryAgent;

import org.ei.bidan.AllConstants;
import org.ei.bidan.Context;
import org.ei.bidan.sync.DrishtiSyncScheduler;

import main.java.com.mindscapehq.android.raygun4android.RaygunClient;
import main.java.com.mindscapehq.android.raygun4android.messages.RaygunUserInfo;

import java.util.Locale;

import static org.ei.bidan.util.Log.logInfo;

public class DrishtiApplication extends Application {
    private Locale locale = null;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        // Init Raygun
        RaygunClient.Init(getApplicationContext());
        RaygunClient.AttachExceptionHandler();

        // Configure Flurry
        FlurryAgent.setLogEnabled(true);
        FlurryAgent.setLogEvents(true);
        FlurryAgent.setLogLevel(Log.VERBOSE);

        // init Flurry
        FlurryAgent.init(this, AllConstants.FLURRY_KEY);

        context = Context.getInstance();
        context.updateApplicationContext(getApplicationContext());
        applyUserLanguagePreference();
        cleanUpSyncState();
    }

    private void cleanUpSyncState() {
        DrishtiSyncScheduler.stop(getApplicationContext());
        context.allSharedPreferences().saveIsSyncInProgress(false);
    }

    @Override
    public void onTerminate() {
        logInfo("Application is terminating. Stopping Dristhi Sync scheduler and resetting isSyncInProgress setting.");
        cleanUpSyncState();
    }

    private void applyUserLanguagePreference() {
        Configuration config = getBaseContext().getResources().getConfiguration();

        String lang = context.allSharedPreferences().fetchLanguagePreference();
        if (!"".equals(lang) && !config.locale.getLanguage().equals(lang)) {
            locale = new Locale(lang);
            updateConfiguration(config);
        }
    }

    private void updateConfiguration(Configuration config) {
        config.locale = locale;
        Locale.setDefault(locale);
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}