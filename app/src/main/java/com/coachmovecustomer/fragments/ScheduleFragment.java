package com.coachmovecustomer.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.RatingBar;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.activity.SingleChatActivity;
import com.coachmovecustomer.adapters.CompleteScheduleAdapter;
import com.coachmovecustomer.adapters.OngoingScheduleAdapter;
import com.coachmovecustomer.adapters.UpcomingScheduleAdapter;
import com.coachmovecustomer.data.AllScheduleData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.myInterface.onClickDelete;
import com.coachmovecustomer.utils.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ScheduleFragment extends BaseFragment {


    TextView upcoming_TV, ongoing_TV, complete_TV, noDataTV;
    RatingBar ratingBar;
    ProfileData profileData = new ProfileData();
    private String termSchedule = "upcoming";
    RecyclerView scheduleRV;
    ArrayList<AllScheduleData> allScheduleList = new ArrayList<>();

    UpcomingScheduleAdapter upcomingScheduleAdapter;
    OngoingScheduleAdapter ongoingScheduleAdapter;
    CompleteScheduleAdapter completeScheduleAdapter;
    private Call<JsonObject> fetchScheduleCall, cancelScheduleCall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.schedule), false);
        profileData = baseActivity.store.getProfileData();
        setHasOptionsMenu(true);
        initUI(view);
    }

    private void initUI(View view) {


        scheduleRV = view.findViewById(R.id.scheduleRV);
        noDataTV = view.findViewById(R.id.noDataTV);
        upcoming_TV = view.findViewById(R.id.upcoming_TV);
        ongoing_TV = view.findViewById(R.id.ongoing_TV);
        complete_TV = view.findViewById(R.id.complete_TV);
        ratingBar = view.findViewById(R.id.ratingBar);


        upcoming_TV.setOnClickListener(this);
        ongoing_TV.setOnClickListener(this);
        complete_TV.setOnClickListener(this);

        fetchScheduleApi(termSchedule);
        setSelectedTab(termSchedule);

    }


    @Override
    public void onResume() {
        super.onResume();


    }

    private void fetchScheduleApi(String selectedSchedule) {
        fetchScheduleCall = baseActivity.apiInterface.getAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN),
                Const.SCHEDULE_TERMS_API + profileData.id + "/workouts?term=" + selectedSchedule);
        baseActivity.apiHitAndHandle.makeApiCall(fetchScheduleCall, this);
        baseActivity.startProgressDialog();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.upcoming_TV:
                termSchedule = "upcoming";
                setSelectedTab(termSchedule);
                fetchScheduleApi(termSchedule);
                break;
            case R.id.ongoing_TV:
                termSchedule = "inprogress";
                setSelectedTab(termSchedule);
                fetchScheduleApi(termSchedule);
                break;
            case R.id.complete_TV:
                termSchedule = "completed";
                setSelectedTab(termSchedule);
                fetchScheduleApi(termSchedule);
                break;
        }
    }

    public void setSelectedTab(String tab) {
        unSeleted();
        if (termSchedule.equalsIgnoreCase("upcoming")) {
            upcoming_TV.setBackgroundResource(R.drawable.round_btn_black);
            upcoming_TV.setTextColor(getResources().getColor(R.color.White));
        } else if (termSchedule.equalsIgnoreCase("inprogress")) {
            ongoing_TV.setBackgroundResource(R.drawable.round_btn_black);
            ongoing_TV.setTextColor(getResources().getColor(R.color.White));
        } else if (termSchedule.equalsIgnoreCase("completed")) {
            complete_TV.setBackgroundResource(R.drawable.round_btn_black);
            complete_TV.setTextColor(getResources().getColor(R.color.White));
        }

    }

    private void unSeleted() {
        ongoing_TV.setTextColor(getResources().getColor(R.color.Black));
        ongoing_TV.setBackgroundResource(R.drawable.round_button_popup);
        complete_TV.setTextColor(getResources().getColor(R.color.Black));
        complete_TV.setBackgroundResource(R.drawable.round_button_popup);
        upcoming_TV.setBackgroundResource(R.drawable.round_button_popup);
        upcoming_TV.setTextColor(getResources().getColor(R.color.Black));
    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        if (call == fetchScheduleCall) {
            try {
                JSONObject jsonObject = new JSONObject(object.toString());
                JSONObject data = jsonObject.getJSONObject("data");
                JSONArray workouts = data.getJSONArray("workouts");

                allScheduleList.clear();
                for (int i = 0; i < workouts.length(); i++) {
                    Log.e("jsonSchedule", workouts.get(i).toString() + "");
                    AllScheduleData cardData = new Gson().fromJson(workouts.get(i).toString(), AllScheduleData.class);
                    allScheduleList.add(cardData);
                    if (termSchedule.equalsIgnoreCase("upcoming")) {
                        setUpcomingAdapter();
                    } else if (termSchedule.equalsIgnoreCase("inprogress")) {
                        setOnGoingAdapter();
                    } else if (termSchedule.equalsIgnoreCase("completed")) {
                        setCompleteAdapter();
                    }
                }
                if (allScheduleList.size() > 0) {
                    scheduleRV.setVisibility(View.VISIBLE);
                    noDataTV.setVisibility(View.GONE);
                } else {
                    noDataTV.setVisibility(View.VISIBLE);
                    scheduleRV.setVisibility(View.GONE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (call == cancelScheduleCall) {
            try {

                JSONObject jsonObject = new JSONObject(object.toString());


                JSONObject data = jsonObject.getJSONObject("data");
                Log.e("dataCancelation", data + "");
                fetchScheduleApi(termSchedule);
              /*  JSONArray workouts = data.getJSONArray("workouts");

                allScheduleList.clear();
                for (int i = 0; i < workouts.length(); i++) {
                    Log.e("jsonSchedule", workouts.get(i).toString() + "");
                    AllScheduleData cardData = new Gson().fromJson(workouts.get(i).toString(), AllScheduleData.class);
                    allScheduleList.add(cardData);
                    setUpcomingAdapter();

                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }


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


    private void setUpcomingAdapter() {
        upcomingScheduleAdapter = new UpcomingScheduleAdapter(baseActivity, this, allScheduleList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        scheduleRV.setLayoutManager(mLayoutManager);
        scheduleRV.setItemAnimator(new DefaultItemAnimator());
//        scheduleRV.addItemDecoration(new MyDividerItemDecoration(baseActivity, LinearLayoutManager.VERTICAL, 16));
        scheduleRV.setAdapter(upcomingScheduleAdapter);


        onClickChat();
        onClickCancel();
    }


    private void setOnGoingAdapter() {
        ongoingScheduleAdapter = new OngoingScheduleAdapter(baseActivity, this, allScheduleList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        scheduleRV.setLayoutManager(mLayoutManager);
        scheduleRV.setItemAnimator(new DefaultItemAnimator());
        scheduleRV.setAdapter(ongoingScheduleAdapter);

    }


    private void setCompleteAdapter() {
        completeScheduleAdapter = new CompleteScheduleAdapter(baseActivity, this, allScheduleList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        scheduleRV.setLayoutManager(mLayoutManager);
        scheduleRV.setItemAnimator(new DefaultItemAnimator());
        scheduleRV.setAdapter(completeScheduleAdapter);


        onClickFeedback();
    }

    private void onClickFeedback() {
        completeScheduleAdapter.setOnNewClick(new onClickAdd() {
            @Override
            public void onClick(Object obj, int pos) {

                Fragment fragmentGet = new FeedbackFragment();
                Bundle bundle = new Bundle();
                bundle.putString("fragment", "fragmentSchedule");
                bundle.putString("workoutID", allScheduleList.get(pos).id + "");
                bundle.putString("requestToId", allScheduleList.get(pos).requestTo.id + "");
                bundle.putString("profilePicPath", allScheduleList.get(pos).requestTo.profilePicPath);
                bundle.putString("firstName", allScheduleList.get(pos).requestTo.firstName);
                bundle.putString("accountId", allScheduleList.get(pos).requestTo.accountId);
                bundle.putString("avgRating", allScheduleList.get(pos).requestTo.avgRating + "");


                if (allScheduleList.get(pos).requestTo.userModalities != null) {
                    for (int i = 0; i < allScheduleList.get(pos).requestTo.userModalities.size(); i++) {
                        if (allScheduleList.get(pos).requestTo.userModalities.get(i).modality.modality.equals(allScheduleList.get(pos).modality.modality)) {
                            bundle.putString("experience", allScheduleList.get(pos).requestTo.userModalities.get(i).experience);
                            Log.e("Compare", allScheduleList.get(pos).requestTo.userModalities.get(i).modality.modality);
                        }
                    }

                }

                fragmentGet.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, fragmentGet)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void onClickChat() {
        upcomingScheduleAdapter.setOnClickChat(new onClickAdd() {
            @Override
            public void onClick(Object obj, int pos) {

                Intent chat = new Intent(baseActivity, SingleChatActivity.class);
                chat.putExtra("receiverName", allScheduleList.get(pos).requestTo.firstName);
                chat.putExtra("receiverID", allScheduleList.get(pos).requestTo.id + "");
                startActivity(chat);

              /*  getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new SingleChatFragment())
                        .addToBackStack(null)
                        .commit();*/
            }
        });
    }

    private void onClickCancel() {
        upcomingScheduleAdapter.setOnClickCancel(new onClickDelete() {
            @Override
            public void onClick(Object obj, int pos) {
                showCancelPopup(pos);

            }
        });
    }

    private void cancelApiCall(int workoutID) {
        try {
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "status=" + 3 + "&undefined=");
            cancelScheduleCall = baseActivity.apiInterface.cancelWorkout("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), workoutID + "", body);
            baseActivity.apiHitAndHandle.makeApiCall(cancelScheduleCall, this);
            baseActivity.startProgressDialog();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void showCancelPopup(final int position) {
        baseActivity.setTheme(R.style.customCheckBox);
        AlertDialog.Builder builder = new AlertDialog.Builder(baseActivity);
        builder.setMessage(getString(R.string.cancel_schedule))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        cancelApiCall(allScheduleList.get(position).id);

                    }
                }).setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}