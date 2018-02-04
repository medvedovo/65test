package com.test.employees.presentation.employeedetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.employees.R;
import com.test.employees.data.db.dataclass.Speciality;

import java.util.List;

public class EmployeeSpecialitiesAdapter extends RecyclerView.Adapter<EmployeeSpecialitiesAdapter.SpecialityHolder> {

    private List<Speciality> data;

    EmployeeSpecialitiesAdapter(List<Speciality> data) {
        this.data = data;
    }

    @Override
    public SpecialityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee_speciality, parent, false);

        return new SpecialityHolder(view);
    }

    @Override
    public void onBindViewHolder(SpecialityHolder holder, int position) {
        Speciality speciality = data.get(position);
        holder.nameText.setText(speciality.getName());
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class SpecialityHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        SpecialityHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.speciality_name);
        }
    }
}
