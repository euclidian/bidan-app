package org.ei.bidan.view.contract;

import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.view.contract.KartuIbuANCClient;
import org.ei.bidan.bidan.view.contract.KartuIbuClient;
import org.ei.bidan.util.IntegerUtil;

import java.util.Comparator;

public interface SmartRegisterClient {

    Comparator<SmartRegisterClient> NAME_COMPARATOR = new Comparator<SmartRegisterClient>() {
        @Override
        public int compare(SmartRegisterClient client, SmartRegisterClient anotherClient) {
            return client.compareName(anotherClient);
        }
    };

    Comparator<SmartRegisterClient> HIGH_PRIORITY_COMPARATOR = new Comparator<SmartRegisterClient>() {
        @Override
        public int compare(SmartRegisterClient client, SmartRegisterClient anotherClient) {
            return client.isHighPriority() == anotherClient.isHighPriority()
                    ? client.name().compareToIgnoreCase(anotherClient.name())
                    : anotherClient.isHighPriority() ? 1 : -1;
        }
    };

    Comparator<SmartRegisterClient> HIGH_RISK_COMPARATOR = new Comparator<SmartRegisterClient>() {
        @Override
        public int compare(SmartRegisterClient client, SmartRegisterClient anotherClient) {
            return client.isHighRisk() == anotherClient.isHighRisk()
                    ? client.name().compareToIgnoreCase(anotherClient.name())
                    : anotherClient.isHighRisk() ? 1 : -1;
        }
    };

    Comparator<SmartRegisterClient> BPL_COMPARATOR = new Comparator<SmartRegisterClient>() {
        @Override
        public int compare(SmartRegisterClient client, SmartRegisterClient anotherClient) {
            if ((client.isBPL() && anotherClient.isBPL())
                    || (!client.isBPL() && !anotherClient.isBPL())) {
                return client.compareName(anotherClient);
            } else {
                return anotherClient.isBPL() ? 1 : -1;
            }
        }
    };

    Comparator<SmartRegisterClient> SC_COMPARATOR = new Comparator<SmartRegisterClient>() {
        @Override
        public int compare(SmartRegisterClient client, SmartRegisterClient anotherClient) {
            if ((client.isSC() && anotherClient.isSC())
                    || (!client.isSC() && !anotherClient.isSC())) {
                return client.compareName(anotherClient);
            } else {
                return anotherClient.isSC() ? 1 : -1;
            }
        }
    };

    Comparator<SmartRegisterClient> ST_COMPARATOR = new Comparator<SmartRegisterClient>() {
        @Override
        public int compare(SmartRegisterClient client, SmartRegisterClient anotherClient) {
            if ((client.isST() && anotherClient.isST())
                    || (!client.isST() && !anotherClient.isST())) {
                return client.compareName(anotherClient);
            } else {
                return anotherClient.isST() ? 1 : -1;
            }
        }
    };

    Comparator<SmartRegisterClient> AGE_COMPARATOR = new Comparator<SmartRegisterClient>() {
        @Override
        public int compare(SmartRegisterClient client, SmartRegisterClient anotherClient) {
            return IntegerUtil.compare(client.ageInDays(), anotherClient.ageInDays());
        }
    };

    Comparator<SmartRegisterClient> HR_COMPARATOR = new Comparator<SmartRegisterClient>() {
        @Override
        public int compare(SmartRegisterClient client, SmartRegisterClient anotherClient) {
            if ((client.isHighRisk() && anotherClient.isHighRisk())
                    || (!client.isHighRisk() && !anotherClient.isHighRisk())) {
                return client.compareName(anotherClient);
            } else {
                return anotherClient.isHighRisk() ? 1 : -1;
            }
        }
    };

    Comparator<SmartRegisterClient> NO_IBU_COMPARATOR = new Comparator<SmartRegisterClient>() {
        @Override
        public int compare(SmartRegisterClient client, SmartRegisterClient anotherClient) {
            KartuIbuClient kartuIbuClient = (KartuIbuClient) client;
            KartuIbuClient kartuIbuClient1 = (KartuIbuClient) anotherClient;
            return IntegerUtil.compare(Integer.parseInt(kartuIbuClient.getNoIbu()),
                    Integer.parseInt(kartuIbuClient1.getNoIbu()));
        }
    };

    Comparator<SmartRegisterClient> EDD_COMPARATOR_KI = new Comparator<SmartRegisterClient>() {
        @Override
        public int compare(SmartRegisterClient client, SmartRegisterClient anotherClient) {
            KartuIbuClient kartuIbuClient = (KartuIbuClient) client;
            KartuIbuClient otherKartuIbuClient = (KartuIbuClient) anotherClient;
            return ((KartuIbuClient) client).edd()
                    .compareTo(((KartuIbuClient) anotherClient).edd());
        }
    };

    Comparator<SmartRegisterClient> EDD_COMPARATOR_KI_ANC = new Comparator<SmartRegisterClient>() {
        @Override
        public int compare(SmartRegisterClient client, SmartRegisterClient anotherClient) {
            return ((KartuIbuANCClient) client).edd()
                    .compareTo(((KartuIbuANCClient) anotherClient).edd());
        }
    };

    public String entityId();

    public String name();

    public String displayName();

    public String village();

    public String wifeName();

    public String husbandName();

    public int age();

    public int ageInDays();

    public String ageInString();

    public boolean isSC();

    public boolean isST();

    public boolean isHighRisk();

    public boolean isHighPriority();

    public boolean isBPL();

    public String profilePhotoPath();

    public String locationStatus();

    public boolean satisfiesFilter(String filterCriterion);

    public int compareName(SmartRegisterClient client);
}
