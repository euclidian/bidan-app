package org.ei.bidan.view.dialog;

import org.ei.bidan.Context;
import org.ei.bidan.R;
import org.ei.bidan.view.contract.SmartRegisterClients;

import java.util.Collections;

import static org.ei.bidan.view.contract.ChildSmartRegisterClient.HR_COMPARATOR;

public class ChildHighRiskSort implements SortOption {
    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.sort_by_child_hr);
    }

    @Override
    public SmartRegisterClients sort(SmartRegisterClients allClients) {
        Collections.sort(allClients, HR_COMPARATOR);
        return allClients;
    }
}
