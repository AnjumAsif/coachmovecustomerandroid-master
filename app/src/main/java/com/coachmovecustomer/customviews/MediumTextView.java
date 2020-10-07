package com.coachmovecustomer.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class MediumTextView extends android.support.v7.widget.AppCompatTextView {

    public MediumTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MediumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MediumTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/AvenirLTStd-Roman.otf");
        setTypeface(tf);

    }
}