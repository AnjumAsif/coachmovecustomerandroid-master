package com.coachmovecustomer.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.adapters.AddedPeopleAdapter;
import com.coachmovecustomer.adapters.GenderAdapter;
import com.coachmovecustomer.customDialog.NeighbourhoodSelectDialog;
import com.coachmovecustomer.data.AddModalitiesData;
import com.coachmovecustomer.data.AddedPeopleData;
import com.coachmovecustomer.data.NeighbourhoodData;
import com.coachmovecustomer.data.PeopleForAddData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.data.SearchWorkoutData;
import com.coachmovecustomer.myInterface.CollageDialogCloseListener;
import com.coachmovecustomer.myInterface.onClickDelete;
import com.coachmovecustomer.utils.Const;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

public class WorkoutFragment extends BaseFragment implements CollageDialogCloseListener {

    CircleImageView profileImg;

    TextView addPeopleTV, userName_TV;
    String[] gender_array;
    Spinner genderSP, modalitySP;
    Button searchBTN,buttonApply;
    RecyclerView addPeopleRV;

    //        SingleDateAndTimePicker nowDatePicker;
    NumberPickerView nowDatePicker;

    EditText dateEDT, timeEDT, addressEDT, neighbourhoodEDT, peopleSP,refferalCode;
    //    ArrayList<AddModalitiesData> selectModalityList = new ArrayList<>();
    ArrayList<String> selectModalityList = new ArrayList<>();
    AddedPeopleAdapter addedPeopleAdapter;


    private ArrayList<PeopleForAddData> selectedPeopleDataList = new ArrayList<>();
    String selected_gender = "", selectModality = "", selectNeighbourhood = "";
    private ArrayList<NeighbourhoodData> neighbourhoodLists = new ArrayList<>();
    String neighbourhood, modality;
    private Call<JsonObject> getModalityCall;
    private AddModalitiesData modalitiesData;

    private ArrayList<PeopleForAddData> peopleDialog = new ArrayList<>();
    private String dateIn12Hour = "";
    private ArrayAdapter adapter;
    String selectedTime;
    private int product;
    private String modalityPrice;

    AddedPeopleData addedPeopleData;


    private ArrayList<AddModalitiesData> allModalitiesData = new ArrayList<>();
    private String modalityID;
    private ProfileData profileData = new ProfileData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.workout), false);
        setHasOptionsMenu(true);
        profileData = baseActivity.store.getProfileData();
        initUI(view);
    }

    private void initUI(View view) {

        addPeopleTV = view.findViewById(R.id.addPeopleTV);
        dateEDT = view.findViewById(R.id.dateEDT);
        timeEDT = view.findViewById(R.id.timeEDT);
        addressEDT = view.findViewById(R.id.addressEDT);
        genderSP = view.findViewById(R.id.genderSP);
        neighbourhoodEDT = view.findViewById(R.id.neighbourhoodEDT);
        modalitySP = view.findViewById(R.id.modalitySP);
        peopleSP = view.findViewById(R.id.peopleSP);
        searchBTN = view.findViewById(R.id.searchBTN);
        buttonApply = view.findViewById(R.id.buttonApply);
        refferalCode = view.findViewById(R.id.refferalCode);
        addPeopleRV = view.findViewById(R.id.addPeopleRV);


        profileImg = view.findViewById(R.id.profileImg);
        userName_TV = view.findViewById(R.id.userName_TV);


        userName_TV.setText(profileData.firstName);
        if (profileData.profilePicPath != null && !profileData.profilePicPath.isEmpty()) {
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + profileData.profilePicPath
                    )
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.user_dummy)
                            .error(R.drawable.placeholder))
                    .into(profileImg);
        }

        addressEDT.setFilters(baseActivity.setFiltersET(100));

        spinnerOptionData();

        genderSP.setOnItemSelectedListener(this);
        modalitySP.setOnItemSelectedListener(this);
        dateEDT.setOnClickListener(this);
        neighbourhoodEDT.setOnClickListener(this);
        searchBTN.setOnClickListener(this);
        addPeopleTV.setOnClickListener(this);
        timeEDT.setOnClickListener(this);
        buttonApply.setOnClickListener(this);


        GenderAdapter genderAdapter = new GenderAdapter(baseActivity, gender_array);
        genderSP.setAdapter(genderAdapter);
    }

    private void spinnerOptionData() {
        String selectGender = getString(R.string.selectGender);
        String male = getString(R.string.male);
        String female = getString(R.string.female);
        gender_array = new String[3];
        gender_array[0] = selectGender;
        gender_array[1] = male;
        gender_array[2] = female;


    }


    @Override
    public void onResume() {
        super.onResume();
        getModalityApi();
        setAddPeopleAdapter(selectedPeopleDataList);
    }

    private void getModalityApi() {
        try {
            getModalityCall = baseActivity.apiInterface.getAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.GET_MODALITIES);
            baseActivity.apiHitAndHandle.makeApiCall(getModalityCall, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAddPeopleAdapter(ArrayList<PeopleForAddData> peopleDataList) {
        this.selectedPeopleDataList = peopleDataList;


        addedPeopleAdapter = new AddedPeopleAdapter(baseActivity, this, selectedPeopleDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        addPeopleRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        addPeopleRV.setItemAnimator(new DefaultItemAnimator());
        addPeopleRV.setAdapter(addedPeopleAdapter);


        try {
            product = (int) (allModalitiesData.get(modalitySP.getSelectedItemPosition()).price * +selectedPeopleDataList.size() + allModalitiesData.get(modalitySP.getSelectedItemPosition()).price * 1);
            Log.e("total", product + "");
            if (product == 0) {
//                peopleSP.setText(getResources().getString(R.string.noPeople));

                peopleSP.setText(getResources().getString(R.string.person) + product + ",00");
                ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.workout), false);
            } else {
//                peopleSP.setText(String.format("%d Person: $ %d,00 ", selectedPeopleDataList.size(), product));
                peopleSP.setText(String.format("%d %s%d,00", selectedPeopleDataList.size() + 1, getResources().getString(R.string.persons), product));

                ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.workout), false);
            }

        } catch (Exception e) {

        }


        onClickRecycler();
    }


    @Override
    public void onSuccess(Call call, Object object, String resp) {
        baseActivity.stopProgressDialog();
        if (call == getModalityCall) {
            try {
                JSONObject jsonObject = new JSONObject(object.toString());
                JSONObject data = jsonObject.getJSONObject("data");
                selectModalityList.clear();
                JSONArray modalities = data.getJSONArray("modalities");
                for (int i = 0; i < modalities.length(); i++) {
                    Log.e("jsonObject", modalities.get(i).toString() + "");
                    modalitiesData = new Gson().fromJson(modalities.get(i).toString(), AddModalitiesData.class);
//                    selectModalityList.add("" + modalitiesData.modality);
//                    allModalitiesData.add(modalitiesData);


                 /*   if (baseActivity.store.getLanguage() == "en") {
                        log(baseActivity.store.getLanguage());
                        selectModalityList.add("" + modalitiesData.modality);
                    } else {
                        selectModalityList.add("" + modalitiesData.modalityBr);
                    }
                    */
                    selectModalityList.add("" + modalitiesData.modalityBr);

                    allModalitiesData.add(modalitiesData);


                }
                setModalityAdapter(selectModalityList);

            } catch (Exception e) {

            }
        }

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.searchBTN:


                if (dateEDT.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertDate), false);
                } else if (dateIn12Hour.length() == 0) {
                    showToast(getResources().getString(R.string.alertTime), false);
                } else if (neighbourhoodEDT.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertNeighbourhood), false);
                } else if (addressEDT.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertAddress));
                }/*else if (selectModalityList.isEmpty()) {
                    showToast(getResources().getString(R.string.selectModality), false);
                }*/ else if (selectModality.equals(getResources().getString(R.string.selectModality))) {
                    showToast(getResources().getString(R.string.selectModality), false);
                }/* else if (selected_gender.equals(getResources().getString(R.string.selectGender))) {
                    showToast(getResources().getString(R.string.selectGender), false);
                } */ else {
                    searchCoachesMethod();
                }
                break;
            case R.id.buttonApply:
                if (TextUtils.isEmpty(refferalCode.getText().toString()))
                    Toast.makeText(baseActivity, "Referal code can't empty.", Toast.LENGTH_SHORT).show();
                else if (refferalCode.getText().toString().length()<6){
                    Toast.makeText(baseActivity, "Invalid referal code.", Toast.LENGTH_SHORT).show();
                    //code for referal code
                }
                else {

                }
                break;

            case R.id.addPeopleTV:
                AddPeopleFragment alertDialogFragment1 = AddPeopleFragment.newInstance(this, peopleDialog, selectedPeopleDataList);
                alertDialogFragment1.show(baseActivity.getFragmentManager(), "arc");
                break;

            case R.id.dateEDT:
                getDate();
                break;

            case R.id.timeEDT:
//                showHourPicker();

                newTimePicker();
                break;

            case R.id.neighbourhoodEDT:
                neighbourhoodLists = new ArrayList<>();
                NeighbourhoodSelectDialog alertDialogFragment = NeighbourhoodSelectDialog.newInstance(this, neighbourhoodLists);
                alertDialogFragment.show(baseActivity.getFragmentManager(), "arc");
                break;
        }
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

    @Override
    public void handelDialogClose(DialogInterface dialog, String ids, String neighbourhoodID) {
        neighbourhoodEDT.setText(ids);
        neighbourhood = neighbourhoodID;
        Log.e("neighbourhood", neighbourhood);
    }


    private void getDate() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(baseActivity, R.style.DatePickerDialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String startTime = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
                        Date dateValue = null;
                        try {
                            dateValue = input.parse(startTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        SimpleDateFormat output = new SimpleDateFormat("dd MMM, yyyy");
                        Log.e("date ", output.format(dateValue) + " real date " + startTime);
                        dateEDT.setText(output.format(dateValue));

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }


    public void showHourPicker() {
        final Calendar myCalender = Calendar.getInstance();
        final int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);


        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String AM_PM;

                if (view.isShown()) {
                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalender.set(Calendar.MINUTE, minute);

                    if (hourOfDay < 12) {
                        AM_PM = " AM";
                        if (hourOfDay == 0) {
                            selectedTime = "12 AM";
                        } else {
                            selectedTime = hourOfDay + AM_PM;
                        }
                    } else if (hourOfDay >= 13 && hourOfDay < 24) {
                        AM_PM = " PM";
                        hourOfDay = hourOfDay - 12;
                        selectedTime = hourOfDay + AM_PM;
                    } else {
                        selectedTime = "12 PM";
                    }
                    timeEDT.setText(selectedTime);
                    dateIn12Hour = new SimpleDateFormat("HH").format(myCalender.getTimeInMillis());

                }

            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, 00, false);
        timePickerDialog.setTitle(getResources().getString(R.string.selectTime));
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        if (parent.getId() == R.id.genderSP) {
            selected_gender = gender_array[position];
            Log.e("selected_gender", selected_gender + "");
        }
        if (parent.getId() == R.id.modalitySP) {
            product = (int) (allModalitiesData.get(modalitySP.getSelectedItemPosition()).price * +selectedPeopleDataList.size() + allModalitiesData.get(modalitySP.getSelectedItemPosition()).price * 1);
            if (selectedPeopleDataList.size() == 0) {
//                peopleSP.setText(getResources().getString(R.string.noPeople));
                peopleSP.setText(getResources().getString(R.string.person) + product + ",00");

            } else {
                peopleSP.setText(String.format("%d %s%d,00", selectedPeopleDataList.size() + 1, getResources().getString(R.string.persons), product));
            }

        }
    }


    private void setModalityAdapter(ArrayList<String> modality) {
        adapter = new ArrayAdapter(baseActivity, R.layout.genderadapter, modality);
        adapter.setDropDownViewResource(R.layout.genderadapter);
        modalitySP.setAdapter(adapter);

    }

    private void onClickRecycler() {
        addedPeopleAdapter.setOnNewClick(new onClickDelete() {
            @Override
            public void onClick(Object obj, int pos) {
                selectedPeopleDataList.remove(pos);
                addedPeopleAdapter.notifyDataSetChanged();

                product = (int) (allModalitiesData.get(modalitySP.getSelectedItemPosition()).price * +selectedPeopleDataList.size() + allModalitiesData.get(modalitySP.getSelectedItemPosition()).price * 1);
                if (selectedPeopleDataList.size() == 0) {
                    peopleSP.setText(getResources().getString(R.string.person) + product + ",00");

                } else {
                    peopleSP.setText(String.format("%d %s%d,00", selectedPeopleDataList.size() + 1, getResources().getString(R.string.persons), product));
                }
            }
        });
    }


    private void searchCoachesMethod() {
        String date = baseActivity.convertDateFormatLocale(dateEDT.getText().toString(), "dd MMM, yyyy", "yyyy-MM-dd");
        String time = dateIn12Hour + ":00:00";

        if (allModalitiesData != null || !allModalitiesData.isEmpty()) {
            modalityID = allModalitiesData.get(modalitySP.getSelectedItemPosition()).id + "";
        }
       /* if (product == 0) {
            modalityPrice = String.valueOf(allModalitiesData.get(modalitySP.getSelectedItemPosition()).price);
        } else {
            modalityPrice = String.valueOf(allModalitiesData.get(modalitySP.getSelectedItemPosition()).price + product);
        }*/
        String gender;

        if (selected_gender.equalsIgnoreCase(getResources().getString(R.string.selectGender))) {
            gender = "";
        } else if (selected_gender.equalsIgnoreCase(getResources().getString(R.string.male))) {
            gender = "M";
        } else {
            gender = "F";
        }


        Fragment fragmentGet = new NearbyCoachFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("SearchCoaches", new SearchWorkoutData(date, time, neighbourhood, addressEDT.getText().toString().trim(), modalityID, gender, product + ""));
        bundle.putParcelableArrayList("workoutUser", selectedPeopleDataList);
        fragmentGet.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.frameLayoutMain, fragmentGet)
                .addToBackStack(null)
                .commit();


        baseActivity.log(date + "\n" + time
                + "\n" + neighbourhood + "\n" + addressEDT.getText().toString().trim() + "\n" + modalityID + "\n" + gender + "\n" + product + "");

    }


    private void newTimePicker() {

        TextView cancel_tv, done_tv;
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(baseActivity);
        bottomSheetDialog.setContentView(R.layout.testing_add_people);
        cancel_tv = bottomSheetDialog.findViewById(R.id.cancel_tv);
        done_tv = bottomSheetDialog.findViewById(R.id.done_tv);
        nowDatePicker = bottomSheetDialog.findViewById(R.id.nowDatePicker);
        bottomSheetDialog.setCanceledOnTouchOutside(false);


        nowDatePicker.setOnValueChangedListener(new NumberPickerView.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {
                Log.e("value selected change", picker.getContentByCurrValue());
                selectedTime = picker.getContentByCurrValue();
                int newTime = Integer.parseInt(selectedTime);
                String AM_PM;

                if (newTime < 12) {
                    AM_PM = " AM";
                    if (newTime == 0) {
                        selectedTime = "12 AM";
                    } else {
                        selectedTime = newTime + AM_PM;
                    }
                    dateIn12Hour = selectedTime.replace(" AM", "");
                } else if (newTime >= 13 && newTime < 24) {
                    AM_PM = " PM";
                    newTime = newTime - 12;
                    selectedTime = newTime + AM_PM;
                    dateIn12Hour = selectedTime.replace(" PM", "");
                } else {
                    selectedTime = "12 PM";
                    dateIn12Hour = selectedTime.replace(" PM", "");
                }
                timeEDT.setText(selectedTime);
                if (Integer.parseInt(dateIn12Hour) < 10)
                    dateIn12Hour = "0" + dateIn12Hour;
                else {
                    if (selectedTime.contains(" PM"))
                        dateIn12Hour = selectedTime.replace(" PM", "");
                    else
                        dateIn12Hour = selectedTime.replace(" AM", "");
                }
                Log.e("final date", dateIn12Hour);

            }
        });


//        nowDatePicker.setIsAmPm(false);
        /*nowDatePicker.addOnDateChangedListener(new SingleDateAndTimePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(String displayed, Date date) {

                selectedTime = baseActivity.convertDateFormat(date + "",
                        "E MMM dd HH:mm:ss Z yyyy", "HH");

                log("=====>" + selectedTime);

                timeEDT.setText(selectedTime + ":00");
                dateIn12Hour = selectedTime;

               String AM_PM;
                int newTime = Integer.parseInt(selectedTime);

                if (newTime < 12) {
                    AM_PM = " AM";
                    if (newTime == 0) {
                        selectedTime = "12 AM";
                    } else {
                        selectedTime = newTime + AM_PM;
                    }
                } else if (newTime >= 13 && newTime < 24) {
                    AM_PM = " PM";
                    newTime = newTime - 12;
                    selectedTime = newTime + AM_PM;
                } else {
                    selectedTime = "12 PM";
                }


                timeEDT.setText(selectedTime);
                dateIn12Hour = baseActivity.convertDateFormat(date + "", "E MMM dd HH:mm:ss Z yyyy", "HH");

                log(timeEDT.getText().toString() + "\n" + dateIn12Hour);
            }
        });
*/

        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeEDT.setText(getResources().getString(R.string.selectTime));
                dateIn12Hour = "";
                bottomSheetDialog.dismiss();
            }
        });

        done_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();

            }
        });


        bottomSheetDialog.show();
    }


}
