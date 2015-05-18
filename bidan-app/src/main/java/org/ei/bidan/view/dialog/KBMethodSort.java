package org.ei.bidan.view.dialog;

import org.ei.bidan.Context;
import org.ei.bidan.R;
import org.ei.bidan.view.contract.SmartRegisterClients;

import java.util.Collections;

import static org.ei.bidan.view.contract.SmartRegisterClient.KB_METHOD_COMPARATOR;
import static org.ei.bidan.view.contract.SmartRegisterClient.NAME_COMPARATOR;

public class KBMethodSort implements SortOption {
    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.sort_by_kb_method);
    }

    @Override
    public SmartRegisterClients sort(SmartRegisterClients allClients) {
        Collections.sort(allClients, KB_METHOD_COMPARATOR);
        return allClients;

    }
}
