package org.ei.drishti.bidan.view.customControls;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ei.drishti.R;
import org.ei.drishti.bidan.view.contract.KISmartRegisterClient;

/**
 * Created by Dimas Ciputra on 3/11/15.
 */
public class BidanObsetriView extends RelativeLayout {

    private TextView txtGravida;
    private TextView txtParity;
    private TextView txtNumberOfAlive;
    private TextView txtNumberOfAbortus;

    @SuppressWarnings("UnusedDeclaration")
    public BidanObsetriView(Context context) {
        this(context, null, 0);
    }

    @SuppressWarnings("UnusedDeclaration")
    public BidanObsetriView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BidanObsetriView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void initialize() {
        txtGravida = (TextView) findViewById(R.id.txt_gravida);
        txtParity = (TextView) findViewById(R.id.txt_parity);
        txtNumberOfAlive = (TextView) findViewById(R.id.txt_number_of_alive);
        txtNumberOfAbortus = (TextView) findViewById(R.id.txt_number_of_abortus);
    }

    public void bind(KISmartRegisterClient client) {
        txtGravida.setText(client.numberOfPregnancies());
        txtParity.setText(client.parity());
        txtNumberOfAlive.setText(client.numberOfLivingChildren());
        txtNumberOfAbortus.setText(client.numberOfAbortions());
    }
}
