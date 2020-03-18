package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.adapters.SliderPagerAdapter;
import com.coachmovecustomer.data.Cards;
import com.coachmovecustomer.data.PeopleForAddData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.ScreenshotUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

public class PaymentFragment extends BaseFragment implements ViewPager.OnPageChangeListener {


    private ArrayList<Cards> cardsList = new ArrayList<>();
    private ViewPager vp_slider;
    private LinearLayout ll_dots, sliderLL;
    SliderPagerAdapter sliderPagerAdapter;
    //    ArrayList<Integer> slider_image_list = new ArrayList<>();

    private ImageView[] dots;
    int page_position = 0;
    TextView addNewCardTV, coachNameTV, priceTV, dateTV, addressTV, noDataTV;
    Button pay_btn;
    EditText cvvNo_edt;
    ProfileData profileData = new ProfileData();
    private Call<JsonObject> fetchMyCards, confirmPayCall;
    private int cardID;
    private String prefferedGender, modalityID, neighbourhoodID, timeslotId, requestID;


    private ArrayList<PeopleForAddData> selectedPeopleDataList = new ArrayList<>();
    private String couponId = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.payments), false);
        profileData = baseActivity.store.getProfileData();
        setHasOptionsMenu(false);
        initUI(view);
    }

    private void initUI(View view) {
//        sliderLL = view.findViewById(R.id.sliderLL);
        vp_slider = view.findViewById(R.id.vp_slider);
        ll_dots = view.findViewById(R.id.ll_dots);
        addNewCardTV = view.findViewById(R.id.addNewCardTV);
        pay_btn = view.findViewById(R.id.pay_btn);
        coachNameTV = view.findViewById(R.id.coachNameTV);
        priceTV = view.findViewById(R.id.priceTV);
        dateTV = view.findViewById(R.id.dateTV);
        addressTV = view.findViewById(R.id.addressTV);
        noDataTV = view.findViewById(R.id.noDataTV);
        cvvNo_edt = view.findViewById(R.id.cvvNo_edt);

        Bundle bundle = getArguments();
        if (bundle != null) {
  /*          String date = baseActivity.convertDateFormat(bundle.getString("date"), "yyyy-MM-dd", "dd MMM, yyyy");
            String time = baseActivity.convertDateFormat(bundle.getString("time"), "HH:mm:ss", "HH:mm"); */
            String date = baseActivity.convertDateFormatLocale(bundle.getString("date"), "yyyy-MM-dd", "dd MMM, yyyy");
            String time = baseActivity.convertDateFormatLocale(bundle.getString("time"), "HH:mm:ss", "HH:mm");
            coachNameTV.setText(bundle.getString("coachName"));
            dateTV.setText(date + " at " + time);
            addressTV.setText(bundle.getString("address"));
            String applyCouponAmount = bundle.getString("price");
            assert applyCouponAmount != null;
            String userValue = ScreenshotUtils.commaSeperatedValue(applyCouponAmount.replace(",", ""));
            priceTV.setText("R$ " + userValue/*+ ",00"*/);


            if (bundle.getString("gender").isEmpty()) {
                prefferedGender = "";
            } else {
                prefferedGender = String.valueOf(bundle.getString("gender").charAt(0));
            }


            modalityID = bundle.getString("modalityID");
            neighbourhoodID = bundle.getString("neighbourhood");
            timeslotId = bundle.getString("timeslotId");
            requestID = bundle.getString("requestTo");
            couponId = bundle.getString("couponId");


        }


        selectedPeopleDataList = getArguments().getParcelableArrayList("workoutUser");
        if (selectedPeopleDataList != null) {
            bundle.putParcelableArrayList("workoutUser", selectedPeopleDataList);
            Log.e("selectedSize", selectedPeopleDataList.size() + "");
        }

        addNewCardTV.setOnClickListener(this);
        pay_btn.setOnClickListener(this);
        setCardAdapter();

    }

    @Override
    public void onResume() {
        super.onResume();
        fetchCardApi();

    }

    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.addNewCardTV:

                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new AddNewCardFragment())
                        .addToBackStack(null)
                        .commit();

                break;

            case R.id.pay_btn:

                if (cardsList.size() == 0 || cardsList.isEmpty()) {
                    showToast(getResources().getString(R.string.pleaseAddCard));
                } else if (cvvNo_edt.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.enterCvvNumber));
                } else {
                    cardID = Integer.parseInt(cardsList.get(vp_slider.getCurrentItem()).id + "");
                    confirmPayApi();
                }

                break;

        }
    }

    private void confirmPayApi() {


        try {
            HashMap<String, Object> jsonObject = new HashMap<>();
            HashMap<String, Object> modality = new HashMap<>();
            modality.put("id", modalityID);
            HashMap<String, Object> neighbourhood = new HashMap<>();
            neighbourhood.put("id", neighbourhoodID);
            HashMap<String, Object> requestTo = new HashMap<>();
            requestTo.put("id", requestID);

            List<HashMap<String, Object>> workoutArray = new ArrayList<>();
            for (int i = 0; i < selectedPeopleDataList.size(); i++) {
                HashMap<String, Object> user = new HashMap<String, Object>();
                HashMap<String, Object> workout = new HashMap<String, Object>();
                workout.put("id", selectedPeopleDataList.get(i).id);

                user.put("user", workout);

                workoutArray.add(user);
            }

            jsonObject.put("workoutUsers", workoutArray);
            jsonObject.put("neighbourhood", neighbourhood);
            jsonObject.put("requestTo", requestTo);
            jsonObject.put("modality", modality);
            jsonObject.put("prefferedGender", prefferedGender);
            jsonObject.put("address", addressTV.getText().toString().trim());
            jsonObject.put("timeslotId", timeslotId);
            jsonObject.put("cardId", cardID);
            jsonObject.put("cvv", cvvNo_edt.getText().toString().trim());

//            jsonObject.put("couponId", couponId);


            Log.e("jsonObject=====>>>.", jsonObject + " " + workoutArray + "\n" + prefferedGender);

            confirmPayCall = baseActivity.apiInterface.postApiObject("Bearer " +
                            baseActivity.store.getString(Const.ACCESS_TOKEN), Const.ADD_GET_CARD + profileData.id + "/workouts?couponId=" + couponId,
                    jsonObject);
            baseActivity.apiHitAndHandle.makeApiCall(confirmPayCall, this);
            baseActivity.startProgressDialog();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void fetchCardApi() {

        fetchMyCards = baseActivity.apiInterface.getAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.ADD_GET_CARD + profileData.id + "/cards");
        baseActivity.apiHitAndHandle.makeApiCall(fetchMyCards, this);
        baseActivity.startProgressDialog();
    }

    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        if (call == fetchMyCards) {
            try {
                JSONObject jsonObject = new JSONObject(object.toString());
                JSONObject data = jsonObject.getJSONObject("data");
                cardsList.clear();
                JSONArray cards = data.getJSONArray("cards");
                for (int i = 0; i < cards.length(); i++) {
                    Log.e("jsonObject", cards.get(i).toString() + "");
                    Cards cardData = new Gson().fromJson(cards.get(i).toString(), Cards.class);
                    cardsList.add(cardData);
                }
                sliderPagerAdapter.notifyDataSetChanged();
                if (cardsList.size() > 0) {
                    vp_slider.setVisibility(View.VISIBLE);
                    noDataTV.setVisibility(View.GONE);
//                    sliderLL.setVisibility(View.VISIBLE);
                } else {
                    noDataTV.setVisibility(View.VISIBLE);
                    vp_slider.setVisibility(View.GONE);
//                    sliderLL.setVisibility(View.GONE);
                }


            } catch (Exception e) {
            }
        } else if (call == confirmPayCall) {
            try {
                JSONObject jsonObject = new JSONObject(object.toString());

                Log.e("confirmPayment", jsonObject.toString());
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new PaymentSuccessFragment())
                        .addToBackStack(null)
                        .commit();
                cvvNo_edt.setText("");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private void setCardAdapter() {
        sliderPagerAdapter = new SliderPagerAdapter(baseActivity, this, cardsList);
        vp_slider.setAdapter(sliderPagerAdapter);
        vp_slider.setOnPageChangeListener(this);
        /* vp_slider.setPageMargin(-100);*/

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //  addBottomDots(position);

    }

    @Override
    public void onPageSelected(int position) {
        addBottomDots(position);
/*
        cardID = Integer.parseInt(cardsList.get(position).id + "");
*/
    }

    @Override
    public void onPageScrollStateChanged(int position) {


    }

    private void addBottomDots(int currentPage) {
        dots = new ImageView[cardsList.size()];

        ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getActivity());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            dots[i].setLayoutParams(params);

            dots[i].setBackgroundResource(R.drawable.unselected_drawable);
            ll_dots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setBackgroundResource(R.drawable.selected_drawable);
    }

}
