package org.matmed.messengerclient.client.utils;

public interface Listener<T> {
    void onHandle(T t);
}
