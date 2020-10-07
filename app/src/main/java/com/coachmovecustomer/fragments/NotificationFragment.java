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
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.activity.SingleChatActivity;
import com.coachmovecustomer.adapters.NotificationAdapter;
import com.coachmovecustomer.data.NotificationData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.MyDividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class NotificationFragment extends BaseFragment {

    private TextView noDataTV;
    NotificationAdapter notificationAdapter;
    RecyclerView notificationRV;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<NotificationData.Notification> notificationDataList = new ArrayList<>();
    ProfileData profileData = new ProfileData();
    private NotificationData notificationData;
    int pageCount = 0;
    private int previousSize;
    private Call<JsonObject> notificationCall, cancellationCall;
    int workoutID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(false);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.notifications), false);
        profileData = baseActivity.store.getProfileData();
        initUI(view);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void initUI(View view) {
        notificationRV = view.findViewById(R.id.notificationRV);
        noDataTV = view.findViewById(R.id.noDataTV);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Log.e("cancelWorkout", bundle.getString("tag"));
            Log.e("cancelWorkout", bundle.getString("body"));
            Log.e("cancelWorkout", bundle.getString("badge"));
            Log.e("cancelWorkout", bundle.getString("title"));
            Log.e("cancelWorkout", bundle.getString("workoutId"));

            if (bundle.getString("workoutId") != null) {
                workoutID = Integer.parseInt(bundle.getString("workoutId"));
                showCancelPopup(workoutID, -1);
            } else {
                showToast("Something went wrong!");
                showToast(getResources().getString(R.string.somethingWrong));
            }
        }

        setNotificationApi(pageCount);
        setNotificationAdapter();

    }


    private void setNotificationApi(int pageNo) {
        notificationCall = baseActivity.apiInterface.getAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.url + "/users/" + profileData.id + "/notification/page/" + pageNo);
        baseActivity.apiHitAndHandle.makeApiCall(notificationCall, this);
        baseActivity.startProgressDialog();
    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();

        if (call == notificationCall) {
            try {
//            notificationDataList.clear();
                notificationData = new Gson().fromJson(resp, NotificationData.class);
                Log.e("resp", resp + "");
                if (notificationData != null && notificationData.data.notifications.size() > 0) {
                    loading = true;
                    noDataTV.setVisibility(View.GONE);
                    notificationRV.setVisibility(View.VISIBLE);
                    ArrayList<NotificationData.Notification> tempList = new ArrayList<>();
                    tempList.addAll(notificationData.data.notifications);
                    notificationDataList.addAll(tempList);
                    if (count == 0) {
                        notificationRV.scrollToPosition(0);
                    } else {
                        notificationRV.scrollToPosition(notificationDataList.size() + 1);
                    }
//                notificationDataList.addAll(notificationData.data.notifications);
                    notificationAdapter.notifyAdapter(notificationDataList);

                } else {
                    loading = false;
                    if (notificationDataList.size() == 0) {
                        noDataTV.setVisibility(View.VISIBLE);
                        notificationRV.setVisibility(View.GONE);
                    } else {
                        notificationAdapter.notifyAdapter(notificationDataList);
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (call == cancellationCall) {
            try {
                JSONObject jsonObject = new JSONObject(object.toString());
                showToast(jsonObject.optString("message"));

                notificationAdapter.notifyAdapter(notificationDataList);
                // setNotificationApi(count);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private void setNotificationAdapter() {
        notificationAdapter = new NotificationAdapter(baseActivity, this, notificationDataList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        notificationRV.setLayoutManager(mLayoutManager);
        notificationRV.setItemAnimator(new DefaultItemAnimator());
        notificationRV.addItemDecoration(new MyDividerItemDecoration(baseActivity, LinearLayoutManager.VERTICAL, 16));
        notificationRV.setAdapter(notificationAdapter);
        notificationRV.addOnScrollListener(recyclerViewOnScrollListener);

        onClickRecycler();

    }

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int count = 0;
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    Log.e("totalItemCount", totalItemCount + "");
//                    Log.e("totalItemCount", pastVisiblesItems + "");
//
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) == totalItemCount) {
                            loading = false;
                            count++;
                            Log.v("pageItem", "Last Item Wow !" + count + "\n" + "\n" + loading);

                            setNotificationApi(count);

                            //Do pagination.. i.e. fetch new data

                        }


                    }


                }
            }
//            showToast(count+"");

        }
    };


    private void showCancelPopup(final int workoutID, final int pos) {
        baseActivity.setTheme(R.style.customCheckBox);
        AlertDialog.Builder builder = new AlertDialog.Builder(baseActivity);
        builder.setMessage(getString(R.string.cancelDueToWeather))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.itsOk), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        cancellationMethod(workoutID, true);
                        if (pos > -1) {
                            notificationDataList.get(pos).hasSeen = true;
                        } else {
                            notificationDataList.clear();
                            setNotificationApi(0);
                        }

                        dialog.cancel();
                    }
                }).setNegativeButton(getString(R.string.notOk), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                cancellationMethod(workoutID, false);
                if (pos > -1) {
                    notificationDataList.get(pos).hasSeen = true;
                } else {
                    notificationDataList.clear();
                    setNotificationApi(0);
                }

                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void cancellationMethod(int workoutID, boolean isVerified) {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "isVerified=" + isVerified + "&undefined=");
        cancellationCall = baseActivity.apiInterface.cancellationAPI("Bearer " +
                baseActivity.store.getString(Const.ACCESS_TOKEN), profileData.id + "", workoutID + "", body);
        baseActivity.apiHitAndHandle.makeApiCall(cancellationCall, this);
        baseActivity.startProgressDialog();
    }


    private void onClickRecycler() {
        notificationAdapter.setOnNotificationClick(new onClickAdd() {
            @Override
            public void onClick(Object obj, int pos) {


                if (notificationDataList.get(pos).tag.equalsIgnoreCase(Const.CHAT_NOTIFICATION)) {
                    Log.e("notificationTag", Const.CHAT_NOTIFICATION);

                    Intent chat = new Intent(baseActivity, SingleChatActivity.class);
                    chat.putExtra("receiverName", notificationDataList.get(pos).senderName);
                    chat.putExtra("receiverID", notificationDataList.get(pos).senderId + "");
                    Log.e("message====>>>>", notificationDataList.get(pos).senderName + "\n" + notificationDataList.get(pos).senderId + "");
                    startActivity(chat);


                } else if (notificationDataList.get(pos).tag.equalsIgnoreCase(Const.WORKOUT_NOTIFICATION)) {
                    Log.e("notificationTag", Const.WORKOUT_NOTIFICATION);


                } else if (notificationDataList.get(pos).tag.equalsIgnoreCase(Const.DIET_NOTIFICATION)) {
                    Log.e("notificationTag", Const.DIET_NOTIFICATION);

                    Fragment fragmentGet = new DietDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("dietID", notificationDataList.get(pos).targetId + "");
                    fragmentGet.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frameLayoutMain, fragmentGet)
                            .addToBackStack(null)
                            .commit();


                } else if (notificationDataList.get(pos).tag.equalsIgnoreCase(Const.WORKOUT_CANCELED)) {
                    Log.e("notificationTag", Const.WORKOUT_CANCELED);
                    Log.e("boolenNotification", notificationDataList.get(pos).hasSeen + "");
                    if (notificationDataList.get(pos).hasSeen == false) {
                        showCancelPopup(notificationDataList.get(pos).targetId, pos);
                    }
//                    gotoChatFragment();
                }


            }
        });
    }


    public void gotoChatFragment() {

     /*   Intent chat = new Intent(baseActivity, SingleChatActivity.class);
        chat.putExtra("receiverName", messageDataList.get(pos).receiver.firstName);
        chat.putExtra("receiverID", messageDataList.get(pos).receiver.id + "");
        Log.e("message====>>>>", messageDataList.get(pos).receiver.firstName + "\n" + messageDataList.get(pos).receiver.id + "");
        startActivity(chat);*/
      /*  Fragment fragment = new SingleChatFragment();
        Bundle bundle = new Bundle();
        bundle.putString("receiverName", messageDataList.get(pos).receiver.firstName);
        bundle.putString("receiverID", messageDataList.get(pos).receiver.id + "");
        fragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayoutMain, fragment)
                .addToBackStack(null)
                .commit();*/

    }


}
