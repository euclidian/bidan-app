package org.ei.bidan.bidan.view.viewHolder;

import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.ei.bidan.R;
import org.ei.bidan.bidan.view.customControls.BidanClientProfileView;
import org.ei.bidan.bidan.view.customControls.BidanClientStatusView;
import org.ei.bidan.bidan.view.customControls.BidanObsetriView;
import org.ei.bidan.view.customControls.ClientChildrenView;

/**
 * Created by Dimas Ciputra on 2/18/15.
 */
public class NativeKIRegisterViewHolder {
    private final BidanClientProfileView profileInfoLayout;
    private final BidanObsetriView bidanObsetriView;
    private final ClientChildrenView childrenView;
    private TextView txtKBMethod;
    private TextView txtKBStart;
    private TextView txtEdd;
    private TextView txtEddDue;
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

        childrenView = (ClientChildrenView) itemView.findViewById(R.id.children_layout);
        childrenView.initialize();

        this.txtEdd = (TextView) itemView.findViewById(R.id.txt_edd);

        this.txtEddDue = (TextView) itemView.findViewById(R.id.txt_edd_due);

        this.txtNoIbu = (TextView) itemView.findViewById(R.id.no_ibu);

        this.editButton = (ImageButton) itemView.findViewById(R.id.btn_edit);
    }

    public BidanClientProfileView profileInfoLayout() {
        return profileInfoLayout;
    }
    public BidanClientStatusView statusView() { return statusView; }
    public BidanObsetriView bidanObsetriView() { return bidanObsetriView; }
    public ClientChildrenView childrenView() {
        return childrenView;
    }
    public TextView txtNoIbu() { return txtNoIbu; }
    public TextView txtEdd() { return txtEdd; }
    public TextView txtEddDue() { return txtEddDue; }
    public ImageButton editButton() { return editButton; }
}
