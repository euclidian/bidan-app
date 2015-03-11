package org.ei.drishti.bidan.view.viewHolder;

import android.view.ViewGroup;
import android.widget.TextView;

import org.ei.drishti.R;
import org.ei.drishti.bidan.view.customControls.BidanClientProfileView;
/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class NativeKIANCRegisterViewHolder {

    private final BidanClientProfileView profileInfoLayout;
    private final TextView ancId;
    private final TextView ancStatus;

    public NativeKIANCRegisterViewHolder(ViewGroup itemView) {
        this.profileInfoLayout = (BidanClientProfileView) itemView.findViewById(R.id.profile_info_layout_ki);
        this.profileInfoLayout.initialize();

        this.ancId = (TextView) itemView.findViewById(R.id.anc_id);
        this.ancStatus = (TextView) itemView.findViewById(R.id.anc_status);
    }

    public TextView ancStatus() { return ancStatus; }
    public TextView ancId() { return ancId; }
    public BidanClientProfileView profileInfoLayout() {
        return profileInfoLayout;
    }
}
