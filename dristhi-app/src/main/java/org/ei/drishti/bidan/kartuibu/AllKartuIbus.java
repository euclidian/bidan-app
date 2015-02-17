package org.ei.drishti.bidan.kartuibu;

import org.ei.drishti.repository.AlertRepository;
import org.ei.drishti.repository.EligibleCoupleRepository;
import org.ei.drishti.repository.TimelineEventRepository;

import java.util.List;
import java.util.Map;

/**
 * Created by Dimas Ciputra on 2/16/15.
 */
public class AllKartuIbus {

    private KartuIbuRepository kartuIbuRepository;
    private final TimelineEventRepository timelineEventRepository;
    private final AlertRepository alertRepository;

    public AllKartuIbus(KartuIbuRepository kartuIbuRepository, AlertRepository alertRepository, TimelineEventRepository timelineEventRepository) {
        this.kartuIbuRepository = kartuIbuRepository;
        this.timelineEventRepository = timelineEventRepository;
        this.alertRepository = alertRepository;
    }

    public List<KartuIbu> all() {
        return kartuIbuRepository.allKartuIbus();
    }

    public KartuIbu findByCaseID(String caseId) {
        return kartuIbuRepository.findByCaseID(caseId);
    }

    public long count() {
        return kartuIbuRepository.count();
    }

    public List<KartuIbu> findByCaseIDs(List<String> caseIds) {
        return kartuIbuRepository.findByCaseIDs(caseIds.toArray(new String[caseIds.size()]));
    }

    public void close(String entityId) {
        alertRepository.deleteAllAlertsForEntity(entityId);
        timelineEventRepository.deleteAllTimelineEventsForEntity(entityId);
        kartuIbuRepository.close(entityId);
    }

    public void mergeDetails(String entityId, Map<String, String> details) {
        kartuIbuRepository.mergeDetails(entityId, details);
    }
}
