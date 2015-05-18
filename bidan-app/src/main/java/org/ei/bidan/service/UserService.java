package org.ei.bidan.service;

import org.ei.bidan.domain.LoginResponse;
import org.ei.bidan.util.Session;
import org.ei.bidan.DristhiConfiguration;
import org.ei.bidan.repository.AllSettings;
import org.ei.bidan.repository.AllSharedPreferences;
import org.ei.bidan.repository.Repository;
import org.ei.bidan.sync.SaveANMLocationTask;

import static org.ei.bidan.AllConstants.*;
import static org.ei.bidan.event.Event.ON_LOGOUT;

public class UserService {
    private final Repository repository;
    private final AllSettings allSettings;
    private final AllSharedPreferences allSharedPreferences;
    private HTTPAgent httpAgent;
    private Session session;
    private DristhiConfiguration configuration;
    private SaveANMLocationTask saveANMLocationTask;

    public UserService(Repository repository, AllSettings allSettings, AllSharedPreferences allSharedPreferences, HTTPAgent httpAgent, Session session,
                       DristhiConfiguration configuration, SaveANMLocationTask saveANMLocationTask) {
        this.repository = repository;
        this.allSettings = allSettings;
        this.allSharedPreferences = allSharedPreferences;
        this.httpAgent = httpAgent;
        this.session = session;
        this.configuration = configuration;
        this.saveANMLocationTask = saveANMLocationTask;
    }

    public boolean isValidLocalLogin(String userName, String password) {
        return isUserRegistered(userName) && repository.canUseThisPassword(password);
    }

    public LoginResponse isValidRemoteLogin(String userName, String password) {
        String requestURL = configuration.dristhiBaseURL() + AUTHENTICATE_USER_URL_PATH + userName;
        return httpAgent.urlCanBeAccessWithGivenCredentials(requestURL, userName, password);
    }

    private void loginWith(String userName, String password) {
        setupContextForLogin(userName, password);
        changeUserRegistration(userName, password);
    }

    public void localLogin(String userName, String password) {
        loginWith(userName, password);
    }

    public void remoteLogin(String userName, String password, String anmLocation) {
        loginWith(userName, password);
        saveANMLocationTask.save(anmLocation);
    }

    public boolean hasARegisteredUser() {
        return !isUserRegistered("");
    }

    public void logout() {
        logoutSession();
        changeUserRegistration("", "");
        allSettings.savePreviousFetchIndex("0");
        repository.deleteRepository();
    }

    public void logoutSession() {
        session().expire();
        ON_LOGOUT.notifyListeners(true);
    }

    public boolean hasSessionExpired() {
        return session().hasExpired();
    }

    protected void setupContextForLogin(String userName, String password) {
        session().start(session().lengthInMilliseconds());
        session().setPassword(password);
    }

    protected Session session() {
        return session;
    }

    public String switchLanguagePreference() {
        String preferredLocale = allSharedPreferences.fetchLanguagePreference();
        if (ENGLISH_LOCALE.equals(preferredLocale)) {
            allSharedPreferences.saveLanguagePreference(INDONESIA_LOCALE);
            return INDONESIA_LANGUAGE;
        } else {
            allSharedPreferences.saveLanguagePreference(ENGLISH_LOCALE);
            return ENGLISH_LANGUAGE;
        }
    }

    public void changeUserRegistration(String userName, String password) {
        if(this.configuration.getAppName().equals("BIDAN")) {
            allSettings.registerBidan(userName, password);
        } else {
            allSettings.registerANM(userName, password);
        }
    }

    private boolean isUserRegistered(String userName) {
        return allSharedPreferences.fetchRegistered().equals(userName);
    }
}
