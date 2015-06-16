package org.ei.bidan.bidan.provider;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.google.common.base.Strings;

import org.ei.bidan.R;
import org.ei.bidan.bidan.view.contract.KartuIbuANCClient;
import org.ei.bidan.bidan.view.contract.KartuIbuPNCClient;
import org.ei.bidan.bidan.view.controller.KartuIbuPNCRegisterController;
import org.ei.bidan.bidan.view.viewHolder.NativeKIANCRegisterViewHolder;
import org.ei.bidan.bidan.view.viewHolder.NativeKIPNCRegisterViewHolder;
import org.ei.bidan.provider.SmartRegisterClientsProvider;
import org.ei.bidan.view.activity.SecuredActivity;
import org.ei.bidan.view.contract.SmartRegisterClient;
import org.ei.bidan.view.contract.SmartRegisterClients;
import org.ei.bidan.view.dialog.FilterOption;
import org.ei.bidan.view.dialog.ServiceModeOption;
import org.ei.bidan.view.dialog.SortOption;
import org.ei.bidan.view.viewHolder.ECProfilePhotoLoader;
import org.ei.bidan.view.viewHolder.OnClickFormLauncher;
import org.ei.bidan.view.viewHolder.ProfilePhotoLoader;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class KartuIbuPNCClientsProvider implements SmartRegisterClientsProvider {

    private final LayoutInflater inflater;
    private final SecuredActivity activity;
    private final View.OnClickListener onClickListener;
    private final ProfilePhotoLoader photoLoader;

    private final AbsListView.LayoutParams clientViewLayoutParams;

    protected KartuIbuPNCRegisterController controller;

    private Drawable iconPencilDrawable;

    public KartuIbuPNCClientsProvider(SecuredActivity activity, View.OnClickListener onClickListener, KartuIbuPNCRegisterController controller) {
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
        NativeKIPNCRegisterViewHolder viewHolder;
        if (convertView == null) {
            itemView = (ViewGroup) inflater().inflate(R.layout.smart_register_ki_pnc_client, null);
            viewHolder = new NativeKIPNCRegisterViewHolder(itemView);
            itemView.setTag(viewHolder);
        } else {
            itemView = (ViewGroup) convertView;
            viewHolder = (NativeKIPNCRegisterViewHolder) itemView.getTag();
        }

        KartuIbuPNCClient kartuIbuClient = (KartuIbuPNCClient) client;
        setupClientProfileView(kartuIbuClient, viewHolder);
        setupIdDetailsView(kartuIbuClient, viewHolder);
        setupPNCPlan(kartuIbuClient, viewHolder);
        setupKomplikasiView(kartuIbuClient, viewHolder);
        setupMetodeKontrasepsiView(kartuIbuClient, viewHolder);
        setupTandaVitalView(kartuIbuClient, viewHolder);
        setupEditView(kartuIbuClient, viewHolder);

        itemView.setLayoutParams(clientViewLayoutParams);
        return itemView;
    }

    private void setupTandaVitalView(KartuIbuPNCClient client, NativeKIPNCRegisterViewHolder viewHolder) {
        viewHolder.getTdDiastolik().setText(client.tdDiastolik());
        viewHolder.getTdSistolik().setText(client.tdSistolik());
        viewHolder.getTdSuhu().setText(client.tdSuhu());
    }

    private void setupMetodeKontrasepsiView(KartuIbuPNCClient client, NativeKIPNCRegisterViewHolder viewHolder) {
        viewHolder.kondisiIbu().setText(client.motherCondition());
        viewHolder.kondisiAnak1().setText(client.getLastChild().getBirthCondition());
        viewHolder.kondisiAnak2().setText(client.getLastChild().gender() + " , " + client.getLastChild().weight());
    }

    private void setupKomplikasiView(KartuIbuPNCClient client, NativeKIPNCRegisterViewHolder viewHolder) {
        viewHolder.getKomplikasi().setText(client.komplikasi() + " " + client.otherKomplikasi());
    }


    private void setupEditView(KartuIbuPNCClient client, NativeKIPNCRegisterViewHolder viewHolder) {
        if (iconPencilDrawable == null) {
            iconPencilDrawable = activity.getApplicationContext().getResources().getDrawable(R.drawable.ic_pencil);
        }
        viewHolder.editButton().setImageDrawable(iconPencilDrawable);
        viewHolder.editButton().setOnClickListener(onClickListener);
        viewHolder.editButton().setTag(client);
    }


    private void setupHighlightColor(ViewGroup itemView, int index) {
        if(index%2==0) {
            itemView.setBackgroundColor(Color.parseColor("#E0F5FF"));
        } else {
            itemView.setBackgroundColor(Color.WHITE);
        }
    }

    private void setupClientProfileView(KartuIbuPNCClient client, NativeKIPNCRegisterViewHolder viewHolder) {
        viewHolder.profileInfoLayout().bindData(client, photoLoader);
        viewHolder.profileInfoLayout().setOnClickListener(onClickListener);
        viewHolder.profileInfoLayout().setTag(client);
    }

    private void setupIdDetailsView(KartuIbuPNCClient client, NativeKIPNCRegisterViewHolder viewHolder) {
        viewHolder.pncId().setText(client.kiNumber()==null?"-":client.kiNumber());
    }

    private void setupPNCPlan(KartuIbuPNCClient client, NativeKIPNCRegisterViewHolder viewHolder) {
        viewHolder.dokTempat().setText(client.tempatPersalinan());
        viewHolder.dokTipe().setText(client.tipePersalinan());
        viewHolder.dokTanggalBersalin().setText(client.getLastBirth());
    }

    @Override
    public SmartRegisterClients getClients() {
        return controller.getKartuIbuPNCClients();
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
