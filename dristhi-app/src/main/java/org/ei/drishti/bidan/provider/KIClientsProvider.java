package org.ei.drishti.bidan.provider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import org.ei.drishti.R;
import org.ei.drishti.bidan.domain.KartuIbu;
import org.ei.drishti.bidan.view.contract.KartuIbuClient;
import org.ei.drishti.bidan.view.controller.KartuIbuRegisterController;
import org.ei.drishti.bidan.view.viewHolder.NativeKIRegisterViewHolder;
import org.ei.drishti.provider.SmartRegisterClientsProvider;
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
 * Created by Dimas Ciputra on 2/16/15.
 */
public class KIClientsProvider implements SmartRegisterClientsProvider {

    private final LayoutInflater inflater;
    private final Context context;
    private final View.OnClickListener onClickListener;
    private final ProfilePhotoLoader photoLoader;

    private final AbsListView.LayoutParams clientViewLayoutParams;

    private Drawable iconPencilDrawable;

    protected KartuIbuRegisterController controller;

    public KIClientsProvider(Context context, View.OnClickListener onClickListener, KartuIbuRegisterController controller) {
        this.context = context;
        this.onClickListener = onClickListener;
        this.controller = controller;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        photoLoader = new ECProfilePhotoLoader(context.getResources(),
                context.getResources().getDrawable(R.drawable.woman_placeholder));

        clientViewLayoutParams = new AbsListView.LayoutParams(MATCH_PARENT,
                (int) context.getResources().getDimension(R.dimen.list_item_height));

    }

    @Override
    public View getView(SmartRegisterClient client, View convertView, ViewGroup viewGroup) {
        ViewGroup itemView;
        NativeKIRegisterViewHolder viewHolder;
        if (convertView == null) {
            itemView = (ViewGroup) inflater().inflate(R.layout.smart_register_ki_client, null);
            viewHolder = new NativeKIRegisterViewHolder(itemView);
            itemView.setTag(viewHolder);
        } else {
            itemView = (ViewGroup) convertView;
            viewHolder = (NativeKIRegisterViewHolder) itemView.getTag();
        }

        KartuIbuClient kartuIbuClient = (KartuIbuClient) client;
        setupClientProfileView(kartuIbuClient, viewHolder);
        setupClientPuskesmasView(kartuIbuClient, viewHolder);
        setupEditView(kartuIbuClient, viewHolder);
        setupClientNoIbuView(kartuIbuClient, viewHolder);
        setupClientTglPeriksaView(kartuIbuClient, viewHolder);
        setupClientEDDView(kartuIbuClient, viewHolder);

        itemView.setLayoutParams(clientViewLayoutParams);
        return itemView;
    }

    private void setupClientProfileView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        viewHolder.profileInfoLayout().bindData(client, photoLoader);
        viewHolder.profileInfoLayout().setOnClickListener(onClickListener);
        viewHolder.profileInfoLayout().setTag(client);
    }

    private void setupClientPuskesmasView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        viewHolder.txtPuskesmas().setText(String.valueOf(client.getPuskesmas()));
    }

    private void setupClientNoIbuView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        viewHolder.txtNoIbu().setText(String.valueOf(client.getNoIbu()));
    }

    private void setupClientTglPeriksaView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        viewHolder.txtTglPeriksa().setText(String.valueOf(client.getTglPeriksa()));
    }

    private void setupClientEDDView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        viewHolder.txtEdd().setText(String.valueOf(client.getEdd()));
    }

    private void setupEditView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        if (iconPencilDrawable == null) {
            iconPencilDrawable = context.getResources().getDrawable(R.drawable.ic_pencil);
        }
        viewHolder.editButton().setImageDrawable(iconPencilDrawable);
        viewHolder.editButton().setOnClickListener(onClickListener);
        viewHolder.editButton().setTag(client);
    }

    @Override
    public SmartRegisterClients getClients() {
        return controller.getKartuIbuClients();
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
        return null;
    }

    public LayoutInflater inflater() {
        return inflater;
    }
}
