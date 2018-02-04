package com.test.employees.presentation.employeedetails;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.test.employees.App;
import com.test.employees.R;
import com.test.employees.other.fabric.FrmFabric;

public class EmployeeDetailsFragment extends MvpAppCompatFragment implements EmployeeDetailsView, FrmFabric.IFragment {

    public static final String SPECIALIST_ID = "getSpecialistId";

    TextView employeeName;
    TextView employeeAge;
    TextView employeeBirthday;
    RecyclerView employeeSpecialities;

    @InjectPresenter
    EmployeeDetailsPresenter presenter;

    public static EmployeeDetailsFragment newInstance(Bundle bundle) {
        EmployeeDetailsFragment fragment = new EmployeeDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int specialistId = getArguments().getInt(SPECIALIST_ID, 0);
            if (specialistId == 0) {
                App.INSTANCE.getRouter().showSystemMessage(getString(R.string.message_data_not_found));
            }
            presenter.onCreate(specialistId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_details, container, false);

        employeeName = view.findViewById(R.id.employee_name);
        employeeAge = view.findViewById(R.id.employee_age);
        employeeBirthday = view.findViewById(R.id.employee_birthday);
        employeeSpecialities = view.findViewById(R.id.employee_specialities);

        employeeSpecialities.setLayoutManager(new LinearLayoutManager(getContext()));
        employeeSpecialities.setAdapter(presenter.onSetAdapter());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void setEmployeeName(String name) {
        employeeName.setText(name);
    }

    @Override
    public void setEmployeeAge(String age) {
        if (age != null && !age.isEmpty()) {
            employeeAge.setText(age);
        } else {
            employeeAge.setVisibility(View.GONE);
        }
    }

    @Override
    public void setEmployeeBirthday(String date) {
        if (date != null && !date.isEmpty()) {
            employeeBirthday.setText(date);
        } else {
            employeeBirthday.setText("—");
        }
    }

    @Override
    public FrmFabric.Type getType() {
        return FrmFabric.Type.EMPLOYEE_DETAILS;
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }
}
