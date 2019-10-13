package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;

public class UpcomingScheduleFragment extends BaseFragment {

    RecyclerView scheduleRV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upcoming_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.schedule), false);
        initUI(view);
    }

    private void initUI(View view) {
        scheduleRV = view.findViewById(R.id.scheduleRV);
    }


}
