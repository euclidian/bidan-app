package org.ei.drishti.bidan.view.contract;

import org.ei.drishti.bidan.domain.Bidan;
import org.ei.drishti.domain.ANM;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class BidanHomeContext {

    private long kartuIbuCount;
    private long kartuIbuANCCount;

    public BidanHomeContext(Bidan bidan) {
        this.kartuIbuCount = bidan.getKartuIbuCount();
        this.kartuIbuANCCount = bidan.getKartuIbuAncCount();
    }

    public long getKartuIbuCount() { return kartuIbuCount; }

    public long getKartuIbuANCCount() { return kartuIbuANCCount; }
}
