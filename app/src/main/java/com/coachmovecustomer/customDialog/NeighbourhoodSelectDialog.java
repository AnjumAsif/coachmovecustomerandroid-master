package com.coachmovecustomer.customDialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.adapters.NeighbourhoodSelectAdapter;
import com.coachmovecustomer.data.NeighbourhoodData;
import com.coachmovecustomer.myInterface.CollageDialogCloseListener;
import com.coachmovecustomer.retrofitManager.ApiResponse;
import com.coachmovecustomer.utils.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;

public class NeighbourhoodSelectDialog extends DialogFragment implements View.OnClickListener, ApiResponse {

    private ArrayList<NeighbourhoodData> neighbourhoodDataList = new ArrayList<>();
    private BaseActivity baseActivity;
    private static CollageDialogCloseListener dialogCloseListener;
    private RecyclerView neighbourhoodRCV;
    private EditText searchET;
    ImageView backIV;
    private NeighbourhoodSelectAdapter neighbourhoodListAdapter;
    private static String selectedID = "";
    private static String neighbourhoodID = "";
    private TextView noDataTV;
    private LinearLayout searchLL;


    public NeighbourhoodSelectDialog() {

    }

    public static NeighbourhoodSelectDialog newInstance(CollageDialogCloseListener listener, ArrayList<NeighbourhoodData> neighbourhoodDataList /*List<String> schoolLists*//*, String selectedSchool*/) {
        NeighbourhoodSelectDialog commentsDialogFragment = new NeighbourhoodSelectDialog();
        dialogCloseListener = listener;
        Bundle args = new Bundle();
        commentsDialogFragment.setArguments(args);
        neighbourhoodDataList = neighbourhoodDataList;

        return commentsDialogFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
        baseActivity = (BaseActivity) getActivity();


    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.setCancelable(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_neighbourhood_list, container);
        return view;
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
        initUI(view);
    }

    private void initUI(View view) {

        searchLL = view.findViewById(R.id.searchLL);
        neighbourhoodRCV = view.findViewById(R.id.neighbourhoodRCV);
        searchET = view.findViewById(R.id.searchET);
        noDataTV = view.findViewById(R.id.noDataTV);
        backIV = view.findViewById(R.id.backIV);
        searchET.addTextChangedListener(watcher);
        backIV.setOnClickListener(this);
        setData();

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
            if (neighbourhoodListAdapter != null)
                neighbourhoodListAdapter.getFilter().filter(newText);
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        getNeighbourhoodApi();
    }

    private void getNeighbourhoodApi() {
        Call<JsonObject> nearbyCoach = baseActivity.apiInterface.getAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.ADD_GET_CARD + "neighbourhoods");
        baseActivity.apiHitAndHandle.makeApiCall(nearbyCoach, this);
        baseActivity.startProgressDialog();
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (dialogCloseListener != null) {
            dialogCloseListener.handelDialogClose(dialog, selectedID, neighbourhoodID);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backIV:
                getDialog().dismiss();

                break;

        }
    }

    public void selctedschool(String list, String sNeighbourhoodID) {
        selectedID = list;
        neighbourhoodID = sNeighbourhoodID;
        getDialog().dismiss();
    }

    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        try {
            JSONObject jsonObject = new JSONObject(resp);
            JSONObject data = jsonObject.getJSONObject("data");
            neighbourhoodDataList.clear();
            JSONArray neighbourhoods = data.getJSONArray("neighbourhoods");
            for (int i = 0; i < neighbourhoods.length(); i++) {
                NeighbourhoodData neighbourhoodData = new Gson().fromJson(neighbourhoods.get(i).toString(), NeighbourhoodData.class);
                neighbourhoodDataList.add(neighbourhoodData);
            }

            neighbourhoodListAdapter.notifyDataSetChanged();
            if (neighbourhoodDataList.size() > 0) {
                neighbourhoodRCV.setVisibility(View.VISIBLE);
                noDataTV.setVisibility(View.GONE);
            } else {
                noDataTV.setVisibility(View.VISIBLE);
                neighbourhoodRCV.setVisibility(View.GONE);
            }

            setData();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public void onError(Call call, String errorMessage, ApiResponse apiResponse, int responceCode) {

        baseActivity.showToast(errorMessage, false);


    }

    @Override
    public void onError(Call call, String errorMessage, ApiResponse apiResponse, int responceCode, JSONObject jsonObject) {

        baseActivity.showToast(errorMessage, false);

    }


    private void setData() {

        neighbourhoodListAdapter = new NeighbourhoodSelectAdapter(baseActivity, this, neighbourhoodDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(baseActivity);
        neighbourhoodRCV.setLayoutManager(mLayoutManager);
        neighbourhoodRCV.setItemAnimator(new DefaultItemAnimator());
        neighbourhoodRCV.setAdapter(neighbourhoodListAdapter);


    }
}