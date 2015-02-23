package org.ei.drishti.bidan.kartuibu;

import org.ei.drishti.util.Cache;
import org.ei.drishti.util.CacheableData;
import org.ei.drishti.view.contract.ECClient;
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
                            kartuIbu.getDetails().get("puskesmas"), kartuIbu.getDetails().get("Province"),
                            kartuIbu.getDetails().get("Kabupaten"), kartuIbu.getDetails().get("phc"),
                            kartuIbu.getDetails().get("householdAddress"), kartuIbu.getDetails().get("ecNumber"),
                            kartuIbu.getDetails().get("wifeName"), kartuIbu.getDetails().get("wifeAge"),
                            kartuIbu.getDetails().get("Golongan_darah"), kartuIbu.getDetails().get("Riwayat_komplikasi"));

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
