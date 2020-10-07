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
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.adapters.NutritionistAdapter;
import com.coachmovecustomer.data.AddDietData;
import com.coachmovecustomer.data.NutritionistData;
import com.coachmovecustomer.myInterface.onClickAdd;
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

public class NutritionistFragment extends BaseFragment implements TextView.OnEditorActionListener {

    RecyclerView nutritionistRV;
    private ArrayList<NutritionistData> nutritionistDataList = new ArrayList<>();
    NutritionistAdapter nutritionistAdapter;
    String search = "";
    TextView noDataTV;
    EditText searchET;
    AddDietData addDietData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutritionist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.nutritionist), false);
        setHasOptionsMenu(false);
        initUI(view);
    }

    private void initUI(View view) {


        nutritionistRV = view.findViewById(R.id.nutritionistRV);
        searchET = view.findViewById(R.id.searchET);
        noDataTV = view.findViewById(R.id.noDataTV);
        searchET.addTextChangedListener(watcher);
        searchET.setOnEditorActionListener(this);

        searchET.setFilters(baseActivity.setFiltersET(30));

        addDietData = getArguments().getParcelable("DietData");
        if (addDietData != null) {
            String email = addDietData.email;
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        getNutritionistApi(search);
        setNearbyNutritionist();
    }

    private void getNutritionistApi(String search) {
        Call<JsonObject> searchNutritionistCall = baseActivity.apiInterface.searchNutritionist("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), search);
        baseActivity.apiHitAndHandle.makeApiCall(searchNutritionistCall, this);
        baseActivity.startProgressDialog();
    }

    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();

        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            JSONObject data = jsonObject.getJSONObject("data");
            nutritionistDataList.clear();
            JSONArray user = data.getJSONArray("coaches");
            for (int i = 0; i < user.length(); i++) {
                Log.e("jsonObject", user.get(i).toString() + "");
                NutritionistData peopleForAddData = new Gson().fromJson(user.get(i).toString(), NutritionistData.class);
                nutritionistDataList.add(peopleForAddData);
            }
            if (nutritionistDataList.size() > 0) {
                nutritionistRV.setVisibility(View.VISIBLE);
                noDataTV.setVisibility(View.GONE);
            } else {
                noDataTV.setVisibility(View.VISIBLE);
                nutritionistRV.setVisibility(View.GONE);
            }

            setNearbyNutritionist();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void setNearbyNutritionist() {
        nutritionistAdapter = new NutritionistAdapter(baseActivity, this, nutritionistDataList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        nutritionistRV.setLayoutManager(mLayoutManager);
        nutritionistRV.setItemAnimator(new DefaultItemAnimator());
//        nutritionistRV.addItemDecoration(new MyDividerItemDecoration(baseActivity, LinearLayoutManager.VERTICAL, 16));
        nutritionistRV.setAdapter(nutritionistAdapter);

        onClickRecycler();
        onClickRecyclerPayment();
    }

    private void onClickRecycler() {
        nutritionistAdapter.setOnCommentClick(new onClickAdd() {
            @Override
            public void onClick(Object obj, int pos) {
//                coachCommentsAdapter.notifyDataSetChanged();
                Fragment fragmentGet = new NutritionistCommentsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", nutritionistDataList.get(pos).id + "");
                bundle.putString("firstName", nutritionistDataList.get(pos).firstName);
                bundle.putString("accountId", nutritionistDataList.get(pos).accountId);
                bundle.putString("profilePicPath", nutritionistDataList.get(pos).profilePicPath);
                bundle.putString("avgRating", nutritionistDataList.get(pos).avgRating + "");
                bundle.putString("comments", nutritionistDataList.get(pos).comments + "");
                bundle.putString("experience", nutritionistDataList.get(pos).experience + "");
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
        nutritionistAdapter.setOnPaymentClick(new onClickAdd() {
            @Override
            public void onClick(Object obj, int pos) {
                Fragment fragmentGet = new NutritionistPaymentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("coachName", nutritionistDataList.get(pos).firstName + "");
                bundle.putParcelable("DietData", addDietData);
                bundle.putString("requestTo", nutritionistDataList.get(pos).id + "");
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
        public void onTextChanged(final CharSequence newText, int a, int b, int c) {
            // TODO Auto-generated method stub

            if (nutritionistAdapter != null)
                nutritionistAdapter.getFilter().filter(newText);

            Log.e("newText", newText + "");
            search = String.valueOf(newText);

            if (searchET.length() == 0) {
                getNutritionistApi(search);
            }

            // TODO: do what you need here (refresh list)
            // you will probably need to use runOnUiThread(Runnable action) for some specific actions

        }
    };

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {

            getNutritionistApi(search);
            // Your piece of code on keyboard search click
            return true;
        }

        return false;
    }
}
