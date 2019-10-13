package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class AddDietData implements Parcelable, Serializable {
    public String email;
    public String price;
    public String routine;
    public String diet;
    public String alimentation;
    public String likeEat;
    public String dontLikeEat;
    public String disorder;
    public String allergies;
    public String diabetes;
    public String cholesterol;
    public String digestive;
    public String thyroid;
    public String pregency;
    public String others;
    public String supplements;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(price);
        parcel.writeString(routine);
        parcel.writeString(diet);
        parcel.writeString(alimentation);
        parcel.writeString(likeEat);
        parcel.writeString(dontLikeEat);
        parcel.writeString(disorder);
        parcel.writeString(allergies);
        parcel.writeString(diabetes);
        parcel.writeString(cholesterol);
        parcel.writeString(digestive);
        parcel.writeString(thyroid);
        parcel.writeString(pregency);
        parcel.writeString(others);
        parcel.writeString(supplements);
    }

    public AddDietData(String emailStr, String priceStr, String routineStr, String dietStr, String alimentationStr, String likeEatStr, String dontLikeEatStr, String disorderStr, String allergiesStr, String diabetesStr, String cholesterolStr, String digestiveStr, String thyroidStr, String pregencyStr, String othersStr, String supplementsStr) {
        this.email = emailStr;
        this.price = priceStr;
        this.routine = routineStr;
        this.diet = dietStr;
        this.alimentation = alimentationStr;
        this.likeEat = likeEatStr;
        this.dontLikeEat = dontLikeEatStr;
        this.disorder = disorderStr;
        this.allergies = allergiesStr;
        this.diabetes = diabetesStr;
        this.cholesterol = cholesterolStr;
        this.digestive = digestiveStr;
        this.thyroid = thyroidStr;
        this.pregency = pregencyStr;
        this.others = othersStr;
        this.supplements = supplementsStr;
    }

    private AddDietData(Parcel in) {
        this.email = in.readString();
        this.price = in.readString();
        this.routine = in.readString();
        this.diet = in.readString();
        this.alimentation = in.readString();
        this.likeEat = in.readString();
        this.dontLikeEat = in.readString();
        this.disorder = in.readString();
        this.allergies = in.readString();
        this.diabetes = in.readString();
        this.cholesterol = in.readString();
        this.digestive = in.readString();
        this.thyroid = in.readString();
        this.pregency = in.readString();
        this.others = in.readString();
        this.supplements = in.readString();
    }

    public static final Parcelable.Creator<AddDietData> CREATOR = new Parcelable.Creator<AddDietData>() {

        @Override
        public AddDietData createFromParcel(Parcel source) {
            return new AddDietData(source);
        }

        @Override
        public AddDietData[] newArray(int size) {
            return new AddDietData[size];
        }
    };
}
