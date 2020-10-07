package com.coachmovecustomer.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddFitnessData implements Parcelable {


    @SerializedName("level")
    @Expose
    public String level;
    @SerializedName("level_pt")
    @Expose
    public String levelPt;
    @SerializedName("id")
    @Expose
    public int id;
    public final static Parcelable.Creator<AddFitnessData> CREATOR = new Creator<AddFitnessData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AddFitnessData createFromParcel(Parcel in) {
            return new AddFitnessData(in);
        }

        public AddFitnessData[] newArray(int size) {
            return (new AddFitnessData[size]);
        }

    };

    protected AddFitnessData(Parcel in) {
        this.level = ((String) in.readValue((String.class.getClassLoader())));
        this.levelPt = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public AddFitnessData() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(level);
        dest.writeValue(levelPt);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}