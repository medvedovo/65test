package com.test.employees.presentation.employeeslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.employees.App;
import com.test.employees.R;
import com.test.employees.data.db.dataclass.Specialist;
import com.test.employees.other.DateUtils;
import com.test.employees.other.StringUtils;

import java.util.List;
import java.util.Locale;

public class EmployeesListAdapter extends RecyclerView.Adapter<EmployeesListAdapter.EmployeeHolder> {

    public List<Specialist> data;
    private IClickListener clickListener;

    EmployeesListAdapter(List<Specialist> data, IClickListener clickListener) {
        this.data = data;
        this.clickListener = clickListener;
    }

    @Override
    public EmployeeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_specialist, parent, false);

        return new EmployeeHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeHolder holder, int position) {
        Specialist specialist = data.get(position);
        holder.nameText.setText(String.format("%s %s",
                StringUtils.capitalize(specialist.getFName()),
                StringUtils.capitalize(specialist.getLName())));
        if (specialist.getBirthday() == null || specialist.getBirthday().isEmpty()) {
            holder.ageText.setText("—");
        } else {
            int age = DateUtils.calculateAge(specialist.getBirthday());
            if (age != 0) {
                holder.ageText.setText(String.format(Locale.getDefault(),
                        "%d %s", age, StringUtils.wordCaseString(age,
                                App.INSTANCE.getString(R.string.age1),
                                App.INSTANCE.getString(R.string.age2),
                                App.INSTANCE.getString(R.string.age5))));
            }
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return data != null ? data.get(position).getSpecialistId() : 0;
    }

    interface IClickListener {
        void onItemClick(int position);
    }

    class EmployeeHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView ageText;
        EmployeeHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.specialist_name);
            ageText = itemView.findViewById(R.id.specialist_age);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(getLayoutPosition());
                }
            });
        }
    }
}
