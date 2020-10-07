package com.coachmovecustomer.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.adapters.ChatAdapter;
import com.coachmovecustomer.data.ChatData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;

public class SingleChatActivity extends BaseActivity {

    private TextView noDataTV;
    private EditText messageEt;
    private ImageView sendIV;
    private RecyclerView chatRV;
    private ChatAdapter chatAdapter;
    private Timer timer;
    private int previousSize;
    ProfileData profileData = new ProfileData();

    private ArrayList<ChatData.ChatMessage> chatDataList = new ArrayList<>();
    private ChatData chatData;
    private Call<JsonObject> sendMessageCall, getMessageCall, latestMessageCall;
    private String receiverID;
    private TextView titleTV;
    private ImageView backIV;
    private String headerTitle;
    private LinearLayoutManager mLayoutManager;

    private int count = 0;
    private String countString = "0";

    private boolean isTrue = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_single_chat);
        profileData = store.getProfileData();
        initUI();


        getMessagesApi(true, count + "");


    }


    private void initUI() {


        noDataTV = findViewById(R.id.noDataTV);
        chatRV = findViewById(R.id.chatRV);
        sendIV = findViewById(R.id.sendIV);
        messageEt = findViewById(R.id.messageEt);
        titleTV = findViewById(R.id.titleTV);
        backIV = findViewById(R.id.backIV);


        sendIV.setOnClickListener(this);
        backIV.setOnClickListener(this);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            titleTV.setText(bundle.getString("receiverName"));
            receiverID = bundle.getString("receiverID");
        }




      /*  chatAdapter = new ChatAdapter(this, chatDataList);
        mLayoutManager = new LinearLayoutManager(this);
        chatRV.setLayoutManager(mLayoutManager);
        chatRV.setItemAnimator(new DefaultItemAnimator());
        chatRV.setAdapter(chatAdapter);*/
//        chatRV.addOnScrollListener(recyclerViewOnScrollListener);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.sendIV:
                if (messageEt.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.messageBox), false);
                } else {
                    sendMessageApi();
                    messageEt.setText("");
                }
                break;
            case R.id.backIV:
                onBackPressed();
                break;
        }
    }

    private void getMessagesApi(boolean isLoader, String pageNO) {
        getMessageCall = apiInterface.getAPI("Bearer " + store.getString(Const.ACCESS_TOKEN), Const.MESSAGE_USER + profileData.id + "/messages?receiverId=" + receiverID + "&page=" + pageNO);
//        apiHitAndHandle.makeApiCall(getMessageCall, this);
        apiHitAndHandle.makeApiCall(getMessageCall, isLoader, this);
    }

    private void sendMessageApi() {


        String toServerUnicodeEncoded = StringEscapeUtils.escapeJava(messageEt.getText().toString());
        Log.e("toServer", toServerUnicodeEncoded + "");


        HashMap<String, Object> jsonObject = new HashMap<>();
        HashMap<String, Object> sender = new HashMap<>();
        sender.put("id", profileData.id);
        HashMap<String, Object> receiver = new HashMap<>();
        receiver.put("id", receiverID);
        jsonObject.put("sender", sender);
        jsonObject.put("receiver", receiver);
//        jsonObject.put("message", messageEt.getText().toString());
        jsonObject.put("message", toServerUnicodeEncoded);

        Log.e("jsonObject=====>>>.", jsonObject + " ");

        sendMessageCall = apiInterface.postApiObject("Bearer " + store.getString(Const.ACCESS_TOKEN), Const.SEND_MESSAGE_API, jsonObject);
        apiHitAndHandle.makeApiCall(sendMessageCall, this);
        startProgressDialog();


    }

    @Override
    public void onSuccess(Call call, Object object, String resp) {
        stopProgressDialog();


        if (call == getMessageCall) {
            try {
                chatData = new Gson().fromJson(resp, ChatData.class);
                if (chatData != null && chatData.data.messagesList.size() > 0) {

                    noDataTV.setVisibility(View.GONE);
                    chatRV.setVisibility(View.VISIBLE);
//                    setChatAdapter(chatData.data.messagesList);

                    if (previousSize == chatData.data.messagesList.size()) {

                    } else {
                        previousSize = chatData.data.messagesList.size();
                        setChatAdapter(chatData.data.messagesList);
                    }

                } else {

                    if (chatDataList.size() == 0) {
                        noDataTV.setVisibility(View.VISIBLE);
                        chatRV.setVisibility(View.GONE);
                    }
                }
                handler.postDelayed(timedTask, 3000);
            } catch (Exception e) {
                e.printStackTrace();
            }



    /*    if (call == getMessageCall) {
            try {
                chatData = new Gson().fromJson(resp, ChatData.class);
                if (chatData != null && chatData.data.messagesList.size() > 0) {





                    count++;
                    loading = false;
                    noDataTV.setVisibility(View.GONE);
                    chatRV.setVisibility(View.VISIBLE);
                    ArrayList<ChatData.ChatMessage> tempList = new ArrayList<>();
                    tempList.addAll(chatData.data.messagesList);
                    previousSize = chatDataList.size();
                    Log.e("previousSize", chatDataList.size() + "\n" + previousSize);
                    tempList.addAll(chatDataList);
                    chatDataList.clear();
                    chatDataList.addAll(chatData.data.messagesList);
                    chatAdapter.notifyAdapter(chatDataList);
                    if (count == 1) {
                        chatRV.scrollToPosition(chatDataList.size() - 1);
                    } else {
                        chatRV.scrollToPosition(chatDataList.size() - previousSize - 1);
                    }
                } else {
                    loading = true;
                    if (chatDataList.size() == 0) {
                        noDataTV.setVisibility(View.VISIBLE);
                        chatRV.setVisibility(View.GONE);
                    }
                }

                handler.postDelayed(timedTask, 10000);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        } else if (call == sendMessageCall) {
//            chatDataList.clear();
            chatData.data.messagesList.clear();
            isTrue = true;
//            messageEt.setText("");
            getMessagesApi(false, 0 + "");

//
           /* try {
                JSONObject jsonObject = new JSONObject(object.toString());
                JSONObject data = jsonObject.optJSONObject("data");
                Log.e("dataMEssage", data + "");

            } catch (Exception e) {
                e.printStackTrace();
            }*/

        } else if (call == latestMessageCall) {

        }

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    Handler handler = new Handler();
    Runnable timedTask = new Runnable() {
        @Override
        public void run() {

            isTrue = true;
            getMessagesApi(false, 0 + "");
//            getLatestMessageApi(false, 0);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(timedTask);
    }


    private void setChatAdapter(List<ChatData.ChatMessage> chatDataList) {
        chatAdapter = new ChatAdapter(this, chatDataList);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setStackFromEnd(isTrue);
        chatRV.setLayoutManager(mLayoutManager);
        chatRV.setItemAnimator(new DefaultItemAnimator());
        chatRV.setAdapter(chatAdapter);
// chat_RV.addOnScrollListener(recyclerViewOnScrollListener);

    }


    private boolean loading = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    private int positionIndex;
    private int topView;
    private boolean isScroolloading = true;
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


            if (dy > 0) {
                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();


                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                    isScroolloading = true;
                } else {
                    isScroolloading = false;
                }
            }
        }

/*
            if (dy < 0) {
                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                Log.v("visibleItemCount", "" + visibleItemCount);
                Log.v("totalItemCount", totalItemCount + "");
                Log.v("pastVisiblesItems", pastVisiblesItems + "");
                Log.v("pastItems+visibleItem", pastVisiblesItems + visibleItemCount + "");
                if (pastVisiblesItems == 0) {
                    if (!loading) {
                        loading = true;
                        getMessagesApi(true, count + "");
                    }
                }

            }*/

    };


    private void getLatestMessageApi(boolean isLoader, int pageCount) {
        latestMessageCall = apiInterface.getAPI("Bearer " + store.getString(Const.ACCESS_TOKEN), Const.MESSAGE_USER + profileData.id + "/messages?receiverId=" + receiverID + "&page=" + pageCount);
        apiHitAndHandle.makeApiCall(latestMessageCall, isLoader, this);
    }


}
