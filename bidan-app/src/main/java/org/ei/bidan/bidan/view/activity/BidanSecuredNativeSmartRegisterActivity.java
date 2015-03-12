package org.ei.bidan.bidan.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;

import org.ei.bidan.view.activity.SecuredNativeSmartRegisterActivity;
import org.ei.bidan.view.dialog.DialogOptionModel;
import org.ei.bidan.view.dialog.SmartRegisterDialogFragment;

/**
 * Created by Dimas Ciputra on 3/8/15.
 */
public abstract class BidanSecuredNativeSmartRegisterActivity extends SecuredNativeSmartRegisterActivity {

    private static final String DIALOG_TAG = "dialog";

    void showFragmentDialog(DialogOptionModel dialogOptionModel) {
        showFragmentDialog(dialogOptionModel, null);
    }

    void showFragmentDialog(DialogOptionModel dialogOptionModel, Object tag) {
        if (dialogOptionModel.getDialogOptions().length <= 0) {
            return;
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(DIALOG_TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        SmartRegisterDialogFragment
                .newInstance(this, dialogOptionModel, tag)
                .show(ft, DIALOG_TAG);
    }

}
