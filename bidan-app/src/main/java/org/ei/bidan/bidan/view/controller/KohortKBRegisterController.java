package org.ei.bidan.bidan.view.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Iterables;

import org.ei.bidan.AllConstants;
import org.ei.bidan.bidan.domain.Anak;
import org.ei.bidan.bidan.domain.Ibu;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.repository.AllKartuIbus;
import org.ei.bidan.bidan.repository.AllKohort;
import org.ei.bidan.bidan.view.contract.KBClient;
import org.ei.bidan.bidan.view.contract.KBClients;
import org.ei.bidan.bidan.view.contract.KIChildClient;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;
import org.ei.bidan.bidan.view.contract.KartuIbuClients;
import org.ei.bidan.util.Cache;
import org.ei.bidan.util.CacheableData;
import org.ei.bidan.util.EasyMap;
import org.ei.bidan.view.contract.SmartRegisterClient;
import org.joda.time.LocalDate;

import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by Dimas Ciputra on 2/18/15.
 */
public class KohortKBRegisterController extends CommonController{
    private static final String KB_CLIENTS_LIST = "KBClientsList";
    public static final String STATUS_DATE_FIELD = "date";
    public static final String ANC_STATUS = "anc";
    public static final String PNC_STATUS = "pnc";
    public static final String STATUS_TYPE_FIELD = "type";
    public static final String STATUS_EDD_FIELD = "edd";

    private final AllKartuIbus allKartuIbus;
    private final Cache<String> cache;
    private final Cache<KBClients> kbClientsCache;
    private final AllKohort allKohort;

    public KohortKBRegisterController(AllKartuIbus allKartuIbus, Cache<String> cache, Cache<KBClients> kbClientsCache, AllKohort allKohort) {
        this.allKartuIbus = allKartuIbus;
        this.cache = cache;
        this.kbClientsCache = kbClientsCache;
        this.allKohort = allKohort;
    }

    public KBClients getKBClients() {
        return kbClientsCache.get(KB_CLIENTS_LIST, new CacheableData<KBClients>() {
            @Override
            public KBClients fetch() {
                List<KartuIbu> kartuIbus = allKartuIbus.all();
                KBClients KBs = new KBClients();

                for (KartuIbu kartuIbu : kartuIbus) {

                    if(Strings.isNullOrEmpty(kartuIbu.getDetails().get("JenisKontrasepsi"))) {
                        continue;
                    }

                    KBClient kbClient = new KBClient(kartuIbu.getCaseId(), kartuIbu.getDetails().get("Namalengkap"),
                            kartuIbu.getDetails().get("Namasuami"), kartuIbu.getDetails().get("Desa"),
                            kartuIbu.getDetails().get("NoIbu"))
                            .withKBMethod(kartuIbu.getDetails().get("JenisKontrasepsi"))
                            .withIMS(kartuIbu.getDetails().get("IMS"))
                            .withHB(kartuIbu.getDetails().get("HB"))
                            .withLila(kartuIbu.getDetails().get("LILA"))
                            .withPenyakitKronis(kartuIbu.getDetails().get("PenyakitKronis"))
                            .withGravida(kartuIbu.getDetails().get("Gravida"))
                            .withParity(kartuIbu.getDetails().get("Partus"))
                            .withAbortus(kartuIbu.getDetails().get("Abortus"))
                            .withLiveChild(kartuIbu.getDetails().get("Hidup"))
                            .withTanggalKunjungan(kartuIbu.getDetails().get("TanggalKunjungan"))
                            .withKeterangan1(kartuIbu.getDetails().get("KeteranganTentangPesertaKB1"))
                            .withKeterangan2(kartuIbu.getDetails().get("KeteranganTentangPesertaKB2"))
                            .withAge(kartuIbu.getDetails().get("Umur"));

                    KBs.add(kbClient);
                }
                sortByName(KBs);
                return KBs;
            }
        });
    }

    private void sortByName(List<?extends SmartRegisterClient> kiClients) {
        sort(kiClients, new Comparator<SmartRegisterClient>() {
            @Override
            public int compare(SmartRegisterClient o1, SmartRegisterClient o2) {
                return o1.wifeName().compareToIgnoreCase(o2.wifeName());
            }
        });
    }

    private void sortByDateOfBirth(List<Anak> children) {
        sort(children, new Comparator<Anak>() {
            @Override
            public int compare(Anak child, Anak anotherChild) {
                return LocalDate.parse(child.getDateOfBirth()).compareTo(LocalDate.parse(anotherChild.getDateOfBirth()));
            }
        });
    }

    public CharSequence[] getRandomNameChars(final SmartRegisterClient client) {
        return onRandomNameChars(
                client,
                getKBClients(),
                allKartuIbus.randomDummyName(),
                AllConstants.DIALOG_DOUBLE_SELECTION_NUM);
    }


}
