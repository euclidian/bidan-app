package org.ei.bidan.bidan.view.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.ei.bidan.R;
import org.ei.bidan.bidan.view.customControls.BidanClientProfileView;
import org.ei.bidan.bidan.view.customControls.ClientAnakBirthStatusView;
import org.ei.bidan.view.customControls.ClientProfileView;

/**
 * Created by Dimas Ciputra on 4/10/15.
 */
public class NativeAnakRegisterViewHolder {

    private final BidanClientProfileView profileViewLayout;
    private final ClientAnakBirthStatusView clientAnakBirthStatusView;
    private final ImageButton editButton;
    private final TextView txtDobView;
    private final TextView txtIbuKiNo;
    private final TextView txtBirthWeight;
    private final TextView txtBirthCondition;

    private final ViewGroup serviceModeViewsHolder;
    private final ViewGroup serviceModeOverviewView;
    private final ViewGroup serviceModeImmunizationView;

    private final TextView txtLastServiceDate;
    private final TextView txtLastServiceName;

    private final TextView hb07;
    private final TextView bcgPolio1;
    private final TextView dptHb1Polio2;
    private final TextView dptHb2Polio3;
    private final TextView dptHb3Polio4;
    private final TextView campak;

    public NativeAnakRegisterViewHolder(ViewGroup itemView) {
        profileViewLayout = (BidanClientProfileView) itemView.findViewById(R.id.profile_info_layout);
        profileViewLayout.initialize();

        txtLastServiceDate = (TextView) itemView.findViewById(R.id.anak_last_service_date);
        txtLastServiceName = (TextView) itemView.findViewById(R.id.anak_last_service_name);

        serviceModeViewsHolder = (ViewGroup) itemView.findViewById(R.id.anak_register_service_mode_options_view);
        serviceModeOverviewView = (ViewGroup) serviceModeViewsHolder.findViewById(R.id.overview_service_mode_views);
        serviceModeImmunizationView = (ViewGroup) serviceModeViewsHolder.findViewById(R.id.immunization_service_mode_views);

        clientAnakBirthStatusView = (ClientAnakBirthStatusView)
                serviceModeOverviewView.findViewById(R.id.client_anak_birth_status_layout);
        clientAnakBirthStatusView.initialize();

        editButton = (ImageButton) serviceModeOverviewView.findViewById(R.id.btn_edit);
        txtDobView = (TextView) serviceModeOverviewView.findViewById(R.id.anak_register_dob);
        txtIbuKiNo = (TextView) serviceModeOverviewView.findViewById(R.id.txt_ibu_ki_no);

        txtBirthWeight = (TextView) clientAnakBirthStatusView.findViewById(R.id.txt_birth_weight);
        txtBirthCondition = txtBirthWeight;

        hb07 = (TextView) serviceModeImmunizationView.findViewById(R.id.hb_0_7);
        bcgPolio1 = (TextView) serviceModeImmunizationView.findViewById(R.id.bcg_polio1);
        dptHb1Polio2 = (TextView) serviceModeImmunizationView.findViewById(R.id.dpt_hb1_polio2);
        dptHb2Polio3 = (TextView) serviceModeImmunizationView.findViewById(R.id.dpt_hb2_polio3);
        dptHb3Polio4 = (TextView) serviceModeImmunizationView.findViewById(R.id.dpt_hb3_polio4);
        campak = (TextView) serviceModeImmunizationView.findViewById(R.id.campak);
    }

    public void hideAllServiceModeOptions() {
        getServiceModeOverviewView().setVisibility(View.GONE);
        getServiceModeImmunizationView().setVisibility(View.GONE);
    }

    public BidanClientProfileView getProfileViewLayout() {
        return profileViewLayout;
    }

    public ClientAnakBirthStatusView getClientAnakBirthStatusView() {
        return clientAnakBirthStatusView;
    }

    public ImageButton getEditButton() {
        return editButton;
    }

    public TextView getTxtDobView() {
        return txtDobView;
    }

    public TextView getTxtIbuKiNo() {
        return txtIbuKiNo;
    }

    public TextView getTxtBirthWeight() {
        return txtBirthWeight;
    }

    public TextView getTxtBirthCondition() {
        return txtBirthCondition;
    }

    public ViewGroup getServiceModeViewsHolder() {
        return serviceModeViewsHolder;
    }

    public ViewGroup getServiceModeOverviewView() {
        return serviceModeOverviewView;
    }

    public ViewGroup getServiceModeImmunizationView() {
        return serviceModeImmunizationView;
    }

    public TextView getTxtLastServiceDate() {
        return txtLastServiceDate;
    }

    public TextView getTxtLastServiceName() {
        return txtLastServiceName;
    }

    public TextView getHb07() {
        return hb07;
    }

    public TextView getBcgPolio1() {
        return bcgPolio1;
    }

    public TextView getDptHb1Polio2() {
        return dptHb1Polio2;
    }

    public TextView getDptHb2Polio3() {
        return dptHb2Polio3;
    }

    public TextView getDptHb3Polio4() {
        return dptHb3Polio4;
    }

    public TextView getCampak() {
        return campak;
    }
}