package org.ei.drishti.bidan.view.controller;

import org.apache.commons.lang3.tuple.Pair;
import org.ei.drishti.bidan.domain.Ibu;
import org.ei.drishti.bidan.domain.KartuIbu;
import org.ei.drishti.bidan.repository.AllIbu;
import org.ei.drishti.bidan.view.contract.KartuIbuANCClient;
import org.ei.drishti.bidan.view.contract.KartuIbuANCClients;
import org.ei.drishti.bidan.view.contract.KartuIbuClients;
import org.ei.drishti.util.Cache;
import org.ei.drishti.util.CacheableData;
import org.ei.drishti.view.contract.SmartRegisterClient;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class KartuIbuANCRegisterController {

    private static final String KI_ANC_CLIENTS_LIST = "KIANCClientsList";

    private final AllIbu allIbu;
    private final Cache<String> cache;
    private final Cache<KartuIbuANCClients> kartuIbuANCClientsCache;

    public KartuIbuANCRegisterController(AllIbu allIbu, Cache<String> cache, Cache<KartuIbuANCClients> kartuIbuANCClientsCache) {
        this.allIbu = allIbu;
        this.cache = cache;
        this.kartuIbuANCClientsCache = kartuIbuANCClientsCache;
    }

    public KartuIbuANCClients getKartuIbuANCClients() {
        return kartuIbuANCClientsCache.get(KI_ANC_CLIENTS_LIST, new CacheableData<KartuIbuANCClients>() {
            @Override
            public KartuIbuANCClients fetch() {
                KartuIbuANCClients ancClients = new KartuIbuANCClients();
                List<Pair<Ibu, KartuIbu>> ancsWithKis = allIbu.allANCsWithKartuIbu();

                for (Pair<Ibu, KartuIbu> ancsWithKi : ancsWithKis) {
                    Ibu anc = ancsWithKi.getLeft();
                    KartuIbu ki = ancsWithKi.getRight();

                    KartuIbuANCClient kartuIbuClient = new KartuIbuANCClient(anc.getId(),
                            anc.getDetails().get("Jamkesmas"), anc.getDetails().get("AnamnesisIbu"),
                            anc.getDetails().get("UsiaKlinis"));

                    ancClients.add(kartuIbuClient);
                }
                sortByName(ancClients);
                return ancClients;
            }
        });
    }

    private void sortByName(List<?extends SmartRegisterClient> kiClients) {
        sort(kiClients, new Comparator<SmartRegisterClient>() {
            @Override
            public int compare(SmartRegisterClient o1, SmartRegisterClient o2) {
                return o1.name().compareToIgnoreCase(o2.name());
            }
        });
    }
}
