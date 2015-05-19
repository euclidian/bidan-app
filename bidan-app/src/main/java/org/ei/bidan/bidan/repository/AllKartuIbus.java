package org.ei.bidan.bidan.repository;

import android.util.Log;

import org.ei.bidan.DristhiConfiguration;
import org.ei.bidan.bidan.domain.KartuIbu;
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
            motherNameList = getDummyData(length - allKartuIbu.size());
        }

        int index = 0;
        while(motherNameList.size() < length) {
            motherNameList.add(StringUtil.humanize(allKartuIbu.get(index).getDetail(WIFE_NAME_COLUMN)));
            index++;
        }

        return motherNameList;
    }

    private List<String> getDummyData(int size) {
        List<String> motherNameList = new ArrayList<String>();
        try {
            JSONObject dummyData = new JSONObject(configuration.getDummyData());
            JSONArray motherJSONArray = dummyData.getJSONArray("motherName");

            if (motherJSONArray != null) {
                int len = motherJSONArray.length();
                for (int i=0;i<len;i++){
                    motherNameList.add(motherJSONArray.get(i).toString());
                }
            }
        } catch (Exception e) {
            Log.d("Random Name",  e.getMessage());
            return null;
        }
        Collections.shuffle(motherNameList);
        return motherNameList.subList(0, size);
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
