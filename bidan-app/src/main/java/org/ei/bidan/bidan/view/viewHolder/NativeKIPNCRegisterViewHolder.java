package org.ei.bidan.bidan.view.viewHolder;

import android.view.ViewGroup;
import android.widget.TextView;

import org.ei.bidan.R;
import org.ei.bidan.bidan.view.customControls.BidanClientProfileView;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class NativeKIPNCRegisterViewHolder {

    private final BidanClientProfileView profileInfoLayout;
    private final TextView pncId;
    private final TextView pncPlan;

    public NativeKIPNCRegisterViewHolder(ViewGroup itemView) {
        this.profileInfoLayout = (BidanClientProfileView) itemView.findViewById(R.id.profile_info_layout_ki);
        this.profileInfoLayout.initialize();

        this.pncId = (TextView) itemView.findViewById(R.id.pnc_id);
        this.pncPlan = (TextView) itemView.findViewById(R.id.pnc_plan);
    }

    public TextView plan() { return pncPlan; }
    public TextView pncId() { return pncId; }
    public BidanClientProfileView profileInfoLayout() {
        return profileInfoLayout;
    }
}
