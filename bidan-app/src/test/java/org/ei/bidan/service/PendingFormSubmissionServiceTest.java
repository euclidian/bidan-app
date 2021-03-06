package org.ei.bidan.service;

import org.ei.bidan.repository.FormDataRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class PendingFormSubmissionServiceTest {
    @Mock
    private FormDataRepository repository;
    private PendingFormSubmissionService pendingFormSubmissionService;

    @Before
    public void setUp() {
        initMocks(this);
        pendingFormSubmissionService = new PendingFormSubmissionService(repository);
    }

    @Test
    public void shouldFetchPendingFormSubmissionCount() {
        pendingFormSubmissionService.pendingFormSubmissionCount();

        verify(repository).getPendingFormSubmissionsCount();
    }
}
