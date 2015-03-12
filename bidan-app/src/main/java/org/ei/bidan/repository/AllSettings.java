package org.ei.bidan.repository;

import org.ei.bidan.Context;

public class AllSettings {
    public static final String APPLIED_VILLAGE_FILTER_SETTING_KEY = "appliedVillageFilter";
    public static final String PREVIOUS_FETCH_INDEX_SETTING_KEY = "previousFetchIndex";
    public static final String PREVIOUS_FORM_SYNC_INDEX_SETTING_KEY = "previousFormSyncIndex";
    private static final String ANM_PASSWORD_PREFERENCE_KEY = "anmPassword";
    private static final String ANM_LOCATION = "anmLocation";
    private static final String BIDAN_PASSWORD_PREFERENCE_KEY = "bidanPassword";

    private AllSharedPreferences preferences;
    private SettingsRepository settingsRepository;

    public AllSettings(AllSharedPreferences preferences, SettingsRepository settingsRepository) {
        this.preferences = preferences;
        this.settingsRepository = settingsRepository;
    }

    public void registerANM(String userName, String password) {
        preferences.updateANMUserName(userName);
        settingsRepository.updateSetting(ANM_PASSWORD_PREFERENCE_KEY, password);
    }

    public void registerBidan(String userName, String password) {
        preferences.updateBidanUserName(userName);
        settingsRepository.updateSetting(BIDAN_PASSWORD_PREFERENCE_KEY, password);
    }

    public void savePreviousFetchIndex(String value) {
        settingsRepository.updateSetting(PREVIOUS_FETCH_INDEX_SETTING_KEY, value);
    }

    public String fetchPreviousFetchIndex() {
        return settingsRepository.querySetting(PREVIOUS_FETCH_INDEX_SETTING_KEY, "0");
    }

    public void saveAppliedVillageFilter(String village) {
        settingsRepository.updateSetting(APPLIED_VILLAGE_FILTER_SETTING_KEY, village);
    }

    public String appliedVillageFilter(String defaultFilterValue) {
        return settingsRepository.querySetting(APPLIED_VILLAGE_FILTER_SETTING_KEY, defaultFilterValue);
    }

    public String fetchPassword() {
        String appName = Context.getInstance().configuration().getAppName();
        return appName.equals("BIDAN") ? fetchBidanPassword() : fetchANMPassword();
    }

    public String fetchANMPassword() {
        return settingsRepository.querySetting(ANM_PASSWORD_PREFERENCE_KEY, "");
    }

    public String fetchBidanPassword() {
        return settingsRepository.querySetting(BIDAN_PASSWORD_PREFERENCE_KEY, "");
    }

    public String fetchPreviousFormSyncIndex() {
        return settingsRepository.querySetting(PREVIOUS_FORM_SYNC_INDEX_SETTING_KEY, "0");
    }

    public void savePreviousFormSyncIndex(String value) {
        settingsRepository.updateSetting(PREVIOUS_FORM_SYNC_INDEX_SETTING_KEY, value);
    }

    public void saveANMLocation(String anmLocation) {
        settingsRepository.updateSetting(ANM_LOCATION, anmLocation);
    }

    public String fetchANMLocation() {
        return settingsRepository.querySetting(ANM_LOCATION, "");
    }
}
