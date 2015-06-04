package org.ei.bidan.service;

import org.ei.bidan.Context;
import org.ei.bidan.repository.*;
import org.robolectric.RobolectricTestRunner;
import org.ei.bidan.DristhiConfiguration;
import org.ei.bidan.sync.SaveANMLocationTask;
import org.ei.bidan.util.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.ei.bidan.AllConstants.ENGLISH_LOCALE;
import static org.ei.bidan.AllConstants.INDONESIA_LANGUAGE;
import static org.ei.bidan.AllConstants.KANNADA_LOCALE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
@RunWith(RobolectricTestRunner.class)
public class UserServiceTest {
    @Mock
    private Repository repository;
    @Mock
    private AllSettings allSettings;
    @Mock
    private AllSharedPreferences allSharedPreferences;
    @Mock
    private AllAlerts allAlerts;
    @Mock
    private AllEligibleCouples allEligibleCouples;
    @Mock
    private Session session;
    @Mock
    private HTTPAgent httpAgent;
    @Mock
    private DristhiConfiguration configuration;
    @Mock
    private SaveANMLocationTask saveANMLocationTask;
    @Mock
    private Context context;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        userService = new UserService(repository, allSettings, allSharedPreferences, httpAgent, session, configuration, saveANMLocationTask);

    }

    @Test
    public void shouldUseHttpAgentToDoRemoteLoginCheck() {
        when(configuration.dristhiBaseURL()).thenReturn("http://dristhi_base_url");

        userService.isValidRemoteLogin("userX", "password Y");

        verify(httpAgent).urlCanBeAccessWithGivenCredentials("http://dristhi_base_url/anm-villages?anm-id=userX", "userX", "password Y");
    }

    @Test
    public void shouldSaveANMLocationWhenRemoteLoginIsSuccessful() {
        when(configuration.getAppName()).thenReturn("BIDAN");
        userService.remoteLogin("userX", "password Y", "anm location");

        verify(saveANMLocationTask).save("anm location");
    }

    @Test
    public void shouldConsiderALocalLoginValidWhenUsernameMatchesRegisteredUserAndPasswordMatchesTheOneInDB() {
        when(allSharedPreferences.fetchRegistered()).thenReturn("ANM X");
        when(repository.canUseThisPassword("password Z")).thenReturn(true);

        assertTrue(userService.isValidLocalLogin("ANM X", "password Z"));

        verify(allSharedPreferences).fetchRegistered();
        verify(repository).canUseThisPassword("password Z");
    }

    @Test
    public void shouldConsiderALocalLoginInvalidWhenRegisteredUserDoesNotMatch() {
        when(allSharedPreferences.fetchRegistered()).thenReturn("ANM X");

        assertFalse(userService.isValidLocalLogin("SOME OTHER ANM", "password"));

        verify(allSharedPreferences).fetchRegistered();
        verifyZeroInteractions(repository);
    }

    @Test
    public void shouldConsiderALocalLoginInvalidWhenRegisteredUserMatchesButNotThePassword() {
        when(configuration.getAppName()).thenReturn("BIDAN");
        when(allSharedPreferences.fetchRegisteredBidan()).thenReturn("ANM X");
        when(allSharedPreferences.fetchRegistered()).thenReturn("ANM X");
        when(repository.canUseThisPassword("password Z")).thenReturn(false);

        assertFalse(userService.isValidLocalLogin("ANM X", "password Z"));

        verify(allSharedPreferences).fetchRegistered();
        verify(repository).canUseThisPassword("password Z");
    }

    @Test
    public void shouldRegisterANewUser() {
        when(configuration.getAppName()).thenReturn("BIDAN");
        userService.remoteLogin("user X", "password Y", "");

        verify(session).setPassword("password Y");
    }

    @Test
    public void shouldDeleteDataAndSettingsWhenLogoutHappens() throws Exception {
        when(configuration.getAppName()).thenReturn("BIDAN");
        userService.logout();

        verify(repository).deleteRepository();
        verify(allSettings).savePreviousFetchIndex("0");
    }

    @Test
    public void shouldSwitchLanguageToKannada() throws Exception {
        when(allSharedPreferences.fetchLanguagePreference()).thenReturn(ENGLISH_LOCALE);

        userService.switchLanguagePreference();

        //verify(allSharedPreferences).saveLanguagePreference(INDONESIA_LANGUAGE);
    }

    @Test
    public void shouldSwitchLanguageToEnglish() throws Exception {
        when(allSharedPreferences.fetchLanguagePreference()).thenReturn(KANNADA_LOCALE);

        userService.switchLanguagePreference();

        verify(allSharedPreferences).saveLanguagePreference(ENGLISH_LOCALE);
    }
}
