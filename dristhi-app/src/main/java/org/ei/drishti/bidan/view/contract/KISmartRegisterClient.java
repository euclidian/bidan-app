package org.ei.drishti.bidan.view.contract;

import org.ei.drishti.view.contract.SmartRegisterClient;

/**
 * Created by Dimas Ciputra on 3/11/15.
 */
public interface KISmartRegisterClient extends SmartRegisterClient {

    public String numberOfPregnancies();

    public String parity();

    public String numberOfLivingChildren();

    public String numberOfAbortions();
}
