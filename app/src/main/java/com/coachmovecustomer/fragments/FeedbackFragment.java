package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.data.DietDetailData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;

public class FeedbackFragment extends BaseFragment implements RatingBar.OnRatingBarChangeListener {


    TextView userNameTV, userIDTV, rating_txt, experience_txt, howSessionTV;
    Button submitBTN;
    EditText reviewET;
    RatingBar ratingBar, reviewRating;
    ImageView profileIV;
    ProfileData profileData = new ProfileData();
    String dietWorkout, reviewRatingStr;
    private String dietWorkoutID, requestToId;
    DietDetailData dietDetailData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.feedback), false);
        profileData = baseActivity.store.getProfileData();
        setHasOptionsMenu(false);
        initUI(view);
    }

    private void initUI(View view) {


        submitBTN = view.findViewById(R.id.submitBTN);
        reviewET = view.findViewById(R.id.reviewET);
        userNameTV = view.findViewById(R.id.userNameTV);
        userIDTV = view.findViewById(R.id.userIDTV);
        rating_txt = view.findViewById(R.id.rating_txt);
        experience_txt = view.findViewById(R.id.experience_txt);
        ratingBar = view.findViewById(R.id.ratingBar);
        reviewRating = view.findViewById(R.id.reviewRating);
        profileIV = view.findViewById(R.id.profileIV);
        howSessionTV = view.findViewById(R.id.howSessionTV);

//        reviewET.setFilters(baseActivity.setFiltersET(200));

        submitBTN.setOnClickListener(this);
        reviewRating.setOnRatingBarChangeListener(this);


        bundleMethod();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.submitBTN:
              /*  if (reviewET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertReview), false);
                } else*/

              if (reviewRating.getRating() == 0) {
                    showToast(getResources().getString(R.string.fillRating), false);
                } else {
                    addFeedBackApi(dietWorkout);
                }
                break;
        }
    }


    private void addFeedBackApi(String param) {

        String toServerUnicodeEncoded = StringEscapeUtils.escapeJava(reviewET.getText().toString());
        Log.e("toServer", toServerUnicodeEncoded + "");

        try {
            HashMap<String, Object> jsonObject = new HashMap<>();
            HashMap<String, Object> commentOn = new HashMap<>();
            commentOn.put("id", requestToId);
            HashMap<String, Object> workout = new HashMap<>();
            workout.put("id", dietWorkoutID);
            jsonObject.put("commentOn", commentOn);
            jsonObject.put(param, workout);
            jsonObject.put("rating", reviewRating.getRating());
            jsonObject.put("comment", toServerUnicodeEncoded);


            Log.e("jsonObject=====>>>.", jsonObject + " ");

            Call<JsonObject> addFeedBackApi = baseActivity.apiInterface.postApiObject("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.url + "/user/" + profileData.id + "/comments", jsonObject);
            baseActivity.apiHitAndHandle.makeApiCall(addFeedBackApi, this);
            baseActivity.startProgressDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            JSONObject data = jsonObject.optJSONObject("data");
            JSONObject rating = data.optJSONObject("rating");
            Log.e("dataFeedback", rating + " ");


            Bundle bundle = getArguments();
            if (bundle != null) {
                if (bundle.getString("fragment").equals("fragmentDiet")) {


                    baseActivity.onBackPressed();
              /*      JSONObject diet = rating.optJSONObject("diet");
                    Fragment fragmentGet = new DietDetailFragment();
                    Bundle bundleBack = new Bundle();
                    bundleBack.putString("dietID", diet.optString("id"));
                    fragmentGet.setArguments(bundleBack);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frameLayoutMain, fragmentGet)
                            .commit();*/

                } else {
                    baseActivity.onBackPressed();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
        reviewRatingStr = String.valueOf(rating);
    }

    private void bundleMethod() {
        Bundle bundle = getArguments();
        if (bundle != null) {
//            showToast(bundle.getString("fragment"), false);
            if (bundle.getString("fragment").equals("fragmentDiet")) {
                dietWorkout = "diet";
                dietWorkoutID = bundle.getString("dietID");
                requestToId = bundle.getString("requestToId");
                userNameTV.setText(bundle.getString("firstName"));
                userIDTV.setText(" (" + bundle.getString("accountId") + ")");
                rating_txt.setText(" (" + bundle.getString("avgRating") + ")");
                ratingBar.setRating(Float.parseFloat(bundle.getString("avgRating") + ""));


                if (bundle.getString("profilePicPath") != null && !bundle.getString("profilePicPath").isEmpty())
                    Glide.with(baseActivity)
                            .load(Const.SERVER_IMAGE_URL + bundle.getString("profilePicPath") + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                            .apply(new RequestOptions()
                                    .dontAnimate()
                                    .placeholder(R.drawable.placeholder)
                                    .error(android.R.drawable.stat_notify_error))
                            .into(profileIV);

                experience_txt.setText(bundle.getString("experience"));


                howSessionTV.setText(getResources().getString(R.string.session_hint_diet));


            } else {
                dietWorkout = "workout";
                dietWorkoutID = bundle.getString("workoutID");
                requestToId = bundle.getString("requestToId");
                userNameTV.setText(bundle.getString("firstName"));
                userIDTV.setText(" (" + bundle.getString("accountId") + ")");
                rating_txt.setText(" (" + bundle.getString("avgRating") + ")");
                ratingBar.setRating(Float.parseFloat(bundle.getString("avgRating") + ""));

                if (bundle.getString("profilePicPath") != null && !bundle.getString("profilePicPath").isEmpty())
                    Glide.with(baseActivity)
                            .load(Const.SERVER_IMAGE_URL + bundle.getString("profilePicPath") + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                            .apply(new RequestOptions()
                                    .dontAnimate()
                                    .placeholder(R.drawable.placeholder)
                                    .error(android.R.drawable.stat_notify_error))
                            .into(profileIV);

                experience_txt.setText(bundle.getString("experience"));

                howSessionTV.setText(getResources().getString(R.string.session_hint));


            }
        }


    }

}
