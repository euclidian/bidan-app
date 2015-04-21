package org.ei.bidan.bidan.view.dialog;

import org.ei.bidan.Context;
import org.ei.bidan.R;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;
import org.ei.bidan.util.IntegerUtil;
import org.ei.bidan.view.contract.SmartRegisterClient;
import org.ei.bidan.view.contract.SmartRegisterClients;
import org.ei.bidan.view.dialog.SortOption;

import java.util.Collections;
import java.util.Comparator;

import static org.ei.bidan.view.contract.SmartRegisterClient.NO_IBU_COMPARATOR;

/**
 * Created by Dimas Ciputra on 4/21/15.
 */
public class NoIbuSort implements SortOption {
    @Override
    public SmartRegisterClients sort(SmartRegisterClients allClients) {
        Collections.sort(allClients, NO_IBU_COMPARATOR);
        return allClients;
    }

    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.sort_by_no_ibu_label);
    }
}

