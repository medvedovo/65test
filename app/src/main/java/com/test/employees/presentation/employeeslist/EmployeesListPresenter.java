package com.test.employees.presentation.employeeslist;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.test.employees.App;
import com.test.employees.R;
import com.test.employees.data.db.DBRepository;
import com.test.employees.other.eventbus.ChangeTitleEvent;
import com.test.employees.other.eventbus.SetBackButtonEvent;
import com.test.employees.other.fabric.FrmFabric;
import com.test.employees.presentation.employeedetails.EmployeeDetailsFragment;

import org.greenrobot.eventbus.EventBus;

@InjectViewState
public class EmployeesListPresenter extends MvpPresenter<EmployeesListView> {
    private EmployeesListAdapter adapter;

    void onCreate(int specialityId) {
        adapter = new EmployeesListAdapter(DBRepository.getSpecialists(specialityId), new EmployeesListAdapter.IClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(EmployeeDetailsFragment.SPECIALIST_ID, (int)adapter.getItemId(position));
                App.INSTANCE.getRouter().navigateTo(FrmFabric.Type.EMPLOYEE_DETAILS.name(), bundle);
            }
        });
    }

    void onResume() {
        EventBus.getDefault().post(new SetBackButtonEvent(true));
        EventBus.getDefault().post(new ChangeTitleEvent(App.INSTANCE.getString(R.string.title_specialists)));
    }

    void onBackPressed() {
        App.INSTANCE.getRouter().exit();
    }

    RecyclerView.Adapter onSetAdapter() {
        return adapter;
    }
}
