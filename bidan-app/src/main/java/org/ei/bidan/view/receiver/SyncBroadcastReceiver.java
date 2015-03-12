package org.ei.bidan.view.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.ei.bidan.sync.SyncAfterFetchListener;
import org.ei.bidan.sync.SyncProgressIndicator;
import org.ei.bidan.sync.UpdateActionsTask;

import static org.ei.bidan.util.Log.logInfo;

public class SyncBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        logInfo("Sync alarm triggered. Trying to Sync.");

        UpdateActionsTask updateActionsTask = new UpdateActionsTask(
                context,
                org.ei.bidan.Context.getInstance().actionService(),
                org.ei.bidan.Context.getInstance().formSubmissionSyncService(), new SyncProgressIndicator());

        updateActionsTask.updateFromServer(new SyncAfterFetchListener());
    }
}

