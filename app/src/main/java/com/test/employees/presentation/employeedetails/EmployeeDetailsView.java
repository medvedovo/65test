package com.test.employees.presentation.employeedetails;

import com.arellomobile.mvp.MvpView;

interface EmployeeDetailsView extends MvpView {
    void setEmployeeName(String name);
    void setEmployeeAge(String age);
    void setEmployeeBirthday(String date);
}
