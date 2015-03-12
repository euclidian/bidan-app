package org.ei.bidan.view.dialog;

public interface DialogOptionModel {
    DialogOption[] getDialogOptions();

    void onDialogOptionSelection(DialogOption option, Object tag);
}
