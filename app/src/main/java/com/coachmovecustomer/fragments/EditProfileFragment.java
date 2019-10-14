package com.coachmovecustomer.fragments;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.activity.MainActivity;
import com.coachmovecustomer.adapters.GenderAdapter;
import com.coachmovecustomer.data.AddFitnessData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.ImageUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class EditProfileFragment extends BaseFragment implements ImageUtils.ImageSelectCallback, BaseActivity.PermCallback, TextWatcher {


    ImageView profileImg, editProfile;
    EditText firstNameET, lastNameET, mobileNoET, cpfNoET, ageET, weightET, heightET, surgeriesET,
            heartDiseasesET, jointET,medicationsET, otherET;
    Button update_btn;
    String[] gender_array;
    Spinner gender_sp, fitnessSP;
    String selected_gender = "", selected_fitnessLevel = "";


    ProfileData profileData = new ProfileData();
    private File profileImageFile;


    Call<JsonObject> updateProfile, profilePictureUpdate, getFitnessCall;
    ArrayList<String> selectFitnessList = new ArrayList<>();
    ArrayList<AddFitnessData> addFitnessList = new ArrayList<>();
    private AddFitnessData addFitnessData;
    private long fitnessId;
    private String fitnessLevelStr;
    private boolean isInstantChange = false;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileData = baseActivity.store.getProfileData();
        ((MainActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.editProfile), false);
        setHasOptionsMenu(false);
        initUI(view);
        //save data on text change
        firstNameET.addTextChangedListener(this);
        lastNameET.addTextChangedListener(this);
        mobileNoET.addTextChangedListener(this);
        cpfNoET.addTextChangedListener(this);
        ageET.addTextChangedListener(this);
        weightET.addTextChangedListener(this);
        heightET.addTextChangedListener(this);
        surgeriesET.addTextChangedListener(this);
        heartDiseasesET.addTextChangedListener(this);
        jointET.addTextChangedListener(this);
        medicationsET.addTextChangedListener(this);
        otherET.addTextChangedListener(this);

    }

    private void initUI(View view) {

        editProfile = view.findViewById(R.id.editProfile);
        profileImg = view.findViewById(R.id.profileImg);
        firstNameET = view.findViewById(R.id.firstNameET);
        lastNameET = view.findViewById(R.id.lastNameET);
        mobileNoET = view.findViewById(R.id.mobileNoET);
        cpfNoET = view.findViewById(R.id.cpfET);
        ageET = view.findViewById(R.id.ageET);
        weightET = view.findViewById(R.id.weightET);
        heightET = view.findViewById(R.id.heightET);
        surgeriesET = view.findViewById(R.id.surgeriesET);
        heartDiseasesET = view.findViewById(R.id.heartDiseasesET);
        jointET = view.findViewById(R.id.jointET);
        medicationsET = view.findViewById(R.id.medicationsET);
        otherET = view.findViewById(R.id.otherET);
        update_btn = view.findViewById(R.id.update_btn);
        gender_sp = view.findViewById(R.id.gender_sp);
        fitnessSP = view.findViewById(R.id.fitnessSp);


        firstNameET.setFilters(baseActivity.setFiltersET(30));
        lastNameET.setFilters(baseActivity.setFiltersET(30));
        mobileNoET.setFilters(baseActivity.setFiltersET(13));
        cpfNoET.setFilters(baseActivity.setFiltersET(14));
        ageET.setFilters(baseActivity.setFiltersET(3));
        weightET.setFilters(baseActivity.setFiltersET(4));
        heightET.setFilters(baseActivity.setFiltersET(4));
        surgeriesET.setFilters(baseActivity.setFiltersET(150));
        heartDiseasesET.setFilters(baseActivity.setFiltersET(150));
        jointET.setFilters(baseActivity.setFiltersET(150));
        medicationsET.setFilters(baseActivity.setFiltersET(150));
        otherET.setFilters(baseActivity.setFiltersET(150));


        if (profileData != null) {
            firstNameET.setText(profileData.firstName);
            lastNameET.setText(profileData.lastName);
            mobileNoET.setText(profileData.mobile);
            cpfNoET.setText(profileData.cpfNo + "");
            ageET.setText(profileData.age + "");
            weightET.setText(profileData.weight + "");
            heightET.setText(profileData.height + "");
            surgeriesET.setText(profileData.surgeries);
            heartDiseasesET.setText(profileData.heartDiseases);
            jointET.setText(profileData.jointPains);
            medicationsET.setText(profileData.medication);
            otherET.setText(profileData.others);

            if (profileData.profilePicPath != null && !profileData.profilePicPath.isEmpty())
                Glide.with(baseActivity)
                        .load(Const.SERVER_IMAGE_URL + profileData.profilePicPath /*+ "" + BaseActivity.setCurrentTimeMillis(baseActivity)*/)
                        .apply(new RequestOptions()
                                .dontAnimate()
                                .placeholder(R.drawable.user_dummy)
                                .error(android.R.drawable.stat_notify_error))
                        .into(profileImg);
        }




         /*   if (profileData.gender.equals("M")) {
            selected_gender = getResources().getString(R.string.male);
            Log.e("gender", selected_gender);
        } else {
            selected_gender = getResources().getString(R.string.female);
            Log.e("gender", selected_gender);
        }
*/

        spinnerOptionData();

        switch (profileData.gender) {
            case "M":
                gender_sp.setSelection(1);
                selected_gender = profileData.gender;
                break;
            case "F":
                gender_sp.setSelection(2);
                selected_gender = profileData.gender;
                break;
            default:
                gender_sp.setSelection(0);
                selected_gender = "";
                break;
        }


        gender_sp.setOnItemSelectedListener(this);
        fitnessSP.setOnItemSelectedListener(this);
        update_btn.setOnClickListener(this);
        profileImg.setOnClickListener(this);
//        editProfile.setOnClickListener(this);


    }


    private void spinnerOptionData() {
        String selectGender = getString(R.string.selectGender);
        String male = getString(R.string.male);
        String female = getString(R.string.female);

        gender_array = new String[3];
        gender_array[0] = selectGender;
        gender_array[1] = male;
        gender_array[2] = female;


        GenderAdapter genderAdapter = new GenderAdapter(baseActivity, gender_array);
        gender_sp.getSelectedItem();
        gender_sp.setAdapter(genderAdapter);
    }

    private void callForInstantChange() {
        fitnessId = addFitnessList.get(fitnessSP.getSelectedItemPosition()).id;
        fitnessLevelStr = addFitnessList.get(fitnessSP.getSelectedItemPosition()).level;
        isInstantChange = true;
        updateProfileApi();
    }


    @Override
    public void onResume() {
        super.onResume();
        getFitnessApi();
    }

    private void getFitnessApi() {
        try {
            getFitnessCall = baseActivity.apiInterface.getAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.FITNESS_API);
            baseActivity.apiHitAndHandle.makeApiCall(getFitnessCall, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.update_btn:

                if (firstNameET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertFirstName), false);
                } else if (lastNameET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertLastName), false);
                } else if (mobileNoET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertMobileNumber), false);
                } /*else if (mobileNoET.getText().toString().trim().length() < 11 || mobileNoET.getText().toString().trim().length() > 14) {
                    showToast(getString(R.string.alertValidNumber), false);
                }*/ else if (selected_gender.equals(getResources().getString(R.string.selectGender))) {
                    showToast(getResources().getString(R.string.alertGender));
                } else if (cpfNoET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertCPFNumber), false);
                } else if (ageET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertAge), false);
                } else if (weightET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertWeight), false);
                } else if (heightET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertHeight), false);
                }

                /* else if (surgeriesET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertSurgeries));
                } else if (heartDiseasesET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertHeartDiseases));
                } else if (jointET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertJoint));
                } else if (medicationsET.getText().toString().trim().length() == 0) {
                    showToast(getResources().getString(R.string.alertMedications));
                }*/

                else {
                    fitnessId = addFitnessList.get(fitnessSP.getSelectedItemPosition()).id;
                    fitnessLevelStr = addFitnessList.get(fitnessSP.getSelectedItemPosition()).level;
                    isInstantChange = false;
                    updateProfileApi();
//                    gotoMainFragment(new AnamnesisFragment());
                }


                break;

            case R.id.profileImg:

                if (baseActivity.checkPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 200, this)) {
                    new ImageUtils.ImageSelect.Builder(baseActivity, this, 200).crop().start();
                }


                break;


        }
    }

    private void gotoMainFragment(Fragment targetFragment) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.frameProfile, targetFragment)
                .commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {

        if (parent.getId() == R.id.gender_sp) {
            selected_gender = gender_array[position];
        }
    }


    private void profilePicApi() {
        try {

            HashMap<String, RequestBody> jsonbody = new HashMap<String, RequestBody>();
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), profileImageFile);
            jsonbody.put("file\"; filename=\"" + profileImageFile.getName(), body);
            Log.e("profileURL", "file\"; filename=\"" + profileImageFile.getName());
            profilePictureUpdate = baseActivity.apiInterface.multipartRequestAPI("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.PROFILE_SIGN_UP + "/" + profileData.id, jsonbody);
            baseActivity.apiHitAndHandle.makeApiCall(profilePictureUpdate, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void updateProfileApi() {
        JSONObject jsonbody = new JSONObject();
        try {
            jsonbody.put("firstName", firstNameET.getText().toString().trim());
            jsonbody.put("lastName", lastNameET.getText().toString().trim());
            jsonbody.put("mobile", mobileNoET.getText().toString().trim());

//            jsonbody.put("gender", String.valueOf(selected_gender.charAt(0)));
            if (selected_gender.equalsIgnoreCase(getResources().getString(R.string.male))) {
                jsonbody.put("gender", "M");
            } else {
                jsonbody.put("gender", "F");
            }


            JSONObject fitnessLevel = new JSONObject();
            fitnessLevel.put("id", fitnessId);
            fitnessLevel.put("level", fitnessLevelStr);
            jsonbody.put("fitnessLevel", fitnessLevel);
            jsonbody.put("cpfNo", cpfNoET.getText().toString().trim());
            jsonbody.put("age", ageET.getText().toString().trim());
            jsonbody.put("weight", weightET.getText().toString().trim());
            jsonbody.put("height", heightET.getText().toString().trim());
            jsonbody.put("surgeries", surgeriesET.getText().toString().trim());
            jsonbody.put("heartDiseases", heartDiseasesET.getText().toString().trim());
            jsonbody.put("jointPains", jointET.getText().toString().trim());
            jsonbody.put("medication", medicationsET.getText().toString().trim());
            jsonbody.put("others", otherET.getText().toString().trim());
            jsonbody.put("id", String.valueOf(profileData.id));
            jsonbody.put("accountId", profileData.accountId);
            jsonbody.put("profilePicPath", profileData.profilePicPath);
            jsonbody.put("email", profileData.email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("jsonBody=====", jsonbody + "");
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonbody + "");
        updateProfile = baseActivity.apiInterface.updateProfile("Bearer " + baseActivity.store.getString(Const.ACCESS_TOKEN), Const.PROFILE_SIGN_UP, body);
        baseActivity.apiHitAndHandle.makeApiCall(updateProfile, this);
        baseActivity.startProgressDialog();
    }


    @Override
    public void onSuccess(Call call, Object object, String reps) {

        baseActivity.stopProgressDialog();

        if (call == updateProfile) {
            try {
                log(object.toString());
                JSONObject jsonObject = new JSONObject(object.toString());
                JSONObject data = jsonObject.optJSONObject("data");
                JSONObject user = data.optJSONObject("user");

                profileData = new Gson().fromJson(user.toString(), ProfileData.class);
                baseActivity.store.setProfileData(profileData);
//                showToast("Profile successfully updated");
                if (!isInstantChange)
                    baseActivity.onBackPressed();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (call == profilePictureUpdate) {
            try {
                JSONObject jsonObject = new JSONObject(reps);
                JSONObject data = jsonObject.optJSONObject("data");
                JSONObject user = data.optJSONObject("user");

                Log.e("user1", jsonObject + "");

                profileData = new Gson().fromJson(user + "", ProfileData.class);
                baseActivity.store.setProfileData(profileData);
                Log.e("user2", jsonObject + "");
                Log.e("profileData.image =--", profileData.profilePicPath);


            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (call == getFitnessCall) {
            try {
                JSONObject jsonObject = new JSONObject(reps);
                JSONObject data = jsonObject.optJSONObject("data");
                selectFitnessList.clear();
                JSONArray fitnessLevel = data.getJSONArray("fitnessLevel");
                for (int i = 0; i < fitnessLevel.length(); i++) {
                    Log.e("jsonFitness", fitnessLevel.get(i).toString() + "");
                    addFitnessData = new Gson().fromJson(fitnessLevel.get(i).toString(), AddFitnessData.class);
//                    selectFitnessList.add("" + addFitnessData.level);
//                    addFitnessList.add(addFitnessData);

                  /*  if (baseActivity.store.getLanguage() == "en") {
                        log(baseActivity.store.getLanguage());
                        selectFitnessList.add("" + addFitnessData.level);
                    } else {
                        log(baseActivity.store.getLanguage());
                        selectFitnessList.add("" + addFitnessData.levelPt);
                    }
*/

                    selectFitnessList.add("" + addFitnessData.levelPt);
                    addFitnessList.add(addFitnessData);

                }

                setFitnessAdapter(selectFitnessList);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    @Override
    public void onImageSelected(String imagePath, int resultCode) {
        if (resultCode == 200) {
            if (imagePath != null && !imagePath.isEmpty()) {
                Bitmap bitmap = ImageUtils.imageCompress(imagePath, 2000, 1500);

                profileImageFile = ImageUtils.bitmapToFile(bitmap, baseActivity);
                profileImg.setImageBitmap(bitmap);

                Log.e("profileIMage", profileImageFile.getName() + "\n" + profileImageFile.getAbsolutePath() + "\n" + profileImageFile.getParent() + "\n");


                profilePicApi();

            }
        }

    }

    @Override
    public void permGranted(int resultCode) {
        new ImageUtils.ImageSelect.Builder(baseActivity, this, 200).crop().start();

    }

    @Override
    public void permDenied(int resultCode) {

    }

    private void setFitnessAdapter(ArrayList<String> fitnessList) {
        ArrayAdapter adapter = new ArrayAdapter(baseActivity, R.layout.genderadapter, fitnessList);
        adapter.setDropDownViewResource(R.layout.genderadapter);
        fitnessSP.setAdapter(adapter);
        if (profileData.fitnessLevel != null) {


            int spinnerPosition = adapter.getPosition(profileData.fitnessLevel.level_pt);
            fitnessSP.setSelection(spinnerPosition);

          /*  if (baseActivity.store.getLanguage().equalsIgnoreCase("en")) {
                int spinnerPosition = adapter.getPosition(profileData.fitnessLevel.level);
                fitnessSP.setSelection(spinnerPosition);
            } else {
                int spinnerPosition = adapter.getPosition(profileData.fitnessLevel.level_pt);
                fitnessSP.setSelection(spinnerPosition);
            }*/


        }
    }


    InputFilter filter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; i++) {
                String checkMe = String.valueOf(source.charAt(i));
                Pattern pattern = Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.*!@#$%^&()-_':;,? ]*");
                Matcher matcher = pattern.matcher(checkMe);
                boolean valid = matcher.matches();
                if (!valid) {
                    Log.d("", "invalid");
                    return "";
                }
            }
            return null;
        }
    };

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        callForInstantChange();

    }
}
