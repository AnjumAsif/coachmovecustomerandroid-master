package com.coachmovecustomer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.utils.Const;

public class LanguageFragment extends BaseFragment {

    RadioButton radio_english, radio_portuguese;
    Button btn_update;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_language, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.language), false);
        setHasOptionsMenu(false);
        initUI(view);
    }

    private void initUI(View view) {
        radio_english = view.findViewById(R.id.radio_english);
        radio_portuguese = view.findViewById(R.id.radio_portuguese);
        btn_update = view.findViewById(R.id.btn_update);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_update:

                if (radio_english.isChecked()) {
                    intentMethod();
                    baseActivity.store.saveString(Const.LANGUAGE, "en");
                } else  {
                    intentMethod();
                    baseActivity.store.saveString(Const.LANGUAGE, "pt");
                }

                break;
        }
    }

    private void intentMethod() {
        Intent main = new Intent(baseActivity, MainActivity.class);
        startActivity(main);
        baseActivity.finish();

    }
}
