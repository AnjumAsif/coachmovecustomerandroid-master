package com.coachmovecustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.data.AddDietData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;

public class AddDietFragment extends BaseFragment {

    EditText emailET, priceET, routineET, dietET, alimentationET, likeEatET, dontLikeET,
            allergiesET, diabetesET, cholesterolET, digestiveET, thyroidET, pregencyET, othersET,
            supplementsET, disorderET;

    Button submit_btn;
    Call<JsonObject> addDietCall;
    ProfileData profileData = new ProfileData();
    String priceStr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_diet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.diet), false);
        profileData = baseActivity.store.getProfileData();

        setHasOptionsMenu(true);
        initUI(view);
    }

    private void initUI(View view) {

        emailET = view.findViewById(R.id.emailET);
        priceET = view.findViewById(R.id.priceET);
        routineET = view.findViewById(R.id.routineET);
        dietET = view.findViewById(R.id.dietET);
        alimentationET = view.findViewById(R.id.alimentationET);
        likeEatET = view.findViewById(R.id.likeEatET);
        dontLikeET = view.findViewById(R.id.dontLikeET);
        disorderET = view.findViewById(R.id.disorderET);
        allergiesET = view.findViewById(R.id.allergiesET);
        diabetesET = view.findViewById(R.id.diabetesET);
        cholesterolET = view.findViewById(R.id.cholesterolET);
        digestiveET = view.findViewById(R.id.digestiveET);
        thyroidET = view.findViewById(R.id.thyroidET);
        pregencyET = view.findViewById(R.id.pregencyET);
        othersET = view.findViewById(R.id.othersET);
        supplementsET = view.findViewById(R.id.supplementsET);
        submit_btn = view.findViewById(R.id.submit_btn);


        emailET.setFilters(baseActivity.setFiltersET(30));
        routineET.setFilters(baseActivity.setFiltersET(150));
        dietET.setFilters(baseActivity.setFiltersET(150));
        alimentationET.setFilters(baseActivity.setFiltersET(150));
        likeEatET.setFilters(baseActivity.setFiltersET(150));
        dontLikeET.setFilters(baseActivity.setFiltersET(150));
        disorderET.setFilters(baseActivity.setFiltersET(150));
        allergiesET.setFilters(baseActivity.setFiltersET(150));
        diabetesET.setFilters(baseActivity.setFiltersET(150));
        cholesterolET.setFilters(baseActivity.setFiltersET(150));
        digestiveET.setFilters(baseActivity.setFiltersET(150));
        thyroidET.setFilters(baseActivity.setFiltersET(150));
        pregencyET.setFilters(baseActivity.setFiltersET(150));
        othersET.setFilters(baseActivity.setFiltersET(150));


        if (profileData.email != null)
            emailET.setText(profileData.email);
        submit_btn.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPriceApi();

    }

    private void getPriceApi() {
        Call<JsonObject> nearbyCoach = baseActivity.apiInterface.getAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.NUTRITIONIST_PRICE_API);
        baseActivity.apiHitAndHandle.makeApiCall(nearbyCoach, this);
        baseActivity.startProgressDialog();
    }

    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        try {
            JSONObject jsonObject = new JSONObject(resp);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject modality = data.getJSONObject("modality");

            priceET.setText("R$ " + modality.getString("price"));
            priceStr = modality.getString("price");

        } catch (Exception e) {

        }

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.submit_btn:

                if (emailET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alert_email), false);
                } else if (!baseActivity.isValidMail(emailET.getText().toString().trim())) {
                    showToast(getResources().getString(R.string.valid_email), false);
                }else if (dietET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertObjective), false);
                }

                /*else if (routineET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertExercise), false);
                } else if (dietET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertObjective), false);
                } else if (alimentationET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertExplainDay), false);
                } else if (likeEatET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertLikeEat), false);
                } else if (dontLikeET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertDontLikeEat), false);
                } else if (disorderET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertDisorder), false);
                } else if (allergiesET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertAllergies), false);
                } else if (diabetesET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertDiabetes), false);
                } else if (cholesterolET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertCholesterol), false);
                } else if (digestiveET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertDigestive), false);
                } else if (thyroidET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertThyroid), false);
                } else if (pregencyET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertPregency), false);
                } else if (othersET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertOthers), false);
                } else if (supplementsET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertSupplements), false);
                }*/ else {
//                    addDietApi();
                    addButtonMethod();
                }
                break;


        }
    }

    private void addButtonMethod() {
        Fragment fragmentGet = new NutritionistFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("DietData", new AddDietData(emailET.getText().toString(), priceStr, routineET.getText().toString(), dietET.getText().toString(), alimentationET.getText().toString(), likeEatET.getText().toString(), dontLikeET.getText().toString(), disorderET.getText().toString(), allergiesET.getText().toString(), diabetesET.getText().toString(), cholesterolET.getText().toString(), digestiveET.getText().toString(), thyroidET.getText().toString(), pregencyET.getText().toString(), othersET.getText().toString(), supplementsET.getText().toString()));
//        bundle.putParcelable("DietData", new AddDietData("emailET", "10", "routineET", "dietET", "alimentationET", "likeEatET", "dontLikeET", "disorderET", "allergiesET", "diabetesET", "cholesterolET", "digestiveET", "", "pregencyET", "othersET", "supplementsET"));
        fragmentGet.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.frameLayoutMain, fragmentGet)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.settings, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayoutMain, new SettingsFragment()).addToBackStack(null).commit();
                break;
        }
        return false;
    }

}