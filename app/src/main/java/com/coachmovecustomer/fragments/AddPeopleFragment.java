package com.coachmovecustomer.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.adapters.PeopleForAddAdapter;
import com.coachmovecustomer.adapters.SelectedPeopleAdapter;
import com.coachmovecustomer.data.PeopleForAddData;
import com.coachmovecustomer.myInterface.CollageDialogCloseListener;
import com.coachmovecustomer.myInterface.onClickAdd;
import com.coachmovecustomer.myInterface.onClickDelete;
import com.coachmovecustomer.retrofitManager.ApiResponse;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.MyDividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;

public class AddPeopleFragment extends DialogFragment implements ApiResponse, View.OnClickListener {

    //    private LinearLayout search_LL;
    private Button done_btn;
    private ImageView backIV;
    private EditText searchET;
    private String search = "";
    private TextView noDataTV;
    private RecyclerView selectPeopleRV, addedPeopleRV;
    private static ArrayList<PeopleForAddData> peopleForAddList = new ArrayList<>();
    private PeopleForAddAdapter mAdapter;

    private BaseActivity baseActivity;
    private static ArrayList<PeopleForAddData> selectedPeopleDataList;
    private SelectedPeopleAdapter selectedPeopleAdapter;
    private Call<JsonObject> searchPeopleCall;
    private static CollageDialogCloseListener dialogCloseListener;


    public static AddPeopleFragment newInstance(CollageDialogCloseListener listener, ArrayList<PeopleForAddData> peopleForList, ArrayList<PeopleForAddData> selectedPeople) {
        AddPeopleFragment commentsDialogFragment = new AddPeopleFragment();
        dialogCloseListener = listener;
        Bundle args = new Bundle();
        commentsDialogFragment.setArguments(args);
        peopleForAddList = peopleForList;
        selectedPeopleDataList = selectedPeople;

        return commentsDialogFragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            //int height = ViewGroup.LayoutParams.MATCH_PARENT;
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            //int width = (int)(size.x * 0.96);
            int h = (int) (size.y * 0.96);

            dialog.getWindow().setLayout(width, h);
        }
    }

  /*  @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.setCancelable(true);
        }
    }*/


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.app.DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
        baseActivity = (BaseActivity) getActivity();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.testing, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public boolean dispatchTouchEvent(@NonNull MotionEvent motionEvent) {
                if (getCurrentFocus() != null) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                return super.dispatchTouchEvent(motionEvent);
            }
        };
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.addPeople), false);
        setHasOptionsMenu(false);
        initUI(view);
    }

    private void initUI(View view) {

//        search_LL = view.findViewById(R.id.search_LL);
        done_btn = view.findViewById(R.id.done_btn);
        searchET = view.findViewById(R.id.searchET);
//        noDataTV = view.findViewById(R.id.noDataTV);
        selectPeopleRV = view.findViewById(R.id.selectPeopleRV);
        addedPeopleRV = view.findViewById(R.id.addedPeopleRV);
        backIV = view.findViewById(R.id.backIV);


        searchET.setFilters(baseActivity.setFiltersET(30));

        backIV.setOnClickListener(this);
        done_btn.setOnClickListener(this);
        searchET.addTextChangedListener(watcher);


        mAdapter = new PeopleForAddAdapter(baseActivity, this, peopleForAddList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        selectPeopleRV.setLayoutManager(mLayoutManager);
        selectPeopleRV.setItemAnimator(new DefaultItemAnimator());
        selectPeopleRV.addItemDecoration(new MyDividerItemDecoration(baseActivity, LinearLayoutManager.VERTICAL, 16));
        selectPeopleRV.setAdapter(mAdapter);


        selectedPeopleAdapter = new SelectedPeopleAdapter(baseActivity, this, selectedPeopleDataList);
//        RecyclerView.LayoutManager mLayoutManagerAdd = new LinearLayoutManager(getActivity());
        addedPeopleRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        addedPeopleRV.setItemAnimator(new DefaultItemAnimator());
        addedPeopleRV.setAdapter(selectedPeopleAdapter);


    }


    @Override
    public void onResume() {
        super.onResume();
        getCustomerApi(search);

    }

    private void getCustomerApi(String search) {
        searchPeopleCall = baseActivity.apiInterface.
                getSearchQuery("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), search);
        baseActivity.apiHitAndHandle.makeApiCall(searchPeopleCall, this);
        baseActivity.startProgressDialog();
    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();

        if (call == searchPeopleCall) {
            try {
                JSONObject jsonObject = new JSONObject(object.toString());
                JSONObject data = jsonObject.getJSONObject("data");
                peopleForAddList.clear();
                JSONArray user = data.getJSONArray("user");
                for (int i = 0; i < user.length(); i++) {
                    Log.e("jsonObject", user.get(i).toString() + "");
                    Log.e("resp", resp + "");
                    PeopleForAddData peopleForAddData = new Gson().fromJson(user.get(i).toString(), PeopleForAddData.class);
                    peopleForAddList.add(peopleForAddData);
                }
                mAdapter.notifyDataSetChanged();
                if (peopleForAddList.size() > 0) {
                    selectPeopleRV.setVisibility(View.VISIBLE);
                    addedPeopleRV.setVisibility(View.GONE);
//                    noDataTV.setVisibility(View.GONE);
                } else {
//                    noDataTV.setVisibility(View.VISIBLE);
                    selectPeopleRV.setVisibility(View.GONE);
                    addedPeopleRV.setVisibility(View.GONE);
                }

                onClickRecycler();
                onDeleteRecycler();
            } catch (Exception e) {

            }
        }

    }

    private void addLoginUser(PeopleForAddData myUserData) {
        selectedPeopleDataList.add(myUserData);
        addedPeopleRV.setVisibility(View.VISIBLE);
        selectedPeopleAdapter.notifyDataSetChanged();

    }

    @Override
    public void onError(Call call, String errorMessage, ApiResponse apiResponse, int responceCode) {

        baseActivity.showToast(errorMessage, false);

    }

    @Override
    public void onError(Call call, String errorMessage, ApiResponse apiResponse, int responceCode, JSONObject jsonObject) {

        baseActivity.showToast(errorMessage, false);
    }


    private void onClickRecycler() {
        mAdapter.setOnNewClick(new onClickAdd() {
            @Override
            public void onClick(Object obj, int pos) {
                addedPeopleRV.setVisibility(View.VISIBLE);
                boolean isAvailable = false;
                for (int i = 0; i < selectedPeopleDataList.size(); i++) {
                    if (peopleForAddList.get(pos).id == selectedPeopleDataList.get(i).id) {
                        isAvailable = true;
                        break;
                    }
                }
                if (!isAvailable) {
                    if (selectedPeopleDataList.size() > 4) {
                        baseActivity.showToast(getResources().getString(R.string.maxPeople), false);
                    } else {
                        selectedPeopleDataList.add(peopleForAddList.get(pos));
                    }
                }
                selectedPeopleAdapter.notifyDataSetChanged();
                Log.e("position", peopleForAddList.get(pos).id + "");
                Log.e("position", peopleForAddList.get(pos).firstName + "");

            }
        });
    }

    private void onDeleteRecycler() {
        selectedPeopleAdapter.setOnClickDelete(new onClickDelete() {
            @Override
            public void onClick(Object obj, int pos) {
                selectedPeopleDataList.remove(pos);
                if (selectedPeopleDataList.isEmpty()) {
                    addedPeopleRV.setVisibility(View.GONE);
                } else {
                    addedPeopleRV.setVisibility(View.VISIBLE);
                }
                selectedPeopleAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backIV:
                getDialog().dismiss();
                ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.workout), false);
                break;
            case R.id.done_btn:
                Fragment fragment = baseActivity.getSupportFragmentManager().findFragmentById(R.id.frameLayoutMain);
                if (fragment instanceof WorkoutFragment) {
                    ((WorkoutFragment) fragment).setAddPeopleAdapter(selectedPeopleDataList);
                }
                getDialog().dismiss();
                break;

        }
    }


    TextWatcher watcher = new TextWatcher() {
        private Timer timer = new Timer();
        private final long DELAY = 1000; // milliseconds

        @Override
        public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(final CharSequence newText, int a, int b, int c) {
            // TODO Auto-generated method stub
            timer.cancel();
            timer = new Timer();
            timer.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            getCustomerApi(newText.toString());
                            // TODO: do what you need here (refresh list)
                            // you will probably need to use runOnUiThread(Runnable action) for some specific actions
                        }
                    },
                    DELAY
            );

        }
    };

}


