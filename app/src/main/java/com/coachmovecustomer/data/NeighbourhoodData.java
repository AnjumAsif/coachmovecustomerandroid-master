package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NeighbourhoodData implements Parcelable {

    @SerializedName("neighbourhood")
    @Expose
    public String neighbourhood;
    @SerializedName("id")
    @Expose
    public Long id;
    public final static Parcelable.Creator<NeighbourhoodData> CREATOR = new Creator<NeighbourhoodData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NeighbourhoodData createFromParcel(Parcel in) {
            return new NeighbourhoodData(in);
        }

        public NeighbourhoodData[] newArray(int size) {
            return (new NeighbourhoodData[size]);
        }

    };

    protected NeighbourhoodData(Parcel in) {
        this.neighbourhood = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Long) in.readValue((Long.class.getClassLoader())));
    }

    public NeighbourhoodData() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(neighbourhood);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}