package org.ei.bidan.repository;

import android.content.SharedPreferences;

import org.ei.bidan.Context;

import static org.ei.bidan.AllConstants.*;

public class AllSharedPreferences {
    public static final String ANM_IDENTIFIER_PREFERENCE_KEY = "anmIdentifier";
    public static final String BIDAN_IDENTIFIER_PREFERENCE_KEY = "bidanIdentifier";

    private SharedPreferences preferences;

    public AllSharedPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void updateANMUserName(String userName) {
        preferences.edit().putString(ANM_IDENTIFIER_PREFERENCE_KEY, userName).commit();
    }

    public void updateBidanUserName(String userName) {
        preferences.edit().putString(BIDAN_IDENTIFIER_PREFERENCE_KEY, userName).commit();
    }

    public String fetchRegistered() {
        String appName = Context.getInstance().configuration().getAppName();
        return appName.equals("BIDAN") ? fetchRegisteredBidan() : fetchRegisteredANM();
    }

    public String fetchRegisteredANM() {
        return preferences.getString(ANM_IDENTIFIER_PREFERENCE_KEY, "").trim();
    }

    public String fetchRegisteredBidan() {
        return preferences.getString(BIDAN_IDENTIFIER_PREFERENCE_KEY, "").trim();
    }

    public String fetchLanguagePreference() {
        return preferences.getString(LANGUAGE_PREFERENCE_KEY, DEFAULT_LOCALE).trim();
    }

    public void saveLanguagePreference(String languagePreference) {
        preferences.edit().putString(LANGUAGE_PREFERENCE_KEY, languagePreference).commit();
    }

    public Boolean fetchIsSyncInProgress() {
        return preferences.getBoolean(IS_SYNC_IN_PROGRESS_PREFERENCE_KEY, false);
    }

    public void saveIsSyncInProgress(Boolean isSyncInProgress) {
        preferences.edit().putBoolean(IS_SYNC_IN_PROGRESS_PREFERENCE_KEY, isSyncInProgress).commit();
    }
}
