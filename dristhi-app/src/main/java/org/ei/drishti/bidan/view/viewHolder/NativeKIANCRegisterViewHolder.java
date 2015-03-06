package org.ei.drishti.bidan.view.viewHolder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ei.drishti.R;
import org.ei.drishti.bidan.view.contract.KartuIbuANCClient;
import org.ei.drishti.view.viewHolder.ProfilePhotoLoader;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class NativeKIANCRegisterViewHolder {

    private final RelativeLayout profileInfoLayout;
    private ImageView imgProfileView;
    private TextView txtNameView;
    private TextView txtAgeView;
    private TextView txtVillageNameView;
    private TextView txtOutOfArea;

    public NativeKIANCRegisterViewHolder(ViewGroup itemView) {
        this.profileInfoLayout = (RelativeLayout) itemView.findViewById(R.id.profile_info_layout_ki);
        // Initialize profile info layout
        initializeProfileInfoLayout(this.profileInfoLayout);
    }

    public void initializeProfileInfoLayout(RelativeLayout layout) {
        this.imgProfileView = (ImageView) layout.findViewById(R.id.img_profile);
        this.txtNameView = (TextView) layout.findViewById(R.id.wife_name);
        this.txtAgeView = (TextView) layout.findViewById(R.id.wife_age);
        this.txtVillageNameView = (TextView) layout.findViewById(R.id.village_name);
        this.txtOutOfArea = (TextView) layout.findViewById(R.id.is_out_of_area);
    }

    public RelativeLayout profileInfoLayout() {
        return profileInfoLayout;
    }

    public void bindDataProfile(KartuIbuANCClient client, ProfilePhotoLoader photoLoader) {
        this.imgProfileView.setBackground(photoLoader.get(client));
        this.txtNameView.setText(client.name() != null ? client.name() : "");
        this.txtVillageNameView.setText(client.village() != null ? client.village() : "");
        this.txtAgeView.setText(client.ageInString() != null ? "("+ client.ageInString() + ")" : "");
        this.txtOutOfArea.setText("");
    }
}
