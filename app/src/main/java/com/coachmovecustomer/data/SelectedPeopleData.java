package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedPeopleData {

    private String coachName, coachID;
    int coachImage;


    public SelectedPeopleData() {
    }

    public SelectedPeopleData(int coachImage, String coachName, String coachID) {
        this.coachImage = coachImage;
        this.coachName = coachName;
        this.coachID = coachID;

    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachID() {
        return coachID;
    }

    public void setCoachID(String coachID) {
        this.coachID = coachID;
    }

    public int getCoachImage() {
        return coachImage;
    }

    public void setCoachImage(int coachImage) {
        this.coachImage = coachImage;
    }


}