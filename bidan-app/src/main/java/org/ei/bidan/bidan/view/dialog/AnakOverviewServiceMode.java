package org.ei.bidan.bidan.view.dialog;

import android.graphics.drawable.Drawable;
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
import org.ei.bidan.view.contract.ANCSmartRegisterClient;
import org.ei.bidan.view.contract.ChildSmartRegisterClient;
import org.ei.bidan.view.contract.FPSmartRegisterClient;
import org.ei.bidan.view.contract.pnc.PNCSmartRegisterClient;
import org.ei.bidan.view.viewHolder.NativeANCSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.NativeChildSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.NativeFPSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.NativePNCSmartRegisterViewHolder;

import static android.view.View.VISIBLE;
import static org.ei.bidan.view.activity.SecuredNativeSmartRegisterActivity.ClientsHeaderProvider;

public class AnakOverviewServiceMode extends BidanServiceModeOption {

    public AnakOverviewServiceMode(SmartRegisterClientsProvider provider) {
        super(provider);
    }

    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.child_service_mode_overview);
    }

    @Override
    public ClientsHeaderProvider getHeaderProvider() {
        return new ClientsHeaderProvider() {
            @Override
            public int count() {
                return 6;
            }

            @Override
            public int weightSum() {
                return 100;
            }

            @Override
            public int[] weights() {
                return new int[]{26, 14, 12, 15, 23, 10};
            }

            @Override
            public int[] headerTextResourceIds() {
                return new int[]{
                        R.string.header_name, R.string.header_id_no, R.string.header_dob,
                        R.string.header_last_service, R.string.header_birth_status, R.string.header_edit};
            }
        };
    }

    @Override
    public void setupListView(AnakClient client,
                              NativeAnakRegisterViewHolder viewHolder,
                              View.OnClickListener clientSectionClickListener) {
        viewHolder.getServiceModeOverviewView().setVisibility(VISIBLE);

        setupDobView(client, viewHolder);
        setupLastServiceView(client, viewHolder);
        setupBirthStatus(client, viewHolder);
        setupEditView(client, viewHolder, clientSectionClickListener);
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

    private void setupDobView(AnakClient client, NativeAnakRegisterViewHolder viewHolder) {
        viewHolder.getTxtDobView().setText(client.dateOfBirth());
    }

    private void setupLastServiceView(AnakClient client, NativeAnakRegisterViewHolder viewHolder) {
        // ServiceProvidedDTO lastService = client.lastServiceProvided();
        viewHolder.getTxtLastServiceDate().setText("-");
        viewHolder.getTxtLastServiceName().setText(client.getServiceAtBirth());
    }

    private void setupBirthStatus(AnakClient client,
                                 NativeAnakRegisterViewHolder viewHolder) {
        viewHolder.getClientAnakBirthStatusView().bindData(client);
    }

    private void setupEditView(AnakClient client,
                               NativeAnakRegisterViewHolder viewHolder, View.OnClickListener onClickListener) {
        Drawable iconPencilDrawable = Context.getInstance().applicationContext().getResources().getDrawable(R.drawable.ic_pencil);
        viewHolder.getEditButton().setImageDrawable(iconPencilDrawable);
        viewHolder.getEditButton().setOnClickListener(onClickListener);
        viewHolder.getEditButton().setTag(client);
    }
}
