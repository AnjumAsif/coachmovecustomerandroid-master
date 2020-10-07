package com.coachmovecustomer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.IntroActivity;
import com.coachmovecustomer.activity.LoginSignActivity;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.PrefStore;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.invoke.ConstantCallSite;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class CreateProfileSecondFragment extends BaseFragment {


    EditText ageET, weightET, heightET, surgeriesET, heartDiseasesET, jointET, medicationsET, otherET, cpfET;
    CheckBox termsCB;
    Button createProfile_btn;
    ProfileData profileData = new ProfileData();
    String fitnessID, fitnessLevelStr;
    private PrefStore mPrefStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_profile_second, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileData = baseActivity.store.getProfileData();
        ((LoginSignActivity) getActivity()).setToolbar(true, true, getResources().getString(R.string.createProfile));
        initUI(view);


    }

    private void initUI(View view) {

        cpfET = view.findViewById(R.id.cpfET);
        ageET = view.findViewById(R.id.ageET);
        weightET = view.findViewById(R.id.weightET);
        heightET = view.findViewById(R.id.heightET);
        surgeriesET = view.findViewById(R.id.surgeriesET);
        heartDiseasesET = view.findViewById(R.id.heartDiseasesET);
        jointET = view.findViewById(R.id.jointET);
        medicationsET = view.findViewById(R.id.medicationsET);
        otherET = view.findViewById(R.id.otherET);
        termsCB = view.findViewById(R.id.termsCB);
        createProfile_btn = view.findViewById(R.id.createProfile_btn);

        cpfET.setFilters(baseActivity.setFiltersET(14));
        ageET.setFilters(baseActivity.setFiltersET(3));
        weightET.setFilters(baseActivity.setFiltersET(4));
        heightET.setFilters(baseActivity.setFiltersET(4));
        surgeriesET.setFilters(baseActivity.setFiltersET(150));
        heartDiseasesET.setFilters(baseActivity.setFiltersET(150));
        jointET.setFilters(baseActivity.setFiltersET(150));
        medicationsET.setFilters(baseActivity.setFiltersET(150));
        otherET.setFilters(baseActivity.setFiltersET(150));


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            profileData = bundle.getParcelable("profileData");
            fitnessID = bundle.getString("fitnessID");
            fitnessLevelStr = bundle.getString("fitnessLevelStr");


        }


        createProfile_btn.setOnClickListener(this);
    }

//    Selected gender not shown

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.createProfile_btn:
                if (cpfET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertCPFNumber), false);
                } else if (ageET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertAge), false);
                } else if (weightET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertWeight), false);
                } else if (heightET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertHeight), false);
                }

                /*else if (surgeriesET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertSurgeries));
                } else if (heartDiseasesET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertHeartDiseases));
                } else if (jointET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertJoint));
                } else if (medicationsET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertMedications));
                }*/

                else if (!termsCB.isChecked()) {
                    showToast(getResources().getString(R.string.alertTermCondition));
                } else {
                    createProfileApi();
                }
                break;
        }
    }

    private void createProfileApi() {

        JSONObject jsonbody = new JSONObject();
        try {
            jsonbody.put("firstName", profileData.firstName);
            jsonbody.put("lastName", profileData.lastName);
            jsonbody.put("mobile", String.valueOf(profileData.mobile));

            if (profileData.gender.equalsIgnoreCase(getResources().getString(R.string.male))) {
                jsonbody.put("gender", "M");
            } else {
                jsonbody.put("gender", "F");
            }

//            jsonbody.put("gender", String.valueOf(profileData.gender.charAt(0)));

            JSONObject fitnessLevel = new JSONObject();
            fitnessLevel.put("id", fitnessID);
            fitnessLevel.put("level", fitnessLevelStr);
            jsonbody.put("fitnessLevel", fitnessLevel);

            jsonbody.put("cpfNo", cpfET.getText().toString().trim());
            jsonbody.put("weight", weightET.getText().toString().trim());
            jsonbody.put("surgeries", surgeriesET.getText().toString().trim());
            jsonbody.put("medication", medicationsET.getText().toString().trim());
            jsonbody.put("id", String.valueOf(profileData.id));
            jsonbody.put("accountId", profileData.accountId);
            jsonbody.put("profilePicPath", profileData.profilePicPath);
            jsonbody.put("jointPains", jointET.getText().toString().trim());
            jsonbody.put("heartDiseases", heartDiseasesET.getText().toString().trim());
            jsonbody.put("email", profileData.email);
            jsonbody.put("age", ageET.getText().toString().trim());
            jsonbody.put("others", otherET.getText().toString().trim());
            jsonbody.put("height", heightET.getText().toString().trim());


        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("jsonBody=====", jsonbody + "");

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonbody + "");
        Call<JsonObject> call = baseActivity.apiInterface.updateProfile("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.PROFILE_SIGN_UP, body);
        baseActivity.apiHitAndHandle.makeApiCall(call, this);
        baseActivity.startProgressDialog();


    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {

        baseActivity.stopProgressDialog();
        try {
            log(object.toString());
            JSONObject jsonObject = new JSONObject(object.toString());
            JSONObject data = jsonObject.optJSONObject("data");
            JSONObject user = data.optJSONObject("user");
            String access_token = baseActivity.store.getString(Const.ACCESS_TOKEN);
            baseActivity.store.saveString(Const.ACCESS_TOKEN, access_token);
            profileData = new Gson().fromJson(user.toString(), ProfileData.class);
            baseActivity.store.setProfileData(profileData);

            baseActivity.store.saveString(Const.LANGUAGE, baseActivity.store.getString(Const.LANGUAGE));
            /*code for first time user*/
            Intent intent = new Intent(baseActivity, IntroActivity.class);
            startActivity(intent);
            baseActivity.finish();

            /*Intent intent = new Intent(baseActivity, MainActivity.class);
            startActivity(intent);
            baseActivity.finish();*/
            /*getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayoutLogin, new HomeFragment())
                    .commit();*/

//            showToast("Successfully profile created", false);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}