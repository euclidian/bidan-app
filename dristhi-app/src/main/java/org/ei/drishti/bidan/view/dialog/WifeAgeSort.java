package org.ei.drishti.bidan.view.dialog;

import org.ei.drishti.Context;
import org.ei.drishti.R;
import org.ei.drishti.view.contract.SmartRegisterClients;
import org.ei.drishti.view.dialog.SortOption;

import java.util.Collections;

import static org.ei.drishti.view.contract.SmartRegisterClient.AGE_COMPARATOR;

/**
 * Created by Dimas Ciputra on 2/23/15.
 */
public class WifeAgeSort implements SortOption {
    @Override
    public SmartRegisterClients sort(SmartRegisterClients allClients) {
        Collections.sort(allClients, AGE_COMPARATOR);
        return allClients;
    }

    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.sort_by_wife_age_label);
    }
}
