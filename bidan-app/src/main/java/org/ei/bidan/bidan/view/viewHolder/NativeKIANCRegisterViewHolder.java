package org.ei.bidan.bidan.view.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
    private final TextView ancStatusBB;
    private final TextView ancStatusTB;
    private final TextView pemeriksaanLILA;
    private final TextView pemeriksaanBB;
    private final TextView penyakitKronis;
    private final TextView lblPenyakitKronis;
    private final TextView alergi;
    private final TextView lblAlergi;
    private final ViewGroup layoutResikoANC;
    private final ImageButton editButton;
    private final TextView edd;

    public NativeKIANCRegisterViewHolder(ViewGroup itemView) {
        this.profileInfoLayout = (BidanClientProfileView) itemView.findViewById(R.id.profile_info_layout_ki);
        this.profileInfoLayout.initialize();

        this.ancId = (TextView) itemView.findViewById(R.id.anc_id);
        this.ancStatusBB = (TextView) itemView.findViewById(R.id.txt_ki_anc_bb);
        this.ancStatusTB = (TextView) itemView.findViewById(R.id.txt_ki_anc_tb);
        this.pemeriksaanLILA = (TextView) itemView.findViewById(R.id.txt_ki_lila_bb);
        this.pemeriksaanBB = (TextView) itemView.findViewById(R.id.txt_ki_beratbadan_tb);
        this.penyakitKronis = (TextView) itemView.findViewById(R.id.txt_ki_anc_penyakit_kronis);
        this.lblPenyakitKronis = (TextView) itemView.findViewById(R.id.lbl_ki_anc_penyakit_kronis);
        this.alergi = (TextView) itemView.findViewById(R.id.txt_ki_anc_alergi);
        this.lblAlergi = (TextView) itemView.findViewById(R.id.lbl_ki_anc_alergi);
        this.layoutResikoANC = (ViewGroup) itemView.findViewById(R.id.layout_resiko_anc);
        this.editButton = (ImageButton) itemView.findViewById(R.id.btn_edit);
        this.edd = (TextView) itemView.findViewById(R.id.txt_edd);
    }

    public ImageButton editButton() { return editButton; }
    public TextView ancStatusBB() { return ancStatusBB; }
    public TextView ancStatusTB() { return ancStatusTB; }
    public TextView ancId() { return ancId; }
    public TextView edd() { return edd; }
    public BidanClientProfileView profileInfoLayout() {
        return profileInfoLayout;
    }
    public TextView getPemeriksaanLILA() {
        return pemeriksaanLILA;
    }
    public TextView getPemeriksaanBB() {
        return pemeriksaanBB;
    }

    public ViewGroup getLayoutResikoANC() {
        return layoutResikoANC;
    }

    public TextView getLblAlergi() {
        return lblAlergi;
    }

    public TextView getAlergi() {
        return alergi;
    }

    public TextView getLblPenyakitKronis() {
        return lblPenyakitKronis;
    }

    public TextView getPenyakitKronis() {
        return penyakitKronis;
    }
}
