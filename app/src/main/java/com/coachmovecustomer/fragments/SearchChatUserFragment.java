package com.coachmovecustomer.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.coachmovecustomer.activity.SingleChatActivity;
import com.coachmovecustomer.adapters.MessageAdapter;
import com.coachmovecustomer.adapters.NearbyCoachesAdapter;
import com.coachmovecustomer.adapters.SearchChatMessageAdapter;
import com.coachmovecustomer.data.MessageData;
import com.coachmovecustomer.data.NearbyCoachesData;
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
import java.util.Objects;

import retrofit2.Call;

public class SearchChatUserFragment extends BaseFragment implements TextView.OnEditorActionListener {

    EditText searchET;
    RecyclerView nearbyCoachRV;
    private ArrayList<MessageData> messageDataList = new ArrayList<>();
    SearchChatMessageAdapter msgsAdapter;
    TextView noDataTV;
    //    RelativeLayout search_RL;
    LinearLayout search_RL;
    private String search="";


    ProfileData profileData = new ProfileData();

    public SearchChatUserFragment() {
        // Required empty public constructor
    }

    public static SearchChatUserFragment newInstance(String param1, String param2) {
        SearchChatUserFragment fragment = new SearchChatUserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_chat_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileData = ((MainActivity) Objects.requireNonNull(getActivity())).store.getProfileData();
        initUI(view);
    }

    private void initUI(View view) {
        search_RL = view.findViewById(R.id.search_RL);
        nearbyCoachRV = view.findViewById(R.id.nearbyCoachRV);
        searchET = view.findViewById(R.id.searchET);
        noDataTV = view.findViewById(R.id.noDataTV);
        searchET.addTextChangedListener(watcher);
        searchET.setOnEditorActionListener(this);

        prepareMessageData();
//        onClickRecycler();

    }

    private void prepareMessageData() {
        msgsAdapter = new SearchChatMessageAdapter(baseActivity, this, messageDataList,new OnClickListener(){

            @Override
            public void onClick(int pos) {
                gotoChatFragment(pos);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        nearbyCoachRV.setLayoutManager(mLayoutManager);
        nearbyCoachRV.setItemAnimator(new DefaultItemAnimator());
        nearbyCoachRV.addItemDecoration(new MyDividerItemDecoration(baseActivity, LinearLayoutManager.VERTICAL, 16));
        nearbyCoachRV.setAdapter(msgsAdapter);

    }

    /*private void onClickRecycler() {
        msgsAdapter.setOnChatClick(new onClickAdd() {
            @Override
            public void onClick(Object obj, int pos) {
                gotoChatFragment(pos);
            }
        });
    }*/

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
            /*if (msgsAdapter != null)
                msgsAdapter.getFilter().filter(newText);

            Log.e("newText", newText + "");
*/
            search = String.valueOf(newText);

            if (searchET.length() != 0) {
                filter(search);
            }
            else {
                prepareMessageData();
            }
        }
    };

    private void filter(String search) {
        //new array list that will hold the filtered data
        ArrayList<MessageData> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (MessageData s : messageDataList) {
            //if the existing elements contains the search input
            if (s.receiver.firstName.toLowerCase().contains(search.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        msgsAdapter.filterList(filterdNames);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            fetchMessageData(true);
            // Your piece of code on keyboard search click
            return true;
        }

        return false;
    }

    private void fetchMessageData(boolean isLoader) {

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
                    nearbyCoachRV.setVisibility(View.VISIBLE);
                    noDataTV.setVisibility(View.GONE);
                } else {
                    noDataTV.setVisibility(View.VISIBLE);
                    nearbyCoachRV.setVisibility(View.GONE);
                }

//            handler.postDelayed(timedTask, 10000);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        fetchMessageData(true);
    }
}
