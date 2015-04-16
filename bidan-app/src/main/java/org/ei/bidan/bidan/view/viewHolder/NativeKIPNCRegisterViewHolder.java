package org.ei.bidan.bidan.view.viewHolder;

import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.ei.bidan.R;
import org.ei.bidan.bidan.view.customControls.BidanClientProfileView;

import javax.xml.soap.Text;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class NativeKIPNCRegisterViewHolder {

    private final BidanClientProfileView profileInfoLayout;
    private final TextView pncId;
    private final TextView pncPlan;
    private final TextView komplikasi;
    private final TextView metodeKontrasepsi;
    private final ViewGroup tandaVitalLayout;
    private final TextView tdSistolik;
    private final TextView tdDiastolik;
    private final TextView tdSuhu;
    private final ImageButton editButton;

    public NativeKIPNCRegisterViewHolder(ViewGroup itemView) {
        this.profileInfoLayout = (BidanClientProfileView) itemView.findViewById(R.id.profile_info_layout_ki);
        this.profileInfoLayout.initialize();

        this.pncId = (TextView) itemView.findViewById(R.id.pnc_id);
        this.pncPlan = (TextView) itemView.findViewById(R.id.pnc_plan);
        this.komplikasi = (TextView) itemView.findViewById(R.id.txt_komplikasi);
        this.metodeKontrasepsi = (TextView) itemView.findViewById(R.id.txt_metode_kontrasepsi);
        this.tandaVitalLayout = (ViewGroup) itemView.findViewById(R.id.tanda_vital_layout);
        this.tdSistolik = (TextView) tandaVitalLayout.findViewById(R.id.txt_td_sistolik);
        this.tdDiastolik = (TextView) tandaVitalLayout.findViewById(R.id.txt_td_diastolik);
        this.tdSuhu = (TextView) tandaVitalLayout.findViewById(R.id.txt_td_suhu);
        this.editButton = (ImageButton) itemView.findViewById(R.id.btn_edit);
    }

    public TextView plan() { return pncPlan; }
    public TextView pncId() { return pncId; }
    public BidanClientProfileView profileInfoLayout() {
        return profileInfoLayout;
    }

    public TextView getKomplikasi() {
        return komplikasi;
    }

    public TextView getMetodeKontrasepsi() {
        return metodeKontrasepsi;
    }

    public ViewGroup getTandaVitalLayout() {
        return tandaVitalLayout;
    }

    public TextView getTdSistolik() {
        return tdSistolik;
    }

    public TextView getTdDiastolik() {
        return tdDiastolik;
    }

    public TextView getTdSuhu() {
        return tdSuhu;
    }

    public ImageButton editButton() {
        return editButton;
    }
}
