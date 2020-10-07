package com.coachmovecustomer.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.LoginSignActivity;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.activity.SplashActivity;
import com.coachmovecustomer.data.AddFitnessData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;

public class SettingsFragment extends BaseFragment {

    LinearLayout notificationLL, myCardsLL, privacyPolicyLL, aboutUsLL, contactUsLL, termConditionLL, logoutLL, languageLL;
    ProfileData profileData = new ProfileData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileData = baseActivity.store.getProfileData();
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.settings), false);
        setHasOptionsMenu(false);
        initUI(view);
    }

    private void initUI(View view) {

        notificationLL = view.findViewById(R.id.notificationLL);
        myCardsLL = view.findViewById(R.id.myCardsLL);
        aboutUsLL = view.findViewById(R.id.aboutUsLL);
        contactUsLL = view.findViewById(R.id.contactUsLL);
        privacyPolicyLL = view.findViewById(R.id.privacyPolicyLL);
        termConditionLL = view.findViewById(R.id.termConditionLL);
        logoutLL = view.findViewById(R.id.logoutLL);
        languageLL = view.findViewById(R.id.languageLL);

        notificationLL.setOnClickListener(this);
        myCardsLL.setOnClickListener(this);
        aboutUsLL.setOnClickListener(this);
        contactUsLL.setOnClickListener(this);
        privacyPolicyLL.setOnClickListener(this);
        termConditionLL.setOnClickListener(this);
        logoutLL.setOnClickListener(this);
        languageLL.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.notificationLL:

                gotoMainFragment(new NotificationFragment());
                break;


            case R.id.myCardsLL:

                gotoMainFragment(new MyCardsFragment());
                break;

            case R.id.aboutUsLL:
       /*         Bundle bundle = new Bundle();
                bundle.putString("status", getResources().getString(R.string.aboutUs));
                bundle.putString("url", Const.URL_SETTINGS);
                WebFragment aboutUsFragment = new WebFragment();
                aboutUsFragment.setArguments(bundle);
                gotoMainFragment(aboutUsFragment);*/
                gotoMainFragment(new AboutUsFragment());
                break;

            case R.id.contactUsLL:
       /*         Bundle bundle = new Bundle();
                bundle.putString("status", getResources().getString(R.string.aboutUs));
                bundle.putString("url", Const.URL_SETTINGS);
                WebFragment aboutUsFragment = new WebFragment();
                aboutUsFragment.setArguments(bundle);
                gotoMainFragment(aboutUsFragment);*/
                gotoMainFragment(new ContactUsFragment());
                break;


            case R.id.privacyPolicyLL:

                Bundle privacy = new Bundle();
                privacy.putString("status", getResources().getString(R.string.privacyPolicy));
                privacy.putString("url", Const.URL_PrivacyPolicy);
                WebFragment privacyFragment = new WebFragment();
                privacyFragment.setArguments(privacy);
                gotoMainFragment(privacyFragment);
                break;

            case R.id.termConditionLL:

                Bundle terms = new Bundle();
                terms.putString("status", getResources().getString(R.string.termCond));
                terms.putString("url", Const.URL_TermAndCondition);
                WebFragment termsFragment = new WebFragment();
                termsFragment.setArguments(terms);
                gotoMainFragment(termsFragment);
                break;


            case R.id.languageLL:
//                gotoMainFragment(new LanguageFragment());

//                languageDialog();

                break;
            case R.id.logoutLL:
                showLogout();
                break;

        }
    }


    private void showLogout() {
        baseActivity.setTheme(R.style.customCheckBox);
        AlertDialog.Builder builder = new AlertDialog.Builder(baseActivity);
        builder.setMessage(getString(R.string.are_you_sure_want_to_Logout))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        logoutApi();
                    }
                }).setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    private void logout() {

        Intent newIntent = new Intent(baseActivity, LoginSignActivity.class);
//        Intent newIntent = new Intent(baseActivity, SplashActivity.class);
        startActivity(newIntent);
        baseActivity.finish();
        baseActivity.store.setProfileData(null);
        baseActivity.store.saveString(Const.ACCESS_TOKEN, "");
//        baseActivity.store.cleanPref();

    }


    private void gotoMainFragment(Fragment targetFragment) {
        baseActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayoutMain, targetFragment)
                .addToBackStack(null)
                .commit();
    }


    private void languageDialog() {
        baseActivity.setTheme(R.style.customCheckBox);
        int selectedPos = -1;
        AlertDialog.Builder builder;
        CharSequence[] array = {"English", "Portuguese"};
        final String selectedLAng = baseActivity.store.getLanguage();
        if (selectedLAng.equals("en")) {
            selectedPos = 0;
        } else if (selectedLAng.equals("pt")) {
            selectedPos = 1;
        }
        builder = new AlertDialog.Builder(baseActivity);
        builder.setTitle(getString(R.string.changeLanguage))
                .setSingleChoiceItems(array, selectedPos, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int pos) {
// TODO Auto-generated method stub
                        dialog.dismiss();
                        if (pos == 0) {
//                            baseActivity.store.saveString(Const.LANGUAGE, "en");
                            baseActivity.store.setLanguage("en");
                        } else {
//                            baseActivity.store.saveString(Const.LANGUAGE, "pt");
                            baseActivity.store.setLanguage("pt");
                        }
                        Intent main = new Intent(baseActivity, MainActivity.class);
                        startActivity(main);
                        baseActivity.finish();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void logoutApi() {

        HashMap<String, String> jsonbody = new HashMap<String, String>();
        jsonbody.put("token", baseActivity.store.getString(Const.FIREBASE_TOKEN));
        Call<JsonObject> call = baseActivity.apiInterface.postAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.LOGOUT_API + profileData.id + "/logout", jsonbody);
        baseActivity.apiHitAndHandle.makeApiCall(call, this);

        baseActivity.startProgressDialog();
    }

    @Override
    public void onSuccess(Call call, Object object, String reps) {
        baseActivity.stopProgressDialog();

        try {
            log(object.toString());
       /*     JSONObject jsonObject = new JSONObject(object.toString());
            JSONObject data = jsonObject.optJSONObject("data");
            JSONObject user = data.optJSONObject("user");*/


            logout();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


