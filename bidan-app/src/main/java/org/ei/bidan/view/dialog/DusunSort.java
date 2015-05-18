package org.ei.bidan.view.dialog;

import org.ei.bidan.Context;
import org.ei.bidan.R;
import org.ei.bidan.view.contract.SmartRegisterClients;
import static org.ei.bidan.view.contract.SmartRegisterClient.*;

import java.util.Collections;

/**
 * Created by Dimas Ciputra on 4/27/15.
 */
public class DusunSort implements SortOption {

    @Override
    public SmartRegisterClients sort(SmartRegisterClients allClients) {
        Collections.sort(allClients, VILLAGE_COMPARATOR);
        return allClients;
    }

    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.sort_by_dusun);
    }
}
