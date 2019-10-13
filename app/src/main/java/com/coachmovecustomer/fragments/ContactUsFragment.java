package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.customviews.ButtonView;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.retrofitManager.ApiResponse;
import com.coachmovecustomer.utils.Const;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;

public class ContactUsFragment extends BaseFragment {

    EditText emailEDT, subjectEDT, descriptionEDT;
    Button submitBtn;
    private ProfileData profileData = new ProfileData();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.contactUs), false);
        profileData = baseActivity.store.getProfileData();
        initUI(view);

    }

    private void initUI(View view) {
        emailEDT = view.findViewById(R.id.emailEDT);
        subjectEDT = view.findViewById(R.id.subjectEDT);
        descriptionEDT = view.findViewById(R.id.descriptionEDT);
        submitBtn = view.findViewById(R.id.submitBtn);

        emailEDT.setFilters(baseActivity.setFiltersET(30));
        subjectEDT.setFilters(baseActivity.setFiltersET(30));
        descriptionEDT.setFilters(baseActivity.setFiltersET(200));
        submitBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.submitBtn:

                if (emailEDT.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.email_alert), false);
                } else if (!baseActivity.isValidMail(emailEDT.getText().toString().trim())) {
                    showToast(getResources().getString(R.string.valid_email), false);
                } else if (subjectEDT.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertSubject));
                } else if (descriptionEDT.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertDescription));
                } else {
                    contactApi();

                }
                break;
        }
    }

    private void contactApi() {

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("email", emailEDT.getText().toString());
        jsonObject.put("subject", subjectEDT.getText().toString());
        jsonObject.put("description", descriptionEDT.getText().toString());

        Log.e("jsonObject=====>>>.", jsonObject + " ");

        Call<JsonObject> addFeedBackApi = baseActivity.apiInterface.postApiObject("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.url + "/users/" + profileData.id + "/contactUs", jsonObject);
        baseActivity.apiHitAndHandle.makeApiCall(addFeedBackApi, this);

        baseActivity.startProgressDialog();
    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            JSONObject data = jsonObject.optJSONObject("data");

            showToast(jsonObject.optString("message"));
            emailEDT.getText().clear();
            subjectEDT.getText().clear();
            descriptionEDT.getText().clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
