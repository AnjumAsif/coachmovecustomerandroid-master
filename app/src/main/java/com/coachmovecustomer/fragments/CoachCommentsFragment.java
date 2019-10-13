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
import com.coachmovecustomer.adapters.CoachCommentsAdapter;
import com.coachmovecustomer.data.CoachCommentsData;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.MyDividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

public class CoachCommentsFragment extends BaseFragment {

    RecyclerView coachCommentsRV;
    CircleImageView profileImg;
    TextView userNameTV, userIDTV, ratingTV, commentsTV, experienceTV, noDataTV;
    RatingBar ratingBar;
    private ArrayList<CoachCommentsData.Rating> coachCommentsDataList = new ArrayList<>();
    CoachCommentsAdapter coachCommentsAdapter;
    String id;
    private int count = 0;
    private String pageNo = "0";
    private LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coach_comments, container, false);
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
//                ratingTV.setText(String.format(" (%s)", bundle.getString("avgRating")));
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

        coachCommentsAdapter = new CoachCommentsAdapter(baseActivity, this, coachCommentsDataList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        coachCommentsRV.setLayoutManager(mLayoutManager);
        coachCommentsRV.setItemAnimator(new DefaultItemAnimator());
        coachCommentsRV.addItemDecoration(new MyDividerItemDecoration(baseActivity, LinearLayoutManager.VERTICAL, 16));
        coachCommentsRV.setAdapter(coachCommentsAdapter);
        coachCommentsRV.addOnScrollListener(recyclerViewOnScrollListener);

    }

    @Override
    public void onResume() {
        super.onResume();
        getAllCommentsApi(pageNo);

    }

    private void getAllCommentsApi(String pageNo) {
        Call<JsonObject> nearbyCoach = baseActivity.apiInterface.getComments("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.url + "/user/" + id + "/comments?page=" + pageNo);
        baseActivity.apiHitAndHandle.makeApiCall(nearbyCoach, this);
        baseActivity.startProgressDialog();
    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();

        try {
            CoachCommentsData coachCommentsData = new Gson().fromJson(object.toString(), CoachCommentsData.class);
            if (coachCommentsData.data.rating.size() > 0) {
                loading = true;
                coachCommentsRV.setVisibility(View.VISIBLE);
                noDataTV.setVisibility(View.GONE);
                List<CoachCommentsData.Rating> temp = new ArrayList<>();
                temp.addAll(coachCommentsData.data.rating);
                coachCommentsDataList.addAll(temp);
            } else {
                loading = false;
                if (coachCommentsDataList.size() == 0) {
                    coachCommentsRV.setVisibility(View.GONE);
                    noDataTV.setVisibility(View.VISIBLE);
                } else {

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            count++;
                            Log.v("pageItem", "Last Item Wow !");
                            getAllCommentsApi(count + "");
                            //Do pagination.. i.e. fetch new data
                        }
                    }
                }
            }
        }
    };


}
