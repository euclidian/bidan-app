package org.ei.bidan.bidan.view.contract;

import org.ei.bidan.view.contract.SmartRegisterClient;

/**
 * Created by Dimas Ciputra on 3/11/15.
 */
public interface KISmartRegisterClient extends KBSmartRegisterClient {

    public String numberOfPregnancies();

    public String parity();

    public String numberOfLivingChildren();

    public String numberOfAbortions();
}
