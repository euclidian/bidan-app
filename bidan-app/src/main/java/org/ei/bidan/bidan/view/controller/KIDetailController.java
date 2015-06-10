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
                kartuIbu.getDetails().get("puskesmas"), kartuIbu.getDetails().get("propinsi"),
                kartuIbu.getDetails().get("kabupaten"), kartuIbu.getDetails().get("posyandu"),
                kartuIbu.getDetails().get("alamatDomisili"), kartuIbu.getDetails().get("noIbu"),
                kartuIbu.getDetails().get("namalengkap"), kartuIbu.getDetails().get("umur"),
                kartuIbu.getDetails().get("golonganDarah"),
                kartuIbu.getDetails().get("namaSuami"),
                kartuIbu.getDetails().get("dusun"))
                .withDateOfBirth(kartuIbu.getDetails().get("tanggalLahir"));
        return kartuIbuClient;
    }

}
