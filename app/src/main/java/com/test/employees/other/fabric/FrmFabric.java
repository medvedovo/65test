package com.test.employees.other.fabric;

import android.os.Bundle;

import com.test.employees.presentation.employeedetails.EmployeeDetailsFragment;
import com.test.employees.presentation.employeeslist.EmployeesListFragment;
import com.test.employees.presentation.specialitieslist.SpecialitiesListFragment;

public final class FrmFabric {
    public enum Type {
        SPECIALITIES_LIST,
        EMPLOYEES_LIST,
        EMPLOYEE_DETAILS
    }

    public static IFragment create(Type type, Bundle bundle) {
        switch (type) {
            case SPECIALITIES_LIST:
                return SpecialitiesListFragment.newInstance(bundle);
            case EMPLOYEES_LIST:
                return EmployeesListFragment.newInstance(bundle);
            case EMPLOYEE_DETAILS:
                return EmployeeDetailsFragment.newInstance(bundle);
        }
        return null;
    }

    public interface IFragment {
        Type getType();
        void onBackPressed();
    }
}
