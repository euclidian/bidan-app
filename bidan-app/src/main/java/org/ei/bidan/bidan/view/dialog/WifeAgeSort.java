package org.ei.bidan.bidan.view.dialog;

import org.ei.bidan.Context;
import org.ei.bidan.R;
import org.ei.bidan.view.contract.SmartRegisterClients;
import org.ei.bidan.view.dialog.SortOption;

import java.util.Collections;

import static org.ei.bidan.view.contract.SmartRegisterClient.AGE_COMPARATOR;

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
