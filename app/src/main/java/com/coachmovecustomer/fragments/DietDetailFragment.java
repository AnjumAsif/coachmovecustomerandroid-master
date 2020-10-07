package com.coachmovecustomer.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.data.DietDetailData;
import com.coachmovecustomer.utils.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;

public class DietDetailFragment extends BaseFragment {


    RatingBar ratingBar;
    ImageView profile_Img;
    TextView dietTV, dietForTV, dateTV, commentTV, userName_txt, userID_txt, daysTV;
    Button receivedBTN, feedbackBTN;
    private Dialog detailConfirmDialog;
    private String dietID, statusStr, requestToId, firstName, profilePicPath, accountId, avgRating, experience;
    RelativeLayout feedbackRL, mainRL;
    DietDetailData dietDetailData;
    private Call<JsonObject> fetchDietCall, updateStatusCall;
    private DietDetailData dietData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diet_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        //dietDetailData = baseActivity.store.getDietData();
//        dietDetailData = getArguments().getParcelable("dietDetailData");
        initUI(view);
    }

    private void initUI(View view) {


        mainRL = view.findViewById(R.id.mainRL);
        receivedBTN = view.findViewById(R.id.receivedBTN);
        profile_Img = view.findViewById(R.id.profile_Img);
        daysTV = view.findViewById(R.id.daysTV);
        feedbackBTN = view.findViewById(R.id.feedbackBTN);
        dietForTV = view.findViewById(R.id.dietForTV);
        dateTV = view.findViewById(R.id.dateTV);
        commentTV = view.findViewById(R.id.commentTV);
        userName_txt = view.findViewById(R.id.userName_txt);
        userID_txt = view.findViewById(R.id.userID_txt);
        ratingBar = view.findViewById(R.id.ratingBar);
        feedbackRL = view.findViewById(R.id.feedbackRL);

        receivedBTN.setOnClickListener(this);
        feedbackBTN.setOnClickListener(this);

        mainRL.setVisibility(View.GONE);

        Bundle bundle = getArguments();
        if (bundle != null) {
            dietID = bundle.getString("dietID");
            getDietApi(dietID);
        }


    }

    private void updateStatusApi() {
        HashMap<String, String> jsonbody = new HashMap<String, String>();
        jsonbody.put("status", "3");
        updateStatusCall = baseActivity.apiInterface.putAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.UPDATE_DIET_API + dietID, jsonbody);
        baseActivity.apiHitAndHandle.makeApiCall(updateStatusCall, this);
        baseActivity.startProgressDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        getDietApi(dietID);
    }


    private void getDietApi(String dietID) {
        fetchDietCall = baseActivity.apiInterface.getAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.UPDATE_DIET_API + dietID);
        baseActivity.apiHitAndHandle.makeApiCall(fetchDietCall, this);
        baseActivity.startProgressDialog();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.receivedBTN:

                showForgotDialog();

                break;

            case R.id.feedbackBTN:

                Fragment fragmentGet = new FeedbackFragment();
                Bundle bundle = new Bundle();
                bundle.putString("fragment", "fragmentDiet");
                bundle.putString("dietID", dietID);
                bundle.putString("requestToId", dietData.requestTo.id + "");
                if (dietData.requestTo.profilePicPath != null) {
                    bundle.putString("profilePicPath", dietData.requestTo.profilePicPath);
                }
                bundle.putString("firstName", dietData.requestTo.firstName);
                bundle.putString("accountId", dietData.requestTo.accountId);
                bundle.putString("avgRating", dietData.requestTo.avgRating + "");
                bundle.putString("experience", experience);

                fragmentGet.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, fragmentGet)
                        .addToBackStack(null)
                        .commit();

                break;

        }
    }


    private void showForgotDialog() {
        detailConfirmDialog = new Dialog(baseActivity);
        detailConfirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflate = (LayoutInflater) baseActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(R.layout.detail_confirm_popup, null);
        detailConfirmDialog.setContentView(view);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(detailConfirmDialog.getWindow().getAttributes());
        detailConfirmDialog.setCancelable(true);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        detailConfirmDialog.getWindow().setAttributes(lp);
        Button doneIV = detailConfirmDialog.findViewById(R.id.doneBTN);
        Button cancelBTN = detailConfirmDialog.findViewById(R.id.cancelBTN);
        doneIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (statusStr.equals("1") || statusStr.equals("2")) {
                    updateStatusApi();
                } else {
                    detailConfirmDialog.dismiss();
                }


             /*   if (status.equals("1")) {
                    showToast(getResources().getString(R.string.notReceivedMail));
                } else if (status.equals("2")) {
                    updateStatusApi();
                } else {
                    detailConfirmDialog.dismiss();
                }
*/

              /*  getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new DietDetailReceivedFragment())
                        .addToBackStack(null)
                        .commit();*/
            }
        });

        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailConfirmDialog.dismiss();
            }
        });

        detailConfirmDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.trans_black)));
        detailConfirmDialog.show();
    }

    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        if (call == updateStatusCall) {
            try {
                JSONObject jsonObject = new JSONObject(object.toString());
                JSONObject data = jsonObject.getJSONObject("data");

                Log.e("updateDietStatus", data + "");
                Drawable img = getContext().getResources().getDrawable(R.drawable.ic_received);
                img.setBounds(0, 0, 60, 60);
                receivedBTN.setCompoundDrawables(img, null, null, null);
                receivedBTN.setText(getResources().getString(R.string.received));
                detailConfirmDialog.dismiss();

                receivedBTN.setClickable(false);
                receivedBTN.setFocusable(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (call == fetchDietCall) {
            try {
                JSONObject jsonObject = new JSONObject(object.toString());
                JSONObject data = jsonObject.getJSONObject("data");
                JSONObject diet = data.getJSONObject("diet");
                dietData = new Gson().fromJson(diet + "", DietDetailData.class);

                mainRL.setVisibility(View.VISIBLE);
                bundleMethod();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void statusMethod(int status) {

        if (status == 1) {
            statusStr = "1";
//            feedbackBTN.setVisibility(View.VISIBLE);
            receivedBTN.setText(getResources().getString(R.string.ireceived));
            receivedBTN.setClickable(true);
            receivedBTN.setFocusable(true);

        } else if (status == 2) {
//            feedbackBTN.setVisibility(View.VISIBLE);
            statusStr = "2";
            receivedBTN.setText(getResources().getString(R.string.ireceived));
            receivedBTN.setClickable(true);
            receivedBTN.setFocusable(true);

        } else if (status == 3) {
            statusStr = "3";
            Drawable img = getContext().getResources().getDrawable(R.drawable.ic_received);
            img.setBounds(0, 0, 60, 60);
            receivedBTN.setCompoundDrawables(img, null, null, null);
            receivedBTN.setText(getResources().getString(R.string.received));

            receivedBTN.setClickable(false);
            receivedBTN.setFocusable(false);

        }
    }


    private void bundleMethod() {
        Bundle bundle = getArguments();
        if (bundle != null) {

//            ((MainActivity) getActivity()).setToolbarTitle(dietData.objective, false);

            ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.diet), false);

            dietForTV.setText(dietData.objective);
            dateTV.setText(baseActivity.convertDateFormatLocale(dietData.createdAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "dd MMM, yyyy"));

            statusMethod(dietData.status);


            if (dietData.requestTo.userModalities != null) {
                for (int i = 0; i < dietData.requestTo.userModalities.size(); i++) {
                    if (dietData.requestTo.userModalities.get(i).modality.modality.equals(dietData.modality.modality)) {
                        experience = dietData.requestTo.userModalities.get(i).experience;
                        Log.e("compare", experience + "\n" + dietData.modality.modality);
                    }
                }
            }

            if (dietData.requestTo.profilePicPath != null || !dietData.requestTo.profilePicPath.isEmpty()) {
                Glide.with(baseActivity)
                        .load(Const.SERVER_IMAGE_URL + dietData.requestTo.profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                        .apply(new RequestOptions()
                                .dontAnimate()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder))
                        .into(profile_Img);
            }


            if (dietData.ratingAndComment != null) {
                feedbackBTN.setVisibility(View.GONE);
                feedbackRL.setVisibility(View.VISIBLE);
                String avgRatingParticular = dietData.ratingAndComment.rating + "";
                ratingBar.setRating(Float.parseFloat(avgRatingParticular + ""));

                String toServerUnicodeEncoded = StringEscapeUtils.unescapeJava(dietData.ratingAndComment.comment);
                commentTV.setText(toServerUnicodeEncoded);
//                commentTV.setText(dietData.ratingAndComment.comment);


                daysTV.setText(baseActivity.changeDate(dietData.ratingAndComment.createdAt, "yyyy-MM-dd'T'HH:mm:ss.SSSZ"/*, "dd MMM,yyyy"*/));
                userID_txt.setText(" (" + dietData.requestTo.accountId + ")");
                userName_txt.setText(dietData.requestTo.firstName);
            } else {
                feedbackBTN.setVisibility(View.VISIBLE);
                feedbackRL.setVisibility(View.GONE);
            }


        }
    }


}
