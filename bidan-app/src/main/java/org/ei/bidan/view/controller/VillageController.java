package org.ei.bidan.view.controller;

import com.google.gson.Gson;
import org.ei.bidan.repository.AllEligibleCouples;
import org.ei.bidan.util.Cache;
import org.ei.bidan.util.CacheableData;
import org.ei.bidan.view.contract.Village;
import org.ei.bidan.view.contract.Villages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VillageController {
    private static final String VILLAGE_LIST = "VILLAGE_LIST";
    private AllEligibleCouples allEligibleCouples;
    private Cache<String> cache;
    private Cache<Villages> villagesCache;
    private Cache<Villages> villagesCacheIna;

    public VillageController(AllEligibleCouples allEligibleCouples, Cache<String> cache,
                             Cache<Villages> villagesCache) {
        this.allEligibleCouples = allEligibleCouples;
        this.cache = cache;
        this.villagesCache = villagesCache;
    }

    public String villages() {
        return cache.get(VILLAGE_LIST, new CacheableData<String>() {
            @Override
            public String fetch() {
                List<Village> villagesList = new ArrayList<Village>();
                List<String> villages = allEligibleCouples.villages();
                for (String village : villages) {
                    villagesList.add(new Village(village));
                }
                return new Gson().toJson(villagesList);
            }
        });
    }

    public Villages getVillages() {
        return villagesCache.get(VILLAGE_LIST, new CacheableData<Villages>() {
            @Override
            public Villages fetch() {
                Villages villagesList = new Villages();
                List<String> villages = allEligibleCouples.villages();
                for (String village : villages) {
                    villagesList.add(new Village(village));
                }
                return villagesList;
            }
        });
    }

    // TODO : Change this
    public Villages getVillagesIndonesia() {
        return villagesCacheIna.get(VILLAGE_LIST, new CacheableData<Villages>() {
            @Override
            public Villages fetch() {
                Villages villagesList = new Villages();
                List<String> villages = getAllVillages();
                for(String village : villages) {
                    villagesList.add(new Village(village));
                }
                return villagesList;
            }
        });
    }

    public List<String> getAllVillages() {
        return new ArrayList<>(Arrays.asList("Lainnya","Saba","Selebung Rembiga","Loang Maka","Setuta","Durian","Lekor","Janapria","Pendem","Langko","Bakan","Kerembong","Jango","Lainnya","Teruwai","Gapura","Kawo","Segala Anyar","Sukadana","Pengengat","Kuta","Mertak","Tumpak","Ketara","Bangket Parak","Prabu","Pengembur","Rembitan","Tanak Awu","Sengkol"));
    }
}
