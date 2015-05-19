package org.ei.bidan.view.controller;

import org.ei.bidan.DristhiConfiguration;
import org.ei.bidan.bidan.domain.Anak;
import org.ei.bidan.bidan.domain.Ibu;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.bidan.repository.AnakRepository;
import org.ei.bidan.bidan.repository.IbuRepository;
import org.ei.bidan.repository.AlertRepository;
import org.ei.bidan.repository.TimelineEventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.Collections;
import java.util.HashMap;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Dimas Ciputra on 4/8/15.
 */
@RunWith(RobolectricTestRunner.class)
public class AllKohortTest {
    @Mock
    private IbuRepository ibuRepository;
    @Mock
    private AnakRepository anakRepository;
    @Mock
    private AlertRepository alertRepository;
    @Mock
    private TimelineEventRepository timelineEventRepository;
    @Mock
    private DristhiConfiguration dristhiConfiguration;
    @Mock
    private Anak anak;
    @Mock
    private Ibu ibu;

    private AllKohort allKohort;

    @Before
    public void setUp() {
        initMocks(this);
        allKohort = new AllKohort(ibuRepository, anakRepository,
                alertRepository, timelineEventRepository, dristhiConfiguration);
    }

    @Test
    public void shouldDeleteTimelineEventsAndAlertsWhileClosingIbu() {
        allKohort.closeIbu("entity id 1");

        verify(alertRepository).deleteAllAlertsForEntity("entity id 1");
        verify(timelineEventRepository).deleteAllTimelineEventsForEntity("entity id 1");
    }

    @Test
    public void shouldDeleteTimelineEventsAndAlertsForAllIbusWhenKIIsClosed() throws Exception {
        when(ibuRepository.findAllCasesForKartuIbu("ki id 1"))
                .thenReturn(asList(new Ibu("mother id 1", "ki id 1", "2012-12-12"),
                        new Ibu("mother id 2", "ki id 1", "2012-12-10")));

        allKohort.closeAllIbuForKartuIbu("ki id 1");

        verify(alertRepository).deleteAllAlertsForEntity("mother id 1");
        verify(alertRepository).deleteAllAlertsForEntity("mother id 2");
        verify(timelineEventRepository).deleteAllTimelineEventsForEntity("mother id 1");
        verify(timelineEventRepository).deleteAllTimelineEventsForEntity("mother id 2");
        verify(ibuRepository).close("mother id 1");
        verify(ibuRepository).close("mother id 2");
    }

    @Test
    public void shouldDeleteTimelineEventsAndAlertsWhenAnakIsClosed() throws Exception {
        when(anakRepository.find("child id 1"))
                .thenReturn(new Anak("child id 1", "mother id 1", "male", new HashMap<String, String>()));

        allKohort.closeAnak("child id 1");

        verify(alertRepository).deleteAllAlertsForEntity("child id 1");
        verify(timelineEventRepository).deleteAllTimelineEventsForEntity("child id 1");
        verify(anakRepository).close("child id 1");
    }

    @Test
    public void shouldNotFailClosingIbuWhenKIIsClosedAndDoesNotHaveAnyIbus() throws Exception {
        when(ibuRepository.findAllCasesForKartuIbu("ki id 1")).thenReturn(null);
        when(ibuRepository.findAllCasesForKartuIbu("ki id 1")).thenReturn(Collections.<Ibu>emptyList());

        allKohort.closeAllIbuForKartuIbu("ki id 1");

        verifyZeroInteractions(alertRepository);
        verifyZeroInteractions(timelineEventRepository);
        verify(ibuRepository, times(0)).close(any(String.class));
    }

    @Test
    public void shouldDelegateToAnakRepositoryWhenUpdateAnakIsCalled() throws Exception {
        allKohort.updateAnak(anak);

        verify(anakRepository).update(anak);
    }

    @Test
    public void shouldDelegateToIbuRepositoryWhenUpdateIbuIsCalled() throws Exception {
        allKohort.updateIbu(ibu);

        verify(ibuRepository).update(ibu);
    }

}
