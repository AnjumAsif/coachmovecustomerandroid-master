package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.data.ProfileData;

public  class AnamnesisFragment extends BaseFragment {

    ProfileData profileData = new ProfileData();
    TextView ageTV ,weightTV, heightTV ,surgerieTV,heartDiseasesTV, jointPainsTV, medicationsTV,otherTV ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anamnesis, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //   ((LoginSignupActivity) getActivity()).setToolbar(false, false, "");
        profileData = baseActivity.store.getProfileData();
        initUI(view);
    }

    private void initUI(View view) {

        ageTV = view.findViewById(R.id.ageTV);
        weightTV = view.findViewById(R.id.weightTV);
        heightTV = view.findViewById(R.id.heightTV);
        surgerieTV = view.findViewById(R.id.surgerieTV);
        heartDiseasesTV = view.findViewById(R.id.heartDiseasesTV);
        jointPainsTV = view.findViewById(R.id.jointPainsTV);
        medicationsTV = view.findViewById(R.id.medicationsTV);
        otherTV = view.findViewById(R.id.otherTV);


        ageTV.setText(profileData.age+"");
        weightTV.setText(profileData.weight+"");
        heightTV.setText(profileData.height+"");
        surgerieTV.setText(profileData.surgeries);
        heartDiseasesTV.setText(profileData.heartDiseases);
        jointPainsTV.setText(profileData.jointPains);
        medicationsTV.setText(profileData.medication);
        otherTV.setText(profileData.others);

    }

}
