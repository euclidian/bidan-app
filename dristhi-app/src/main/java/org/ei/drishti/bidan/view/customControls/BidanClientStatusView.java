package org.ei.drishti.bidan.view.customControls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.ei.drishti.R;
import org.ei.drishti.bidan.view.contract.KartuIbuClient;
import org.ei.drishti.view.viewHolder.ViewStubInflater;

import java.util.HashMap;
import java.util.Map;

import static org.ei.drishti.util.DateUtil.formatDate;
import static org.ei.drishti.view.controller.ECSmartRegisterController.ANC_STATUS;
import static org.ei.drishti.view.controller.ECSmartRegisterController.STATUS_DATE_FIELD;
import static org.ei.drishti.view.controller.ECSmartRegisterController.STATUS_EDD_FIELD;
import static org.ei.drishti.view.controller.ECSmartRegisterController.STATUS_TYPE_FIELD;

/**
 * Created by Dimas Ciputra on 3/9/15.
 */
public class BidanClientStatusView extends FrameLayout {

    private Map<String, ViewStubInflater> statusLayouts;

    @SuppressWarnings("UnusedDeclaration")
    public BidanClientStatusView(Context context) {
        this(context, null, 0);
    }

    @SuppressWarnings("UnusedDeclaration")
    public BidanClientStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BidanClientStatusView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void initialize() {
        this.statusLayouts = new HashMap<String, ViewStubInflater>();

        this.statusLayouts
                .put(ANC_STATUS, new ViewStubInflater((ViewStub) findViewById(R.id.anc_status_layout)));
    }

    public void bindData(KartuIbuClient client) {
        hideAllLayout();

        if (client.status().containsKey(STATUS_TYPE_FIELD)) {
            String statusType = client.status().get(STATUS_TYPE_FIELD);
            String statusDate = formatDate(client.status().get(STATUS_DATE_FIELD));

            ViewGroup statusViewGroup = statusLayout(statusType);
            statusViewGroup.setVisibility(View.VISIBLE);
            dateView(statusViewGroup).setText(statusDate);

            eddDateView(statusViewGroup).setText(formatDate(client.status().get(STATUS_EDD_FIELD)));
        }
    }

    public ViewGroup statusLayout(String statusType) {
        return statusLayouts.get(statusType).get();
    }

    public void hideAllLayout() {
        for (String statusLayout : statusLayouts.keySet()) {
            statusLayouts.get(statusLayout).setVisibility(View.GONE);
        }
    }

    public TextView dateView(ViewGroup statusViewGroup) {
        return ((TextView) statusViewGroup.findViewById(R.id.txt_anc_status_date));
    }

    public TextView typeView(ViewGroup statusViewGroup) {
        return ((TextView) statusViewGroup.findViewById(R.id.txt_status_type));
    }

    public TextView eddDateView(ViewGroup statusViewGroup) {
        return ((TextView) statusViewGroup.findViewById(R.id.txt_anc_status_edd_date));
    }
}