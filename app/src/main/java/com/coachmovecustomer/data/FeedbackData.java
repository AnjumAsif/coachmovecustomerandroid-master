package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackData implements Parcelable {

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
    public final static Parcelable.Creator<FeedbackData> CREATOR = new Creator<FeedbackData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public FeedbackData createFromParcel(Parcel in) {
            return new FeedbackData(in);
        }

        public FeedbackData[] newArray(int size) {
            return (new FeedbackData[size]);
        }

    };

    protected FeedbackData(Parcel in) {
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public FeedbackData() {
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
        public Rating rating;
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
            this.rating = ((Rating) in.readValue((Rating.class.getClassLoader())));
        }

        public Data() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(rating);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Rating implements Parcelable
    {

      /*  @SerializedName("commentBy")
        @Expose
        public CommentBy commentBy;*/
        @SerializedName("commentOn")
        @Expose
        public CommentOn commentOn;
        @SerializedName("rating")
        @Expose
        public Double rating;
        @SerializedName("comment")
        @Expose
        public String comment;
        @SerializedName("diet")
        @Expose
        public Diet diet;
        @SerializedName("createdAt")
        @Expose
        public String createdAt;
        @SerializedName("id")
        @Expose
        public Long id;
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
//            this.commentBy = ((CommentBy) in.readValue((CommentBy.class.getClassLoader())));
            this.commentOn = ((CommentOn) in.readValue((CommentOn.class.getClassLoader())));
            this.rating = ((Double) in.readValue((Double.class.getClassLoader())));
            this.comment = ((String) in.readValue((String.class.getClassLoader())));
            this.diet = ((Diet) in.readValue((Diet.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        }

        public Rating() {
        }

        public void writeToParcel(Parcel dest, int flags) {
//            dest.writeValue(commentBy);
            dest.writeValue(commentOn);
            dest.writeValue(rating);
            dest.writeValue(comment);
            dest.writeValue(diet);
            dest.writeValue(createdAt);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }
    public class CommentOn implements Parcelable
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
     /*   @SerializedName("role")
        @Expose
        public Role_ role;*/
        @SerializedName("comments")
        @Expose
        public Long comments;
        @SerializedName("otherDocPath")
        @Expose
        public Object otherDocPath;
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
      /*  @SerializedName("userModalities")
        @Expose
        public List<UserModality> userModalities = null;*/
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
        public Long id;
        @SerializedName("stripeCustomerId")
        @Expose
        public String stripeCustomerId;
        @SerializedName("cpfNo")
        @Expose
        public String cpfNo;
        @SerializedName("email")
        @Expose
        public String email;
        public final  Parcelable.Creator<CommentOn> CREATOR = new Creator<CommentOn>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public CommentOn createFromParcel(Parcel in) {
                return new CommentOn(in);
            }

            public CommentOn[] newArray(int size) {
                return (new CommentOn[size]);
            }

        }
                ;

        protected CommentOn(Parcel in) {
            this.lastName = ((String) in.readValue((String.class.getClassLoader())));
            this.universityDocPath = ((String) in.readValue((String.class.getClassLoader())));
            this.address = ((String) in.readValue((String.class.getClassLoader())));
//            this.role = ((Role_) in.readValue((Role_.class.getClassLoader())));
            this.comments = ((Long) in.readValue((Long.class.getClassLoader())));
            this.otherDocPath = ((Object) in.readValue((Object.class.getClassLoader())));
            this.gender = ((String) in.readValue((String.class.getClassLoader())));
            this.mobile = ((String) in.readValue((String.class.getClassLoader())));
            this.criminalDocPath = ((String) in.readValue((String.class.getClassLoader())));
            this.panelityCount = ((Object) in.readValue((Object.class.getClassLoader())));
            this.isOnline = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            this.aboutMe = ((String) in.readValue((String.class.getClassLoader())));
            this.crefCrnPath = ((String) in.readValue((String.class.getClassLoader())));
//            in.readList(this.userModalities, (UserModality.class.getClassLoader()));
            this.accountId = ((String) in.readValue((String.class.getClassLoader())));
            this.firstName = ((String) in.readValue((String.class.getClassLoader())));
            this.profilePicPath = ((String) in.readValue((String.class.getClassLoader())));
            this.dob = ((String) in.readValue((String.class.getClassLoader())));
            this.avgRating = ((Double) in.readValue((Double.class.getClassLoader())));
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
            this.stripeCustomerId = ((String) in.readValue((String.class.getClassLoader())));
            this.cpfNo = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
        }

        public CommentOn() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(lastName);
            dest.writeValue(universityDocPath);
            dest.writeValue(address);
//            dest.writeValue(role);
            dest.writeValue(comments);
            dest.writeValue(otherDocPath);
            dest.writeValue(gender);
            dest.writeValue(mobile);
            dest.writeValue(criminalDocPath);
            dest.writeValue(panelityCount);
            dest.writeValue(isOnline);
            dest.writeValue(aboutMe);
            dest.writeValue(crefCrnPath);
//            dest.writeList(userModalities);
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
    public class Diet implements Parcelable
    {

        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("objective")
        @Expose
        public String objective;
        @SerializedName("price")
        @Expose
        public Double price;
        @SerializedName("routine")
        @Expose
        public String routine;
        @SerializedName("dayAliment")
        @Expose
        public String dayAliment;
        @SerializedName("foodLikes")
        @Expose
        public String foodLikes;
        @SerializedName("foodDislikes")
        @Expose
        public String foodDislikes;
        @SerializedName("eatingDisorder")
        @Expose
        public String eatingDisorder;
        @SerializedName("allergies")
        @Expose
        public String allergies;
        @SerializedName("diabetes")
        @Expose
        public String diabetes;
        @SerializedName("cholestrol")
        @Expose
        public String cholestrol;
        @SerializedName("digestiveSystem")
        @Expose
        public String digestiveSystem;
        @SerializedName("thyroid")
        @Expose
        public String thyroid;
        @SerializedName("pregency")
        @Expose
        public String pregency;
        @SerializedName("others")
        @Expose
        public String others;
        @SerializedName("foodSuppliments")
        @Expose
        public String foodSuppliments;
        @SerializedName("status")
        @Expose
        public Long status;
      /*  @SerializedName("modality")
        @Expose
        public Modality_ modality;*/
        @SerializedName("remainingTime")
        @Expose
        public Long remainingTime;
       /* @SerializedName("requestBy")
        @Expose
        public RequestBy requestBy;*/
   /*     @SerializedName("requestTo")
        @Expose
        public RequestTo requestTo;*/
        @SerializedName("ratingAndComment")
        @Expose
        public Object ratingAndComment;
        @SerializedName("createdAt")
        @Expose
        public String createdAt;
        @SerializedName("lastUpdatedAt")
        @Expose
        public String lastUpdatedAt;
        @SerializedName("id")
        @Expose
        public Long id;
        public final  Parcelable.Creator<Diet> CREATOR = new Creator<Diet>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Diet createFromParcel(Parcel in) {
                return new Diet(in);
            }

            public Diet[] newArray(int size) {
                return (new Diet[size]);
            }

        }
                ;

        protected Diet(Parcel in) {
            this.email = ((String) in.readValue((String.class.getClassLoader())));
            this.objective = ((String) in.readValue((String.class.getClassLoader())));
            this.price = ((Double) in.readValue((Double.class.getClassLoader())));
            this.routine = ((String) in.readValue((String.class.getClassLoader())));
            this.dayAliment = ((String) in.readValue((String.class.getClassLoader())));
            this.foodLikes = ((String) in.readValue((String.class.getClassLoader())));
            this.foodDislikes = ((String) in.readValue((String.class.getClassLoader())));
            this.eatingDisorder = ((String) in.readValue((String.class.getClassLoader())));
            this.allergies = ((String) in.readValue((String.class.getClassLoader())));
            this.diabetes = ((String) in.readValue((String.class.getClassLoader())));
            this.cholestrol = ((String) in.readValue((String.class.getClassLoader())));
            this.digestiveSystem = ((String) in.readValue((String.class.getClassLoader())));
            this.thyroid = ((String) in.readValue((String.class.getClassLoader())));
            this.pregency = ((String) in.readValue((String.class.getClassLoader())));
            this.others = ((String) in.readValue((String.class.getClassLoader())));
            this.foodSuppliments = ((String) in.readValue((String.class.getClassLoader())));
            this.status = ((Long) in.readValue((Long.class.getClassLoader())));
//            this.modality = ((Modality_) in.readValue((Modality_.class.getClassLoader())));
            this.remainingTime = ((Long) in.readValue((Long.class.getClassLoader())));
//            this.requestBy = ((RequestBy) in.readValue((RequestBy.class.getClassLoader())));
//            this.requestTo = ((RequestTo) in.readValue((RequestTo.class.getClassLoader())));
            this.ratingAndComment = ((Object) in.readValue((Object.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.lastUpdatedAt = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        }

        public Diet() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(email);
            dest.writeValue(objective);
            dest.writeValue(price);
            dest.writeValue(routine);
            dest.writeValue(dayAliment);
            dest.writeValue(foodLikes);
            dest.writeValue(foodDislikes);
            dest.writeValue(eatingDisorder);
            dest.writeValue(allergies);
            dest.writeValue(diabetes);
            dest.writeValue(cholestrol);
            dest.writeValue(digestiveSystem);
            dest.writeValue(thyroid);
            dest.writeValue(pregency);
            dest.writeValue(others);
            dest.writeValue(foodSuppliments);
            dest.writeValue(status);
//            dest.writeValue(modality);
            dest.writeValue(remainingTime);
//            dest.writeValue(requestBy);
//            dest.writeValue(requestTo);
            dest.writeValue(ratingAndComment);
            dest.writeValue(createdAt);
            dest.writeValue(lastUpdatedAt);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }
}