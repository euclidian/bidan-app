package org.ei.bidan.bidan.view.controller;

import android.content.Context;

import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.repository.AllKartuIbus;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;

import java.util.Map;

/**
 * Created by Dimas Ciputra on 4/17/15.
 */
public class KIDetailController {
    private final Context context;
    private final String caseId;
    private final AllKartuIbus allKartuIbus;

    public KIDetailController(Context context, String caseId, AllKartuIbus allKartuIbus) {
        this.context = context;
        this.caseId = caseId;
        this.allKartuIbus = allKartuIbus;
    }

    public KartuIbuClient get() {
        KartuIbu kartuIbu = allKartuIbus.findByCaseID(caseId);
        KartuIbuClient kartuIbuClient = new KartuIbuClient(kartuIbu.getCaseId(),
                kartuIbu.getDetails().get("puskesmas"), kartuIbu.getDetails().get("Propinsi"),
                kartuIbu.getDetails().get("Kabupaten"), kartuIbu.getDetails().get("Posyandu"),
                kartuIbu.getDetails().get("Alamatdomisili"), kartuIbu.getDetails().get("NoIbu"),
                kartuIbu.getDetails().get("Namalengkap"), kartuIbu.getDetails().get("Umur"),
                kartuIbu.getDetails().get("GolonganDarah"), kartuIbu.getDetails().get("RiwayatKomplikasiKebidanan"),
                kartuIbu.getDetails().get("Namasuami"), kartuIbu.getDetails().get("TanggalPeriksa"),
                kartuIbu.getDetails().get("EDD"), kartuIbu.getDetails().get("Desa"))
                .withDateOfBirth(kartuIbu.getDetails().get("Tanggallahir"))
                .withNumberOfLivingChildren(kartuIbu.getDetails().get("Hidup"))
                .withNumberOfPregnancies(kartuIbu.getDetails().get("Gravida"))
                .withNumberOfAbortions(kartuIbu.getDetails().get("Abortus"))
                .withParity(kartuIbu.getDetails().get("Partus"));

        kartuIbuClient.setRtRw(kartuIbu.getDetails().get("RTRW"));

        return kartuIbuClient;
    }

}
