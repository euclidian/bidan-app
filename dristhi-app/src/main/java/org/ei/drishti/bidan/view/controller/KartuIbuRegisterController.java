package org.ei.drishti.bidan.view.controller;

import org.ei.drishti.bidan.domain.KartuIbu;
import org.ei.drishti.bidan.view.contract.KartuIbuClient;
import org.ei.drishti.bidan.view.contract.KartuIbuClients;
import org.ei.drishti.bidan.repository.AllKartuIbus;
import org.ei.drishti.util.Cache;
import org.ei.drishti.util.CacheableData;
import org.ei.drishti.view.contract.SmartRegisterClient;

import java.util.Comparator;
import java.util.List;
import static java.util.Collections.sort;

/**
 * Created by Dimas Ciputra on 2/18/15.
 */
public class KartuIbuRegisterController {
    private static final String KI_CLIENTS_LIST = "KIClientsList";

    private final AllKartuIbus allKartuIbus;
    private final Cache<String> cache;
    private final Cache<KartuIbuClients> kartuIbuClientsCache;

    public KartuIbuRegisterController(AllKartuIbus allKartuIbus, Cache<String> cache, Cache<KartuIbuClients> kartuIbuClientsCache) {
        this.allKartuIbus = allKartuIbus;
        this.cache = cache;
        this.kartuIbuClientsCache = kartuIbuClientsCache;
    }

    public KartuIbuClients getKartuIbuClients() {
        return kartuIbuClientsCache.get(KI_CLIENTS_LIST, new CacheableData<KartuIbuClients>() {
            @Override
            public KartuIbuClients fetch() {
                List<KartuIbu> kartuIbus = allKartuIbus.all();
                KartuIbuClients kartuIbuClients = new KartuIbuClients();

                for (KartuIbu kartuIbu : kartuIbus) {
                    KartuIbuClient kartuIbuClient = new KartuIbuClient(kartuIbu.getCaseId(),
                            kartuIbu.getDetails().get("puskesmas"), kartuIbu.getDetails().get("Propinsi"),
                            kartuIbu.getDetails().get("Kabupaten"), kartuIbu.getDetails().get("Posyandu"),
                            kartuIbu.getDetails().get("Alamatdomisili"), kartuIbu.getDetails().get("NoIbu"),
                            kartuIbu.getDetails().get("Namalengkap"), kartuIbu.getDetails().get("Umur"),
                            kartuIbu.getDetails().get("GolonganDarah"), kartuIbu.getDetails().get("RiwayatKomplikasiKebidanan"),
                            kartuIbu.getDetails().get("Namasuami"), kartuIbu.getDetails().get("TanggalPeriksa"),
                            kartuIbu.getDetails().get("EDD"), kartuIbu.getDetails().get("Desa"));

                    kartuIbuClients.add(kartuIbuClient);
                }
                sortByName(kartuIbuClients);
                return kartuIbuClients;
            }
        });
    }

    private void sortByName(List<?extends SmartRegisterClient> kiClients) {
        sort(kiClients, new Comparator<SmartRegisterClient>() {
            @Override
            public int compare(SmartRegisterClient o1, SmartRegisterClient o2) {
                return o1.wifeName().compareToIgnoreCase(o2.wifeName());
            }
        });
    }
}
