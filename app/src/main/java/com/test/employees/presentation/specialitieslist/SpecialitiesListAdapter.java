package com.test.employees.presentation.specialitieslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.employees.R;
import com.test.employees.data.db.dataclass.Speciality;

import java.util.List;

public class SpecialitiesListAdapter extends RecyclerView.Adapter<SpecialitiesListAdapter.SpecialityHolder> {

    private List<Speciality> data;
    private IClickListener clickListener;

    SpecialitiesListAdapter(List<Speciality> data, IClickListener clickListener) {
        this.data = data;
        this.clickListener = clickListener;
    }

    @Override
    public SpecialitiesListAdapter.SpecialityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speciality, parent, false);
        return new SpecialityHolder(view);
    }

    @Override
    public void onBindViewHolder(SpecialityHolder holder, int position) {
        Speciality speciality = data.get(position);
        holder.specialityText.setText(speciality.getName());
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return data != null ? data.get(position).getSpecialityId() : 0;
    }

    interface IClickListener {
        void onItemClick(int position);
    }

    class SpecialityHolder extends RecyclerView.ViewHolder {
        TextView specialityText;
        SpecialityHolder(View itemView) {
            super(itemView);
            specialityText=itemView.findViewById(R.id.speciality_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(getLayoutPosition());
                }
            });
        }
    }
}
