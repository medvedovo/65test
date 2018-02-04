package com.test.employees.presentation.main;

import com.arellomobile.mvp.MvpView;

interface MainView extends MvpView {
    void showSystemMessage(String message);
    void changePreloaderVisibility(boolean visible);
    void changeBackButtonVisibility(boolean visible);
    void changeTitle(String title);
    void finish();
}
