package com.luxoft.task.base;

import androidx.annotation.NonNull;

public abstract class BaseMvpPresenter<V> implements MvpPresenter<V> {

    public V view;

    @Override
    public void attachView(@NonNull V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}