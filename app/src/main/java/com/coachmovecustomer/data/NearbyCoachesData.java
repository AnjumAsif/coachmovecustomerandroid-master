package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearbyCoachesData implements Parcelable {

    @SerializedName("accountId")
    @Expose
    public String accountId;
    @SerializedName("firstName")
    @Expose
    public String firstName;
    @SerializedName("lastName")
    @Expose
    public String lastName;
    @SerializedName("profilePicPath")
    @Expose
    public String profilePicPath;
    @SerializedName("comments")
    @Expose
    public Long comments;
    @SerializedName("timeslotId")
    @Expose
    public Long timeslotId;
    @SerializedName("avgRating")
    @Expose
    public Double avgRating;
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("experience")
    @Expose
    public String experience;
    public final  Parcelable.Creator<NearbyCoachesData> CREATOR = new Creator<NearbyCoachesData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NearbyCoachesData createFromParcel(Parcel in) {
            return new NearbyCoachesData(in);
        }

        public NearbyCoachesData[] newArray(int size) {
            return (new NearbyCoachesData[size]);
        }

    };
    public NearbyCoachesData(Parcel in ) {
        this.accountId = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.profilePicPath = ((String) in.readValue((String.class.getClassLoader())));
        this.comments = ((Long) in.readValue((Long.class.getClassLoader())));
        this.timeslotId = ((Long) in.readValue((Long.class.getClassLoader())));
        this.avgRating = ((Double) in.readValue((Double.class.getClassLoader())));
        this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        this.experience = ((String) in.readValue((String.class.getClassLoader())));
    }

    public NearbyCoachesData() {
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accountId);
        dest.writeValue(firstName);
        dest.writeValue(lastName);
        dest.writeValue(profilePicPath);
        dest.writeValue(comments);
        dest.writeValue(timeslotId);
        dest.writeValue(avgRating);
        dest.writeValue(id);
        dest.writeValue(experience);
    }

    public int describeContents() {
        return 0;
    }

}
