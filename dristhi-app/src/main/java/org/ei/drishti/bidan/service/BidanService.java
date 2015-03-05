package org.ei.drishti.bidan.service;

import org.ei.drishti.bidan.repository.AllKartuIbus;
import org.ei.drishti.bidan.domain.Bidan;
import org.ei.drishti.repository.AllSharedPreferences;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class BidanService {
    private AllSharedPreferences allSharedPreferences;
    private AllKartuIbus allKartuIbus;

    public BidanService(AllSharedPreferences allSharedPreferences, AllKartuIbus allKartuIbus) {
        this.allSharedPreferences = allSharedPreferences;
        this.allKartuIbus = allKartuIbus;
    }

    public Bidan fetchDetails() {
        return new Bidan(allSharedPreferences.fetchRegisteredBidan(), allKartuIbus.count(), allKartuIbus.count());
    }
}
