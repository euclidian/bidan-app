package org.ei.bidan.view.dialog;

import android.view.View;

import org.ei.bidan.view.activity.SecuredNativeSmartRegisterActivity;
import org.ei.bidan.provider.SmartRegisterClientsProvider;
import org.ei.bidan.view.contract.ANCSmartRegisterClient;
import org.ei.bidan.view.contract.ChildSmartRegisterClient;
import org.ei.bidan.view.contract.FPSmartRegisterClient;
import org.ei.bidan.view.contract.pnc.PNCSmartRegisterClient;
import org.ei.bidan.view.viewHolder.NativeANCSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.NativeChildSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.NativeFPSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.NativePNCSmartRegisterViewHolder;

public abstract class ServiceModeOption implements DialogOption {

    private SmartRegisterClientsProvider clientsProvider;

    public ServiceModeOption(SmartRegisterClientsProvider clientsProvider) {
        this.clientsProvider = clientsProvider;
    }

    public void apply() {
        clientsProvider.onServiceModeSelected(this);
    }

    public SmartRegisterClientsProvider provider() {
        return clientsProvider;
    }

    public abstract SecuredNativeSmartRegisterActivity.ClientsHeaderProvider getHeaderProvider();

    public abstract void setupListView(ChildSmartRegisterClient client,
                                       NativeChildSmartRegisterViewHolder viewHolder,
                                       View.OnClickListener clientSectionClickListener);

    public abstract void setupListView(ANCSmartRegisterClient client,
                                       NativeANCSmartRegisterViewHolder viewHolder,
                                       View.OnClickListener clientSectionClickListener);

    public abstract void setupListView(FPSmartRegisterClient client,
                                       NativeFPSmartRegisterViewHolder viewHolder,
                                       View.OnClickListener clientSectionClickListener);

    public abstract void setupListView(PNCSmartRegisterClient client,
                                       NativePNCSmartRegisterViewHolder viewHolder,
                                       View.OnClickListener clientSectionClickListener);

}
