package org.ei.bidan.bidan.view.controller;

import org.apache.commons.lang3.tuple.Pair;
import org.ei.bidan.bidan.domain.Ibu;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.bidan.view.contract.KartuIbuANCClient;
import org.ei.bidan.bidan.view.contract.KartuIbuANCClients;
import org.ei.bidan.util.Cache;
import org.ei.bidan.util.CacheableData;
import org.ei.bidan.view.contract.SmartRegisterClient;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class KartuIbuANCRegisterController {

    private static final String KI_ANC_CLIENTS_LIST = "KIANCClientsList";

    private final AllKohort allKohort;
    private final Cache<String> cache;
    private final Cache<KartuIbuANCClients> kartuIbuANCClientsCache;

    public KartuIbuANCRegisterController(AllKohort allKohort, Cache<String> cache, Cache<KartuIbuANCClients> kartuIbuANCClientsCache) {
        this.allKohort = allKohort;
        this.cache = cache;
        this.kartuIbuANCClientsCache = kartuIbuANCClientsCache;
    }

    public KartuIbuANCClients getKartuIbuANCClients() {
        return kartuIbuANCClientsCache.get(KI_ANC_CLIENTS_LIST, new CacheableData<KartuIbuANCClients>() {
            @Override
            public KartuIbuANCClients fetch() {
                KartuIbuANCClients ancClients = new KartuIbuANCClients();
                List<Pair<Ibu, KartuIbu>> ancsWithKis = allKohort.allANCsWithKartuIbu();

                for (Pair<Ibu, KartuIbu> ancsWithKi : ancsWithKis) {
                    Ibu anc = ancsWithKi.getLeft();
                    KartuIbu ki = ancsWithKi.getRight();

                    KartuIbuANCClient kartuIbuClient = new KartuIbuANCClient(anc.getId(),
                            ki.getDetails().get("Desa"), ki.getDetails().get("puskesmas"),
                            ki.getDetails().get("Namalengkap"), ki.getDetails().get("Umur")
                    )
                            .withHusband(ki.getDetails().get("Namasuami"))
                            .withKINumber(ki.getDetails().get("NoIbu"))
                            .withEDD(ki.getDetail("EDD"))
                            .withANCStatus(anc.getDetails().get("StatusGiziibu"))
                            .withRiskFactors(anc.getDetail("KomplikasidalamKehamilan"))
                            .withKunjunganData(anc.getDetail("TrimesterKe"))
                            .withTTImunisasiData(anc.getDetail("StatusImunisasiTT"))
                            .withUsiaKlinisData(anc.getDetail("UsiaKlinis"));

                    kartuIbuClient.setBB(anc.getDetail("BB"));
                    kartuIbuClient.setTB(anc.getDetail("TB"));
                    kartuIbuClient.setLILA(anc.getDetail("berat_badan"));
                    kartuIbuClient.setBeratBadan(anc.getDetail("lila"));
                    kartuIbuClient.setPenyakitKronis(anc.getDetail("PenyakitKronis"));
                    kartuIbuClient.setAlergi(anc.getDetail("Alergi"));

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
