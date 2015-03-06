package org.ei.drishti.bidan.view.dialog;

import android.view.View;

import org.ei.drishti.bidan.view.contract.KartuIbuANCClient;
import org.ei.drishti.bidan.view.contract.KartuIbuClient;
import org.ei.drishti.bidan.view.viewHolder.NativeKIANCRegisterViewHolder;
import org.ei.drishti.bidan.view.viewHolder.NativeKIRegisterViewHolder;
import org.ei.drishti.provider.SmartRegisterClientsProvider;
import org.ei.drishti.view.dialog.ServiceModeOption;

/**
 * Created by Dimas Ciputra on 3/5/15.
 */
public abstract class BidanServiceModeOption extends ServiceModeOption {

    public BidanServiceModeOption(SmartRegisterClientsProvider clientsProvider) {
        super(clientsProvider);
    }

    public abstract void setupListView(KartuIbuClient client,
                                       NativeKIRegisterViewHolder viewHolder,
                                       View.OnClickListener clientSectionClickListener);

    public abstract void setupListView(KartuIbuANCClient client,
                                       NativeKIANCRegisterViewHolder viewHolder,
                                       View.OnClickListener clientSectionClickListener);

}
