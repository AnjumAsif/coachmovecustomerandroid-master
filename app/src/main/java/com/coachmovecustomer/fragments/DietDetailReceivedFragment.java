package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;

public class DietDetailReceivedFragment extends BaseFragment {


    Button receivedBTN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diet_detail_received, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle("Weight Loss", false);
        setHasOptionsMenu(false);
        initUI(view);
    }

    private void initUI(View view) {


        receivedBTN = view.findViewById(R.id.receivedBTN);

        receivedBTN.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.receivedBTN:

                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new DietFragment())
                        .commit();

                break;
        }

    }

}
