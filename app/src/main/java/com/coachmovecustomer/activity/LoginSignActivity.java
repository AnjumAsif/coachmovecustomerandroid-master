package com.coachmovecustomer.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.fragments.CreateProfileFirstFragment;
import com.coachmovecustomer.fragments.CreateProfileSecondFragment;
import com.coachmovecustomer.fragments.LoginFragment;
import com.coachmovecustomer.fragments.SignUpFragment;

public class LoginSignActivity extends BaseActivity {

    private Toolbar toolbarLoginSignUp;
    ImageView back_TB_IV;
    public ImageView title_TB_Img;
    TextView title_TB_TV;
    ProfileData profileData = new ProfileData();
    private Boolean exit = false;
    private String languageType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeLang(store.getLanguage());
        setContentView(R.layout.activity_login_sign);
        profileData = store.getProfileData();
/*

        if (store.getString(Const.LANGUAGE) != null) {
            changeLang(store.getLanguage());
        }
*/

        toolbarLoginSignUp = findViewById(R.id.toolbarLoginSignUp);
        title_TB_Img = findViewById(R.id.title_TB_IV);
        back_TB_IV = findViewById(R.id.back_TB_IV);
        title_TB_TV = findViewById(R.id.title_TB_TV);
        back_TB_IV.setOnClickListener(this);


        gotoLoginSignUpFragment();

    }

    public void setToolbar(boolean showBackIcon, boolean showText, String text) {
        title_TB_Img.setVisibility(showBackIcon ? View.VISIBLE : View.GONE);
       /* title_TB_Img.setVisibility(show ? View.VISIBLE : View.GONE);
        toolbarLoginSignUp.setVisibility(show ? View.VISIBLE : View.GONE);*/
        back_TB_IV.setVisibility(showBackIcon ? View.VISIBLE : View.GONE);
        if (showText) {
            title_TB_TV.setVisibility(View.VISIBLE);
            title_TB_TV.setText(text);
            title_TB_Img.setVisibility(View.GONE);
        } else {
            title_TB_TV.setVisibility(View.GONE);
            title_TB_Img.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.back_TB_IV:

                onBackPressed();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutLogin);
        if (fragment instanceof LoginFragment || fragment instanceof CreateProfileFirstFragment /*|| fragment instanceof CreateProfileSecondFragment*/) {
//            showExit();

            if (exit) {
                finish(); // finish activity
            } else {
                showToast(getResources().getString(R.string.exitBackPressed), false);
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);

            }


        } else if (fragment instanceof SignUpFragment) {
            gotoLoginSignUpFragment();
        } else if (fragment instanceof CreateProfileSecondFragment) {
            setToolbar(false,true,"Create Profile");
            getSupportFragmentManager().popBackStack();
        } else {
            getSupportFragmentManager().popBackStack();
        }


    }

    private void gotoLoginSignUpFragment() {
     /*   Fragment loginFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putString("language", languageType);
        loginFragment.setArguments(bundle);*/
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayoutLogin, new LoginFragment())
                .commit();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
