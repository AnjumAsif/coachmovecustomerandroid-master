package com.coachmovecustomer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.data.Cards;
import com.coachmovecustomer.fragments.MyCardsFragment;
import com.coachmovecustomer.myInterface.onClickDelete;

import java.util.ArrayList;

public class MyCardsAdapter extends RecyclerView.Adapter<MyCardsAdapter.MyViewHolder> {

    private onClickDelete clickDelete;
    Fragment fragment;
    ArrayList<Cards> cardsList = new ArrayList<>();
    Context mContext;


    public MyCardsAdapter(Context mContext, MyCardsFragment myCardsFragment, ArrayList<Cards> cardsList) {
        this.mContext = mContext;
        this.fragment = myCardsFragment;
        this.cardsList = cardsList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView cardHolderET, deleteCard_TV, cardNumTV, validDateTV, cardTypeTV;
        private ImageView card_IV;

        public MyViewHolder(View view) {
            super(view);
            cardHolderET = view.findViewById(R.id.cardHolderET);
            cardTypeTV = view.findViewById(R.id.cardTypeTV);
            cardNumTV = view.findViewById(R.id.cardNumTV);
            validDateTV = view.findViewById(R.id.validDateTV);
            card_IV = view.findViewById(R.id.card_IV);
            deleteCard_TV = view.findViewById(R.id.deleteCard_TV);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lists_my_cards, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Cards myCardData = cardsList.get(position);
        if (myCardData.type.equalsIgnoreCase("VISA")) {
            holder.card_IV.setImageResource(R.drawable.visa);
            holder.cardTypeTV.setText("VISA");
        } else if (myCardData.type.equalsIgnoreCase("MasterCard")) {
            holder.card_IV.setImageResource(R.drawable.master_card);
            holder.cardTypeTV.setText("Mastercard");
        } else if (myCardData.type.equalsIgnoreCase("AmericanExpress")) {
            holder.card_IV.setImageResource(R.drawable.american);
            holder.cardTypeTV.setText("American Express");
        } else if (myCardData.type.equalsIgnoreCase("DinersClub")) {
            holder.card_IV.setImageResource(R.drawable.dinersclub);
            holder.cardTypeTV.setText("Diners Club");
        } else if (myCardData.type.equalsIgnoreCase("Discover")) {
            holder.card_IV.setImageResource(R.drawable.discover);
            holder.cardTypeTV.setText("Discover");
        } else if (myCardData.type.equalsIgnoreCase("JCB")) {
            holder.card_IV.setImageResource(R.drawable.jcb_card);
            holder.cardTypeTV.setText("JCB");
        }


        holder.cardNumTV.setText("xxxx xxxx xxxx " + myCardData.cardNo);
        holder.validDateTV.setText(myCardData.expiryDate);
        holder.cardHolderET.setText(myCardData.name);

        holder.deleteCard_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = position;
                if (clickDelete != null) {
                    clickDelete.onClick(cardsList.get(pos), pos);

                }
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }


    public void setOnNewClick(onClickDelete click) {
        this.clickDelete = click;
    }

}