package org.ei.drishti.bidan.view.viewHolder;

import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ei.drishti.R;
import org.ei.drishti.bidan.view.contract.KartuIbuClient;
import org.ei.drishti.bidan.view.customControls.BidanClientProfileView;
import org.ei.drishti.view.viewHolder.ProfilePhotoLoader;

import javax.xml.soap.Text;

/**
 * Created by Dimas Ciputra on 2/18/15.
 */
public class NativeKIRegisterViewHolder {
    private final BidanClientProfileView profileInfoLayout;
    private final TextView txtPuskesmas;
    private TextView txtTglPeriksa;
    private TextView txtEdd;
    private TextView txtNoIbu;
    private final ImageButton editButton;

    public NativeKIRegisterViewHolder(ViewGroup itemView) {
        this.profileInfoLayout = (BidanClientProfileView) itemView.findViewById(R.id.profile_info_layout_ki);
        this.profileInfoLayout.initialize();

        this.txtPuskesmas = (TextView) itemView.findViewById(R.id.puskesmas);
        this.txtTglPeriksa = (TextView) itemView.findViewById(R.id.tgl_periksa);
        this.txtEdd = (TextView) itemView.findViewById(R.id.edd);
        this.txtNoIbu = (TextView) itemView.findViewById(R.id.no_ibu);

        this.editButton = (ImageButton) itemView.findViewById(R.id.btn_edit);
    }

    public BidanClientProfileView profileInfoLayout() {
        return profileInfoLayout;
    }
    public TextView txtPuskesmas() {
        return txtPuskesmas;
    }
    public TextView txtNoIbu() { return txtNoIbu; }
    public TextView txtTglPeriksa() { return txtTglPeriksa; }
    public TextView txtEdd() { return txtEdd; }
    public ImageButton editButton() { return editButton; }
}
