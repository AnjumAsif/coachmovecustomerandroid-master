package com.coachmovecustomer.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.coachmovecustomer.data.AddDietData;
import com.coachmovecustomer.data.DietDetailData;
import com.coachmovecustomer.data.ProfileData;

import java.util.ArrayList;

public class PrefStore {

    private Context mAct;
    private String mypreference="language";
    SharedPreferences.Editor languageEditor;
    private SharedPreferences languagePref;

    public PrefStore(Context aAct) {
        this.mAct = aAct;
        initLanguage();
    }



    public void initLanguage(){
        languagePref = mAct.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        languageEditor=languagePref.edit();
    }

    public void setLanguage(String value){
        languageEditor.putString("language",value).commit();
    }

    public String getLanguage(){
        return languagePref.getString("language",null);
    }


    private SharedPreferences getPref() {
        return PreferenceManager.getDefaultSharedPreferences(mAct);
    }

    public void cleanPref() {
        SharedPreferences settings = getPref();
        settings.edit().clear().apply();
    }

    public boolean containValue(String key) {
        SharedPreferences settings = getPref();
        return settings.contains(key);
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences settings = getPref();
        return settings.getBoolean(key, defaultValue);
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences settings = getPref();
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void saveString(String key, String value) {
        SharedPreferences settings = getPref();
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return getString(key, null);
    }


    public String getString(String key, String defaultVal) {
        SharedPreferences settings = getPref();
        return (settings.getString(key, defaultVal));
    }


    public void saveLong(String key, long value) {
        SharedPreferences settings = getPref();
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key) {
        return getLong(key, 0);
    }

    public long getLong(String key, long defaultVal) {
        SharedPreferences settings = getPref();
        return settings.getLong(key, defaultVal);
    }

    public void setInt(String subscription_id, int sbu_id) {
        SharedPreferences settings = getPref();
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(subscription_id, sbu_id);
        editor.apply();
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultVal) {
        SharedPreferences settings = getPref();
        return settings.getInt(key, defaultVal);
    }

    public <T extends Object> void setData(String value, ArrayList<T> datas) {
        getPref().edit().putString(value, ObjectSerializer.serialize(datas)).commit();
    }

    public <T extends Object> ArrayList<T> getData(String name) {

        return (ArrayList<T>) ObjectSerializer.deserialize(getPref().
                getString(name, ObjectSerializer.serialize(new ArrayList<T>())));
    }


    public void setProfileData(ProfileData datas) {
        getPref().edit().putString("profile_data", ObjectSerializer.serialize(datas)).commit();
    }

    public ProfileData getProfileData() {
        return (ProfileData) ObjectSerializer.deserialize(getPref().
                getString("profile_data", null));
    }

    public void setDietData(DietDetailData datas) {
        getPref().edit().putString("diet_detail_data", ObjectSerializer.serialize(datas)).commit();
    }

    public DietDetailData getDietData() {
        return (DietDetailData) ObjectSerializer.deserialize(getPref().
                getString("diet_detail_data", null));
    }

}