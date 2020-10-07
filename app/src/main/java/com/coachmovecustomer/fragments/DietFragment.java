package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.adapters.DietDetailAdapter;
import com.coachmovecustomer.data.DietDetailData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.utils.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;

public class DietFragment extends BaseFragment {


    ImageView addIV;
    RecyclerView dietRV;
    TextView noDataTV;
    private ArrayList<DietDetailData> dietDataList = new ArrayList<>();
    DietDetailAdapter dietAdapter;
    ProfileData profileData = new ProfileData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.diet), false);
        profileData = baseActivity.store.getProfileData();
        setHasOptionsMenu(true);
        initUI(view);
    }

    private void initUI(View view) {
        dietRV = view.findViewById(R.id.dietRV);
        addIV = view.findViewById(R.id.addIV);
        noDataTV = view.findViewById(R.id.noDataTV);
        addIV.setOnClickListener(this);

        getDietApi();
        setDietData();
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void getDietApi() {
        Call<JsonObject> fetchDietCall = baseActivity.apiInterface.getAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.DIET_API + profileData.id + "/diets");
        baseActivity.apiHitAndHandle.makeApiCall(fetchDietCall, this);
        baseActivity.startProgressDialog();
    }

    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            JSONObject data = jsonObject.getJSONObject("data");
            dietDataList.clear();
            JSONArray diets = data.getJSONArray("diets");
            for (int i = 0; i < diets.length(); i++) {
                Log.e("jsonDiet", diets.get(i).toString() + "");
                DietDetailData dietData = new Gson().fromJson(diets.get(i).toString(), DietDetailData.class);
                dietDataList.add(dietData);
            }
            if (dietDataList.size() > 0) {
                dietRV.setVisibility(View.VISIBLE);
                noDataTV.setVisibility(View.GONE);
            } else {
                noDataTV.setVisibility(View.VISIBLE);
                dietRV.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDietData() {
        dietAdapter = new DietDetailAdapter(baseActivity, dietDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        dietRV.setLayoutManager(mLayoutManager);
        dietRV.setItemAnimator(new DefaultItemAnimator());
        dietRV.setAdapter(dietAdapter);

        onClickRecycler();
    }


    private void onClickRecycler() {
        dietAdapter.setOnNewClick(new onClickAdd() {
            @Override
            public void onClick(Object obj, int pos) {


                if ( dietDataList.get(pos).status ==4){

                   showToast(getResources().getString(R.string.dietCancel),false);


                }else {
                Fragment fragmentGet = new DietDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("dietID", dietDataList.get(pos).id + "");
                fragmentGet.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, fragmentGet)
                        .addToBackStack(null)
                        .commit();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.addIV:
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new AddDietFragment())
                        .addToBackStack(null)
                        .commit();
                break;

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.settings, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.settings:
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new SettingsFragment()).addToBackStack(null).commit();
                break;


        }

        return false;
    }

}
