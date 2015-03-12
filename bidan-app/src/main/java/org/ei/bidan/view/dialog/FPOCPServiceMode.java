package org.ei.bidan.view.dialog;

import org.ei.bidan.R;
import org.ei.bidan.provider.SmartRegisterClientsProvider;

import static org.ei.bidan.Context.getInstance;

public class FPOCPServiceMode extends FPAllMethodsServiceMode {

    public FPOCPServiceMode(SmartRegisterClientsProvider provider) {
        super(provider);
    }

    @Override
    public String name() {
        return getInstance().getStringResource(R.string.fp_register_service_mode_ocp);
    }
}
