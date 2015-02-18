package org.ei.drishti.bidan.kartuibu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ei.drishti.R;
import org.ei.drishti.view.contract.SmartRegisterClient;
import org.ei.drishti.view.viewHolder.ProfilePhotoLoader;

/**
 * Created by Dimas Ciputra on 2/18/15.
 */
public class NativeKIRegisterViewHolder {
    private final RelativeLayout profileInfoLayout;
    private final TextView txtPuskesmas;
    private ImageView imgProfileView;
    private TextView txtNameView;
    private TextView txtAgeView;
    private TextView txtVillageNameView;
    private TextView txtOutOfArea;

    public NativeKIRegisterViewHolder(ViewGroup itemView) {
        this.profileInfoLayout = (RelativeLayout) itemView.findViewById(R.id.profile_info_layout_ki);

        this.txtPuskesmas = (TextView) itemView.findViewById(R.id.txt_puskesmas);

        // Initialize profile info layout
        initializeProfileInfoLayout(this.profileInfoLayout);
    }

    private void initializeProfileInfoLayout(RelativeLayout layout) {
        this.imgProfileView = (ImageView) layout.findViewById(R.id.img_profile);
        this.txtNameView = (TextView) layout.findViewById(R.id.wife_name);
        this.txtAgeView = (TextView) layout.findViewById(R.id.wife_age);
        this.txtVillageNameView = (TextView) layout.findViewById(R.id.village_name);
        this.txtOutOfArea = (TextView) layout.findViewById(R.id.is_out_of_area);
    }

    public RelativeLayout profileInfoLayout() {
        return profileInfoLayout;
    }
    public TextView txtPuskesmas() {
        return txtPuskesmas;
    }

    public void bindDataProfile(KartuIbuClient client, ProfilePhotoLoader photoLoader) {
        this.imgProfileView.setBackground(photoLoader.get(client));
        this.txtNameView.setText(client.getWifeName() != null ? client.getWifeName() : "");
        this.txtVillageNameView.setText(client.getKabupaten() != null ? client.getKabupaten() : "");
        this.txtAgeView.setText(client.getWifeAge() != null ? "("+ client.getWifeAge() + ")" : "");
        this.txtOutOfArea.setText("");
    }
}
