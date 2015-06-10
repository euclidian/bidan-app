package org.ei.bidan.bidan.view.customControls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ei.bidan.AllConstants;
import org.ei.bidan.R;
import org.ei.bidan.bidan.view.contract.KartuIbuANCSmartRegisterClient;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;
import org.ei.bidan.view.contract.SmartRegisterClient;
import org.ei.bidan.view.viewHolder.ProfilePhotoLoader;

/**
 * Created by Dimas Ciputra on 3/6/15.
 */
public class BidanClientProfileView extends RelativeLayout {

    private ImageView imgProfileView;
    private TextView txtNameView;
    private TextView txtAgeView;
    private TextView txtVillageNameView;
    private TextView txtHusbandName;
    private ImageView badgeHRView;
    private ImageView badgeHPView;

    @SuppressWarnings("UnusedDeclaration")
    public BidanClientProfileView(Context context) {
        this(context, null, 0);
    }

    @SuppressWarnings("UnusedDeclaration")
    public BidanClientProfileView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BidanClientProfileView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void initialize() {
        this.imgProfileView = (ImageView) findViewById(R.id.img_profile);
        this.txtNameView = (TextView) findViewById(R.id.wife_name);
        this.txtAgeView = (TextView) findViewById(R.id.wife_age);
        this.txtHusbandName = (TextView) findViewById(R.id.txt_husband_name);
        this.txtVillageNameView = (TextView) findViewById(R.id.txt_village_name);
        this.badgeHRView = (ImageView) findViewById(R.id.img_hr_badge);
        this.badgeHPView = (ImageView) findViewById(R.id.img_hp_badge);
    }

    public void bindData(SmartRegisterClient client, ProfilePhotoLoader photoLoader) {
        this.imgProfileView.setBackground(photoLoader.get(client));
        this.txtNameView.setText(client.wifeName() != null ? client.wifeName() : "");
        this.txtVillageNameView.setText(client.village() != null ? client.village() : "");
        this.txtAgeView.setText(client.ageInString() != null ? client.ageInString() : "");
        this.txtHusbandName.setText(client.husbandName() != null ? client.husbandName() : "-");
        this.badgeHRView.setVisibility(client.isHighRisk() ? View.VISIBLE : View.GONE);
        this.badgeHPView.setVisibility(client.isHighPriority() ? View.VISIBLE : View.GONE);
    }

    private boolean isAnANCClient(SmartRegisterClient client) {
        return client instanceof KartuIbuANCSmartRegisterClient;
    }

    private String getOutOfAreaText(String locationStatus) {
        return isOutOfArea(locationStatus) ? org.ei.bidan.Context.getInstance().getStringResource(R.string.str_out_of_area) : "";
    }

    private boolean isOutOfArea(String locationStatus) {
        return AllConstants.OUT_OF_AREA.equalsIgnoreCase(locationStatus);
    }
}
