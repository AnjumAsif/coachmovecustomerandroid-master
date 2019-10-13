package com.coachmovecustomer.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.coachmovecustomer.R;

/**
 * Created by netset on 20/3/18.
 */

public class RegularEditText extends android.support.v7.widget.AppCompatEditText {


    public RegularEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RegularEditText(Context context, AttributeSet attrs) {
        super(context, attrs);


        init();
    }

    public RegularEditText(Context context) {
        super(context);
        init();
    }

    private void init() {

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/AvenirLTStd-Book.otf");
        setTypeface(tf);


    }

}