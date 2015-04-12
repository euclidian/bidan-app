package org.ei.bidan.bidan.view.customControls;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.ei.bidan.R;
import org.ei.bidan.bidan.view.contract.AnakClient;

/**
 * Created by Dimas Ciputra on 4/10/15.
 */
public class ClientAnakBirthStatusView extends RelativeLayout {
    private TextView txtBirthWeight;
    private TextView txtBirthCondition;
    private TextView lblBirthWeight;
    private TextView lblBirthCondition;

    @SuppressWarnings("UnusedDeclaration")
    public ClientAnakBirthStatusView(Context context) {
        this(context, null, 0);
    }

    @SuppressWarnings("UnusedDeclaration")
    public ClientAnakBirthStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClientAnakBirthStatusView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void initialize() {
        txtBirthWeight = (TextView) findViewById(R.id.txt_birth_weight);
        txtBirthCondition = (TextView) findViewById(R.id.txt_birth_condition);
        lblBirthWeight = (TextView) findViewById(R.id.lbl_birth_weight);
        lblBirthCondition = (TextView) findViewById(R.id.lbl_birth_condition);
    }

    public void bindData(AnakClient client) {
        String birthWeight = client.weight();
        String birthCondition = client.getBirthCondition();

        txtBirthWeight.setText(birthWeight);
        txtBirthCondition.setText(birthCondition);
    }
}
