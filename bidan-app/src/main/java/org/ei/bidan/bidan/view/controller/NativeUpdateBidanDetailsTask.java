package org.ei.bidan.bidan.view.controller;

import android.os.AsyncTask;

import org.ei.bidan.bidan.view.activity.BidanHomeActivity;
import org.ei.bidan.bidan.view.contract.BidanHomeContext;
import org.ei.bidan.view.contract.HomeContext;
import org.ei.bidan.view.controller.NativeAfterANMDetailsFetchListener;

import java.util.concurrent.locks.ReentrantLock;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;
import static org.ei.bidan.util.Log.logWarn;

/**
 * Created by Dimas Ciputra on 3/4/15.
 */
public class NativeUpdateBidanDetailsTask {
    private final BidanController bidanController;
    private final BidanHomeActivity bidanHomeActivity;
    private static final ReentrantLock lock = new ReentrantLock();

    public NativeUpdateBidanDetailsTask(BidanHomeActivity bidanHomeActivity, BidanController bidanController) {
        this.bidanController = bidanController;
        this.bidanHomeActivity = bidanHomeActivity;
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
                if(bidanHomeActivity != null) {
                    afterFetchListener.afterFetch(bidan);
                }
            }
        }.executeOnExecutor(THREAD_POOL_EXECUTOR);
    }
}
