package com.coachmovecustomer.fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;

import java.sql.Time;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends BaseFragment {


    CircleImageView profile_Img;
    TextView userNameTV, userIDTV, genderTV, fitnessTV, financialTV, anamnesisTV;
    LinearLayout anamnesis_LL;
    View anamnesis_View, financial_View;
    ProfileData profileData = new ProfileData();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle("", true);
        profileData = baseActivity.store.getProfileData();

        initUI(view);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.edit, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.settings:
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new SettingsFragment()).addToBackStack(null).commit();
                break;
            case R.id.editProfile:
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new EditProfileFragment()).addToBackStack(null).commit();
                break;


        }

        return false;
    }

    private void initUI(View view) {

        profile_Img = view.findViewById(R.id.profile_Img);
        userNameTV = view.findViewById(R.id.userNameTV);
        userIDTV = view.findViewById(R.id.userIDTV);
        genderTV = view.findViewById(R.id.genderTV);
        fitnessTV = view.findViewById(R.id.fitnessTV);
        anamnesis_LL = view.findViewById(R.id.anamnesis_LL);
        anamnesis_View = view.findViewById(R.id.anamnesis_View);
        financialTV = view.findViewById(R.id.financialTV);
        anamnesisTV = view.findViewById(R.id.anamnesisTV);
        financial_View = view.findViewById(R.id.financial_View);

        anamnesis_LL.setOnClickListener(this);
        financialTV.setOnClickListener(this);


        userNameTV.setText(profileData.firstName + " " + profileData.lastName);
        userIDTV.setText(" (" + profileData.accountId + ")");
        if (profileData.gender.equals("M"))
            genderTV.setText(getResources().getString(R.string.male));
        else {
            genderTV.setText(getResources().getString(R.string.female));
        }

        fitnessTV.setText(profileData.fitnessLevel.level_pt);

//        if (baseActivity.store.getLanguage().equalsIgnoreCase("en")) {
//            fitnessTV.setText(profileData.fitnessLevel.level);
//        } else {
//            fitnessTV.setText(profileData.fitnessLevel.level_pt);
//        }


        if (profileData.profilePicPath != null && !profileData.profilePicPath.isEmpty())
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + profileData.profilePicPath /*+ "" + BaseActivity.setCurrentTimeMillis(baseActivity)*/)
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.user_dummy)
                            .error(android.R.drawable.stat_notify_error))
                    .into(profile_Img);


        gotoMainFragment(new AnamnesisFragment());
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.anamnesis_LL:
                anamnesis_View.setVisibility(View.VISIBLE);
                financial_View.setVisibility(View.INVISIBLE);
                financialTV.setTextSize(14f);
                anamnesisTV.setTextSize(16f);
                gotoMainFragment(new AnamnesisFragment());
                break;


            case R.id.financialTV:
                financial_View.setVisibility(View.VISIBLE);
                anamnesis_View.setVisibility(View.INVISIBLE);
                financialTV.setTextSize(16f);
                anamnesisTV.setTextSize(14f);
                gotoMainFragment(new FinancialFragment());
                break;

        }
    }

    private void gotoMainFragment(Fragment targetFragment) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.frameProfile, targetFragment)
                .commit();
    }
}
