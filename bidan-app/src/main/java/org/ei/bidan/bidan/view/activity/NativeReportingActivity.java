package org.ei.bidan.bidan.view.activity;

import android.view.View;
import android.widget.TextView;

import org.ei.bidan.R;
import org.ei.bidan.bidan.view.cards.ReportingNativeCard;
import org.ei.bidan.bidan.view.controller.ReportingController;
import org.ei.bidan.view.activity.SecuredActivity;

import it.gmariotti.cardslib.library.view.CardViewNative;

/**
 * Created by Dimas Ciputra on 6/23/15.
 */
public class NativeReportingActivity extends SecuredActivity {

    private final NavBarActionsHandler navBarActionsHandler = new NavBarActionsHandler();

    private ReportingController controller;

    private ReportingNativeCard reportingNativeCard;

    @Override
    protected void onCreation() {
        setContentView(R.layout.reporting_main_card_view);

        findViewById(R.id.btn_back_to_home).setOnClickListener(navBarActionsHandler);
        ((TextView) findViewById(R.id.txt_title_nav)).setText("Reporting");

        initialize();
    }

    @Override
    protected void onResumption() {

    }

    private void initialize() {

        controller = new ReportingController(context.kartuIbuRegisterController(), context.kartuIbuANCRegisterController(), context.anakRegisterController());

        reportingNativeCard = new ReportingNativeCard(this, controller);
        reportingNativeCard.init();

        CardViewNative cardViewNative = (CardViewNative) findViewById(R.id.reporting_list_card);
        cardViewNative.setCard(reportingNativeCard);

    }

    public class NavBarActionsHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.title_layout:
                case R.id.btn_back_to_home:
                    finish();
                    break;
            }
        }
    }
}
