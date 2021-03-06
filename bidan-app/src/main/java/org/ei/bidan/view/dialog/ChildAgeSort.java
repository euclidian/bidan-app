package org.ei.bidan.view.dialog;

import org.ei.bidan.Context;
import org.ei.bidan.R;
import org.ei.bidan.view.contract.SmartRegisterClients;

import java.util.Collections;

import static org.ei.bidan.view.contract.ChildSmartRegisterClient.AGE_COMPARATOR;

public class ChildAgeSort implements SortOption {
    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.sort_by_child_age);
    }

    @Override
    public SmartRegisterClients sort(SmartRegisterClients allClients) {
        Collections.sort(allClients, AGE_COMPARATOR);
        return allClients;
    }
}
