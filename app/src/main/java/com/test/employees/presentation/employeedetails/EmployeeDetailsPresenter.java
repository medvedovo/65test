package com.test.employees.presentation.employeedetails;

import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.test.employees.App;
import com.test.employees.R;
import com.test.employees.data.db.DBRepository;
import com.test.employees.data.db.dataclass.Specialist;
import com.test.employees.other.DateUtils;
import com.test.employees.other.StringUtils;
import com.test.employees.other.eventbus.ChangeTitleEvent;
import com.test.employees.other.eventbus.SetBackButtonEvent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@InjectViewState
public class EmployeeDetailsPresenter extends MvpPresenter<EmployeeDetailsView> {
    private Specialist specialist;

    private EmployeeSpecialitiesAdapter adapter;

    void onCreate(int specialistId) {
        specialist = DBRepository.getSpecialistById(specialistId);
        adapter = new EmployeeSpecialitiesAdapter(DBRepository.getSpecialities(specialistId));

        getViewState().setEmployeeName(String.format("%s %s",
                StringUtils.capitalize(specialist.getFName()),
                StringUtils.capitalize(specialist.getLName())));

        int age = DateUtils.calculateAge(specialist.getBirthday());
        getViewState().setEmployeeAge(age > 0 ? String.format(Locale.getDefault(),
                "%d %s", age, StringUtils.wordCaseString(age,
                        App.INSTANCE.getString(R.string.age1), App.INSTANCE.getString(R.string.age2), App.INSTANCE.getString(R.string.age5))) : "");

        Date birthday = DateUtils.stringToDate(specialist.getBirthday());
        getViewState().setEmployeeBirthday(birthday != null ? new SimpleDateFormat("dd.MM.yyyy г.", Locale.getDefault()).format(birthday) : "");
    }

    void onResume() {
        EventBus.getDefault().post(new SetBackButtonEvent(true));
        EventBus.getDefault().post(new ChangeTitleEvent(String.format("%s %s",
                StringUtils.capitalize(specialist.getFName()),
                StringUtils.capitalize(specialist.getLName()))));
    }

    void onBackPressed() {
        App.INSTANCE.getRouter().exit();
    }

    RecyclerView.Adapter onSetAdapter() {
        return adapter;
    }
}
