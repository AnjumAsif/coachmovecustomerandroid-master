package com.coachmovecustomer.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by netset on 20/3/18.
 */

public class RegularTextView extends android.support.v7.widget.AppCompatTextView {

    public RegularTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RegularTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/AvenirLTStd-Book.otf");
        setTypeface(tf);
    }
}