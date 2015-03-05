package org.ei.drishti.bidan.view.controller;

import android.os.AsyncTask;

import org.ei.drishti.bidan.view.contract.BidanHomeContext;
import org.ei.drishti.view.contract.HomeContext;
import org.ei.drishti.view.controller.ANMController;
import org.ei.drishti.view.controller.NativeAfterANMDetailsFetchListener;

import java.util.concurrent.locks.ReentrantLock;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;
import static org.ei.drishti.util.Log.logWarn;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class NativeUpdateBidanDetailsTask {
    private final BidanController bidanController;
    private static final ReentrantLock lock = new ReentrantLock();

    public NativeUpdateBidanDetailsTask(BidanController bidanController) {
        this.bidanController = bidanController;
    }

    public void fetch(final NativeAfterBidanDetailsFetchListener afterFetchListener) {
        new AsyncTask<Void, Void, BidanHomeContext>() {
            @Override
            protected BidanHomeContext doInBackground(Void... params) {
                if (!lock.tryLock()) {
                    logWarn("Update Bidan details is in progress, so going away.");
                    cancel(true);
                    return null;
                }
                try {
                    return bidanController.getBidanHomeContext();
                } finally {
                    lock.unlock();
                }
            }

            @Override
            protected void onPostExecute(BidanHomeContext bidan) {
                afterFetchListener.afterFetch(bidan);
            }
        }.executeOnExecutor(THREAD_POOL_EXECUTOR);
    }
}
