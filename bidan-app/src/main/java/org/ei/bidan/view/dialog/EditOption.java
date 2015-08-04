package org.ei.bidan.view.dialog;

import org.ei.bidan.view.contract.SmartRegisterClient;

public interface EditOption extends DialogOption {
    public void doEdit(SmartRegisterClient client);
    public void doEditWithMetadata(SmartRegisterClient client, String metadata);
}
