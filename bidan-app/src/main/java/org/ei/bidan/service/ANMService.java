package org.ei.bidan.service;

import org.ei.bidan.Context;
import org.ei.bidan.bidan.repository.AllKartuIbus;
import org.ei.bidan.domain.ANM;
import org.ei.bidan.repository.AllBeneficiaries;
import org.ei.bidan.repository.AllEligibleCouples;
import org.ei.bidan.repository.AllSharedPreferences;

public class ANMService {
    private AllSharedPreferences allSharedPreferences;
    private AllBeneficiaries allBeneficiaries;
    private AllEligibleCouples allEligibleCouples;

    public ANMService(AllSharedPreferences allSharedPreferences, AllBeneficiaries allBeneficiaries, AllEligibleCouples allEligibleCouples) {
        this.allSharedPreferences = allSharedPreferences;
        this.allBeneficiaries = allBeneficiaries;
        this.allEligibleCouples = allEligibleCouples;
    }

    public ANM fetchDetails() {
            return new ANM(allSharedPreferences.fetchRegisteredANM(), allEligibleCouples.count(), allEligibleCouples.fpCount(),
                    allBeneficiaries.ancCount(), allBeneficiaries.pncCount(), allBeneficiaries.childCount());
    }
}
