package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.adapters.ChatAdapter;
import com.coachmovecustomer.data.ChatData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import retrofit2.Call;

public class SingleChatFragment extends BaseFragment {

    private TextView noDataTV , titleTBTV ;
    private EditText messageEt;
    private ImageView sendIV , title_TBIMG ;
    private RecyclerView chatRV;
    private ChatAdapter chatAdapter;
    private Timer timer;
    private int previousSize;
    ProfileData profileData = new ProfileData();

    ArrayList<ChatData.ChatMessage> chatDataList = new ArrayList<>();
    private ChatData chatData;
    private Call<JsonObject> sendMessageCall, getMessageCall;
    private String receiverID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileData = baseActivity.store.getProfileData();
        setHasOptionsMenu(false);
        initUI(view);

    }

    @Override
    public void onResume() {
        super.onResume();
//        getMessages();
    /*    timer = new Timer();
        baseActivity.store.setBoolean(Const.FORGROUND, true);
        timer.scheduleAtFixedRate(new TimerTask() {

            synchronized public void run() {

                getMessages();
            }

        }, 4000, 4000);
*/
    }


    @Override
    public void onPause() {
        super.onPause();
     /*   baseActivity.store.setBoolean(Const.FORGROUND, false);
        if (timer != null) {
            timer.cancel();
            //cancel timer task and assign null
        }*/
    }

    private void initUI(View view) {

        noDataTV = view.findViewById(R.id.noDataTV);
        chatRV = view.findViewById(R.id.chatRV);
        sendIV = view.findViewById(R.id.sendIV);
        messageEt = view.findViewById(R.id.messageEt);
        titleTBTV = view.findViewById(R.id.titleTBTV);
        title_TBIMG = view.findViewById(R.id.title_TBIMG);


        sendIV.setOnClickListener(this);



        Bundle bundle = getArguments();
        if (bundle != null) {
            ((MainActivity) getActivity()).setToolbarTitle(bundle.getString("receiverName"), false);
            receiverID = bundle.getString("receiverID");
        }


        chatAdapter = new ChatAdapter(baseActivity, chatDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(baseActivity);
        chatRV.setLayoutManager(mLayoutManager);
        chatRV.setItemAnimator(new DefaultItemAnimator());
        chatRV.setAdapter(chatAdapter);

    }



    private void getMessages() {
        getMessageCall = baseActivity.apiInterface.getAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.MESSAGE_USER + profileData.id + "/messages?receiverId=" + receiverID + "&page=0");
        baseActivity.apiHitAndHandle.makeApiCall(getMessageCall, this);
        baseActivity.startProgressDialog();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.sendIV:
                if (messageEt.getText().toString().length() == 0) {
//                    showToast("Message box empty");
                } else {
//                    sendMessageApi();
                }
                break;
        }
    }

    private void sendMessageApi() {

        HashMap<String, Object> jsonObject = new HashMap<>();
        HashMap<String, Object> sender = new HashMap<>();
        sender.put("id", profileData.id);
        HashMap<String, Object> receiver = new HashMap<>();
        receiver.put("id", receiverID);
        jsonObject.put("sender", sender);
        jsonObject.put("receiver", receiver);
        jsonObject.put("message", messageEt.getText().toString());

        Log.e("jsonObject=====>>>.", jsonObject + " ");

        sendMessageCall = baseActivity.apiInterface.postApiObject("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.SEND_MESSAGE_API, jsonObject);
        baseActivity.apiHitAndHandle.makeApiCall(sendMessageCall, this);
        baseActivity.startProgressDialog();


    }

/*

    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        if (call == getMessageCall) {
            try {
                chatData = new Gson().fromJson(resp, ChatData.class);
                if (chatData != null && chatData.data.messagesList.size() > 0) {
                    chatDataList.addAll(chatData.data.messagesList);
                    chatAdapter.notifyAdapter(chatDataList);
                    chatRV.smoothScrollToPosition(chatDataList.size());
                    chatRV.setVisibility(View.VISIBLE);
                    noDataTV.setVisibility(View.GONE);
                } else {
                    if (chatDataList.size() == 0) {
                        noDataTV.setVisibility(View.VISIBLE);
                        chatRV.setVisibility(View.GONE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (call == sendMessageCall) {
            messageEt.setText("");
//            getMessages();
        }

    }
*/


}
