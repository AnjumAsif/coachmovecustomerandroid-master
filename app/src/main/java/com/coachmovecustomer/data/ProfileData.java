package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileData implements Serializable, Parcelable
{

    @SerializedName("lastName")
    @Expose
    public String lastName;
    @SerializedName("role")
    @Expose
    public Role role;
    @SerializedName("comments")
    @Expose
    public Long comments;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("surgeries")
    @Expose
    public String surgeries;
    @SerializedName("isProfileCreated")
    @Expose
    public Boolean isProfileCreated;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("weight")
    @Expose
    public Long weight;
    @SerializedName("medication")
    @Expose
    public String medication;
    @SerializedName("accountId")
    @Expose
    public String accountId;
    @SerializedName("firstName")
    @Expose
    public String firstName;
    @SerializedName("profilePicPath")
    @Expose
    public String profilePicPath;
    @SerializedName("jointPains")
    @Expose
    public String jointPains;
    @SerializedName("heartDiseases")
    @Expose
    public String heartDiseases;
    @SerializedName("avgRating")
    @Expose
    public Float avgRating;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("stripeCustomerId")
    @Expose
    public String stripeCustomerId;
    @SerializedName("cpfNo")
    @Expose
    public String cpfNo;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("age")
    @Expose
    public Long age;
    @SerializedName("others")
    @Expose
    public String others;
    @SerializedName("height")
    @Expose
    public Double height;
    @SerializedName("fitnessLevel")
    @Expose
    public FitnessLevel fitnessLevel=new FitnessLevel();
    public final static Creator<ProfileData> CREATOR = new Creator<ProfileData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProfileData createFromParcel(Parcel in) {
            return new ProfileData(in);
        }

        public ProfileData[] newArray(int size) {
            return (new ProfileData[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6594743037969823387L;

    protected ProfileData(Parcel in) {
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.role = ((Role) in.readValue((Role.class.getClassLoader())));
        this.comments = ((Long) in.readValue((Long.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.surgeries = ((String) in.readValue((String.class.getClassLoader())));
        this.isProfileCreated = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.weight = ((Long) in.readValue((Long.class.getClassLoader())));
        this.medication = ((String) in.readValue((String.class.getClassLoader())));
        this.accountId = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.profilePicPath = ((String) in.readValue((String.class.getClassLoader())));
        this.jointPains = ((String) in.readValue((String.class.getClassLoader())));
        this.heartDiseases = ((String) in.readValue((String.class.getClassLoader())));
        this.avgRating = ((Float) in.readValue((Float.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.stripeCustomerId = ((String) in.readValue((String.class.getClassLoader())));
        this.cpfNo = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.age = ((Long) in.readValue((Long.class.getClassLoader())));
        this.others = ((String) in.readValue((String.class.getClassLoader())));
        this.height = ((Double) in.readValue((Double.class.getClassLoader())));
        this.fitnessLevel = ((FitnessLevel) in.readValue((FitnessLevel.class.getClassLoader())));
    }

    public ProfileData() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lastName);
        dest.writeValue(role);
        dest.writeValue(comments);
        dest.writeValue(gender);
        dest.writeValue(surgeries);
        dest.writeValue(isProfileCreated);
        dest.writeValue(mobile);
        dest.writeValue(weight);
        dest.writeValue(medication);
        dest.writeValue(accountId);
        dest.writeValue(firstName);
        dest.writeValue(profilePicPath);
        dest.writeValue(jointPains);
        dest.writeValue(heartDiseases);
        dest.writeValue(avgRating);
        dest.writeValue(id);
        dest.writeValue(stripeCustomerId);
        dest.writeValue(cpfNo);
        dest.writeValue(email);
        dest.writeValue(age);
        dest.writeValue(others);
        dest.writeValue(height);
        dest.writeValue(fitnessLevel);
    }

    public int describeContents() {
        return 0;
    }


    public class Role implements Serializable, Parcelable
    {

        @SerializedName("id")
        @Expose
        public Long id;
        @SerializedName("role")
        @Expose
        public String role;
        public final  Creator<Role> CREATOR = new Creator<Role>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Role createFromParcel(Parcel in) {
                return new Role(in);
            }

            public Role[] newArray(int size) {
                return (new Role[size]);
            }

        }
                ;
        private final static long serialVersionUID = -4549847249728898489L;

        protected Role(Parcel in) {
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
            this.role = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Role() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(role);
        }

        public int describeContents() {
            return 0;
        }

    }


    public class FitnessLevel implements Serializable, Parcelable
    {

        @SerializedName("level")
        @Expose
        public String level;
        @SerializedName("level_pt")
        @Expose
        public String level_pt;
        @SerializedName("id")
        @Expose
        public Long id;
        public final  Creator<FitnessLevel> CREATOR = new Creator<FitnessLevel>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public FitnessLevel createFromParcel(Parcel in) {
                return new FitnessLevel(in);
            }

            public FitnessLevel[] newArray(int size) {
                return (new FitnessLevel[size]);
            }

        }
                ;
        private final static long serialVersionUID = 6921172135886040407L;

        protected FitnessLevel(Parcel in) {
            this.level = ((String) in.readValue((String.class.getClassLoader())));
            this.level_pt = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        }

        public FitnessLevel() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(level);
            dest.writeValue(level_pt);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }

}
