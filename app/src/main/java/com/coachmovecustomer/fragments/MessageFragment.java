package com.coachmovecustomer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.activity.SingleChatActivity;
import com.coachmovecustomer.adapters.MessageAdapter;
import com.coachmovecustomer.data.MessageData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.myInterface.OnClickListener;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.MyDividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;

import retrofit2.Call;

public class MessageFragment extends BaseFragment {


    RecyclerView messageRV;
    MessageAdapter msgsAdapter;
    FloatingActionButton mFloatingButton;
    TextView noDataTV;
    ProfileData profileData = new ProfileData();
    Handler handler = new Handler();
    Runnable timedTask =
            new Runnable() {

                @Override
                public void run() {
                    fetchMessageApi(false);
                    msgsAdapter.notifyDataSetChanged();
                }
            };
    private ArrayList<MessageData> messageDataList = new ArrayList<>();
    private Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_message, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileData = baseActivity.store.getProfileData();
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.messages), false);
        setHasOptionsMenu(true);
        initUI(view);

    }

    private void initUI(View view) {

        messageRV = view.findViewById(R.id.messageRV);
        noDataTV = view.findViewById(R.id.noDataTV);
        mFloatingButton = view.findViewById(R.id.floatingSearch);

        prepareMessageData();
        onClickRecycler();

        //click on view
        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainFragment(new SearchChatUserFragment());
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        fetchMessageApi(true);
        Log.e("response", "onResume");
    }

    private void fetchMessageApi(boolean isLoader) {

        Call<JsonObject> fetchMessageCall = baseActivity.apiInterface.getAPI("Bearer " +
                baseActivity.store.getString(Const.ACCESS_TOKEN), Const.MESSAGE_USER + profileData.id + Const.MESSAGE_LIST_API);
//        baseActivity.apiHitAndHandle.makeApiCall(fetchMessageCall, this);
        baseActivity.apiHitAndHandle.makeApiCall(fetchMessageCall, isLoader, this);
//        baseActivity.startProgressDialog();
    }

    @Override
    public void onSuccess(Call call, Object object, String resp) {
//        baseActivity.stopProgressDialog();


        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            if (jsonObject.getString("message").equalsIgnoreCase("user is blocked") ||
                    jsonObject.getString("message").equalsIgnoreCase("user is unblocked")) {
                gotoMainFragment(new MessageFragment());
            } else {
                JSONObject data = jsonObject.getJSONObject("data");
                messageDataList.clear();
                JSONArray cards = data.getJSONArray("messages");
                for (int i = 0; i < cards.length(); i++) {
                    Log.e("jsonMessages", cards.get(i).toString() + "");
                    MessageData messageData = new Gson().fromJson(cards.get(i).toString(), MessageData.class);
                    messageDataList.add(messageData);
                }
                /*     msgsAdapter.notifyDataSetChanged();*/
                if (messageDataList.size() > 0) {
                    messageRV.setVisibility(View.VISIBLE);
                    noDataTV.setVisibility(View.GONE);
                } else {
                    noDataTV.setVisibility(View.VISIBLE);
                    messageRV.setVisibility(View.GONE);
                }
            }
            handler.postDelayed(timedTask, 10000);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void gotoMainFragment(Fragment targetFragment) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayoutMain, targetFragment)
                .commit();
    }

    private void prepareMessageData() {
        msgsAdapter = new MessageAdapter(baseActivity, this, messageDataList, new OnClickListener() {
            @Override
            public void onClick(int pos) {
                if (messageDataList.get(pos).mBlock)
                    unBlockUserApiCall(pos);
                else
                    blockUserApiCall(pos);
            }
        }
        );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        messageRV.setLayoutManager(mLayoutManager);
        messageRV.setItemAnimator(new DefaultItemAnimator());
        messageRV.addItemDecoration(new MyDividerItemDecoration(baseActivity, LinearLayoutManager.VERTICAL, 16));
        messageRV.setAdapter(msgsAdapter);

    }

    //TODO new API call
    private void blockUserApiCall(int pos) {
        Call<JsonObject> fetchMessageCall = baseActivity.apiInterface.blockUser("Bearer " +
                        baseActivity.store.getString(Const.ACCESS_TOKEN),
                "CoachMove/api/user/blockUser?senderId=" +
                        messageDataList.get(pos).message.receiver.id
                        + "&" + "receiverId=" + messageDataList.get(pos).message.sender.id);
//        baseActivity.apiHitAndHandle.makeApiCall(fetchMessageCall, this);
        baseActivity.apiHitAndHandle.makeApiCall(fetchMessageCall, true, this);
    }

    //TODO new API call
    private void unBlockUserApiCall(int pos) {
        Call<JsonObject> fetchMessageCall = baseActivity.apiInterface.blockUser("Bearer " +
                        baseActivity.store.getString(Const.ACCESS_TOKEN),
                "CoachMove/api/user/unblockUser?senderId=" +
                        messageDataList.get(pos).message.receiver.id
                        + "&" + "receiverId=" + messageDataList.get(pos).message.sender.id);
//        baseActivity.apiHitAndHandle.makeApiCall(fetchMessageCall, this);
        baseActivity.apiHitAndHandle.makeApiCall(fetchMessageCall, true, this);
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

    private void onClickRecycler() {
        msgsAdapter.setOnChatClick(new onClickAdd() {
            @Override
            public void onClick(Object obj, int pos) {
                gotoChatFragment(pos);
            }
        });
    }

    public void gotoChatFragment(int pos) {

        Intent chat = new Intent(baseActivity, SingleChatActivity.class);
        chat.putExtra("receiverName", messageDataList.get(pos).receiver.firstName);
        chat.putExtra("receiverID", messageDataList.get(pos).receiver.id + "");
        Log.e("message====>>>>", messageDataList.get(pos).receiver.firstName + "\n" + messageDataList.get(pos).receiver.id + "");
        startActivity(chat);
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

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(timedTask);

    }


}
