package org.ei.bidan.bidan.view.contract;

import org.ei.bidan.bidan.domain.Bidan;
import org.ei.bidan.domain.ANM;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class BidanHomeContext {

    private long kartuIbuCount;
    private long kartuIbuANCCount;
    private long kartuIbuPNCCount;

    public BidanHomeContext(Bidan bidan) {
        this.kartuIbuCount = bidan.getKartuIbuCount();
        this.kartuIbuANCCount = bidan.getKartuIbuAncCount();
        this.kartuIbuPNCCount = bidan.getKartuIbuPNCCount();
    }

    public long getKartuIbuCount() { return kartuIbuCount; }

    public long getKartuIbuANCCount() { return kartuIbuANCCount; }

    public long getKartuIbuPNCCount() { return kartuIbuPNCCount; }
}
