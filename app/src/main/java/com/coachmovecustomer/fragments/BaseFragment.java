package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.retrofitManager.ApiResponse;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class BaseFragment extends Fragment implements AdapterView.OnItemClickListener,
        View.OnClickListener, AdapterView.OnItemSelectedListener,
        CompoundButton.OnCheckedChangeListener, ApiResponse {

    public BaseActivity baseActivity ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = (BaseActivity) getActivity();
        baseActivity.hideSoftKeyboard();

    }

    public void setToolbar(String title, boolean show) {
        ((MainActivity) baseActivity).setToolbarTitle(title, show);
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onClick(View v) {

    }

    public void showToast(String msg) {
        baseActivity.showToast(msg, false);
    }

    public void showToast(String msg, boolean isError) {
        baseActivity.showToast(msg, isError);
    }

    public void log(String s) {
        baseActivity.log(s);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }


    public boolean isValidPassword(String password) {
        return password.matches("^(?=\\S+$).{8,}$");
    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
    }

    @Override
    public void onError(Call call, String errorMessage, ApiResponse apiResponse, int responceCode) {
        baseActivity.stopProgressDialog();
        int code = responceCode;

        if (errorMessage != null && errorMessage.equals("socketTimeout")) {
            baseActivity.showRetry(call, apiResponse);
        } else {
            baseActivity.handleError(code, errorMessage);
            call.cancel();
        }
    }


    @Override
    public void onError(Call call, String errorMessage, ApiResponse apiResponse, int responceCode, JSONObject jsonObject) {
        baseActivity.stopProgressDialog();
        int code = responceCode;
        if (jsonObject.has("code"))
            code = jsonObject.optInt("code");

        if (errorMessage != null && errorMessage.equals("socketTimeout")) {
            baseActivity.showRetry(call, apiResponse);
        } else {

            baseActivity.handleError(code, errorMessage);
            call.cancel();
        }
    }

    public static RequestBody getRequestBodyParam(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }


}
