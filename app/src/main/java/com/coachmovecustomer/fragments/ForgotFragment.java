package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.LoginSignActivity;
import com.coachmovecustomer.utils.Const;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;

public class ForgotFragment extends BaseFragment {

    private Button submitBTN;
    EditText emailET;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((LoginSignActivity) getActivity()).setToolbar(true, true, getResources().getString(R.string.forgotPassScreen));
        initUI(view);
    }

    private void initUI(View view) {

        emailET = view.findViewById(R.id.emailET);
        submitBTN = view.findViewById(R.id.submitBTN);

        submitBTN.setOnClickListener(this);

        emailET.setFilters(baseActivity.setFiltersET(30));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.submitBTN:

                if (emailET.getText().toString().trim().length() == 0) {
                  /*  emailET.setError(getString(R.string.valid_email));
                    emailET.requestFocus();*/
                    showToast(getString(R.string.email_register_alert), false);
                } else if (!baseActivity.isValidMail(emailET.getText().toString().trim())) {
                    showToast(getString(R.string.valid_email), false);
                } else {
                    forgotPasswordApi();
                }

                break;
        }
    }

    private void forgotPasswordApi() {
        HashMap<String, String> jsonbody = new HashMap<String, String>();
        jsonbody.put("email", emailET.getText().toString());
        jsonbody.put("roleId", "2");
        Call<JsonObject> call = baseActivity.apiInterface.putAPI(baseActivity.store.getString(Const.ACCESS_TOKEN), Const.FORGOT_API, jsonbody);
        baseActivity.apiHitAndHandle.makeApiCall(call, this);
        baseActivity.startProgressDialog();

    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            JSONObject data = jsonObject.optJSONObject("data");

//            showToast(jsonObject.optString("message"), false);
//            String toast =  jsonObject.optString("message") ;
            toastMethod(jsonObject.optString("message"));

            emailET.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void toastMethod(String message) {

        final Toast toast = Toast.makeText(baseActivity,message, Toast.LENGTH_SHORT);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 8000);
    }
}