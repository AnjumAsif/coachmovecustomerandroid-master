package com.coachmovecustomer.fragments;

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
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;

public class SignUpFragment extends BaseFragment {

    private Button signUp_btn;
    EditText emailEDT, passwordEDT, confirm_passwordEDT;
    TextView signInTV;
    private ProfileData profileData;
    private String android_deviceID;
    String languageType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((LoginSignActivity) getActivity()).setToolbar(false, false, "");
        initUI(view);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            languageType = bundle.getString("language");
        }
    }

    private void initUI(View view) {

        emailEDT = view.findViewById(R.id.emailEDT);
        passwordEDT = view.findViewById(R.id.passwordEDT);
        confirm_passwordEDT = view.findViewById(R.id.confirm_passwordEDT);
        signInTV = view.findViewById(R.id.signInTV);
        signUp_btn = view.findViewById(R.id.signUp_btn);

        signUp_btn.setOnClickListener(this);
        signInTV.setOnClickListener(this);

        emailEDT.setFilters(baseActivity.setFiltersET(30));
        passwordEDT.setFilters(baseActivity.setFiltersET(30));
        confirm_passwordEDT.setFilters(baseActivity.setFiltersET(30));

        android_deviceID = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.signUp_btn:
                if (emailEDT.getText().toString().trim().length() == 0) {
                    /*emailEDT.setError(getString(R.string.valid_email));
                    emailEDT.requestFocus();*/
                    showToast(getString(R.string.email_alert), false);
                } else if (emailEDT.getText().toString().trim().length()<7) {
                    showToast(getString(R.string.valid_email), false);
                }else if (!baseActivity.isValidMail(emailEDT.getText().toString().trim())) {
                    showToast(getString(R.string.valid_email), false);
                } else if (passwordEDT.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.passwordEmpty), false);
                } else if (passwordEDT.getText().toString().trim().length() <= 7) {
                    showToast(getString(R.string.passwordLength), false);
                } else if (confirm_passwordEDT.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.confirmEmpty), false);
                } else if (!passwordEDT.getText().toString().equals(confirm_passwordEDT.getText().toString())) {
                    showToast(getString(R.string.passwordconfirmpassword), false);
                } else {
                    signUpMethod();
                }
                break;

            case R.id.signInTV:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutLogin, new LoginFragment())
                        .commit();
                break;
        }
    }

    private void signUpMethod() {
        HashMap<String, String> jsonbody = new HashMap<String, String>();
        jsonbody.put("email", emailEDT.getText().toString());
        jsonbody.put("password", passwordEDT.getText().toString());
        jsonbody.put("deviceId", android_deviceID);
        jsonbody.put("token", baseActivity.store.getString(Const.FIREBASE_TOKEN));
        Call<JsonObject> call = baseActivity.apiInterface.postAPI(Const.PROFILE_SIGN_UP, jsonbody);
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
            String access_token = data.optString("access_token");

            baseActivity.store.saveString(Const.ACCESS_TOKEN, access_token);

            profileData = new Gson().fromJson(user.toString(), ProfileData.class);
            baseActivity.store.setProfileData(profileData);

            Fragment createProfileFirstFragment = new CreateProfileFirstFragment();
            Bundle bundle = new Bundle();
//            bundle.putString("language", languageType);
            createProfileFirstFragment.setArguments(bundle);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayoutLogin, createProfileFirstFragment)
                    .addToBackStack(null)
                    .commit();

            emailEDT.setText("");
            passwordEDT.setText("");
            confirm_passwordEDT.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}