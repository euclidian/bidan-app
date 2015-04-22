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
public class NativeKIANCRegisterViewHolder {

    private final BidanClientProfileView profileInfoLayout;
    private final TextView ancId;
    private final TextView ancStatus;
    private final TextView riskFactors;
    private final TextView kunjungan;
    private final TextView ttImunisasi;
    private final ImageButton editButton;
    private final TextView edd;

    public NativeKIANCRegisterViewHolder(ViewGroup itemView) {
        this.profileInfoLayout = (BidanClientProfileView) itemView.findViewById(R.id.profile_info_layout_ki);
        this.profileInfoLayout.initialize();

        this.ancId = (TextView) itemView.findViewById(R.id.anc_id);
        this.ancStatus = (TextView) itemView.findViewById(R.id.anc_status);
        this.riskFactors = (TextView) itemView.findViewById(R.id.risk_factors);
        this.kunjungan = (TextView) itemView.findViewById(R.id.kunjungan);
        this.ttImunisasi = (TextView) itemView.findViewById(R.id.tt_imunisasi);
        this.editButton = (ImageButton) itemView.findViewById(R.id.btn_edit);
        this.edd = (TextView) itemView.findViewById(R.id.txt_edd);
    }

    public ImageButton editButton() { return editButton; }
    public TextView ancStatus() { return ancStatus; }
    public TextView ancId() { return ancId; }
    public TextView riskFactors() { return riskFactors; }
    public TextView kunjungan() { return kunjungan; }
    public TextView ttImunisasi() { return ttImunisasi; }
    public TextView edd() { return edd; }
    public BidanClientProfileView profileInfoLayout() {
        return profileInfoLayout;
    }
}
