package org.ei.drishti.bidan.view.viewHolder;

import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.ei.drishti.R;
import org.ei.drishti.bidan.view.customControls.BidanClientProfileView;
import org.ei.drishti.bidan.view.customControls.BidanClientStatusView;
import org.ei.drishti.bidan.view.customControls.BidanObsetriView;

/**
 * Created by Dimas Ciputra on 2/18/15.
 */
public class NativeKIRegisterViewHolder {
    private final BidanClientProfileView profileInfoLayout;
    private final BidanObsetriView bidanObsetriView;
    private final TextView txtPuskesmas;
    private TextView txtEdd;
    private TextView txtNoIbu;
    private final BidanClientStatusView statusView;
    private final ImageButton editButton;

    public NativeKIRegisterViewHolder(ViewGroup itemView) {
        this.profileInfoLayout = (BidanClientProfileView) itemView.findViewById(R.id.profile_info_layout_ki);
        this.profileInfoLayout.initialize();

        this.bidanObsetriView = (BidanObsetriView) itemView.findViewById(R.id.obsetri_layout);
        this.bidanObsetriView.initialize();

        statusView = (BidanClientStatusView) itemView.findViewById(R.id.status_layout);
        statusView.initialize();

        this.txtPuskesmas = (TextView) itemView.findViewById(R.id.puskesmas);
        this.txtEdd = (TextView) itemView.findViewById(R.id.edd);
        this.txtNoIbu = (TextView) itemView.findViewById(R.id.no_ibu);

        this.editButton = (ImageButton) itemView.findViewById(R.id.btn_edit);
    }

    public BidanClientProfileView profileInfoLayout() {
        return profileInfoLayout;
    }
    public BidanClientStatusView statusView() { return statusView; }
    public BidanObsetriView bidanObsetriView() { return bidanObsetriView; }
    public TextView txtPuskesmas() {
        return txtPuskesmas;
    }
    public TextView txtNoIbu() { return txtNoIbu; }
    public TextView txtEdd() { return txtEdd; }
    public ImageButton editButton() { return editButton; }
}
