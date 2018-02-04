package com.test.employees.presentation.specialitieslist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.test.employees.R;
import com.test.employees.other.components.DividerDecoration;
import com.test.employees.other.fabric.FrmFabric;

public class SpecialitiesListFragment extends MvpAppCompatFragment implements SpecialitiesListView, FrmFabric.IFragment {

    @InjectPresenter
    SpecialitiesListPresenter presenter;

    RecyclerView specialitiesList;

    public static SpecialitiesListFragment newInstance(Bundle bundle) {
        SpecialitiesListFragment fragment = new SpecialitiesListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specialities_list, container, false);

        specialitiesList = view.findViewById(R.id.specialities_list);
        specialitiesList.setLayoutManager(new LinearLayoutManager(getContext()));
        specialitiesList.addItemDecoration(new DividerDecoration(getContext(), R.drawable.divider, 50, 50));
        specialitiesList.setAdapter(presenter.onSetAdapter());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public FrmFabric.Type getType() {
        return FrmFabric.Type.SPECIALITIES_LIST;
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }
}
