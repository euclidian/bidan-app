package org.ei.bidan.sync;

import org.ei.bidan.domain.FetchStatus;

public interface AfterFetchListener {
    void afterFetch(FetchStatus fetchStatus);
}
