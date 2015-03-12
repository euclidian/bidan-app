package org.ei.bidan.bidan.service;

import org.ei.bidan.bidan.repository.AllIbu;
import org.ei.bidan.bidan.repository.AllKartuIbus;
import org.ei.bidan.bidan.domain.Bidan;
import org.ei.bidan.repository.AllSharedPreferences;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class BidanService {
    private AllSharedPreferences allSharedPreferences;
    private AllKartuIbus allKartuIbus;
    private AllIbu allIbu;

    public BidanService(AllSharedPreferences allSharedPreferences, AllKartuIbus allKartuIbus, AllIbu allIbu) {
        this.allSharedPreferences = allSharedPreferences;
        this.allKartuIbus = allKartuIbus;
        this.allIbu = allIbu;
    }

    public Bidan fetchDetails() {
        return new Bidan(allSharedPreferences.fetchRegisteredBidan(), allKartuIbus.count(), allIbu.ancCount());
    }
}
