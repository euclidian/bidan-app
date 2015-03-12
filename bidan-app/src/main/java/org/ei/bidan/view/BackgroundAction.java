package org.ei.bidan.view;

public interface BackgroundAction<T> {
    T actionToDoInBackgroundThread();

    void postExecuteInUIThread(T result);
}
