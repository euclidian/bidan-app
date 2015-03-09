package org.ei.drishti.bidan.view.viewHolder;

import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.ei.drishti.R;
import org.ei.drishti.bidan.view.customControls.BidanClientProfileView;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class NativeKIANCRegisterViewHolder {

    private final BidanClientProfileView profileInfoLayout;

    public NativeKIANCRegisterViewHolder(ViewGroup itemView) {
        this.profileInfoLayout = (BidanClientProfileView) itemView.findViewById(R.id.profile_info_layout_ki);
        this.profileInfoLayout.initialize();
    }

    public BidanClientProfileView profileInfoLayout() {
        return profileInfoLayout;
    }
}
