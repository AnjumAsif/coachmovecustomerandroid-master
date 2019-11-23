package com.wm.onboarder;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.wm.onboardingactivity.R;

public class OnBoarderFragment extends Fragment {

    private static final String ONBOARDER_PAGE_TITLE = "onboarder_page_title";
    private static final String ONBOARDER_PAGE_TITLE_RES_ID = "onboarder_page_title_res_id";
    private static final String ONBOARDER_PAGE_TITLE_COLOR = "onboarder_page_title_color";
    private static final String ONBOARDER_PAGE_TITLE_TEXT_SIZE = "onboarder_page_title_text_size";
    private static final String ONBOARDER_PAGE_DESCRIPTION = "onboarder_page_description";
    private static final String ONBOARDER_PAGE_DESCRIPTION_RES_ID = "onboarder_page_description_res_id";
    private static final String ONBOARDER_PAGE_DESCRIPTION_COLOR = "onborader_page_description_color";
    private static final String ONBOARDER_PAGE_DESCRIPTION_TEXT_SIZE = "onboarder_page_description_text_size";
    private static final String ONBOARDER_PAGE_DESCRIPTION_CENTERED = "onboarder_page_description_centered";
    private static final String ONBOARDER_PAGE_IMAGE_RES_ID = "onboarder_page_iamge_res_id";

    private String onboarderTitle;
    private String onboarderDescription;
    @StringRes
    private int onboarderTitleResId;
    @ColorRes
    private int onboarderTitleColor;
    @StringRes
    private int onboarderDescriptionResId;
    @ColorRes
    private int onboarderDescriptionColor;
    @DrawableRes
    private int onboarderImageResId;
    private float onboarderTitleTextSize;
    private float onboarderDescriptionTextSize;
    private boolean onboarderDescriptionTextCentered;

    private View onboarderView;
    private ImageView ivOnboarderImage;
    private TextView tvOnboarderTitle;
    private TextView tvOnboarderDescription;


    public OnBoarderFragment() {
    }


    static OnBoarderFragment newInstance(OnboarderPage page) {
        Bundle args = new Bundle();
        args.putString(ONBOARDER_PAGE_TITLE, page.getTitle());
        args.putString(ONBOARDER_PAGE_DESCRIPTION, page.getDescription());
        args.putInt(ONBOARDER_PAGE_TITLE_RES_ID, page.getTitleResourceId());
        args.putInt(ONBOARDER_PAGE_DESCRIPTION_RES_ID, page.getDescriptionResourceId());
        args.putInt(ONBOARDER_PAGE_TITLE_COLOR, page.getTitleColor());
        args.putInt(ONBOARDER_PAGE_DESCRIPTION_COLOR, page.getDescriptionColor());
        args.putInt(ONBOARDER_PAGE_IMAGE_RES_ID, page.getImageResourceId());
        args.putFloat(ONBOARDER_PAGE_TITLE_TEXT_SIZE, page.getTitleTextSize());
        args.putFloat(ONBOARDER_PAGE_DESCRIPTION_TEXT_SIZE, page.getDescriptionTextSize());
        args.putBoolean(ONBOARDER_PAGE_DESCRIPTION_CENTERED, page.isMultilineDescriptionCentered());
        OnBoarderFragment fragment = new OnBoarderFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        onboarderTitle = bundle.getString(ONBOARDER_PAGE_TITLE, null);
        onboarderTitleResId = bundle.getInt(ONBOARDER_PAGE_TITLE_RES_ID, 0);
        onboarderTitleColor = bundle.getInt(ONBOARDER_PAGE_TITLE_COLOR, 0);
        onboarderTitleTextSize = bundle.getFloat(ONBOARDER_PAGE_TITLE_TEXT_SIZE, 0f);
        onboarderDescription = bundle.getString(ONBOARDER_PAGE_DESCRIPTION, null);
        onboarderDescriptionResId = bundle.getInt(ONBOARDER_PAGE_DESCRIPTION_RES_ID, 0);
        onboarderDescriptionColor = bundle.getInt(ONBOARDER_PAGE_DESCRIPTION_COLOR, 0);
        onboarderDescriptionTextSize = bundle.getFloat(ONBOARDER_PAGE_DESCRIPTION_TEXT_SIZE, 0f);
        onboarderDescriptionTextCentered = bundle.getBoolean(ONBOARDER_PAGE_DESCRIPTION_CENTERED, false);
        onboarderImageResId = bundle.getInt(ONBOARDER_PAGE_IMAGE_RES_ID, 0);

        onboarderView = inflater.inflate(R.layout.fragment_onboarder, container, false);
        /*hide for show only textual view*/
//        ivOnboarderImage = onboarderView.findViewById(R.id.iv_onboarder_image);
        tvOnboarderTitle = onboarderView.findViewById(R.id.tv_onboarder_title);
        tvOnboarderDescription = onboarderView.findViewById(R.id.tv_onboarder_description);

        if (onboarderTitle != null) tvOnboarderTitle.setText(onboarderTitle);

        if (onboarderTitleResId != 0) tvOnboarderTitle.setText(getResources().getString(onboarderTitleResId));

        if (onboarderDescription != null) tvOnboarderDescription.setText(onboarderDescription);

        if (onboarderDescriptionResId != 0)
            tvOnboarderDescription.setText(getResources().getString(onboarderDescriptionResId));

        if (onboarderTitleColor != 0)
            tvOnboarderTitle.setTextColor(ContextCompat.getColor(getActivity(), onboarderTitleColor));

        if (onboarderDescriptionColor != 0) {
            tvOnboarderDescription.setTextColor(ContextCompat.getColor(getActivity(), onboarderDescriptionColor));
        }

       /* if (onboarderImageResId != 0)
            ivOnboarderImage.setImageDrawable(AppCompatResources.getDrawable(getActivity(), onboarderImageResId));
*/
        if (onboarderTitleTextSize != 0f) tvOnboarderTitle.setTextSize(onboarderTitleTextSize);

        if (onboarderDescriptionTextSize != 0f) tvOnboarderDescription.setTextSize(onboarderDescriptionTextSize);

        return onboarderView;
    }


    @Override
    public void onResume() {
        super.onResume();

        tvOnboarderDescription.post(new Runnable() {
            @Override
            public void run() {
                if (onboarderDescriptionTextCentered) {
                    tvOnboarderDescription.setGravity(Gravity.CENTER);
                } else
                    tvOnboarderDescription.setGravity(tvOnboarderDescription.getLineCount() > 1 ? Gravity.START : Gravity.CENTER);
            }
        });

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
