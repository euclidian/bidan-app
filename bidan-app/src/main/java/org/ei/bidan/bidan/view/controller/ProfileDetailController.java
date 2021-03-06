package org.ei.bidan.bidan.view.controller;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import org.ei.bidan.AllConstants;
import org.ei.bidan.bidan.domain.Ibu;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.repository.AllKartuIbus;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;
import org.ei.bidan.view.contract.SmartRegisterClient;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.gmariotti.cardslib.library.prototypes.CardWithList;

/**
 * Created by Dimas Ciputra on 4/17/15.
 */
public class ProfileDetailController {
    private final String caseId;
    private final KartuIbuRegisterController controller;
    private final AllKartuIbus allKartuIbus;
    private CharSequence[] filterKeys = {"isoutofarea", "high", "submission", "registration", "risk", "namalengkap", "hidden"};
    private CharSequence[] filterValues = {"invalid"};
    private final AllKohort allKohort;

    public ProfileDetailController(String caseId, KartuIbuRegisterController controller, AllKartuIbus allKartuIbus, AllKohort allKohort) {
        this.caseId = caseId;
        this.controller = controller;
        this.allKartuIbus = allKartuIbus;
        this.allKohort = allKohort;
    }

    public KartuIbuClient get() {

        List<SmartRegisterClient> clients = FluentIterable.from(controller.getKartuIbuClients()).filter(new Predicate<SmartRegisterClient>() {
            @Override
            public boolean apply(SmartRegisterClient input) {
                return caseId.equalsIgnoreCase(input.entityId());
            }
        }).toList();

        if(clients.size()==0) return null;
        return (KartuIbuClient) clients.get(0);
    }

    public boolean containsCaseInsensitive(String s, CharSequence[] l) {
        for(int i=0;i<l.length;i++) {
            if(s.toLowerCase().contains(l[i])) {
                return true;
            }
        }
        return false;
    }

    public Map<String, String> detailMap() {

        KartuIbu kartuIbu = allKartuIbus.findByCaseID(caseId);
        Ibu ibuDetail = allKohort.findIbuByKartuIbuId(kartuIbu.getCaseId());

        Map<String, String> detail = kartuIbu.getDetails();

        if(ibuDetail != null) {
            detail.putAll(ibuDetail.getDetails());
        }

        Predicate<String> filterKey = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return !containsCaseInsensitive(input, filterKeys);
            }
        };

        Predicate<String> filterValue = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return !containsCaseInsensitive(input, filterValues);
            }
        };

        Map<String, String> dMap = new TreeMap<>(Maps.filterKeys(kartuIbu.getDetails(), filterKey));

        return dMap;
    }

    public List<CardWithList.ListObject> getListObject() {
        return null;
    }

    public String getClientJson() {
        return new Gson().toJson(get());
    }

}
