package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.data.AboutUsData;
import com.coachmovecustomer.data.Cards;
import com.coachmovecustomer.data.ChatData;
import com.coachmovecustomer.utils.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;

public class AboutUsFragment extends BaseFragment {
    TextView aboutUsTV;
    AboutUsData aboutUsData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        getContext().getTheme().applyStyle(R.style.customCheckBox, true);
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.aboutUs), false);
        initUI(view);
    }

    private void initUI(View view) {
        aboutUsTV = view.findViewById(R.id.aboutUsTV);

        aboutUSApi();
    }

    private void aboutUSApi() {

        Call<JsonObject> aboutUsCall = baseActivity.apiInterface.getAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.URL_AboutUS);
        baseActivity.apiHitAndHandle.makeApiCall(aboutUsCall, this);
        baseActivity.startProgressDialog();
    }

    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        try {
            aboutUsData = new Gson().fromJson(resp, AboutUsData.class);
            aboutUsTV.setText(Html.fromHtml(aboutUsData.data.aboutus.text));

        } catch (Exception e) {

        }
    }
}
