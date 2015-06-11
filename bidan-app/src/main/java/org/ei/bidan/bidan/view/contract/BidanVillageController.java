package org.ei.bidan.bidan.view.contract;

import org.ei.bidan.bidan.repository.AllKartuIbus;
import org.ei.bidan.util.Cache;
import org.ei.bidan.util.CacheableData;
import org.ei.bidan.view.contract.Village;
import org.ei.bidan.view.contract.Villages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dimas Ciputra on 6/11/15.
 */
public class BidanVillageController {

    private static final String VILLAGE_LIST = "VILLAGE_LIST";
    private Cache<Villages> villagesCache;
    private AllKartuIbus allKartuIbus;

    public BidanVillageController(Cache<Villages> villagesCache, AllKartuIbus allKartuIbus) {
        this.villagesCache = villagesCache;
        this.allKartuIbus = allKartuIbus;
    }

    public Villages getVillagesIndonesia() {
        return villagesCache.get(VILLAGE_LIST, new CacheableData<Villages>() {
            @Override
            public Villages fetch() {
                Villages villagesList = new Villages();
                List<String> villages = allKartuIbus.villages();
                for (String village : villages) {
                    villagesList.add(new Village(village));
                }
                return villagesList;
            }
        });
    }

    public List<String> getAllVillages() {
        return new ArrayList<>(Arrays.asList("Lainnya", "Saba", "Selebung Rembiga", "Loang Maka", "Setuta", "Durian", "Lekor", "Janapria", "Pendem", "Langko", "Bakan", "Kerembong", "Jango", "Lainnya", "Teruwai", "Gapura", "Kawo", "Segala Anyar", "Sukadana", "Pengengat", "Kuta", "Mertak", "Tumpak", "Ketara", "Bangket Parak", "Prabu", "Pengembur", "Rembitan", "Tanak Awu", "Sengkol"));
    }

}
