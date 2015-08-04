package org.ei.bidan.bidan.view.activity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.flurry.android.FlurryAgent;

import org.ei.bidan.Context;
import org.ei.bidan.R;
import org.ei.bidan.bidan.view.contract.BidanHomeContext;
import org.ei.bidan.bidan.view.controller.NativeAfterBidanDetailsFetchListener;
import org.ei.bidan.bidan.view.controller.NativeUpdateBidanDetailsTask;
import org.ei.bidan.event.Listener;
import org.ei.bidan.service.PendingFormSubmissionService;
import org.ei.bidan.sync.SyncAfterFetchListener;
import org.ei.bidan.sync.SyncProgressIndicator;
import org.ei.bidan.sync.UpdateActionsTask;
import org.ei.bidan.view.activity.SecuredActivity;

import static java.lang.String.valueOf;
import static org.ei.bidan.event.Event.ACTION_HANDLED;
import static org.ei.bidan.event.Event.FORM_SUBMITTED;
import static org.ei.bidan.event.Event.SYNC_COMPLETED;
import static org.ei.bidan.event.Event.SYNC_STARTED;

/**
 * Created by Dimas Ciputra on 2/27/15.
 */
public class BidanHomeActivity extends SecuredActivity {
    private MenuItem updateMenuItem;
    private MenuItem remainingFormsToSyncMenuItem;
    private PendingFormSubmissionService pendingFormSubmissionService;

    private Listener<Boolean> onSyncStartListener = new Listener<Boolean>() {
        @Override
        public void onEvent(Boolean data) {
            if (updateMenuItem != null) {
                updateMenuItem.setActionView(R.layout.progress);
            }
        }
    };

    private Listener<Boolean> onSyncCompleteListener = new Listener<Boolean>() {
        @Override
        public void onEvent(Boolean data) {
            //#TODO: RemainingFormsToSyncCount cannot be updated from a back ground thread!!
            updateRemainingFormsToSyncCount();
            if (updateMenuItem != null) {
                updateMenuItem.setActionView(null);
            }
            updateRegisterCounts();
        }
    };

    private Listener<String> onFormSubmittedListener = new Listener<String>() {
        @Override
        public void onEvent(String instanceId) {
            updateRegisterCounts();
        }
    };

    private Listener<String> updateANMDetailsListener = new Listener<String>() {
        @Override
        public void onEvent(String data) {
            updateRegisterCounts();
        }
    };

    private TextView kartuIbuRegisterClientCountView;
    private TextView kartuIbuANCRegisterClientCountView;
    private TextView kartuIbuPNCRegisterClientCountView;
    private TextView anakRegisterClientCountView;
    private TextView kohortKbCountView;

    @Override
    protected void onCreation() {
        setContentView(R.layout.smart_registers_home_bidan);
        setupViews();
        initialize();
    }

    private void setupViews() {
        findViewById(R.id.btn_kartu_ibu_register).setOnClickListener(onRegisterStartListener);
        findViewById(R.id.btn_kartu_ibu_anc_register).setOnClickListener(onRegisterStartListener);
        findViewById(R.id.btn_kartu_ibu_pnc_register).setOnClickListener(onRegisterStartListener);
        findViewById(R.id.btn_anak_register).setOnClickListener(onRegisterStartListener);
        findViewById(R.id.btn_kohort_kb_register).setOnClickListener(onRegisterStartListener);

        findViewById(R.id.btn_reporting).setOnClickListener(onButtonsClickListener);
        findViewById(R.id.btn_videos).setOnClickListener(onButtonsClickListener);

        kartuIbuRegisterClientCountView = (TextView) findViewById(R.id.txt_kartu_ibu_register_client_count);
        kartuIbuANCRegisterClientCountView = (TextView) findViewById(R.id.txt_kartu_ibu_anc_register_client_count);
        kartuIbuPNCRegisterClientCountView = (TextView) findViewById(R.id.txt_kartu_ibu_pnc_register_client_count);
        anakRegisterClientCountView = (TextView) findViewById(R.id.txt_anak_client_count);
        kohortKbCountView = (TextView) findViewById(R.id.txt_kohort_kb_register_count);
    }

    private void initialize() {
        pendingFormSubmissionService = context.pendingFormSubmissionService();
        SYNC_STARTED.addListener(onSyncStartListener);
        SYNC_COMPLETED.addListener(onSyncCompleteListener);
        FORM_SUBMITTED.addListener(onFormSubmittedListener);
        ACTION_HANDLED.addListener(updateANMDetailsListener);
    }

    @Override
    protected void onResumption() {
        updateRegisterCounts();
        updateSyncIndicator();
        updateRemainingFormsToSyncCount();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
            FlurryAgent.logEvent("home_dashboard");
        }
    }

    private void updateRegisterCounts() {
        NativeUpdateBidanDetailsTask task = new NativeUpdateBidanDetailsTask(this, Context.getInstance().bidanController());
        task.fetch(new NativeAfterBidanDetailsFetchListener() {
            @Override
            public void afterFetch(BidanHomeContext bidanDetails) {
                updateRegisterCounts(bidanDetails);
            }
        });
    }

    public void updateRegisterCounts(BidanHomeContext homeContext) {
        if(homeContext != null) {
            kartuIbuRegisterClientCountView.setText(valueOf(homeContext.getKartuIbuCount()));
            kartuIbuANCRegisterClientCountView.setText(valueOf(homeContext.getKartuIbuANCCount()));
            kartuIbuPNCRegisterClientCountView.setText(valueOf(homeContext.getKartuIbuPNCCount()));
            anakRegisterClientCountView.setText(valueOf(homeContext.getAnakCount()));
            kohortKbCountView.setText(valueOf(homeContext.getKBCount()));
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        updateMenuItem = menu.findItem(R.id.updateMenuItem);
        remainingFormsToSyncMenuItem = menu.findItem(R.id.remainingFormsToSyncMenuItem);

        updateSyncIndicator();
        updateRemainingFormsToSyncCount();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.updateMenuItem:
                updateFromServer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateFromServer() {
        FlurryAgent.logEvent("clicked_update_from_server");
        UpdateActionsTask updateActionsTask = new UpdateActionsTask(
                this, context.actionService(), context.formSubmissionSyncService(), new SyncProgressIndicator());
        updateActionsTask.updateFromServer(new SyncAfterFetchListener());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SYNC_STARTED.removeListener(onSyncStartListener);
        SYNC_COMPLETED.removeListener(onSyncCompleteListener);
        FORM_SUBMITTED.removeListener(onFormSubmittedListener);
        ACTION_HANDLED.removeListener(updateANMDetailsListener);
    }

    private void updateSyncIndicator() {
        if (updateMenuItem != null) {
            if (context.allSharedPreferences().fetchIsSyncInProgress()) {
                updateMenuItem.setActionView(R.layout.progress);
            } else
                updateMenuItem.setActionView(null);
        }
    }

    private void updateRemainingFormsToSyncCount() {
        if (remainingFormsToSyncMenuItem == null) {
            return;
        }

        long size = pendingFormSubmissionService.pendingFormSubmissionCount();
        if (size > 0) {
            remainingFormsToSyncMenuItem.setTitle(valueOf(size) + " " + getString(R.string.unsynced_forms_count_message));
            remainingFormsToSyncMenuItem.setVisible(true);
        } else {
            remainingFormsToSyncMenuItem.setVisible(false);
        }
    }

    private View.OnClickListener onRegisterStartListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_kartu_ibu_register:
                    navigationController.startKartuIbuRegistry();
                    break;
                case R.id.btn_kartu_ibu_anc_register:
                    navigationController.startKartuIbuANCRegistry();
                    break;
                case R.id.btn_kartu_ibu_pnc_register:
                    navigationController.startKartuIbuPNCRegistry();
                    break;
                case R.id.btn_anak_register:
                    navigationController.startAnakBayiRegistry();
                    break;
                case R.id.btn_kohort_kb_register:
                    navigationController.startKBRegistry();
                    break;
            }
        }
    };

    private View.OnClickListener onButtonsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_reporting:
                    navigationController.startReports();
                    break;

                case R.id.btn_videos:
                    navigationController.startVideos();
                    break;
            }
        }
    };
}
