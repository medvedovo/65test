package com.test.employees.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.test.employees.App;
import com.test.employees.R;
import com.test.employees.data.db.DBRepository;
import com.test.employees.domain.interactor.LoadDataInteractor;
import com.test.employees.other.eventbus.ChangePreloaderVisibilityEvent;
import com.test.employees.other.eventbus.ChangeTitleEvent;
import com.test.employees.other.eventbus.GetDataEvent;
import com.test.employees.other.eventbus.SetBackButtonEvent;
import com.test.employees.other.eventbus.Status;
import com.test.employees.other.fabric.FrmFabric;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private Navigator navigator;
    private FragmentManager fragmentManager;

    private LoadDataInteractor loadDataInteractor = new LoadDataInteractor();

    void onCreate(FragmentManager fragmentManager, Bundle bundle, Intent intent) {
        EventBus.getDefault().register(this);
        this.fragmentManager = fragmentManager;
        this.navigator = new SupportFragmentNavigator(fragmentManager, R.id.content) {
            @Override
            protected Fragment createFragment(String screenKey, Object data) {
                return (Fragment) FrmFabric.create(FrmFabric.Type.valueOf(screenKey), data != null ? (Bundle) data : new Bundle());
            }

            @Override
            protected void showSystemMessage(String message) {
                getViewState().showSystemMessage(message);
            }

            @Override
            protected void exit() {
                getViewState().finish();
            }
        };

        if (DBRepository.getSpecialists().isEmpty()) {
            loadDataInteractor.execute();
        } else {
            App.INSTANCE.getRouter().newRootScreen(FrmFabric.Type.SPECIALITIES_LIST.name());
        }
    }

    FrmFabric.IFragment getCurrentFragment() {
        if (fragmentManager != null) {
            return (FrmFabric.IFragment) fragmentManager.findFragmentById(R.id.content);
        }
        return null;
    }

    void onPause() {
        App.INSTANCE.getNavigator().removeNavigator();
    }

    void onResume() {
        App.INSTANCE.getNavigator().setNavigator(this.navigator);
    }

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        loadDataInteractor.unsubscribe();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ChangePreloaderVisibilityEvent event) {
        getViewState().changePreloaderVisibility(event.visible);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetDataEvent event) {
        if (event.status == Status.SUCCESS) {
            App.INSTANCE.getRouter().newRootScreen(FrmFabric.Type.SPECIALITIES_LIST.name());
        } else {
            getViewState().showSystemMessage(App.INSTANCE.getString(R.string.message_data_not_loaded));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SetBackButtonEvent event) {
        getViewState().changeBackButtonVisibility(event.visible);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ChangeTitleEvent event) {
        getViewState().changeTitle(event.title);
    }
}
