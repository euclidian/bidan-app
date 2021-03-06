package org.ei.bidan.service;

import com.google.gson.Gson;
import org.ei.bidan.repository.AllSharedPreferences;
import org.robolectric.RobolectricTestRunner;
import org.ei.bidan.DristhiConfiguration;
import org.ei.bidan.domain.FetchStatus;
import org.ei.bidan.domain.Response;
import org.ei.bidan.domain.ResponseStatus;
import org.ei.bidan.domain.form.FormSubmission;
import org.ei.drishti.dto.form.FormSubmissionDTO;
import org.ei.bidan.repository.AllSettings;
import org.ei.bidan.repository.FormDataRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.ei.bidan.domain.FetchStatus.fetched;
import static org.ei.bidan.domain.FetchStatus.nothingFetched;
import static org.ei.bidan.domain.ResponseStatus.failure;
import static org.ei.bidan.domain.ResponseStatus.success;
import static org.ei.bidan.domain.SyncStatus.PENDING;
import static org.ei.bidan.domain.SyncStatus.SYNCED;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class FormSubmissionSyncServiceTest {
    @Mock
    private FormDataRepository repository;
    @Mock
    private HTTPAgent httpAgent;
    @Mock
    private AllSettings allSettings;
    @Mock
    private AllSharedPreferences allSharedPreferences;
    @Mock
    private FormSubmissionService formSubmissionService;
    @Mock
    private DristhiConfiguration configuration;

    private FormSubmissionSyncService service;
    private List<FormSubmissionDTO> expectedFormSubmissionsDto;
    private List<FormSubmission> submissions;
    private String formInstanceJSON;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new FormSubmissionSyncService(formSubmissionService, httpAgent, repository, allSettings, allSharedPreferences, configuration);

        formInstanceJSON = "{form:{bind_type: 'ec'}}";
        submissions = asList(new FormSubmission("id 1", "entity id 1", "form name", formInstanceJSON, "123", PENDING, "1"));
        expectedFormSubmissionsDto = asList(new FormSubmissionDTO(
                "anm id 1", "id 1", "entity id 1", "form name", formInstanceJSON, "123", "1"));
        when(configuration.getAppName()).thenReturn("BIDAN");
        when(configuration.dristhiBaseURL()).thenReturn("http://dristhi_base_url");
        when(allSharedPreferences.fetchRegistered()).thenReturn("anm id 1");
        when(repository.getPendingFormSubmissions()).thenReturn(submissions);
    }

    @Test
    public void shouldPushPendingFormSubmissionsToServerAndMarkThemAsSynced() throws Exception {
        when(httpAgent.post("http://dristhi_base_url" + "/form-submissions", new Gson().toJson(expectedFormSubmissionsDto)))
                .thenReturn(new Response<String>(success, null));

        service.pushToServer();

        inOrder(allSettings, httpAgent, repository);
        verify(httpAgent).post("http://dristhi_base_url" + "/form-submissions", new Gson().toJson(expectedFormSubmissionsDto));
        verify(repository).markFormSubmissionsAsSynced(submissions);
    }

    @Test
    public void shouldNotMarkPendingSubmissionsAsSyncedIfPostFails() throws Exception {
        when(httpAgent.post("http://dristhi_base_url" + "/form-submissions", new Gson().toJson(expectedFormSubmissionsDto)))
                .thenReturn(new Response<String>(ResponseStatus.failure, null));

        service.pushToServer();

        verify(repository).getPendingFormSubmissions();
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void shouldNotPushIfThereAreNoPendingSubmissions() throws Exception {
        when(repository.getPendingFormSubmissions()).thenReturn(Collections.<FormSubmission>emptyList());

        service.pushToServer();

        verify(repository).getPendingFormSubmissions();
        verifyNoMoreInteractions(repository);
        verifyZeroInteractions(allSettings);
        verifyZeroInteractions(httpAgent);
    }

    @Test
    public void shouldPullFormSubmissionsFromServerInBatchesAndDelegateToProcessing() throws Exception {
        this.expectedFormSubmissionsDto = asList(new FormSubmissionDTO(
                "anm id 1", "id 1", "entity id 1", "form name", formInstanceJSON, "123", "1"));
        List<FormSubmission> expectedFormSubmissions = asList(new FormSubmission("id 1", "entity id 1", "form name",
                formInstanceJSON, "123", SYNCED, "1"));
        when(configuration.syncDownloadBatchSize()).thenReturn(1);
        when(allSettings.fetchPreviousFormSyncIndex()).thenReturn("122");
        when(httpAgent.fetch("http://dristhi_base_url/form-submissions?anm-id=anm id 1&timestamp=122&batch-size=1"))
                .thenReturn(new Response<String>(success, new Gson().toJson(this.expectedFormSubmissionsDto)),
                        new Response<String>(success, new Gson().toJson(Collections.emptyList())));

        FetchStatus fetchStatus = service.pullFromServer();

        assertEquals(fetched, fetchStatus);
        verify(httpAgent, times(2))
                .fetch("http://dristhi_base_url/form-submissions?anm-id=anm id 1&timestamp=122&batch-size=1");
        verify(formSubmissionService).processSubmissions(expectedFormSubmissions);
    }

    @Test
    public void shouldReturnNothingFetchedStatusWhenNoFormSubmissionsAreGotFromServer() throws Exception {
        when(configuration.syncDownloadBatchSize()).thenReturn(1);
        when(allSettings.fetchPreviousFormSyncIndex()).thenReturn("122");
        when(httpAgent.fetch("http://dristhi_base_url/form-submissions?anm-id=anm id 1&timestamp=122&batch-size=1"))
                .thenReturn(new Response<String>(success, new Gson().toJson(Collections.emptyList())));

        FetchStatus fetchStatus = service.pullFromServer();

        assertEquals(nothingFetched, fetchStatus);
        verify(httpAgent).fetch("http://dristhi_base_url/form-submissions?anm-id=anm id 1&timestamp=122&batch-size=1");
        verifyZeroInteractions(formSubmissionService);
    }

    @Test
    public void shouldNotDelegateToProcessingIfPullFails() throws Exception {
        when(configuration.syncDownloadBatchSize()).thenReturn(1);
        when(allSettings.fetchPreviousFormSyncIndex()).thenReturn("122");
        when(httpAgent.fetch("http://dristhi_base_url/form-submissions?anm-id=anm id 1&timestamp=122&batch-size=1"))
                .thenReturn(new Response<String>(failure, null));

        FetchStatus fetchStatus = service.pullFromServer();

        assertEquals(FetchStatus.fetchedFailed, fetchStatus);
        verifyZeroInteractions(formSubmissionService);
    }
}
