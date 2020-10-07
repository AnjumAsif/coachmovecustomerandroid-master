package com.coachmovecustomer.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.adapters.NearbyCoachesAdapter;
import com.coachmovecustomer.data.AddedPeopleData;
import com.coachmovecustomer.data.NearbyCoachesData;
import com.coachmovecustomer.data.PeopleForAddData;
import com.coachmovecustomer.data.SearchWorkoutData;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.MyDividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;

public class NearbyCoachFragment extends BaseFragment implements TextView.OnEditorActionListener {

    SearchWorkoutData searchWorkoutData;
    EditText searchET;
    RecyclerView nearbyCoachRV;
    private ArrayList<NearbyCoachesData> nearbyCoachesDataList = new ArrayList<>();
    NearbyCoachesAdapter nearbyCoachesAdapter;
    TextView noDataTV;
    //    RelativeLayout search_RL;
    LinearLayout search_RL;
    String neigbourhoodStr = "", modalityIdStr = "", search = "", timeStr = "", dateStr = "", genderStr = "";


    private ArrayList<PeopleForAddData> selectedPeopleDataList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nearby_coach, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.coaches), false);
        setHasOptionsMenu(false);
        initUI(view);
    }

    private void initUI(View view) {

        search_RL = view.findViewById(R.id.search_RL);
        nearbyCoachRV = view.findViewById(R.id.nearbyCoachRV);
        searchET = view.findViewById(R.id.searchET);
        noDataTV = view.findViewById(R.id.noDataTV);
        searchET.addTextChangedListener(watcher);
        searchET.setOnEditorActionListener(this);

        searchET.setFilters(baseActivity.setFiltersET(30));

        searchWorkoutData = getArguments().getParcelable("SearchCoaches");
        if (searchWorkoutData != null) {
            neigbourhoodStr = searchWorkoutData.neighbourhood;
            modalityIdStr = searchWorkoutData.modality;
            timeStr = searchWorkoutData.time;
            dateStr = searchWorkoutData.date;

            if (searchWorkoutData.gender.isEmpty()){
                genderStr = "";
            }
            else {
                genderStr = String.valueOf(searchWorkoutData.gender.charAt(0));
            }

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getNearbyCoaches(search);

    }

    private void getNearbyCoaches(String searchStr) {
        Call<JsonObject> nearbyCoach = baseActivity.apiInterface.getSearchWorkoutCoaches("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), neigbourhoodStr, modalityIdStr, searchStr, timeStr, dateStr, genderStr);
        baseActivity.apiHitAndHandle.makeApiCall(nearbyCoach, this);
        baseActivity.startProgressDialog();
    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        try {
            JSONObject jsonObject = new JSONObject(resp);
            JSONObject data = jsonObject.getJSONObject("data");
            nearbyCoachesDataList.clear();
            JSONArray coaches = data.getJSONArray("coaches");
            Log.e("coaches", coaches + " ");
            for (int i = 0; i < coaches.length(); i++) {
                NearbyCoachesData nearbyCoachesData = new Gson().fromJson(coaches.get(i).toString(), NearbyCoachesData.class);
                nearbyCoachesDataList.add(nearbyCoachesData);
            }

            if (nearbyCoachesDataList.size() > 0) {
                nearbyCoachRV.setVisibility(View.VISIBLE);
                noDataTV.setVisibility(View.GONE);
            } else {
                noDataTV.setVisibility(View.VISIBLE);
                nearbyCoachRV.setVisibility(View.GONE);
            }

            setNearbyCoaches();
        } catch (Exception e) {

        }

    }


    private void setNearbyCoaches() {
        nearbyCoachesAdapter = new NearbyCoachesAdapter(baseActivity, this, nearbyCoachesDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        nearbyCoachRV.setLayoutManager(mLayoutManager);
        nearbyCoachRV.setItemAnimator(new DefaultItemAnimator());
        nearbyCoachRV.addItemDecoration(new MyDividerItemDecoration(baseActivity, LinearLayoutManager.VERTICAL, 16));
        nearbyCoachRV.setAdapter(nearbyCoachesAdapter);

        onClickRecycler();
        onClickRecyclerPayment();
    }


    private void onClickRecycler() {
        nearbyCoachesAdapter.setOnCommentClick(new onClickAdd() {
            @Override
            public void onClick(Object obj, int pos) {
                Log.e("position", nearbyCoachesDataList.get(pos).id + "");
                Fragment fragmentGet = new CoachCommentsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", nearbyCoachesDataList.get(pos).id + "");
                bundle.putString("firstName", nearbyCoachesDataList.get(pos).firstName + "");
                bundle.putString("accountId", nearbyCoachesDataList.get(pos).accountId + "");
                bundle.putString("profilePicPath", nearbyCoachesDataList.get(pos).profilePicPath + "");
                bundle.putString("avgRating", nearbyCoachesDataList.get(pos).avgRating + "");
                bundle.putString("comments", nearbyCoachesDataList.get(pos).comments + "");
                bundle.putString("experience", nearbyCoachesDataList.get(pos).experience + "");
                fragmentGet.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutMain, fragmentGet)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void onClickRecyclerPayment() {
        nearbyCoachesAdapter.setOnPaymentClick(new onClickAdd() {
            @Override
            public void onClick(Object obj, int pos) {
                Fragment fragmentGet = new PaymentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("coachName", nearbyCoachesDataList.get(pos).firstName + "");
                bundle.putString("price", searchWorkoutData.price + "");
                bundle.putString("date", searchWorkoutData.date + "");
                bundle.putString("time", searchWorkoutData.time + "");
                bundle.putString("address", searchWorkoutData.address + "");
                bundle.putString("gender", searchWorkoutData.gender);
                bundle.putString("modalityID", searchWorkoutData.modality);
                bundle.putString("neighbourhood", searchWorkoutData.neighbourhood);
                bundle.putString("timeslotId", nearbyCoachesDataList.get(pos).timeslotId + "");
                bundle.putString("requestTo", nearbyCoachesDataList.get(pos).id + "");


                selectedPeopleDataList = getArguments().getParcelableArrayList("workoutUser");
                if (selectedPeopleDataList != null) {
                    bundle.putParcelableArrayList("workoutUser", selectedPeopleDataList);
                    Log.e("sizeSElected", selectedPeopleDataList.size() + "");
                }


                fragmentGet.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutMain, fragmentGet)
                        .addToBackStack(null)
                        .commit();

            }
        });
    }


    TextWatcher watcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence newText, int a, int b, int c) {
            // TODO Auto-generated method stub
            if (nearbyCoachesAdapter != null)
                nearbyCoachesAdapter.getFilter().filter(newText);

            Log.e("newText", newText + "");

            search = String.valueOf(newText);

            if (searchET.length() == 0) {
                getNearbyCoaches(search);
            }
//            getNearbyCoaches(String.valueOf(newText));
        }
    };

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            getNearbyCoaches(search);
            // Your piece of code on keyboard search click
            return true;
        }

        return false;
    }
}
