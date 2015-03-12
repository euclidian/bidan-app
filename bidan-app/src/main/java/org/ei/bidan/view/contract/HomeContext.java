package org.ei.bidan.view.contract;

import org.ei.bidan.domain.ANM;

public class HomeContext {
    private final long fpCount;
    private String anmName;
    private long ancCount;
    private long pncCount;
    private long childCount;
    private long eligibleCoupleCount;
    private long kartuIbuCount;

    public HomeContext(ANM anm) {
        this.anmName = anm.name();
        this.ancCount = anm.ancCount();
        this.pncCount = anm.pncCount();
        this.childCount = anm.childCount();
        this.eligibleCoupleCount = anm.ecCount();
        this.fpCount = anm.fpCount();
        this.kartuIbuCount = anm.getKartuIbuCount();
    }

    public long fpCount() {
        return fpCount;
    }

    public long ancCount() {
        return ancCount;
    }

    public long pncCount() {
        return pncCount;
    }

    public long childCount() {
        return childCount;
    }

    public long eligibleCoupleCount() {
        return eligibleCoupleCount;
    }

    public long kartuIbuCount() { return kartuIbuCount; }
}
