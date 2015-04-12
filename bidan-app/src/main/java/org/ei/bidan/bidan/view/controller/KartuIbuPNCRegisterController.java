package org.ei.bidan.bidan.view.controller;

import org.apache.commons.lang3.tuple.Pair;
import org.ei.bidan.bidan.domain.Ibu;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.bidan.view.contract.KartuIbuPNCClient;
import org.ei.bidan.bidan.view.contract.KartuIbuPNCClients;
import org.ei.bidan.util.Cache;
import org.ei.bidan.util.CacheableData;
import org.ei.bidan.view.contract.SmartRegisterClient;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class KartuIbuPNCRegisterController {

    private static final String KI_PNC_CLIENTS_LIST = "KIPNClientsList";

    private final AllKohort allKohort;
    private final Cache<String> cache;
    private final Cache<KartuIbuPNCClients> kartuIbuPNCClientsCache;

    public KartuIbuPNCRegisterController(AllKohort allKohort, Cache<String> cache, Cache<KartuIbuPNCClients> kartuIbuPNCClientsCache) {
        this.allKohort = allKohort;
        this.cache = cache;
        this.kartuIbuPNCClientsCache = kartuIbuPNCClientsCache;
    }

    public KartuIbuPNCClients getKartuIbuPNCClients() {
        return kartuIbuPNCClientsCache.get(KI_PNC_CLIENTS_LIST, new CacheableData<KartuIbuPNCClients>() {
            @Override
            public KartuIbuPNCClients fetch() {
                KartuIbuPNCClients pncClients = new KartuIbuPNCClients();
                List<Pair<Ibu, KartuIbu>> pncsWithKis = allKohort.allPNCsWithKartuIbu();

                for (Pair<Ibu, KartuIbu> pncsWithKi : pncsWithKis) {
                    Ibu pnc = pncsWithKi.getLeft();
                    KartuIbu ki = pncsWithKi.getRight();

                    KartuIbuPNCClient kartuIbuClient = new KartuIbuPNCClient(pnc.getId(),
                            ki.getDetails().get("Desa"), ki.getDetails().get("puskesmas"),
                            ki.getDetails().get("Namalengkap"), ki.getDetails().get("Umur")
                    )
                            .withHusband(ki.getDetails().get("Namasuami"))
                            .withKINumber(ki.getDetails().get("NoIbu"))
                            .withEDD(ki.getDetails().get("EDD"))
                            .withPlan(pnc.getDetail("Rencana"))
                            .withKomplikasi(pnc.getDetail("Komplikasi"))
                            .withMetodeKontrasepsi(pnc.getDetail("Metodekontrasepsi"))
                            .withTandaVital(pnc.getDetail("TandaVitalTDDiastolik"),
                                    pnc.getDetail("TandaVitalTDSistolik"),
                                    pnc.getDetail("TandaVitalSuhu"));

                    pncClients.add(kartuIbuClient);
                }
                sortByName(pncClients);
                return pncClients;
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
