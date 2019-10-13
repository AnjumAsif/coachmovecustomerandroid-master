package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NutritionistData implements Parcelable
{

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
    @SerializedName("avgRating")
    @Expose
    public Double avgRating;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("experience")
    @Expose
    public String experience;
    public final static Parcelable.Creator<NutritionistData> CREATOR = new Creator<NutritionistData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NutritionistData createFromParcel(Parcel in) {
            return new NutritionistData(in);
        }

        public NutritionistData[] newArray(int size) {
            return (new NutritionistData[size]);
        }

    }
            ;

    protected NutritionistData(Parcel in) {
        this.accountId = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.profilePicPath = ((String) in.readValue((String.class.getClassLoader())));
        this.comments = ((Long) in.readValue((Long.class.getClassLoader())));
        this.avgRating = ((Double) in.readValue((Double.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.experience = ((String) in.readValue((String.class.getClassLoader())));
    }

    public NutritionistData() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accountId);
        dest.writeValue(firstName);
        dest.writeValue(lastName);
        dest.writeValue(profilePicPath);
        dest.writeValue(comments);
        dest.writeValue(avgRating);
        dest.writeValue(id);
        dest.writeValue(experience);
    }

    public int describeContents() {
        return 0;
    }

}