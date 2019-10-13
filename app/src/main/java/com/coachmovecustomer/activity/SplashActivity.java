package com.coachmovecustomer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.coachmovecustomer.R;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;

public class SplashActivity extends BaseActivity {

    ProfileData profileData = new ProfileData();
    private String langType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        profileData = store.getProfileData();


//        showToast(store.getString(Const.LANGUAGE), false);
        initFCM();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (store.getString(Const.ACCESS_TOKEN) != null && !store.getString(Const.ACCESS_TOKEN).isEmpty()) {
                    if (store.getProfileData() != null && store.getProfileData().isProfileCreated != null && store.getProfileData().isProfileCreated) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginSignActivity.class));
//                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();

                    }
                } else {

                    startActivity(new Intent(SplashActivity.this, LoginSignActivity.class));
                    finish();

                 /*   if (store.getLanguage()== null) {
                        popupMethod();
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginSignActivity.class));
                        finish();
                    }*/


                }
            }
        }, 2000);


    }

    private void popupMethod() {
        final Dialog dialogue = new Dialog(this, android.R.style.Theme_Black_NoTitleBar);
        dialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        dialogue.setContentView(R.layout.language_popup);
        RelativeLayout relativeLayout = (RelativeLayout) dialogue.findViewById(R.id.relativeLayout);
        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        relativeLayout.startAnimation(slide_up);
        dialogue.setCancelable(true);

        LinearLayout english_LL = dialogue.findViewById(R.id.english_LL);
        LinearLayout portuguese_LL = dialogue.findViewById(R.id.portuguese_LL);


        english_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogue.dismiss();
//                langType = "en";
//                intentMethod(langType);
                intentMethod();
//                store.saveString(Const.LANGUAGE, "en");
                store.setLanguage("en");


            }
        });


        portuguese_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogue.dismiss();
//                langType = "pt";
//                langType = "en";
                intentMethod();
//                intentMethod(langType);
//                store.saveString(Const.LANGUAGE, "pt");
                store.setLanguage("pt");

            }
        });


        dialogue.show();


    }

    private void intentMethod() {
        Intent intent = new Intent(SplashActivity.this, LoginSignActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
