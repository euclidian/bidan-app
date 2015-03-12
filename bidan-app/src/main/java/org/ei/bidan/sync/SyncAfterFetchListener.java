package org.ei.bidan.sync;

import org.ei.bidan.domain.FetchStatus;

import static org.ei.bidan.event.Event.ON_DATA_FETCHED;

public class SyncAfterFetchListener implements AfterFetchListener {
    public void afterFetch(FetchStatus status) {
        ON_DATA_FETCHED.notifyListeners(status);
    }
}
