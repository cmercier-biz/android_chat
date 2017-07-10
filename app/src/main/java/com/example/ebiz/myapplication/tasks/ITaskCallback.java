package com.example.ebiz.myapplication.tasks;

/**
 * Created by ebiz on 10/07/2017.
 */
public interface ITaskCallback<T> {

    void callback(T taskResult);
}
