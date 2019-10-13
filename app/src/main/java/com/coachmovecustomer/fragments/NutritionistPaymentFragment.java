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
import com.coachmovecustomer.adapters.SlideAdapter;
import com.coachmovecustomer.data.AddDietData;
import com.coachmovecustomer.data.Cards;
import com.coachmovecustomer.data.NutritionistData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;

public class NutritionistPaymentFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private ArrayList<Cards> cardsList = new ArrayList<>();
    private ViewPager vp_slider;
    private LinearLayout ll_dots;
    SlideAdapter sliderPagerAdapter;
    ArrayList<NutritionistData> slider_image_list = new ArrayList<>();
    private ImageView[] dots;
    int page_position = 0;
    TextView addNewCardTV, noDataTV, coachNameTV, priceTV;
    Button pay_btn;
    EditText cvvNoEDT;
    ProfileData profileData = new ProfileData();
    private Call<JsonObject> fetchMyCards, confirmPayCall;
    private int cardID;
    String requestID, priceStr;
    AddDietData addDietData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nutritionist_payment, container, false);
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


        vp_slider = view.findViewById(R.id.vp_slider);
        ll_dots = view.findViewById(R.id.ll_dots);
        cvvNoEDT = view.findViewById(R.id.cvvNoEDT);
        priceTV = view.findViewById(R.id.priceTV);
        addNewCardTV = view.findViewById(R.id.addNewCardTV);
        coachNameTV = view.findViewById(R.id.coachNameTV);
        pay_btn = view.findViewById(R.id.pay_btn);
        noDataTV = view.findViewById(R.id.noDataTV);
        addNewCardTV.setOnClickListener(this);
        pay_btn.setOnClickListener(this);


        Bundle bundle = getArguments();
        if (bundle != null) {
            coachNameTV.setText(bundle.getString("coachName"));
            requestID = bundle.getString("requestTo");
        }

        addDietData = bundle.getParcelable("DietData");
        if (addDietData != null) {
            String email = addDietData.email;
            priceTV.setText("R$ " + addDietData.price);
            priceStr = addDietData.price;

        }


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
                } else if (cvvNoEDT.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertCvvNumber));
                } else {
                    cardID = Integer.parseInt(cardsList.get(vp_slider.getCurrentItem()).id + "");
                    confirmPayApi();
                }
                break;
        }
    }


    private void confirmPayApi() {
        try {

            HashMap<String, Object> jsonbody = new HashMap<>();
            jsonbody.put("email", addDietData.email);
            jsonbody.put("price", priceStr);
            jsonbody.put("routine", addDietData.routine);
            jsonbody.put("objective", addDietData.diet);
            jsonbody.put("dayAliment", addDietData.alimentation);
            jsonbody.put("foodLikes", addDietData.likeEat);
            jsonbody.put("foodDislikes", addDietData.dontLikeEat);
            jsonbody.put("eatingDisorder", addDietData.disorder);
            jsonbody.put("allergies", addDietData.allergies);
            jsonbody.put("diabetes", addDietData.diabetes);
            jsonbody.put("cholestrol", addDietData.cholesterol);
            jsonbody.put("digestiveSystem", addDietData.digestive);
            jsonbody.put("thyroid", addDietData.thyroid);
            jsonbody.put("pregency", addDietData.pregency);
            jsonbody.put("others", addDietData.others);
            jsonbody.put("foodSuppliments", addDietData.supplements);
            HashMap<String, Object> requestTo = new HashMap<>();
            requestTo.put("id", requestID);
            jsonbody.put("requestTo", requestTo);
            jsonbody.put("cardId", cardID);
            jsonbody.put("cvv", cvvNoEDT.getText().toString().trim());

            Log.e("jsonBody=====>>>>>>>>>>", jsonbody + "");
            Log.e("Bearer ", baseActivity.store.getString(Const.ACCESS_TOKEN) + "");

            confirmPayCall = baseActivity.apiInterface.postApiPaymentCoach("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.ADD_GET_CARD + profileData.id + "/diets", jsonbody);
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
                } else {
                    noDataTV.setVisibility(View.VISIBLE);
                    vp_slider.setVisibility(View.GONE);
                }


            } catch (Exception e) {
            }
        } else if (call == confirmPayCall) {
            try {
                JSONObject jsonObject = new JSONObject(object.toString());
                Log.e("confirmPay", jsonObject.toString());
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new DietFragment())
                        .commit();
                cvvNoEDT.setText("");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void setCardAdapter() {
        sliderPagerAdapter = new SlideAdapter(baseActivity, this, cardsList);
        vp_slider.setAdapter(sliderPagerAdapter);
        /*   vp_slider.setPageMargin(-100);*/

        vp_slider.setOnPageChangeListener(this);

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

