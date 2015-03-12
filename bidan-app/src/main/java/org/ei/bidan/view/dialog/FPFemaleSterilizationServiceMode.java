package org.ei.bidan.view.dialog;

import org.ei.bidan.R;
import org.ei.bidan.provider.SmartRegisterClientsProvider;

import static org.ei.bidan.Context.getInstance;

public class FPFemaleSterilizationServiceMode extends FPAllMethodsServiceMode {

    public FPFemaleSterilizationServiceMode(SmartRegisterClientsProvider provider) {
        super(provider);
    }

    @Override
    public String name() {
        return getInstance().getStringResource(R.string.fp_register_service_mode_female_sterilization);
    }
}
