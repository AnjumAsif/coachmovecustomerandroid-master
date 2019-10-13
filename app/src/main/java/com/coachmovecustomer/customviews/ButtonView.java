package com.coachmovecustomer.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by netset on 23/8/18.
 */

public class ButtonView extends android.support.v7.widget.AppCompatButton {

    public ButtonView(Context context) {
        super(context);
        init();
    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/AEH.ttf");
        setTypeface(tf);
    }
}
