package com.coachmovecustomer.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coachmovecustomer.R;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.fragments.AddDietFragment;
import com.coachmovecustomer.fragments.DietDetailFragment;
import com.coachmovecustomer.fragments.DietFragment;
import com.coachmovecustomer.fragments.HomeFragment;
import com.coachmovecustomer.fragments.MessageFragment;
import com.coachmovecustomer.fragments.NotificationFragment;
import com.coachmovecustomer.fragments.ProfileFragment;
import com.coachmovecustomer.fragments.ScheduleFragment;
import com.coachmovecustomer.fragments.SettingsFragment;
import com.coachmovecustomer.fragments.WorkoutFragment;
import com.coachmovecustomer.utils.Const;

public class MainActivity extends BaseActivity {

    private LinearLayout profileLL, workoutLL, dietLL, scheduleLL, messageLL, bottom_layout;
    private TextView profileTV, workoutTV, dietTV, scheduleTV, messageTV;
    private ImageView profileIMG, workoutIMG, dietIMG, scheduleIMG, messageIMG;
    private TextView titleTBTV;
    private ImageView title_TBIMG;
    private Toolbar toolbarTB;
    ProfileData profileData = new ProfileData();
    private Boolean exit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new WebView(this).destroy();
        changeLang(store.getLanguage());
        setContentView(R.layout.activity_main);
        profileData = store.getProfileData();

        initToolBar();
        profileIMG = findViewById(R.id.profileIMG);
        workoutIMG = findViewById(R.id.workoutIMG);
        dietIMG = findViewById(R.id.dietIMG);
        scheduleIMG = findViewById(R.id.scheduleIMG);
        messageIMG = findViewById(R.id.messageIMG);
        profileLL = findViewById(R.id.profileLL);
        workoutLL = findViewById(R.id.workoutLL);
        dietLL = findViewById(R.id.dietLL);
        scheduleLL = findViewById(R.id.scheduleLL);
        messageLL = findViewById(R.id.messageLL);
        bottom_layout = findViewById(R.id.bottom_layout);
        profileTV = findViewById(R.id.profileTV);
        workoutTV = findViewById(R.id.workoutTV);
        dietTV = findViewById(R.id.dietTV);
        scheduleTV = findViewById(R.id.scheduleTV);
        messageTV = findViewById(R.id.messageTV);


        profileLL.setOnClickListener(this);
        workoutLL.setOnClickListener(this);
        dietLL.setOnClickListener(this);
        scheduleLL.setOnClickListener(this);
        messageLL.setOnClickListener(this);


        if (getIntent().getBooleanExtra("messageBody", false)) {
            if (store.getProfileData() != null) {
                getNotificationBundleIfExist(getIntent());
            } else {
                startActivity(new Intent(this, LoginSignActivity.class));
                finish();
            }
        } else
            gotoMainFragment(new HomeFragment());
        unseleted();


    }

    private void unseleted() {
        profileIMG.setImageDrawable(getResources().getDrawable(R.drawable.profile_white));
        workoutIMG.setImageDrawable(getResources().getDrawable(R.drawable.workout_white));
        dietIMG.setImageDrawable(getResources().getDrawable(R.drawable.diet_white));
        scheduleIMG.setImageDrawable(getResources().getDrawable(R.drawable.schedule_white));
        messageIMG.setImageDrawable(getResources().getDrawable(R.drawable.chat_white));
        profileTV.setTextColor(getResources().getColor(R.color.White));
        workoutTV.setTextColor(getResources().getColor(R.color.White));
        dietTV.setTextColor(getResources().getColor(R.color.White));
        scheduleTV.setTextColor(getResources().getColor(R.color.White));
        messageTV.setTextColor(getResources().getColor(R.color.White));
    }


    private void initToolBar() {
        toolbarTB = findViewById(R.id.toolbarTB);
        titleTBTV = findViewById(R.id.titleTBTV);
        title_TBIMG = findViewById(R.id.title_TBIMG);
//        titleTBTV.setText(getString(R.string.app_name));
        titleTBTV.setText("");
        setSupportActionBar(toolbarTB);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.home, getTheme());
        toolbarTB.setNavigationIcon(drawable);

    }

    public void setToolbarTitle(String title, boolean b) {
        if (titleTBTV != null) {
            titleTBTV.setText(title);
        }
        if (b) {
            title_TBIMG.setVisibility(View.VISIBLE);
        } else {
            title_TBIMG.setVisibility(View.GONE);
        }

    }


    private void getNotificationBundleIfExist(Intent intent) {
        if (intent.getExtras().getBoolean("messageBody", false)) {
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancelAll();
            Bundle notifyBundle = intent.getExtras().getBundle("tagNotification");
            Fragment fragment = null;
            Bundle bundle = new Bundle();
            String notification_type = intent.getExtras().getString("tag");
            Log.e("notification_type", notifyBundle + "");

            if (notifyBundle.getString("tag").equalsIgnoreCase(Const.CHAT_NOTIFICATION)) {
                Intent chatIntent = new Intent(this, SingleChatActivity.class);
                chatIntent.putExtra("receiverName", notifyBundle.getString("senderName"));
                chatIntent.putExtra("receiverID", notifyBundle.getString("senderId") + "");
                startActivity(chatIntent);
//                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

            } else if (notifyBundle.getString("tag").equalsIgnoreCase(Const.WORKOUT_NOTIFICATION)) {
                fragment = new NotificationFragment();
            } else if (notifyBundle.getString("tag").equalsIgnoreCase(Const.DIET_NOTIFICATION)) {
                fragment = new DietDetailFragment();
                bundle.putString("dietID", notifyBundle.getString("dietId"));
                fragment.setArguments(bundle);

            } else if (notifyBundle.getString("tag").equalsIgnoreCase(Const.WORKOUT_CANCELED)) {
                fragment = new NotificationFragment();
                bundle.putString("tag", notifyBundle.getString("tag"));
                bundle.putString("body", notifyBundle.getString("body"));
                bundle.putString("badge", notifyBundle.getString("badge"));
                bundle.putString("title", notifyBundle.getString("title"));
                bundle.putString("workoutId", notifyBundle.getString("workoutId"));
                fragment.setArguments(bundle);
//                bundle.putAll(notifyBundle);

            } else {
                fragment = new NotificationFragment();
            }
            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutMain, fragment)
                        .commit();
            } else {
                gotoMainFragment(new HomeFragment());
            }
        } else {
            gotoMainFragment(new HomeFragment());
        }

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.profileLL:
                unseleted();
                profileIMG.setImageDrawable(getResources().getDrawable(R.drawable.profile_select));
                profileTV.setTextColor(getResources().getColor(R.color.Black));
                gotoMainFragment(new ProfileFragment());
                break;


            case R.id.workoutLL:
                unseleted();
                workoutIMG.setImageDrawable(getResources().getDrawable(R.drawable.workout_select));
                workoutTV.setTextColor(getResources().getColor(R.color.Black));
                gotoMainFragment(new WorkoutFragment());
                break;

            case R.id.dietLL:
                unseleted();
                dietIMG.setImageDrawable(getResources().getDrawable(R.drawable.groceries_select));
                dietTV.setTextColor(getResources().getColor(R.color.Black));
                gotoMainFragment(new DietFragment());
                break;
            case R.id.scheduleLL:
                unseleted();
                scheduleIMG.setImageDrawable(getResources().getDrawable(R.drawable.schedule_select));
                scheduleTV.setTextColor(getResources().getColor(R.color.Black));
                gotoMainFragment(new ScheduleFragment());

                break;

            case R.id.messageLL:
                unseleted();
                messageIMG.setImageDrawable(getResources().getDrawable(R.drawable.message_select));
                messageTV.setTextColor(getResources().getColor(R.color.Black));
                gotoMainFragment(new MessageFragment());

                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutMain);
        if (fragment instanceof HomeFragment ||
                fragment instanceof ProfileFragment ||
                fragment instanceof DietFragment ||
                fragment instanceof WorkoutFragment ||
                fragment instanceof ScheduleFragment ||
                fragment instanceof MessageFragment ||
                fragment instanceof AddDietFragment) {
            bottom_layout.setVisibility(View.VISIBLE);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.home, getTheme());
                toolbarTB.setNavigationIcon(drawable);
            }

        } else if (fragment instanceof SettingsFragment) {
            bottom_layout.setVisibility(View.GONE);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.home, getTheme());
                toolbarTB.setNavigationIcon(drawable);
            }
        } else {
            bottom_layout.setVisibility(View.GONE);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                log("onOption");
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutMain);

                if (fragment instanceof HomeFragment) {
                } else if (fragment instanceof ProfileFragment ||
                        fragment instanceof DietFragment ||
                        fragment instanceof WorkoutFragment ||
                        fragment instanceof ScheduleFragment ||
                        fragment instanceof MessageFragment ||
                        fragment instanceof SettingsFragment) {

                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.frameLayoutMain, new HomeFragment())
                            .addToBackStack(null).commit();

                    unseleted();
                    //gotoSettings
                } else
                    onBackPressed();
                break;


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutMain);
        //   hideSoftKeyboard();
        if (fragment instanceof HomeFragment) {
//            showExit();
            showExitMethod();
        } else if (fragment instanceof ProfileFragment ||
                fragment instanceof DietFragment ||
                fragment instanceof WorkoutFragment ||
                fragment instanceof ScheduleFragment ||
                fragment instanceof MessageFragment) {
            gotoMainFragment(new HomeFragment());
            unseleted();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                gotoMainFragment(new HomeFragment());
            }
        }
    }


    private void gotoMainFragment(Fragment targetFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayoutMain, targetFragment)
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


    private void showExitMethod() {
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

    }

}
