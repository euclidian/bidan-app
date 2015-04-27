package org.ei.bidan.bidan.view.dialog;

import android.view.View;

import org.ei.bidan.Context;
import org.ei.bidan.R;
import org.ei.bidan.bidan.view.contract.AnakClient;
import org.ei.bidan.bidan.view.contract.KartuIbuANCClient;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;
import org.ei.bidan.bidan.view.viewHolder.NativeAnakRegisterViewHolder;
import org.ei.bidan.bidan.view.viewHolder.NativeKIANCRegisterViewHolder;
import org.ei.bidan.bidan.view.viewHolder.NativeKIRegisterViewHolder;
import org.ei.bidan.provider.SmartRegisterClientsProvider;
import org.ei.bidan.view.activity.SecuredNativeSmartRegisterActivity;
import org.ei.bidan.view.contract.ANCSmartRegisterClient;
import org.ei.bidan.view.contract.ChildSmartRegisterClient;
import org.ei.bidan.view.contract.FPSmartRegisterClient;
import org.ei.bidan.view.contract.pnc.PNCSmartRegisterClient;
import org.ei.bidan.view.viewHolder.NativeANCSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.NativeChildSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.NativeFPSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.NativePNCSmartRegisterViewHolder;

/**
 * Created by Dimas Ciputra on 4/27/15.
 */
public class AnakImmunizationServiceMode extends BidanServiceModeOption {

    public AnakImmunizationServiceMode(SmartRegisterClientsProvider provider) {
        super(provider);
    }

    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.child_service_mode_immunization);
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
                return new int[]{26, 12, 12, 12, 12, 12, 12};
            }

            @Override
            public int[] headerTextResourceIds() {
                return new int[]{
                        R.string.header_name, R.string.header_imunisasi_hb07, R.string.header_imunisasi_bcg_polio1,
                        R.string.header_imunisasi_dpt_hb1_polio2, R.string.header_imunisasi_dpt_hb2_polio3, R.string.header_imunisasi_dpt_hb3_polio4,
                        R.string.header_imunisasi_campak};
            }
        };
    }

    @Override
    public void setupListView(AnakClient client, NativeAnakRegisterViewHolder viewHolder, View.OnClickListener clientSectionClickListener) {
        viewHolder.getServiceModeImmunizationView().setVisibility(View.VISIBLE);
        viewHolder.getServiceModeImmunizationView().setOnClickListener(clientSectionClickListener);
        viewHolder.getServiceModeImmunizationView().setTag(client.entityId());
        setupAllImunisasi(client, viewHolder);
    }

    private void setupAllImunisasi(AnakClient client, NativeAnakRegisterViewHolder viewHolder) {
        viewHolder.getHb07().setText(client.getHb07());
        viewHolder.getBcgPolio1().setText(client.getBcgPol1());
        viewHolder.getDptHb1Polio2().setText(client.getDptHb1Pol2());
        viewHolder.getDptHb2Polio3().setText(client.getDptHb2Pol3());
        viewHolder.getDptHb3Polio4().setText(client.getDptHb3Pol4());
        viewHolder.getCampak().setText(client.getCampak());
    }

    @Override
    public void setupListView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder, View.OnClickListener clientSectionClickListener) {

    }

    @Override
    public void setupListView(KartuIbuANCClient client, NativeKIANCRegisterViewHolder viewHolder, View.OnClickListener clientSectionClickListener) {

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

}
