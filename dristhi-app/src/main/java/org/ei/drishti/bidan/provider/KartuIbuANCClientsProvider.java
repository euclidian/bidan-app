package org.ei.drishti.bidan.provider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import org.ei.drishti.R;
import org.ei.drishti.bidan.view.contract.KartuIbuANCClient;
import org.ei.drishti.bidan.view.controller.KartuIbuANCRegisterController;
import org.ei.drishti.bidan.view.viewHolder.NativeKIANCRegisterViewHolder;
import org.ei.drishti.bidan.view.viewHolder.NativeKIRegisterViewHolder;
import org.ei.drishti.provider.SmartRegisterClientsProvider;
import org.ei.drishti.view.activity.SecuredActivity;
import org.ei.drishti.view.contract.SmartRegisterClient;
import org.ei.drishti.view.contract.SmartRegisterClients;
import org.ei.drishti.view.dialog.FilterOption;
import org.ei.drishti.view.dialog.ServiceModeOption;
import org.ei.drishti.view.dialog.SortOption;
import org.ei.drishti.view.viewHolder.ECProfilePhotoLoader;
import org.ei.drishti.view.viewHolder.OnClickFormLauncher;
import org.ei.drishti.view.viewHolder.ProfilePhotoLoader;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class KartuIbuANCClientsProvider implements SmartRegisterClientsProvider {

    private final LayoutInflater inflater;
    private final SecuredActivity activity;
    private final View.OnClickListener onClickListener;
    private final ProfilePhotoLoader photoLoader;

    private final AbsListView.LayoutParams clientViewLayoutParams;

    protected KartuIbuANCRegisterController controller;

    public KartuIbuANCClientsProvider(SecuredActivity activity, View.OnClickListener onClickListener, KartuIbuANCRegisterController controller) {
        this.activity = activity;
        this.onClickListener = onClickListener;
        this.controller = controller;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        photoLoader = new ECProfilePhotoLoader(activity.getResources(),
                activity.getResources().getDrawable(R.drawable.woman_placeholder));

        clientViewLayoutParams = new AbsListView.LayoutParams(MATCH_PARENT,
                (int) activity.getResources().getDimension(R.dimen.list_item_height));

    }

    @Override
    public View getView(SmartRegisterClient client, View convertView, ViewGroup viewGroup) {
        ViewGroup itemView;
        NativeKIANCRegisterViewHolder viewHolder;
        if (convertView == null) {
            itemView = (ViewGroup) inflater().inflate(R.layout.smart_register_ki_anc_client, null);
            viewHolder = new NativeKIANCRegisterViewHolder(itemView);
            itemView.setTag(viewHolder);
        } else {
            itemView = (ViewGroup) convertView;
            viewHolder = (NativeKIANCRegisterViewHolder) itemView.getTag();
        }

        KartuIbuANCClient kartuIbuClient = (KartuIbuANCClient) client;
        setupClientProfileView(kartuIbuClient, viewHolder);
        setupIdDetailsView(kartuIbuClient, viewHolder);
        setupANCStatusView(kartuIbuClient, viewHolder);

        itemView.setLayoutParams(clientViewLayoutParams);
        return itemView;
    }

    private void setupClientProfileView(KartuIbuANCClient client, NativeKIANCRegisterViewHolder viewHolder) {
        viewHolder.profileInfoLayout().bindData(client, photoLoader);
        viewHolder.profileInfoLayout().setOnClickListener(onClickListener);
        viewHolder.profileInfoLayout().setTag(client);
    }

    private void setupIdDetailsView(KartuIbuANCClient client, NativeKIANCRegisterViewHolder viewHolder) {
        viewHolder.ancId().setText(client.kiNumber()==null?"-":client.kiNumber());
    }

    private void setupANCStatusView(KartuIbuANCClient client, NativeKIANCRegisterViewHolder viewHolder) {
        viewHolder.ancStatus().setText(client.ancStatus()==null?"-":client.ancStatus());
    }

    @Override
    public SmartRegisterClients getClients() {
        return controller.getKartuIbuANCClients();
    }

    @Override
    public SmartRegisterClients updateClients(FilterOption villageFilter, ServiceModeOption serviceModeOption, FilterOption searchFilter, SortOption sortOption) {
        return getClients().applyFilter(villageFilter, serviceModeOption, searchFilter, sortOption);
    }

    @Override
    public void onServiceModeSelected(ServiceModeOption serviceModeOption) {

    }

    @Override
    public OnClickFormLauncher newFormLauncher(String formName, String entityId, String metaData) {
        return new OnClickFormLauncher(activity, formName, entityId, metaData);
    }

    public LayoutInflater inflater() {
        return inflater;
    }
}
