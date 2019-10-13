package com.coachmovecustomer.fragments;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coachmovecustomer.R;
import com.coachmovecustomer.activity.BaseActivity;
import com.coachmovecustomer.activity.LoginSignActivity;
import com.coachmovecustomer.adapters.GenderAdapter;
import com.coachmovecustomer.data.AddFitnessData;
import com.coachmovecustomer.data.ProfileData;
import com.coachmovecustomer.utils.Const;
import com.coachmovecustomer.utils.ImageUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class CreateProfileFirstFragment extends BaseFragment implements ImageUtils.ImageSelectCallback, BaseActivity.PermCallback {

    CircleImageView profileImg;
    EditText firstNameET, lastNameET, mobileNoET;
    RelativeLayout next_RLayout;
    Spinner genderSP, fitnessSP;
    String[] gender_array;
    String selected_gender = "";

    ProfileData profileData = new ProfileData();
    ArrayList<String> selectFitnessList = new ArrayList<>();
    ArrayList<AddFitnessData> addFitnessList = new ArrayList<>();
    private AddFitnessData addFitnessData;

    private File profileImageFile;
    private Call<JsonObject> profilePictureUpdate, getFitnessCall, getProfileCall;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_profile_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((LoginSignActivity) getActivity()).setToolbar(false, true, getResources().getString(R.string.createProfile));
        profileData = baseActivity.store.getProfileData();
        initUI(view);

    }

    private void initUI(View view) {

        genderSP = view.findViewById(R.id.genderSP);
        fitnessSP = view.findViewById(R.id.fitnessSP);
        profileImg = view.findViewById(R.id.profileImg);
        firstNameET = view.findViewById(R.id.firstNameET);
        lastNameET = view.findViewById(R.id.lastNameET);
        mobileNoET = view.findViewById(R.id.mobileNoET);
        next_RLayout = view.findViewById(R.id.next_layout);


        firstNameET.setFilters(baseActivity.setFiltersET(30));
        lastNameET.setFilters(baseActivity.setFiltersET(30));
        mobileNoET.setFilters(baseActivity.setFiltersET(13));


        spinnerOptionData();
        genderSP.setOnItemSelectedListener(this);
        profileImg.setOnClickListener(this);
        next_RLayout.setOnClickListener(this);

        GenderAdapter genderAdapter = new GenderAdapter(getActivity(), gender_array);
        genderSP.setAdapter(genderAdapter);


        if (profileData.profilePicPath != null && !profileData.profilePicPath.isEmpty())
            Glide.with(baseActivity)
                    .load(Const.SERVER_IMAGE_URL + profileData.profilePicPath + "" + BaseActivity.setCurrentTimeMillis(baseActivity))
                    .apply(new RequestOptions()
                            .dontAnimate()
                            .placeholder(R.drawable.user_dummy)
                            .error(android.R.drawable.stat_notify_error))
                    .into(profileImg);


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
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.next_layout:


                if (firstNameET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertFirstName), false);
                } else if (lastNameET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertLastName), false);
                } else if (mobileNoET.getText().toString().trim().length() == 0) {
                    showToast(getString(R.string.alertMobileNumber), false);
                }/* else if (mobileNoET.getText().toString().trim().length() < 11 || mobileNoET.getText().toString().trim().length() > 14) {
                    showToast(getString(R.string.alertValidNumber), false);
                } */ else if (selected_gender.equals(getResources().getString(R.string.selectGender))) {
                    showToast(getResources().getString(R.string.alertGender));
                }else if (profileImageFile == null && profileData.profilePicPath == null ){
                    showToast(getResources().getString(R.string.uploadProfilePic));
                }


                else {



                    log(profileImageFile+"");


                    profileData.firstName = firstNameET.getText().toString().trim();
                    profileData.lastName = lastNameET.getText().toString().trim();
                    profileData.mobile = mobileNoET.getText().toString().trim();
                    profileData.gender = selected_gender.trim();
                    long id = addFitnessList.get(fitnessSP.getSelectedItemPosition()).id;
                    String fitnessLevel = addFitnessList.get(fitnessSP.getSelectedItemPosition()).level;
                    Fragment fragment = new CreateProfileSecondFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("profileData", profileData);
                    bundle.putString("fitnessID", id + "");
                    bundle.putString("fitnessLevelStr", fitnessLevel);
                    fragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().add(R.id.frameLayoutLogin, fragment)
                            .addToBackStack(null)
                            .commit();
                }

                break;

            case R.id.profileImg:

                if (baseActivity.checkPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 200, this)) {
                    new ImageUtils.ImageSelect.Builder(baseActivity, this, 200).crop().start();
                }
                break;
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

    @Override
    public void permGranted(int resultCode) {
        new ImageUtils.ImageSelect.Builder(baseActivity, this, 200).crop().start();

    }

    @Override
    public void permDenied(int resultCode) {

    }


    @Override
    public void onSuccess(Call call, Object object, String reps) {
        baseActivity.stopProgressDialog();
        if (call == profilePictureUpdate) {
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
                        selectFitnessList.add(addFitnessData.level);
                    } else {
                        log(baseActivity.store.getLanguage());
                        selectFitnessList.add(addFitnessData.levelPt);
                    }*/

                    selectFitnessList.add(addFitnessData.levelPt);
                    addFitnessList.add(addFitnessData);

                }

                setFitnessAdapter(selectFitnessList);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        if (parent.getId() == R.id.genderSP) {
            selected_gender = gender_array[position];
        }
    }


    private void setFitnessAdapter(ArrayList<String> fitness) {
        ArrayAdapter adapter = new ArrayAdapter(baseActivity, R.layout.genderadapter, fitness);
        adapter.setDropDownViewResource(R.layout.genderadapter);
        fitnessSP.setAdapter(adapter);
    }

}

