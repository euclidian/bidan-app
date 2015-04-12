package org.ei.bidan.bidan.view.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.ei.bidan.R;
import org.ei.bidan.bidan.view.customControls.ClientAnakBirthStatusView;
import org.ei.bidan.view.customControls.ClientProfileView;

/**
 * Created by Dimas Ciputra on 4/10/15.
 */
public class NativeAnakRegisterViewHolder {

    private final ClientProfileView profileViewLayout;
    private final ClientAnakBirthStatusView clientAnakBirthStatusView;
    private final ImageButton editButton;
    private final TextView txtDobView;
    private final TextView txtIbuKiNo;
    private final TextView txtBirthWeight;
    private final TextView txtBirthCondition;

    private final ViewGroup serviceModeViewsHolder;
    private final ViewGroup serviceModeOverviewView;
    // private final ViewGroup serviceModeImmunizationView;

    private final TextView txtLastServiceDate;
    private final TextView txtLastServiceName;

    public NativeAnakRegisterViewHolder(ViewGroup itemView) {
        profileViewLayout = (ClientProfileView) itemView.findViewById(R.id.profile_info_layout);
        profileViewLayout.initialize();

        txtIbuKiNo = (TextView) itemView.findViewById(R.id.txt_ibu_ki_no);

        txtLastServiceDate = (TextView) itemView.findViewById(R.id.anak_last_service_date);
        txtLastServiceName = (TextView) itemView.findViewById(R.id.anak_last_service_name);

        serviceModeViewsHolder = (ViewGroup) itemView.findViewById(R.id.anak_register_service_mode_options_view);
        serviceModeOverviewView = (ViewGroup) serviceModeViewsHolder.findViewById(R.id.overview_service_mode_views);

        clientAnakBirthStatusView = (ClientAnakBirthStatusView)
                serviceModeOverviewView.findViewById(R.id.client_anak_birth_status_layout);
        clientAnakBirthStatusView.initialize();

        editButton = (ImageButton) serviceModeOverviewView.findViewById(R.id.btn_edit);
        txtDobView = (TextView) serviceModeOverviewView.findViewById(R.id.anak_register_dob);

        txtBirthWeight = (TextView) clientAnakBirthStatusView.findViewById(R.id.txt_birth_weight);
        txtBirthCondition = (TextView) clientAnakBirthStatusView.findViewById(R.id.txt_birth_weight);
    }

    public void hideAllServiceModeOptions() {
        getServiceModeOverviewView().setVisibility(View.GONE);
        //serviceModeImmunizationView().setVisibility(View.GONE);
    }

    public ClientProfileView getProfileViewLayout() {
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

    public TextView getTxtLastServiceDate() {
        return txtLastServiceDate;
    }

    public TextView getTxtLastServiceName() {
        return txtLastServiceName;
    }
}