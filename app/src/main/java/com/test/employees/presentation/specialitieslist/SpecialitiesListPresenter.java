package com.test.employees.presentation.specialitieslist;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.test.employees.App;
import com.test.employees.R;
import com.test.employees.data.db.DBRepository;
import com.test.employees.other.eventbus.ChangePreloaderVisibilityEvent;
import com.test.employees.other.eventbus.ChangeTitleEvent;
import com.test.employees.other.eventbus.SetBackButtonEvent;
import com.test.employees.other.fabric.FrmFabric;
import com.test.employees.presentation.employeeslist.EmployeesListFragment;

import org.greenrobot.eventbus.EventBus;

@InjectViewState
public class SpecialitiesListPresenter extends MvpPresenter<SpecialitiesListView> {
    private SpecialitiesListAdapter adapter;

    void onCreate() {
        EventBus.getDefault().post(new ChangePreloaderVisibilityEvent(false));
        adapter = new SpecialitiesListAdapter(DBRepository.getSpecialities(), new SpecialitiesListAdapter.IClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(EmployeesListFragment.SPECIALITY_ID, (int) adapter.getItemId(position));
                App.INSTANCE.getRouter().navigateTo(FrmFabric.Type.EMPLOYEES_LIST.name(), bundle);
            }
        });
    }

    void onResume() {
        EventBus.getDefault().post(new SetBackButtonEvent(false));
        EventBus.getDefault().post(new ChangeTitleEvent(App.INSTANCE.getString(R.string.title_specialities)));
    }

    void onBackPressed() {
        App.INSTANCE.getRouter().exit();
    }

    RecyclerView.Adapter onSetAdapter() {
        return adapter;
    }
}
