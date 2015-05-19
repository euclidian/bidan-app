package org.ei.bidan.bidan.repository;

import android.util.Log;

import org.ei.bidan.DristhiConfiguration;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.service.DummyNameService;
import org.ei.bidan.repository.AlertRepository;
import org.ei.bidan.repository.TimelineEventRepository;
import org.ei.bidan.util.StringUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.ei.bidan.bidan.repository.KartuIbuRepository.*;

/**
 * Created by Dimas Ciputra on 2/16/15.
 */
public class AllKartuIbus {

    private KartuIbuRepository kartuIbuRepository;
    private final TimelineEventRepository timelineEventRepository;
    private final AlertRepository alertRepository;
    private final DristhiConfiguration configuration;

    public AllKartuIbus(KartuIbuRepository kartuIbuRepository, AlertRepository alertRepository,
                        TimelineEventRepository timelineEventRepository, DristhiConfiguration configuration) {
        this.kartuIbuRepository = kartuIbuRepository;
        this.timelineEventRepository = timelineEventRepository;
        this.alertRepository = alertRepository;
        this.configuration = configuration;
    }

    public List<KartuIbu> all() {
        return kartuIbuRepository.allKartuIbus();
    }

    public List<KartuIbu> allKartuIbuWithKB() { return kartuIbuRepository.allKartuIbuWithKB(); }

    public KartuIbu findByCaseID(String caseId) {
        return kartuIbuRepository.findByCaseID(caseId);
    }

    public long count() {
        return kartuIbuRepository.count();
    }

    public long kbCount() { return kartuIbuRepository.kbCount(); }

    public List<String> randomName(int length)  {

        List<String> motherNameList = new ArrayList<String>();
        List<KartuIbu> allKartuIbu = kartuIbuRepository.getRandomKartuIbu(length);

        if(length > allKartuIbu.size()) {
            motherNameList = DummyNameService.getMotherDummyName(configuration,length - allKartuIbu.size(),true);
        }

        int index = 0;
        while(motherNameList.size() < length) {
            motherNameList.add(StringUtil.humanize(allKartuIbu.get(index).getDetail(WIFE_NAME_COLUMN)));
            index++;
        }

        return motherNameList;
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
