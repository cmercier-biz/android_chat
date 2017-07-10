package com.example.ebiz.myapplication.activities;

/**
 * Abstract Presenter (MVP)
 * Created by ebiz on 10/07/2017.
 */
public abstract class Presenter<T> {

    private T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }
}
