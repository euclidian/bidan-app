package org.ei.bidan.bidan.view.contract;

import android.util.Log;

import com.google.common.base.Strings;

import org.ei.bidan.util.IntegerUtil;
import org.ei.bidan.view.contract.SmartRegisterClient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Dimas Ciputra on 6/11/15.
 */
public abstract class BidanSmartRegisterClient implements SmartRegisterClient {

    private boolean isHighRiskLabour = false;
    private boolean isHighRiskFromANC = false;
    private boolean isHighRiskPregnancy = false;

    // High Risk Factors
    private String riskLila;
    private String hbLevels;
    private String bloodSugar;
    private String rTdSistolik;
    private String rTdDiastolik;
    private String rPartus;
    private String rAbortus;
    private String chronicDisease;

    public String getChronicDisease() {
        return chronicDisease;
    }

    public void setChronicDisease(String chronicDisease) {
        this.chronicDisease = chronicDisease;
    }

    public void setIsHighRiskLabour(boolean isHighRiskLabour) {
        this.isHighRiskLabour = isHighRiskLabour;
    }

    public void setIsHighRiskPregnancy(boolean isHighRiskPregnancy) {
        this.isHighRiskPregnancy = isHighRiskPregnancy;
    }

    public void setIsHighRiskFromANC(boolean isHighRiskFromANC) {
        this.isHighRiskFromANC = isHighRiskFromANC;
    }

    public boolean isHighRiskLabour() { return this.isHighRiskLabour; }

    public boolean isHighRiskPregnancy() {
        return this.isHighRiskPregnancy;
    }

    public boolean isHighRiskFromANC() { return this.isHighRiskFromANC; }

    public boolean isMotherTooYoungOrTooOld() { return age() < 20 || age() > 35;}

    public int riskFlagsCount() {
        int i = 0;
        i += isHighRiskLabour() ? 3 : 0;
        i += isHighRiskPregnancy() ? 4 : 0;
        i += isHighRisk() ? 2 : 0;
        return i;
    }

    public List<String> highRiskReason() {
        List<String> reason = new ArrayList<>();

        if(age() < 20) {
            reason.add("Ibu terlalu muda");
        } else if(age() > 35) {
            reason.add("Ibu terlalu tua");
        }

        if(!Strings.isNullOrEmpty(getChronicDisease())) {
            String[] chronicDisease = getChronicDisease().split("\\s+");
            for(int i=0; i < chronicDisease.length; i++) {
                reason.add(chronicDisease[i]);
            }
        }

        return reason;
    }


    @Override
    public boolean isHighRisk() {
        return isMotherTooYoungOrTooOld() || isHighRiskFromANC();
    }

}
