package org.ei.bidan.bidan.service;

import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.bidan.repository.AllKartuIbus;
import org.ei.bidan.bidan.domain.Bidan;
import org.ei.bidan.repository.AllSharedPreferences;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class BidanService {
    private AllSharedPreferences allSharedPreferences;
    private AllKartuIbus allKartuIbus;
    private AllKohort allKohort;

    public BidanService(AllSharedPreferences allSharedPreferences, AllKartuIbus allKartuIbus, AllKohort allKohort) {
        this.allSharedPreferences = allSharedPreferences;
        this.allKartuIbus = allKartuIbus;
        this.allKohort = allKohort;
    }

    public Bidan fetchDetails() {
        return new Bidan(allSharedPreferences.fetchRegisteredBidan(), allKartuIbus.count(), allKohort.ancCount(), allKohort.pncCount(), allKohort.anakCount());
    }
}
