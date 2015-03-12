package org.ei.bidan.view.dialog;

import org.ei.bidan.view.contract.SmartRegisterClient;

public interface FilterOption extends DialogOption {
    public boolean filter(SmartRegisterClient client);
}
