package com.coachmovecustomer.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.adapters.MyCardsAdapter;
import com.coachmovecustomer.data.Cards;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.myInterface.onClickDelete;
import com.coachmovecustomer.utils.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import retrofit2.Call;

public class MyCardsFragment extends BaseFragment {


    RecyclerView myCardsRV;
    MyCardsAdapter myCardAdapter;

    TextView noDataTV;
    long removeID;
    private Call<JsonObject> fetchMyCards, removeMyCard;
    private ArrayList<Cards> cardsList = new ArrayList<>();
    private JSONArray cards;
    ProfileData profileData = new ProfileData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_cards, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.myCard), false);
        profileData = baseActivity.store.getProfileData();
        initUI(view);
    }

    private void initUI(View view) {

        myCardsRV = view.findViewById(R.id.myCardsRV);
        noDataTV = view.findViewById(R.id.noDataTV);

        setGetCardData();
        onClickRecycler();

    }


    @Override
    public void onResume() {
        super.onResume();
        fetchCardApi();

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

                    myCardAdapter.notifyDataSetChanged();

                }
                if (cardsList.size() > 0) {
                    myCardsRV.setVisibility(View.VISIBLE);
                    noDataTV.setVisibility(View.GONE);
                } else {
                    noDataTV.setVisibility(View.VISIBLE);
                    myCardsRV.setVisibility(View.GONE);
                }
            } catch (Exception e) {

            }

        } else if (call == removeMyCard) {
            try {
                JSONObject jsonObject = new JSONObject(object.toString());
                Log.e("removeCard", jsonObject.toString());
                fetchCardApi();


            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    private void setGetCardData() {
        myCardAdapter = new MyCardsAdapter(getActivity(), this, cardsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        myCardsRV.setLayoutManager(mLayoutManager);
        myCardsRV.setItemAnimator(new DefaultItemAnimator());
        myCardsRV.setAdapter(myCardAdapter);


    }


    private void onClickRecycler() {

        myCardAdapter.setOnNewClick(new onClickDelete() {
            @Override
            public void onClick(Object obj, int pos) {
                showCancelPopup(pos);

            }
        });
    }

    private void removeCardMethod(int id) {

        removeMyCard = baseActivity.apiInterface.getDelete("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), id);
        baseActivity.apiHitAndHandle.makeApiCall(removeMyCard, this);

        baseActivity.startProgressDialog();
    }

    private void showCancelPopup(final int pos) {

        baseActivity.setTheme(R.style.customCheckBox);
        AlertDialog.Builder builder = new AlertDialog.Builder(baseActivity);
        builder.setMessage(getString(R.string.delete_card))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        removeCardMethod(cardsList.get(pos).id);
                    }
                }).setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
