package org.ei.bidan.view.dialog;

import org.ei.bidan.Context;
import org.ei.bidan.R;
import org.ei.bidan.view.contract.pnc.PNCSmartRegisterClient;
import org.ei.bidan.view.contract.SmartRegisterClients;

import java.util.Collections;

public class DateOfDeliverySort implements SortOption {
    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.sort_by_date_of_delivery_label);
    }

    @Override
    public SmartRegisterClients sort(SmartRegisterClients allClients) {
        Collections.sort(allClients, PNCSmartRegisterClient.DATE_OF_DELIVERY_COMPARATOR);
        return allClients;
    }
}
