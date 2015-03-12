package org.ei.bidan.bidan.repository;

import org.apache.commons.lang3.tuple.Pair;
import org.ei.bidan.bidan.domain.Ibu;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.repository.AlertRepository;
import org.ei.bidan.repository.TimelineEventRepository;

import java.util.List;

import static org.ei.bidan.bidan.repository.IbuRepository.TYPE_ANC;

/**
 * Created by Dimas Ciputra on 3/5/15.
 */
public class AllIbu {
    private IbuRepository ibuRepository;
    private final AlertRepository alertRepository;
    private final TimelineEventRepository timelineEventRepository;

    public AllIbu(IbuRepository ibuRepository, AlertRepository alertRepository, TimelineEventRepository timelineEventRepository) {
        this.alertRepository = alertRepository;
        this.timelineEventRepository = timelineEventRepository;
        this.ibuRepository = ibuRepository;
    }

    public Ibu findIbu(String caseId) {
        List<Ibu> mothers = ibuRepository.findByCaseIds(caseId);
        if (mothers.isEmpty())
            return null;
        return mothers.get(0);
    }

    public long ancCount() {
        return ibuRepository.ancCount();
    }

    public List<Pair<Ibu, KartuIbu>> allANCsWithKartuIbu() {
        return ibuRepository.allIbuOfATypeWithKartuIbu(TYPE_ANC);
    }

    public Ibu findIbuByKartuIbuId(String kartuIbuId) {
        List<Ibu> ibu = ibuRepository.findAllCasesForKartuIbu(kartuIbuId);
        if (ibu.isEmpty())
            return null;
        return ibu.get(0);
    }

    public void updateIbu(Ibu ibu) {
        ibuRepository.update(ibu);
    }

    public void closeIbu(String entityId) {
        alertRepository.deleteAllAlertsForEntity(entityId);
        timelineEventRepository.deleteAllTimelineEventsForEntity(entityId);
        ibuRepository.close(entityId);
    }

    public void closeAllIbuForKartuIbu(String ecId) {
        List<Ibu> mothers = ibuRepository.findAllCasesForKartuIbu(ecId);
        if (mothers == null || mothers.isEmpty())
            return;
        for (Ibu ibu : mothers) {
            closeIbu(ibu.getId());
        }
    }
}
