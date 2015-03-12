package org.ei.bidan.sync;

import org.ei.bidan.view.ProgressIndicator;

import static org.ei.bidan.event.Event.SYNC_COMPLETED;
import static org.ei.bidan.event.Event.SYNC_STARTED;

public class SyncProgressIndicator implements ProgressIndicator {
    @Override
    public void setVisible() {
        org.ei.bidan.Context.getInstance().allSharedPreferences().saveIsSyncInProgress(true);
        SYNC_STARTED.notifyListeners(true);
    }

    @Override
    public void setInvisible() {
        org.ei.bidan.Context.getInstance().allSharedPreferences().saveIsSyncInProgress(false);
        SYNC_COMPLETED.notifyListeners(true);
    }
}
