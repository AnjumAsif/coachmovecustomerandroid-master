package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.google.gson.JsonObject;

import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

public class HomeFragment extends BaseFragment {


    CircleImageView profile_Img;
    ImageView fullBackgroundIV;
    TextView userNameTV, userIDTV, ratingText;
    RatingBar ratingBar;
    private ProfileData profileData = new ProfileData();
    private Call<JsonObject> getVenues;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle("", true);
        setHasOptionsMenu(true);

        profileData = baseActivity.store.getProfileData();

        initUI(view);
    }

    private void initUI(View view) {

        fullBackgroundIV = view.findViewById(R.id.fullBackgroundIV);
        profile_Img = view.findViewById(R.id.profile_Img);
        userNameTV = view.findViewById(R.id.userNameTV);
        userIDTV = view.findViewById(R.id.userIDTV);
        ratingText = view.findViewById(R.id.ratingText);
        ratingBar = view.findViewById(R.id.ratingBar);

        userNameTV.setText(String.format("%s %s", profileData.firstName, profileData.lastName));
        userIDTV.setText(String.format("(%s)", profileData.accountId));

        ratingBar.setRating(profileData.avgRating);

        if (profileData.avgRating ==0.0){
            ratingText.setVisibility(View.GONE);
        }else {
            DecimalFormat twoDForm = new DecimalFormat("#.0");
            ratingText.setText(new StringBuilder().append("(").append(twoDForm.format(profileData.avgRating)).append(")").toString());
//            ratingText.setText(new StringBuilder().append("(").append(profileData.avgRating).append(")").toString());
        }


        Log.e("imagePath", "" + "" + profileData.profilePicPath);
        if (profileData.profilePicPath != null && !profileData.profilePicPath.isEmpty())
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + profileData.profilePicPath/*+""+BaseActivity.setCurrentTimeMillis(baseActivity)*/)
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.user_dummy)
                            .error(R.drawable.placeholder))
                    .into(profile_Img);



        Glide.with(baseActivity)
                .load(Const.SERVER_IMAGE_URL + profileData.profilePicPath/*+""+ BaseActivity.setCurrentTimeMillis(baseActivity)*/)
                .apply(new RequestOptions()
                                .dontAnimate()
                                .placeholder(R.drawable.user_placeholder)
                        /*.error(android.R.drawable.stat_notify_error)*/)
                .into(fullBackgroundIV);

//        baseActivity.startProgressDialog();
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
                baseActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new SettingsFragment()).addToBackStack(null).commit();
                break;
        }
        return false;
    }


}
