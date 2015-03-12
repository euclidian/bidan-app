package org.ei.bidan.event;

public interface Listener<T> {
    void onEvent(T data);
}
