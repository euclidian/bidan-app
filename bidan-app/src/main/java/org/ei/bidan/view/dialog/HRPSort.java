package org.ei.bidan.view.dialog;

import org.ei.bidan.Context;
import org.ei.bidan.R;

import static org.ei.bidan.view.contract.SmartRegisterClient.HIGH_RISK_COMPARATOR;

public class HRPSort extends HighRiskSort {
    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.sort_by_high_risk_pregnancy_label);
    }

}
