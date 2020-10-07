package com.coachmovecustomer.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.data.Cards;
import com.coachmovecustomer.fragments.NutritionistPaymentFragment;

import java.util.ArrayList;

public class SlideAdapter  extends PagerAdapter {
    private LayoutInflater layoutInflater;
    Fragment fragment;
    ArrayList<Cards> cardsList = new ArrayList<>();
    BaseActivity baseActivity;
    Context mContext;

    public SlideAdapter(BaseActivity baseActivity, NutritionistPaymentFragment nutritionistPaymentFragment, ArrayList<Cards> cardsList) {
        this.baseActivity = baseActivity;
        this.cardsList = cardsList;
        this.fragment = nutritionistPaymentFragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) baseActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_slider, container, false);
        Cards cards = cardsList.get(position);
        ImageView im_slider = view.findViewById(R.id.im_slider);
        TextView cardHolderET = view.findViewById(R.id.cardHolderET);
        TextView cardTypeTV = view.findViewById(R.id.cardTypeTV);
        TextView cardNo_TV = view.findViewById(R.id.cardNo_TV);
        TextView expiry_TV = view.findViewById(R.id.expiry_TV);

        if (cards.type.equalsIgnoreCase("VISA")) {
            im_slider.setImageResource(R.drawable.visa);
            cardTypeTV.setText("VISA");
        } else if (cards.type.equalsIgnoreCase("MasterCard")) {
            im_slider.setImageResource(R.drawable.master_card);
            cardTypeTV.setText("Mastercard");
        } else if (cards.type.equalsIgnoreCase("AmericanExpress")) {
            im_slider.setImageResource(R.drawable.american);
            cardTypeTV.setText("AmericanExpress");
        } else if (cards.type.equalsIgnoreCase("DinersClub")) {
            im_slider.setImageResource(R.drawable.dinersclub);
            cardTypeTV.setText("DinersClub");
        } else if (cards.type.equalsIgnoreCase("Discover")) {
            im_slider.setImageResource(R.drawable.discover);
            cardTypeTV.setText("Discover");
        } else if (cards.type.equalsIgnoreCase("JCB")) {
            im_slider.setImageResource(R.drawable.jcb_card);
            cardTypeTV.setText("JCB");
        }


        cardNo_TV.setText("xxxx xxxx xxxx " + cards.cardNo);
        expiry_TV.setText(cards.expiryDate);
        cardHolderET.setText(cards.name);

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return cardsList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}