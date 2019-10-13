package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoachCommentsData implements Parcelable {

    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public String status;
    public final static Parcelable.Creator<CoachCommentsData> CREATOR = new Creator<CoachCommentsData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CoachCommentsData createFromParcel(Parcel in) {
            return new CoachCommentsData(in);
        }

        public CoachCommentsData[] newArray(int size) {
            return (new CoachCommentsData[size]);
        }

    };

    protected CoachCommentsData(Parcel in) {
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CoachCommentsData() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeValue(data);
        dest.writeValue(message);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

    public class Data implements Parcelable {

        @SerializedName("rating")
        @Expose
        public List<Rating> rating = null;
        public final  Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            public Data[] newArray(int size) {
                return (new Data[size]);
            }

        };

        protected Data(Parcel in) {
            in.readList(this.rating, (Rating.class.getClassLoader()));
        }

        public Data() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(rating);
        }

        public int describeContents() {
            return 0;
        }

    }
    public class Rating implements Parcelable
    {

        @SerializedName("commentBy")
        @Expose
        public CommentBy commentBy;
        @SerializedName("rating")
        @Expose
        public float rating;
        @SerializedName("comment")
        @Expose
        public String comment;
        @SerializedName("createdAt")
        @Expose
        public String createdAt;
        @SerializedName("id")
        @Expose
        public int id;
        public final  Parcelable.Creator<Rating> CREATOR = new Creator<Rating>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Rating createFromParcel(Parcel in) {
                return new Rating(in);
            }

            public Rating[] newArray(int size) {
                return (new Rating[size]);
            }

        }
                ;

        protected Rating(Parcel in) {
            this.commentBy = ((CommentBy) in.readValue((CommentBy.class.getClassLoader())));
            this.rating = ((Float) in.readValue((Float.class.getClassLoader())));
            this.comment = ((String) in.readValue((String.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Rating() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(commentBy);
            dest.writeValue(rating);
            dest.writeValue(comment);
            dest.writeValue(createdAt);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }
    public class CommentBy implements Parcelable
    {

        @SerializedName("lastName")
        @Expose
        public String lastName;
        @SerializedName("universityDocPath")
        @Expose
        public String universityDocPath;
        @SerializedName("address")
        @Expose
        public String address;
        @SerializedName("role")
        @Expose
        public Role role;
        @SerializedName("comments")
        @Expose
        public Long comments;
        @SerializedName("otherDocPath")
        @Expose
        public String otherDocPath;
        @SerializedName("gender")
        @Expose
        public String gender;
        @SerializedName("mobile")
        @Expose
        public String mobile;
        @SerializedName("criminalDocPath")
        @Expose
        public String criminalDocPath;
        @SerializedName("panelityCount")
        @Expose
        public Object panelityCount;
        @SerializedName("isOnline")
        @Expose
        public Boolean isOnline;
        @SerializedName("aboutMe")
        @Expose
        public String aboutMe;
        @SerializedName("crefCrnPath")
        @Expose
        public String crefCrnPath;
        @SerializedName("userModalities")
        @Expose
        public List<UserModality> userModalities = null;
        @SerializedName("accountId")
        @Expose
        public String accountId;
        @SerializedName("firstName")
        @Expose
        public String firstName;
        @SerializedName("profilePicPath")
        @Expose
        public String profilePicPath;
        @SerializedName("dob")
        @Expose
        public String dob;
        @SerializedName("avgRating")
        @Expose
        public Double avgRating;
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
        public final  Parcelable.Creator<CommentBy> CREATOR = new Creator<CommentBy>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public CommentBy createFromParcel(Parcel in) {
                return new CommentBy(in);
            }

            public CommentBy[] newArray(int size) {
                return (new CommentBy[size]);
            }

        }
                ;

        protected CommentBy(Parcel in) {
            this.lastName = ((String) in.readValue((String.class.getClassLoader())));
            this.universityDocPath = ((String) in.readValue((String.class.getClassLoader())));
            this.address = ((String) in.readValue((String.class.getClassLoader())));
            this.role = ((Role) in.readValue((Role.class.getClassLoader())));
            this.comments = ((Long) in.readValue((Long.class.getClassLoader())));
            this.otherDocPath = ((String) in.readValue((String.class.getClassLoader())));
            this.gender = ((String) in.readValue((String.class.getClassLoader())));
            this.mobile = ((String) in.readValue((String.class.getClassLoader())));
            this.criminalDocPath = ((String) in.readValue((String.class.getClassLoader())));
            this.panelityCount = ((Object) in.readValue((Object.class.getClassLoader())));
            this.isOnline = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            this.aboutMe = ((String) in.readValue((String.class.getClassLoader())));
            this.crefCrnPath = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.userModalities, (UserModality.class.getClassLoader()));
            this.accountId = ((String) in.readValue((String.class.getClassLoader())));
            this.firstName = ((String) in.readValue((String.class.getClassLoader())));
            this.profilePicPath = ((String) in.readValue((String.class.getClassLoader())));
            this.dob = ((String) in.readValue((String.class.getClassLoader())));
            this.avgRating = ((Double) in.readValue((Double.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.stripeCustomerId = ((String) in.readValue((String.class.getClassLoader())));
            this.cpfNo = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
        }

        public CommentBy() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(lastName);
            dest.writeValue(universityDocPath);
            dest.writeValue(address);
            dest.writeValue(role);
            dest.writeValue(comments);
            dest.writeValue(otherDocPath);
            dest.writeValue(gender);
            dest.writeValue(mobile);
            dest.writeValue(criminalDocPath);
            dest.writeValue(panelityCount);
            dest.writeValue(isOnline);
            dest.writeValue(aboutMe);
            dest.writeValue(crefCrnPath);
            dest.writeList(userModalities);
            dest.writeValue(accountId);
            dest.writeValue(firstName);
            dest.writeValue(profilePicPath);
            dest.writeValue(dob);
            dest.writeValue(avgRating);
            dest.writeValue(id);
            dest.writeValue(stripeCustomerId);
            dest.writeValue(cpfNo);
            dest.writeValue(email);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Role implements Parcelable
    {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("role")
        @Expose
        public String role;
        public final  Parcelable.Creator<Role> CREATOR = new Creator<Role>() {


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

        protected Role(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
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
    public class UserModality implements Parcelable
    {

        @SerializedName("modality")
        @Expose
        public Modality modality;
        @SerializedName("experience")
        @Expose
        public String experience;
        @SerializedName("id")
        @Expose
        public int id;
        public final  Parcelable.Creator<UserModality> CREATOR = new Creator<UserModality>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public UserModality createFromParcel(Parcel in) {
                return new UserModality(in);
            }

            public UserModality[] newArray(int size) {
                return (new UserModality[size]);
            }

        }
                ;

        protected UserModality(Parcel in) {
            this.modality = ((Modality) in.readValue((Modality.class.getClassLoader())));
            this.experience = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public UserModality() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(modality);
            dest.writeValue(experience);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }


    public class Modality implements Parcelable
    {

        @SerializedName("modality")
        @Expose
        public String modality;
        @SerializedName("price")
        @Expose
        public Double price;
        @SerializedName("id")
        @Expose
        public int id;
        public final  Parcelable.Creator<Modality> CREATOR = new Creator<Modality>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Modality createFromParcel(Parcel in) {
                return new Modality(in);
            }

            public Modality[] newArray(int size) {
                return (new Modality[size]);
            }

        }
                ;

        protected Modality(Parcel in) {
            this.modality = ((String) in.readValue((String.class.getClassLoader())));
            this.price = ((Double) in.readValue((Double.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Modality() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(modality);
            dest.writeValue(price);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }
}