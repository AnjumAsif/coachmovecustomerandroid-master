package com.coachmovecustomer.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ToggleButton;


/**
 * Created by netset on 23/8/18.
 */

public class ToggleButtonView extends ToggleButton {


    public ToggleButtonView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public ToggleButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ToggleButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToggleButtonView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular.otf");
        setTypeface(tf);
    }
}
