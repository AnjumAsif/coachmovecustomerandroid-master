package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class SearchWorkoutData implements Parcelable {


    public String date;
    public String time;
    public String neighbourhood;
    public String address;
    public String modality;
    public String gender;
    public String price;


    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Storing the Student data to Parcel object
     **/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(neighbourhood);
        dest.writeString(address);
        dest.writeString(modality);
        dest.writeString(gender);
        dest.writeString(price);
    }

    /**
     * A constructor that initializes the Student object
     **/
    public SearchWorkoutData(String sDate, String sTime, String sNeighbourhood, String sAddress, String sModality, String sGender, String sPrice) {
        this.date = sDate;
        this.time = sTime;
        this.neighbourhood = sNeighbourhood;
        this.address = sAddress;
        this.modality = sModality;
        this.gender = sGender;
        this.price = sPrice;
    }

    /**
     * Retrieving Student data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     **/
    private SearchWorkoutData(Parcel in) {
        this.date = in.readString();
        this.time = in.readString();
        this.neighbourhood = in.readString();
        this.address = in.readString();
        this.modality = in.readString();
        this.gender = in.readString();
        this.price = in.readString();
    }

    public static final Parcelable.Creator<SearchWorkoutData> CREATOR = new Parcelable.Creator<SearchWorkoutData>() {

        @Override
        public SearchWorkoutData createFromParcel(Parcel source) {
            return new SearchWorkoutData(source);
        }

        @Override
        public SearchWorkoutData[] newArray(int size) {
            return new SearchWorkoutData[size];
        }
    };
}