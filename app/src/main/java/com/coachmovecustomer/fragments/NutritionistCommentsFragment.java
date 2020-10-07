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
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.adapters.NutritionistCommentsAdapter;
import com.coachmovecustomer.data.NutritionistCommentsData;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.MyDividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

public class NutritionistCommentsFragment extends BaseFragment {

    RecyclerView coachCommentsRV;
    CircleImageView profileImg;
    TextView userNameTV, userIDTV, ratingTV, commentsTV, experienceTV, noDataTV;
    RatingBar ratingBar;
    private ArrayList<NutritionistCommentsData> nutritionistDataList = new ArrayList<>();
    NutritionistCommentsAdapter mAdapter;

    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutritionist_comments, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.commentss), false);
        setHasOptionsMenu(false);
        initUI(view);
    }

    private void initUI(View view) {

        coachCommentsRV = view.findViewById(R.id.coachCommentsRV);
        profileImg = view.findViewById(R.id.profile_Img);
        userNameTV = view.findViewById(R.id.userNameTV);
        userIDTV = view.findViewById(R.id.userIDTV);
        ratingBar = view.findViewById(R.id.ratingBar);
        ratingTV = view.findViewById(R.id.ratingTV);
        commentsTV = view.findViewById(R.id.commentsTV);
        experienceTV = view.findViewById(R.id.experienceTV);
        noDataTV = view.findViewById(R.id.noDataTV);


        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString("id");
            String profilePicPath = bundle.getString("profilePicPath");
            userNameTV.setText(bundle.getString("firstName"));
            userIDTV.setText(String.format(" (%s)", bundle.getString("accountId")));
            ratingBar.setRating(Float.parseFloat(bundle.getString("avgRating")));

            if (Float.parseFloat(bundle.getString("avgRating")) == 0.0) {
                ratingTV.setVisibility(View.GONE);
            } else {
                DecimalFormat twoDForm = new DecimalFormat("#.0");
                ratingTV.setText(String.format("(%s)", twoDForm.format(Float.parseFloat(bundle.getString("avgRating")))));
//                ratingTV.setText(" (" + bundle.getString("avgRating") + ")");
            }


            commentsTV.setText(String.format("%s %s", bundle.getString("comments"), getResources().getString(R.string.commentss)));
            experienceTV.setText(bundle.getString("experience"));

            if (profilePicPath != null && !profilePicPath.isEmpty())
                Glide.with(baseActivity)
                        .load(Const.SERVER_IMAGE_URL + profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                        .apply(new RequestOptions()
                                .dontAnimate()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder))
                        .into(profileImg);

            ((MainActivity) getActivity()).setToolbarTitle(bundle.getString("firstName") + " Comments", false);

        }

        mAdapter = new NutritionistCommentsAdapter(baseActivity, this, nutritionistDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        coachCommentsRV.setLayoutManager(mLayoutManager);
        coachCommentsRV.setItemAnimator(new DefaultItemAnimator());
        coachCommentsRV.addItemDecoration(new MyDividerItemDecoration(baseActivity, LinearLayoutManager.VERTICAL, 16));
        coachCommentsRV.setAdapter(mAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        getAllCommentsApi();

    }

    private void getAllCommentsApi() {
        Call<JsonObject> nearbyCoach = baseActivity.apiInterface.getComments("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.url + "/user/" + id + "/comments?page=0");
        baseActivity.apiHitAndHandle.makeApiCall(nearbyCoach, this);
        baseActivity.startProgressDialog();
    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();

        try {
            JSONObject jsonObject = new JSONObject(resp);
            JSONObject data = jsonObject.getJSONObject("data");
            nutritionistDataList.clear();
            JSONArray rating = data.getJSONArray("rating");
            Log.e("rating", rating + "");
            for (int i = 0; i < rating.length(); i++) {
                NutritionistCommentsData nutritionistCommentsData = new Gson().fromJson(rating.get(i).toString(), NutritionistCommentsData.class);
                nutritionistDataList.add(nutritionistCommentsData);
            }
            mAdapter.notifyDataSetChanged();
            if (nutritionistDataList.size() > 0) {
                coachCommentsRV.setVisibility(View.VISIBLE);
                noDataTV.setVisibility(View.GONE);
            } else {
                noDataTV.setVisibility(View.VISIBLE);
                coachCommentsRV.setVisibility(View.GONE);
            }

        } catch (Exception e) {

        }

    }

}
