package org.ei.bidan.bidan.provider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import org.ei.bidan.AllConstants;
import org.ei.bidan.R;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;
import org.ei.bidan.bidan.view.controller.KartuIbuRegisterController;
import org.ei.bidan.bidan.view.viewHolder.NativeKIRegisterViewHolder;
import org.ei.bidan.provider.SmartRegisterClientsProvider;
import org.ei.bidan.view.contract.SmartRegisterClient;
import org.ei.bidan.view.contract.SmartRegisterClients;
import org.ei.bidan.view.dialog.FilterOption;
import org.ei.bidan.view.dialog.ServiceModeOption;
import org.ei.bidan.view.dialog.SortOption;
import org.ei.bidan.view.viewHolder.ECProfilePhotoLoader;
import org.ei.bidan.view.viewHolder.OnClickFormLauncher;
import org.ei.bidan.view.viewHolder.ProfilePhotoLoader;

import android.graphics.Color;

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

    private final String maleChildAgeFormatString;
    private final String femaleChildAgeFormatString;

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


        maleChildAgeFormatString = context.getResources().getString(R.string.ec_register_male_child);
        femaleChildAgeFormatString = context.getResources().getString(R.string.ec_register_female_child);

    }

    @Override
    public View getView(int i, SmartRegisterClient client, View convertView, ViewGroup viewGroup) {
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

        if(i%2>0) {
            itemView.setBackgroundColor(Color.parseColor(AllConstants.HIGHLIGHT_COLOR));
        } else {
            itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        KartuIbuClient kartuIbuClient = (KartuIbuClient) client;
        setupClientProfileView(kartuIbuClient, viewHolder);
        setupChildrenView(kartuIbuClient, viewHolder);
        setupEditView(kartuIbuClient, viewHolder);
        setupClientNoIbuView(kartuIbuClient, viewHolder);
        setupObsetriView(kartuIbuClient, viewHolder);
        setupClientEDDView(kartuIbuClient, viewHolder);
        setupStatusView(kartuIbuClient, viewHolder);

        itemView.setLayoutParams(clientViewLayoutParams);
        return itemView;
    }

    private void setupHighlightColor(ViewGroup itemView, int index) {
        if(index%2==0) {
            itemView.setBackgroundColor(Color.parseColor("#E0F5FF"));
        } else {
            itemView.setBackgroundColor(Color.WHITE);
        }
    }

    private void setupClientProfileView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        viewHolder.profileInfoLayout().bindData(client, photoLoader);
        viewHolder.profileInfoLayout().setOnClickListener(onClickListener);
        viewHolder.profileInfoLayout().setTag(client);
    }

    private void setupChildrenView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        viewHolder.childrenView().bindKIData(client, maleChildAgeFormatString, femaleChildAgeFormatString);
    }

    private void setupClientNoIbuView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        viewHolder.txtNoIbu().setText(String.valueOf(client.getNoIbu()));
    }

    private void setupObsetriView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        viewHolder.bidanObsetriView().bind(client);
    }

    private void setupClientEDDView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        viewHolder.txtEdd().setText(String.valueOf(client.getEdd()));
        viewHolder.txtEddDue().setText(String.valueOf(client.getDueEdd()));
    }

    private void setupEditView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        if (iconPencilDrawable == null) {
            iconPencilDrawable = context.getResources().getDrawable(R.drawable.ic_pencil);
        }
        viewHolder.editButton().setImageDrawable(iconPencilDrawable);
        viewHolder.editButton().setOnClickListener(onClickListener);
        viewHolder.editButton().setTag(client);
    }

    private void setupStatusView(KartuIbuClient client, NativeKIRegisterViewHolder viewHolder) {
        viewHolder.statusView().bindData(client);
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
