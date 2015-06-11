package org.ei.bidan.bidan.view.contract;

import org.ei.bidan.view.contract.SmartRegisterClient;

/**
 * Created by Dimas Ciputra on 6/11/15.
 */
public abstract class BidanSmartRegisterClient implements SmartRegisterClient {

    private boolean isHighRiskLabour = false;
    private boolean isHighRiskFromANC = false;
    private boolean isHighRiskPregnancy = false;

    public void setIsHighRiskLabour(boolean isHighRiskLabour) {
        this.isHighRiskLabour = isHighRiskLabour;
    }

    public void setIsHighRiskPregnancy(boolean isHighRiskPregnancy) {
        this.isHighRiskPregnancy = isHighRiskPregnancy;
    }

    public void setIsHighRiskFromANC(boolean isHighRiskFromANC) {
        this.isHighRiskFromANC = isHighRiskFromANC;
    }

    public boolean isHighRiskLabour() { return this.isHighRiskLabour; };

    public boolean isHighRiskPregnancy() {
        return this.isHighRiskPregnancy;
    };

    public boolean isHighRiskFromANC() { return this.isHighRiskFromANC; }

    public boolean isMotherTooYoungOrTooOld() { return age() < 20 || age() > 35;}

    @Override
    public boolean isHighRisk() {
        return isMotherTooYoungOrTooOld() || isHighRiskFromANC();
    }

}
