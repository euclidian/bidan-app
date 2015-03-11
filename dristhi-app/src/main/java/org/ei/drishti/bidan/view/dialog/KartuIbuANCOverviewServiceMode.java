package org.ei.drishti.bidan.view.dialog;

import android.view.View;

import org.ei.drishti.Context;
import org.ei.drishti.R;
import org.ei.drishti.bidan.view.contract.KartuIbuANCClient;
import org.ei.drishti.bidan.view.contract.KartuIbuClient;
import org.ei.drishti.bidan.view.viewHolder.NativeKIANCRegisterViewHolder;
import org.ei.drishti.bidan.view.viewHolder.NativeKIRegisterViewHolder;
import org.ei.drishti.provider.SmartRegisterClientsProvider;
import org.ei.drishti.view.activity.SecuredNativeSmartRegisterActivity;
import org.ei.drishti.view.contract.ANCSmartRegisterClient;
import org.ei.drishti.view.contract.ChildSmartRegisterClient;
import org.ei.drishti.view.contract.FPSmartRegisterClient;
import org.ei.drishti.view.contract.pnc.PNCSmartRegisterClient;
import org.ei.drishti.view.viewHolder.NativeANCSmartRegisterViewHolder;
import org.ei.drishti.view.viewHolder.NativeChildSmartRegisterViewHolder;
import org.ei.drishti.view.viewHolder.NativeFPSmartRegisterViewHolder;
import org.ei.drishti.view.viewHolder.NativePNCSmartRegisterViewHolder;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class KartuIbuANCOverviewServiceMode extends BidanServiceModeOption {

    public KartuIbuANCOverviewServiceMode(SmartRegisterClientsProvider provider) {
        super(provider);
    }

    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.anc_service_mode_overview);
    }

    @Override
    public SecuredNativeSmartRegisterActivity.ClientsHeaderProvider getHeaderProvider() {
        return new SecuredNativeSmartRegisterActivity.ClientsHeaderProvider() {
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
                return new int[]{24, 9, 12, 12, 12, 12, 19};
            }

            @Override
            public int[] headerTextResourceIds() {
                return new int[]{
                        R.string.header_name, R.string.header_id, R.string.header_anc_status,
                        R.string.header_risk_factors, R.string.header_visits, R.string.header_tt,
                        R.string.header_edit};
            }
        };
    }

    @Override
    public void setupListView(ChildSmartRegisterClient client, NativeChildSmartRegisterViewHolder viewHolder, View.OnClickListener clientSectionClickListener) {

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

    @Override
    public void setupListView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder, View.OnClickListener clientSectionClickListener) {

    }

    @Override
    public void setupListView(KartuIbuANCClient client, NativeKIANCRegisterViewHolder viewHolder, View.OnClickListener clientSectionClickListener) {

    }
}
