package com.test.employees.presentation.employeeslist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.test.employees.App;
import com.test.employees.R;
import com.test.employees.other.components.DividerDecoration;
import com.test.employees.other.fabric.FrmFabric;

public class EmployeesListFragment extends MvpAppCompatFragment implements EmployeesListView, FrmFabric.IFragment {

    public static final String SPECIALITY_ID = "specialityId";

    RecyclerView employeesList;

    @InjectPresenter
    EmployeesListPresenter presenter;

    public static EmployeesListFragment newInstance(Bundle bundle) {
        EmployeesListFragment fragment = new EmployeesListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int specialityId = getArguments().getInt(SPECIALITY_ID, 0);
            if (specialityId == 0) {
                App.INSTANCE.getRouter().showSystemMessage(getString(R.string.message_data_not_found));
            }
            presenter.onCreate(specialityId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employees_list, container, false);

        employeesList = view.findViewById(R.id.employees_list);
        employeesList.setLayoutManager(new LinearLayoutManager(getContext()));
        employeesList.addItemDecoration(new DividerDecoration(getContext(), R.drawable.divider, 50, 50));
        employeesList.setAdapter(presenter.onSetAdapter());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public FrmFabric.Type getType() {
        return FrmFabric.Type.EMPLOYEES_LIST;
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }
}
