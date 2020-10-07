package com.coachmovecustomer.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PeopleForAddData implements Parcelable {

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
    @SerializedName("id")
    @Expose
    public int id;
    public final static Parcelable.Creator<PeopleForAddData> CREATOR = new Creator<PeopleForAddData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PeopleForAddData createFromParcel(Parcel in) {
            return new PeopleForAddData(in);
        }

        public PeopleForAddData[] newArray(int size) {
            return (new PeopleForAddData[size]);
        }

    };

    protected PeopleForAddData(Parcel in) {
        this.accountId = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.profilePicPath = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
    }

    public PeopleForAddData() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accountId);
        dest.writeValue(firstName);
        dest.writeValue(lastName);
        dest.writeValue(profilePicPath);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}
