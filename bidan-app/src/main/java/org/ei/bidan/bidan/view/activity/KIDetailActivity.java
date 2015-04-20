package org.ei.bidan.bidan.view.activity;

import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ei.bidan.R;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;
import org.ei.bidan.bidan.view.controller.KIDetailController;
import org.ei.bidan.view.activity.SecuredActivity;
import org.ei.bidan.view.activity.SecuredNativeSmartRegisterActivity;

/**
 * Created by Dimas Ciputra on 4/17/15.
 */
public class KIDetailActivity extends SecuredActivity {

    private TextView puskesmasTextView;
    private TextView noIbuView;
    private TextView namaLengkapView;
    private TextView namaSuami;
    private TextView tanggalLahir;
    private TextView umur;
    private TextView alamat;
    private TextView rtRw;
    private KIDetailController kiDetailController;
    private String caseId;

    private final NavBarActionsHandler navBarActionsHandler = new NavBarActionsHandler();

    @Override
    protected void onCreation() {

        setContentView(R.layout.ki_detail_views);

        initialize();

        setupNavBarViews();
    }

    @Override
    protected void onResumption() {

    }

    private void initialize() {
        // ViewGroup itemView;
        // itemView = (ViewGroup) getLayoutInflater().inflate(R.layout.ki_detail_views, null);
        KartuIbuClient kartuIbuClient = controller().get();

        puskesmasTextView = (TextView) findViewById(R.id.txt_puskesmas_ki_detail);
        puskesmasTextView.setText(kartuIbuClient.getPuskesmas());

        noIbuView = (TextView) findViewById(R.id.txt_no_ibu_ki_detail);
        noIbuView.setText(kartuIbuClient.getNoIbu());

        namaLengkapView = (TextView) findViewById(R.id.txt_nama_lengkap_ki_detail);
        namaLengkapView.setText(kartuIbuClient.wifeName());

        namaSuami = (TextView) findViewById(R.id.txt_nama_suami_ki_detail);
        namaSuami.setText(kartuIbuClient.husbandName());

        tanggalLahir = (TextView) findViewById(R.id.txt_tgl_lahir_ki_detail);
        tanggalLahir.setText(kartuIbuClient.getDateOfBirth());

        umur = (TextView) findViewById(R.id.txt_umur_ki_detail);
        umur.setText("" + kartuIbuClient.age());

        alamat = (TextView) findViewById(R.id.txt_alamat_domisili_ki_detail);
        alamat.setText(kartuIbuClient.getHouseholdAddress());

        rtRw = (TextView) findViewById(R.id.txt_rt_rw_ki_detail);
        rtRw.setText(kartuIbuClient.getRtRw());
    }

    private void setupNavBarViews() {
        findViewById(R.id.btn_back_to_home).setOnClickListener(navBarActionsHandler);
    }

    private KIDetailController controller() {
        if(kiDetailController == null) {
            kiDetailController = new KIDetailController(this, caseId(), context.allKartuIbus());
        }
        return kiDetailController;
    }

    private String caseId() {
        if(caseId == null) {
            caseId = (String) getIntent().getExtras().get("caseId");
        }
        return caseId;
    }

    public class NavBarActionsHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.title_layout:
                case R.id.btn_back_to_home:
                    goBack();
                    break;
            }
        }
    }

    private void goBack() {
        finish();
    }
}
