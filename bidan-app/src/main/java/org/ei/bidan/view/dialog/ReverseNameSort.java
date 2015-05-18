package org.ei.bidan.view.dialog;

import org.ei.bidan.Context;
import org.ei.bidan.R;
import org.ei.bidan.view.contract.SmartRegisterClient;
import org.ei.bidan.view.contract.SmartRegisterClients;

import java.util.Collections;

import static org.ei.bidan.view.contract.SmartRegisterClient.NAME_COMPARATOR;

public class ReverseNameSort implements SortOption {
    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.sort_by_name_label_reverse);
    }

    @Override
    public SmartRegisterClients sort(SmartRegisterClients allClients) {
        Collections.sort(allClients, NAME_COMPARATOR);
        Collections.reverse(allClients);
        return allClients;

    }
}
