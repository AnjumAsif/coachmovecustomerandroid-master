package com.coachmovecustomer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.data.Cards;
import com.coachmovecustomer.fragments.FinancialFragment;
import com.coachmovecustomer.myInterface.SlideRecycleClick;

import java.util.ArrayList;

public class SlideTestAdapter extends RecyclerView.Adapter<SlideTestAdapter.MyViewHolder> {


    private Fragment fragment;
    private ArrayList<Cards> cardsList = new ArrayList<>();
    Context mContext;

    private SlideRecycleClick callBack;


    public SlideTestAdapter(Context mContext, FinancialFragment financialFragment, ArrayList<Cards> cardsList) {
        this.mContext = mContext;
        this.fragment = financialFragment;
        this.cardsList = cardsList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView im_slider;
        private TextView cardHolderET ,cardNo_TV, expiry_TV, cardTypeTV;

        public MyViewHolder(View view) {
            super(view);
            cardHolderET = view.findViewById(R.id.cardHolderET);
            cardTypeTV = view.findViewById(R.id.cardTypeTV);
            im_slider = view.findViewById(R.id.im_slider);
            cardNo_TV = view.findViewById(R.id.cardNo_TV);
            expiry_TV = view.findViewById(R.id.expiry_TV);

        }
    }


    @Override
    public SlideTestAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_slider_test, parent, false);

        return new SlideTestAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SlideTestAdapter.MyViewHolder holder, final int position) {
        Cards myCardData = cardsList.get(position);
        if (myCardData.type.equalsIgnoreCase("VISA")) {
            holder.im_slider.setImageResource(R.drawable.visa);
            holder.cardTypeTV.setText("VISA");
        } else if (myCardData.type.equalsIgnoreCase("MasterCard")) {
            holder.im_slider.setImageResource(R.drawable.master_card);
            holder.cardTypeTV.setText("Mastercard");
        } else if (myCardData.type.equalsIgnoreCase("AmericanExpress")) {
            holder.im_slider.setImageResource(R.drawable.american);
            holder.cardTypeTV.setText("AmericanExpress");
        } else if (myCardData.type.equalsIgnoreCase("DinersClub")) {
            holder.im_slider.setImageResource(R.drawable.dinersclub);
            holder.cardTypeTV.setText("DinersClub");
        } else if (myCardData.type.equalsIgnoreCase("Discover")) {
            holder.im_slider.setImageResource(R.drawable.discover);
            holder.cardTypeTV.setText("Discover");
        } else if (myCardData.type.equalsIgnoreCase("JCB")) {
            holder.im_slider.setImageResource(R.drawable.jcb_card);
            holder.cardTypeTV.setText("JCB");
        }
        holder.cardNo_TV.setText("xxxx xxxx xxxx " + myCardData.cardNo);
        holder.expiry_TV.setText(myCardData.expiryDate);
        holder.cardHolderET.setText(myCardData.name);
    }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }


}