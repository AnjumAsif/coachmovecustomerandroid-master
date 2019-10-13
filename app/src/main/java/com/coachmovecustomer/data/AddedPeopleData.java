package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

public class AddedPeopleData implements Parcelable {


    public int id;
    public String profilePicPath;
    public String lastName;
    public String firstName;
    public String accountId;


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
        dest.writeInt(id);
        dest.writeString(profilePicPath);
        dest.writeString(lastName);
        dest.writeString(firstName);
        dest.writeString(accountId);
    }

    /**
     * A constructor that initializes the Student object
     **/
    public AddedPeopleData(int iID, String sProfilePicPath, String sLastName, String sFirstName, String sAccountId) {
        this.id = iID;
        this.profilePicPath = sProfilePicPath;
        this.lastName = sLastName;
        this.firstName = sFirstName;
        this.accountId = sAccountId;
    }

    /**
     * Retrieving Student data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     **/
    private AddedPeopleData(Parcel in) {
        this.id = in.readInt();
        this.profilePicPath = in.readString();
        this.lastName = in.readString();
        this.firstName = in.readString();
        this.accountId = in.readString();
    }

    public static final Parcelable.Creator<AddedPeopleData> CREATOR = new Parcelable.Creator<AddedPeopleData>() {

        @Override
        public AddedPeopleData createFromParcel(Parcel source) {
            return new AddedPeopleData(source);
        }

        @Override
        public AddedPeopleData[] newArray(int size) {
            return new AddedPeopleData[size];
        }
    };
}