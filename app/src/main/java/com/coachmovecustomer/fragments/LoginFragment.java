package com.coachmovecustomer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.LoginSignActivity;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;

public class LoginFragment extends BaseFragment {

    private Button signInBTN;
    EditText emailET, passwordET;
    TextView forgotPasswordTV, accountTV, signUpTV;
    ProfileData profileData = new ProfileData();
    private String android_deviceID;
    private String languageType;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((LoginSignActivity) getActivity()).setToolbar(false, false, "");
        profileData = baseActivity.store.getProfileData();
        initUI(view);

    }

    private void initUI(View view) {

        emailET = view.findViewById(R.id.emailET);
        passwordET = view.findViewById(R.id.passwordET);
        forgotPasswordTV = view.findViewById(R.id.forgotPasswordTV);
        accountTV = view.findViewById(R.id.accountTV);
        signUpTV = view.findViewById(R.id.signUpTV);
        signInBTN = view.findViewById(R.id.signInBTN);

        forgotPasswordTV.setOnClickListener(this);
        signInBTN.setOnClickListener(this);
        accountTV.setOnClickListener(this);
        signUpTV.setOnClickListener(this);

        emailET.setFilters(baseActivity.setFiltersET(30));
        passwordET.setFilters(baseActivity.setFiltersET(30));
        android_deviceID = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);


    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.signInBTN:

                if (emailET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.email_register_alert), false);
               /*     emailET.setError(getString(R.string.valid_email));
                    emailET.requestFocus();*/
                } else if (!baseActivity.isValidMail(emailET.getText().toString().trim())) {
                    showToast(getResources().getString(R.string.valid_email), false);
                } else if (passwordET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.passwordEmpty));
                } else {


                    loginMethod();
                }


                break;

            case R.id.forgotPasswordTV:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutLogin, new ForgotFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.signUpTV:

          /*      Fragment signUpFragment = new SignUpFragment();
                Bundle bundle = new Bundle();
                bundle.putString("language", languageType);
                signUpFragment.setArguments(bundle);*/
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutLogin, new SignUpFragment())
                        .commit();

                break;
        }
    }

    private void loginMethod() {
        HashMap<String, String> jsonbody = new HashMap<String, String>();
        jsonbody.put("email", emailET.getText().toString());
        jsonbody.put("password", passwordET.getText().toString());
        jsonbody.put("roleId", "2");
        jsonbody.put("deviceId", android_deviceID);
        jsonbody.put("token", baseActivity.store.getString(Const.FIREBASE_TOKEN));
        Call<JsonObject> call = baseActivity.apiInterface.postAPI(Const.LOGIN_API, jsonbody);
        baseActivity.apiHitAndHandle.makeApiCall(call, this);
        baseActivity.startProgressDialog();
    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            JSONObject data = jsonObject.optJSONObject("data");
            JSONObject user = data.optJSONObject("user");
            String access_token = data.optString("access_token");
            Log.e("accessToken", access_token + "");
            baseActivity.store.saveString(Const.ACCESS_TOKEN, access_token);
            profileData = new Gson().fromJson(user.toString(), ProfileData.class);
            baseActivity.store.setProfileData(profileData);
            baseActivity.store.saveString(Const.LANGUAGE, baseActivity.store.getString(Const.LANGUAGE));
            if (profileData.isProfileCreated != null && profileData.isProfileCreated) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                baseActivity.startActivity(intent);
                baseActivity.finish();
            } else {
                gotoCreateProfile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void gotoCreateProfile() {
        /*
           Fragment createProfileFirstFragment = new CreateProfileFirstFragment();
           Bundle bundle = new Bundle();
           bundle.putString("language", languageType);
           createProfileFirstFragment.setArguments(bundle);
       */
        baseActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayoutLogin,new CreateProfileFirstFragment())
                .addToBackStack(null)
                .commit();
    }

}
