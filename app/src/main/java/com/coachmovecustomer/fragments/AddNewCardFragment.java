package com.coachmovecustomer.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.customviews.ButtonView;
import com.coachmovecustomer.data.Cards;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;

public class AddNewCardFragment extends BaseFragment implements TextWatcher {

    EditText cardHolderET, cardNoET, expiryET, cvvNoET;
    Button addCardBTN;
    private Dialog addCardDialog;
    private String mLastInput;
    ProfileData profileData = new ProfileData();
    int count = 0;


    ArrayList<String> listOfPattern = new ArrayList<String>();
    private String cardValue, cardType;
    private String ptVisa, ptMasterCard, ptAmeExp, ptDinClb, ptDiscover, ptJcb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_card, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.addNewCard), false);
        profileData = baseActivity.store.getProfileData();
        setHasOptionsMenu(false);
        initUI(view);
    }

    private void initUI(View view) {

        cardHolderET = view.findViewById(R.id.cardHolderET);
        cardNoET = view.findViewById(R.id.cardNoET);
        expiryET = view.findViewById(R.id.expiryET);
        cvvNoET = view.findViewById(R.id.cvvNoET);
        addCardBTN = view.findViewById(R.id.addCardBTN);


        cardHolderET.setFilters(baseActivity.setFiltersET(30));
        addCardBTN.setOnClickListener(this);
//        expiryET.setOnClickListener(this);
        cardNoET.addTextChangedListener(this);
        expiryET.addTextChangedListener(this);

        cardNumberPattern();
    }

    private void cardNumberPattern() {
        ptVisa = "^4[0-9]{6,}$";
        listOfPattern.add(ptVisa);
        ptMasterCard = "^5[1-5][0-9]{5,}$";
        listOfPattern.add(ptMasterCard);
        ptAmeExp = "^3[47][0-9]{5,}$";
        listOfPattern.add(ptAmeExp);
        ptDinClb = "^3(?:0[0-5]|[68][0-9])[0-9]{4,}$";
        listOfPattern.add(ptDinClb);
        ptDiscover = "^6(?:011|5[0-9]{2})[0-9]{3,}$";
        listOfPattern.add(ptDiscover);
        ptJcb = "^(?:2131|1800|35[0-9]{3})[0-9]{3,}$";
        listOfPattern.add(ptJcb);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.addCardBTN:
                if (cardHolderET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertCardHolderName));
                } else if (cardNoET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertCardNumber));
                } else if (count < 14 || count > 19) {
                    showToast(getResources().getString(R.string.completeCardNo), false);
                } else if (expiryET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertExpiryDate));
                } else if (expiryET.getText().toString().trim().length() != 5) {
                    showToast(getResources().getString(R.string.alertValidExpiryDate));
                }else if (!expiryET.getText().toString().contains("/")) {
                    showToast(getResources().getString(R.string.invalidExpiry));
                }
                else if (cvvNoET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertCvvNumber));
                } else {
                    appCardApi();
                }
                break;

          /*  case R.id.expiryET:
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                if (monthOfYear + 1 > 9) {
                                    expiryET.setText((monthOfYear + 1) + "/" + String.valueOf(year).substring(2) + "");
                                } else {
                                    expiryET.setText("0" + (monthOfYear + 1) + "/" + String.valueOf(year).substring(2) + "");
                                }

                                Log.e("year", expiryET.getText().toString().trim());

                            }
                        }, mYear, mMonth, mDay);
                ((ViewGroup) datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

                break;*/
        }
    }

    private void appCardApi() {

        String cardNO = cardNoET.getText().toString().replace(" ", "");
        HashMap<String, String> jsonbody = new HashMap<String, String>();
        jsonbody.put("name", cardHolderET.getText().toString().trim());
        jsonbody.put("cardNo", cardNO);
        jsonbody.put("expiryDate", expiryET.getText().toString().trim());
        jsonbody.put("cvv", cvvNoET.getText().toString().trim());
        jsonbody.put("type", cardType);
        Call<JsonObject> addCardCall = baseActivity.apiInterface.postAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.ADD_GET_CARD + profileData.id + "/cards", jsonbody);
        baseActivity.apiHitAndHandle.makeApiCall(addCardCall, this);

        baseActivity.startProgressDialog();

    }

    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        try {
            JSONObject jsonObject = new JSONObject(object.toString());
            JSONObject data = jsonObject.optJSONObject("data");
            JSONObject card = data.optJSONObject("card");

            showForgotDialog();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private int mLength;

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        mLength = expiryET.getText().length();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        expiryET.setError(null);
    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (editable == expiryET.getEditableText()) {
            String input = editable.toString();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/yy", Locale.US);
            Calendar expiryDateDate = Calendar.getInstance();
            try {
                expiryDateDate.setTime(formatter.parse(input));
            } catch (java.text.ParseException e) {
                if (editable.length() == 2 && !mLastInput.endsWith("/")) {
                    int month = Integer.parseInt(input);
                    if (month <= 12) {
                        expiryET.setText(expiryET.getText().toString() + "/");
                        expiryET.setSelection(expiryET.getText().toString().length());
                    }
                } else if (editable.length() == 2 && mLastInput.endsWith("/")) {
                    int month = Integer.parseInt(input);
                    if (month <= 12) {
                        expiryET.setText(expiryET.getText().toString().substring(0, 1));
                        expiryET.setSelection(expiryET.getText().toString().length());
                    } else {
                        expiryET.setText("");
                        expiryET.setSelection(expiryET.getText().toString().length());
                        showToast("Enter a valid month");
                    }
                } else if (editable.length() == 1) {
                    int month = Integer.parseInt(input);
                    if (month > 1) {
                        expiryET.setText("0" + expiryET.getText().toString() + "/");
                        expiryET.setSelection(expiryET.getText().toString().length());
                    }
                } else {

                }
                mLastInput = expiryET.getText().toString();


                return;
            }
        } else if (editable == cardNoET.getEditableText()) {

            if (count <= cardNoET.getText().toString().length()
                    && (cardNoET.getText().toString().length() == 4
                    || cardNoET.getText().toString().length() == 9
                    || cardNoET.getText().toString().length() == 14)) {
                cardNoET.setText(cardNoET.getText().toString() + " ");
                int pos = cardNoET.getText().length();
                cardNoET.setSelection(pos);

            } else if (count >= cardNoET.getText().toString().length()
                    && (cardNoET.getText().toString().length() == 4
                    || cardNoET.getText().toString().length() == 9
                    || cardNoET.getText().toString().length() == 14)) {
                cardNoET.setText(cardNoET.getText().toString().substring(0, cardNoET.getText().toString().length() - 1));
                int pos = cardNoET.getText().length();
                cardNoET.setSelection(pos);
            }
            count = cardNoET.getText().toString().length();

            if (count < 14 || count > 19) {
            } else {
                for (int i = 0; i < listOfPattern.size(); i++) {
                    if (cardNoET.getText().toString().replace(" ", "").matches(listOfPattern.get(i))) {
                        cardValue = String.valueOf(i);

                        if (cardValue.equals("0")) {
                            cardType = "Visa";
                        } else if (cardValue.equals("1")) {
                            cardType = "MasterCard";
                        } else if (cardValue.equals("2")) {
                            cardType = "AmericanExpress";
                        } else if (cardValue.equals("3")) {
                            cardType = "DinersClub";
                        } else if (cardValue.equals("4")) {
                            cardType = "Discover";
                        } else if (cardValue.equals("5")) {
                            cardType = "JCB";
                        }
                    }

                }
            }

        }

    }

    private void showForgotDialog() {
        addCardDialog = new Dialog(baseActivity);
        addCardDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflate = (LayoutInflater) baseActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(R.layout.card_added_popup, null);
        addCardDialog.setContentView(view);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(addCardDialog.getWindow().getAttributes());
        addCardDialog.setCancelable(true);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        addCardDialog.getWindow().setAttributes(lp);
        Button doneIV = addCardDialog.findViewById(R.id.doneBTN);
        doneIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addCardDialog.dismiss();
                baseActivity.onBackPressed();
              /*  cardNoET.setText("");
                expiryET.setText("");
                cvvNoET.setText("");*/


            }
        });

        addCardDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.trans_black)));
        addCardDialog.show();
    }


}
