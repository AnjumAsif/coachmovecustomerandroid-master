package com.coachmovecustomer.activity;

import android.content.Intent;
import android.os.Bundle;

import com.coachmovecustomer.R;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.PrefStore;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends FancyWalkthroughActivity {
    PrefStore mPrefStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefStore = new PrefStore(this);
        FancyWalkthroughCard fancyWalkThroughCard1 =
                new FancyWalkthroughCard("Duration",
                        "The duration of all modalities is 50 minute.",
                        R.drawable.co_logo);
        FancyWalkthroughCard fancyWalkThroughCard2 =
                new FancyWalkthroughCard("Client Number",
                        "The maximum number of clients per class is 1 for Nutrition and 6 for Physical activity.",
                        R.drawable.co_logo);
        FancyWalkthroughCard fancyWalkThroughCard3 =
                new FancyWalkthroughCard("Hire Coach",
                        "It is forbidden to hire Coaches outside the App, Coaches may be held liable for.",
                        R.drawable.co_logo);
        FancyWalkthroughCard fancyWalkThroughCard4 =
                new FancyWalkthroughCard("Awareness",
                        "By clicking here you are aware of your health and consider yourself fit for the activities you choose.",
                        R.drawable.co_logo);
        FancyWalkthroughCard fancyWalkThroughCard5 =
                new FancyWalkthroughCard("Opening Hours Rule",
                        "Check the opening hours of the training site and if you will train in gyms if no fees will be charged , if they are charged it is the customer's responsibility to pay",
                        R.drawable.co_logo);
        FancyWalkthroughCard fancyWalkThroughCard6 =
                new FancyWalkthroughCard("Plan Consultation",
                        "The amount charged for the nutritional consultation is included 50 face-to-face minutes for anamnesis and the sending of a meal plan. ",
                        R.drawable.co_logo);

        fancyWalkThroughCard1.setBackgroundColor(R.color.white);
        fancyWalkThroughCard1.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancyWalkThroughCard2.setBackgroundColor(R.color.white);
        fancyWalkThroughCard2.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancyWalkThroughCard3.setBackgroundColor(R.color.white);
        fancyWalkThroughCard3.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancyWalkThroughCard4.setBackgroundColor(R.color.white);
        fancyWalkThroughCard4.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancyWalkThroughCard5.setBackgroundColor(R.color.white);
        fancyWalkThroughCard5.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        fancyWalkThroughCard6.setBackgroundColor(R.color.white);
        fancyWalkThroughCard6.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        //
        fancyWalkThroughCard1.setTitleTextSize(16f);
        fancyWalkThroughCard1.setDescriptionTextSize(12f);


        List<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(fancyWalkThroughCard1);
        pages.add(fancyWalkThroughCard2);
        pages.add(fancyWalkThroughCard3);
        pages.add(fancyWalkThroughCard4);
        pages.add(fancyWalkThroughCard5);
        pages.add(fancyWalkThroughCard6);

        for (FancyWalkthroughCard page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.black);
        }
        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setColorBackground(R.color.colorPrimary);
        //setImageBackground(R.drawable.restaurant);
        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.colorPrimary);
        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        mPrefStore.saveString(Const.FIRST_TIME_VISIT, "1");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
