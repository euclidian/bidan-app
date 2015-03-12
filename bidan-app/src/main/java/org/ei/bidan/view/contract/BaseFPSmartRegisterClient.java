package org.ei.bidan.view.contract;

import org.ei.bidan.domain.FPMethod;
import org.ei.bidan.util.IntegerUtil;

public interface BaseFPSmartRegisterClient extends SmartRegisterClient {

    public FPMethod fpMethod();

    public String familyPlanningMethodChangeDate();

    public String numberOfOCPDelivered();

    public String numberOfCondomsSupplied();

    public String numberOfCentchromanPillsDelivered();

    public String iudPerson();

    public String iudPlace();

}
