package org.ei.bidan.bidan.view.dialog;

import android.view.View;

import org.ei.bidan.Context;
import org.ei.bidan.R;
import org.ei.bidan.provider.SmartRegisterClientsProvider;
import org.ei.bidan.view.contract.ANCSmartRegisterClient;
import org.ei.bidan.view.contract.ChildSmartRegisterClient;
import org.ei.bidan.view.contract.FPSmartRegisterClient;
import org.ei.bidan.view.contract.pnc.PNCSmartRegisterClient;
import org.ei.bidan.view.dialog.ServiceModeOption;
import org.ei.bidan.view.viewHolder.NativeANCSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.NativeChildSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.NativeFPSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.NativePNCSmartRegisterViewHolder;

import static org.ei.bidan.view.activity.SecuredNativeSmartRegisterActivity.ClientsHeaderProvider;

public class AllKartuIbuServiceMode extends ServiceModeOption {

    public AllKartuIbuServiceMode(SmartRegisterClientsProvider provider) {
        super(provider);
    }

    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.kartu_ibu_selection);
    }

    @Override
    public ClientsHeaderProvider getHeaderProvider() {
        return new ClientsHeaderProvider() {
            @Override
            public int count() {
                return 7;
            }

            @Override
            public int weightSum() {
                return 100;
            }

            @Override
            public int[] weights() {
                return new int[]{24, 8, 12, 12, 13, 20, 9};
            }

            @Override
            public int[] headerTextResourceIds() {
                return new int[]{
                        R.string.header_nama, R.string.header_no_ibu, R.string.header_obsetri,
                        R.string.header_edd, R.string.header_puskesmas, R.string.header_status_b,
                        R.string.header_edit};
            }
        };
    }

    @Override
    public void setupListView(ChildSmartRegisterClient client,
                              NativeChildSmartRegisterViewHolder viewHolder,
                              View.OnClickListener clientSectionClickListener) {

    }

    @Override
    public void setupListView(ANCSmartRegisterClient client, NativeANCSmartRegisterViewHolder viewHolder, View.OnClickListener clientSectionClickListener) {

    }

    @Override
    public void setupListView(FPSmartRegisterClient client, NativeFPSmartRegisterViewHolder viewHolder, View.OnClickListener clientSectionClickListener) {

    }

    @Override
    public void setupListView(PNCSmartRegisterClient client, NativePNCSmartRegisterViewHolder viewHolder, View.OnClickListener clientSectionClickListener) {

    }
}
