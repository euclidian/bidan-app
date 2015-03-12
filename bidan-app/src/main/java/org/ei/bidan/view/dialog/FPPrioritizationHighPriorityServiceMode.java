package org.ei.bidan.view.dialog;

import org.ei.bidan.R;
import org.ei.bidan.provider.SmartRegisterClientsProvider;
import org.ei.bidan.view.viewHolder.NativeChildSmartRegisterViewHolder;
import org.ei.bidan.view.viewHolder.OnClickFormLauncher;

import static org.ei.bidan.AllConstants.FormNames.FP_CHANGE;
import static org.ei.bidan.AllConstants.FormNames.FP_COMPLICATIONS;
import static org.ei.bidan.Context.getInstance;

public class FPPrioritizationHighPriorityServiceMode extends FPPrioritizationAllECServiceMode {

    public FPPrioritizationHighPriorityServiceMode(SmartRegisterClientsProvider provider) {
        super(provider);
    }

    @Override
    public String name() {
        return getInstance().getStringResource(R.string.fp_prioritization_high_priority_service_mode);
    }
}
