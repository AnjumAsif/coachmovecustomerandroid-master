package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;

public class PaymentSuccessFragment extends BaseFragment {

    Button thanksBTN ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_payment_success, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.payments), false);
        setHasOptionsMenu(false);
        initUI(view);
    }

    private void initUI(View view) {


        thanksBTN = view.findViewById(R.id.thanksBTN);
        thanksBTN.setOnClickListener(this);

    }


    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.thanksBTN:
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new WorkoutFragment())
                        .commit();
                break;
        }
    }

}
