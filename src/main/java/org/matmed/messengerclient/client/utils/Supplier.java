package org.matmed.messengerclient.client.utils;

@FunctionalInterface
public interface Supplier<T>
{
    T get() throws Exception;
}
