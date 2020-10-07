package com.coachmovecustomer.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class CheckBoxView extends android.support.v7.widget.AppCompatCheckBox {

    public CheckBoxView(Context context) {
        super(context);
        init();
    }

    public CheckBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckBoxView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/AvenirLTStd-Book.otf");
        setTypeface(tf);
    }
}