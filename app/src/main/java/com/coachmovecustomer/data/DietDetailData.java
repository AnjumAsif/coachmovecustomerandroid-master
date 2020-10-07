package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DietDetailData implements Serializable, Parcelable {

    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("objective")
    @Expose
    public String objective;
    @SerializedName("price")
    @Expose
    public Long price;
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
    public int status;
    @SerializedName("modality")
    @Expose
    public Modality modality;
    @SerializedName("remainingTime")
    @Expose
    public Long remainingTime;
    @SerializedName("requestBy")
    @Expose
    public RequestBy requestBy;
    @SerializedName("requestTo")
    @Expose
    public RequestTo requestTo;
    @SerializedName("ratingAndComment")
    @Expose
    public RatingAndComment ratingAndComment;
    @SerializedName("createdAt")
    @Expose
    public String createdAt;
    @SerializedName("lastUpdatedAt")
    @Expose
    public String lastUpdatedAt;
    @SerializedName("id")
    @Expose
    public int id;
    public final static Parcelable.Creator<DietDetailData> CREATOR = new Creator<DietDetailData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DietDetailData createFromParcel(Parcel in) {
            return new DietDetailData(in);
        }

        public DietDetailData[] newArray(int size) {
            return (new DietDetailData[size]);
        }

    };

    protected DietDetailData(Parcel in) {
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.objective = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((Long) in.readValue((Long.class.getClassLoader())));
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
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.modality = ((Modality) in.readValue((Modality.class.getClassLoader())));
        this.remainingTime = ((Long) in.readValue((Long.class.getClassLoader())));
        this.requestBy = ((RequestBy) in.readValue((RequestBy.class.getClassLoader())));
        this.requestTo = ((RequestTo) in.readValue((RequestTo.class.getClassLoader())));
        this.ratingAndComment = ((RatingAndComment) in.readValue((RatingAndComment.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public DietDetailData() {
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
        dest.writeValue(modality);
        dest.writeValue(remainingTime);
        dest.writeValue(requestBy);
        dest.writeValue(requestTo);
        dest.writeValue(ratingAndComment);
        dest.writeValue(createdAt);
        dest.writeValue(lastUpdatedAt);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

    public class Modality implements Parcelable {

        @SerializedName("modality")
        @Expose
        public String modality;
        @SerializedName("price")
        @Expose
        public Long price;
        @SerializedName("id")
        @Expose
        public int id;
        public final Parcelable.Creator<Modality> CREATOR = new Creator<Modality>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Modality createFromParcel(Parcel in) {
                return new Modality(in);
            }

            public Modality[] newArray(int size) {
                return (new Modality[size]);
            }

        };

        protected Modality(Parcel in) {
            this.modality = ((String) in.readValue((String.class.getClassLoader())));
            this.price = ((Long) in.readValue((Long.class.getClassLoader())));
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

    public class RequestBy implements Parcelable {

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
        public Float height;


        @SerializedName("fitnessLevel")
        @Expose
        public FitnessLevel fitnessLevel;
        public final Parcelable.Creator<RequestBy> CREATOR = new Creator<RequestBy>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public RequestBy createFromParcel(Parcel in) {
                return new RequestBy(in);
            }

            public RequestBy[] newArray(int size) {
                return (new RequestBy[size]);
            }

        };

        protected RequestBy(Parcel in) {
            this.lastName = ((String) in.readValue((String.class.getClassLoader())));
            this.role = ((Role) in.readValue((Role.class.getClassLoader())));
            this.comments = ((Long) in.readValue((Long.class.getClassLoader())));
            this.gender = ((String) in.readValue((String.class.getClassLoader())));
            this.surgeries = ((String) in.readValue((String.class.getClassLoader())));
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
            this.email = ((String) in.readValue((String.class.getClassLoader())));
            this.age = ((Long) in.readValue((Long.class.getClassLoader())));
            this.others = ((String) in.readValue((String.class.getClassLoader())));
            this.height = ((Float) in.readValue((Float.class.getClassLoader())));
            this.fitnessLevel = ((FitnessLevel) in.readValue((FitnessLevel.class.getClassLoader())));
        }

        public RequestBy() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(lastName);
            dest.writeValue(role);
            dest.writeValue(comments);
            dest.writeValue(gender);
            dest.writeValue(surgeries);
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
            dest.writeValue(email);
            dest.writeValue(age);
            dest.writeValue(others);
            dest.writeValue(height);
            dest.writeValue(fitnessLevel);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class RequestTo implements Parcelable {

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
        public Role_ role;
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
        public List<UserModality> userModalities = null ;
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
        public final Parcelable.Creator<RequestTo> CREATOR = new Creator<RequestTo>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public RequestTo createFromParcel(Parcel in) {
                return new RequestTo(in);
            }

            public RequestTo[] newArray(int size) {
                return (new RequestTo[size]);
            }

        };

        protected RequestTo(Parcel in) {
            this.lastName = ((String) in.readValue((String.class.getClassLoader())));
            this.universityDocPath = ((String) in.readValue((String.class.getClassLoader())));
            this.address = ((String) in.readValue((String.class.getClassLoader())));
            this.role = ((Role_) in.readValue((Role_.class.getClassLoader())));
            this.comments = ((Long) in.readValue((Long.class.getClassLoader())));
            this.otherDocPath = ((String) in.readValue((String.class.getClassLoader())));
            this.gender = ((String) in.readValue((String.class.getClassLoader())));
            this.mobile = ((String) in.readValue((String.class.getClassLoader())));
            this.criminalDocPath = ((String) in.readValue((String.class.getClassLoader())));
            this.isOnline = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            this.aboutMe = ((String) in.readValue((String.class.getClassLoader())));
            this.crefCrnPath = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.userModalities, (UserModality.class.getClassLoader()));
            this.accountId = ((String) in.readValue((String.class.getClassLoader())));
            this.firstName = ((String) in.readValue((String.class.getClassLoader())));
            this.profilePicPath = ((String) in.readValue((String.class.getClassLoader())));
            this.dob = ((String) in.readValue((String.class.getClassLoader())));
            this.avgRating = ((Float) in.readValue((Float.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.stripeCustomerId = ((String) in.readValue((String.class.getClassLoader())));
            this.cpfNo = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
        }

        public RequestTo() {
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

    public class RatingAndComment implements Parcelable {

        @SerializedName("createdAt")
        @Expose
        public String createdAt;
        @SerializedName("rating")
        @Expose
        public float rating;
        @SerializedName("comment")
        @Expose
        public String comment;
        public final Parcelable.Creator<RatingAndComment> CREATOR = new Creator<RatingAndComment>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public RatingAndComment createFromParcel(Parcel in) {
                return new RatingAndComment(in);
            }

            public RatingAndComment[] newArray(int size) {
                return (new RatingAndComment[size]);
            }

        };

        protected RatingAndComment(Parcel in) {
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.rating = ((Float) in.readValue((Float.class.getClassLoader())));
            this.comment = ((String) in.readValue((String.class.getClassLoader())));
        }

        public RatingAndComment() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(createdAt);
            dest.writeValue(rating);
            dest.writeValue(comment);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Role implements Parcelable {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("role")
        @Expose
        public String role;
        public final Parcelable.Creator<Role> CREATOR = new Creator<Role>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Role createFromParcel(Parcel in) {
                return new Role(in);
            }

            public Role[] newArray(int size) {
                return (new Role[size]);
            }

        };

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

    public class FitnessLevel implements Parcelable {

        @SerializedName("level")
        @Expose
        public String level;
        @SerializedName("id")
        @Expose
        public int id;
        public final Parcelable.Creator<FitnessLevel> CREATOR = new Creator<FitnessLevel>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public FitnessLevel createFromParcel(Parcel in) {
                return new FitnessLevel(in);
            }

            public FitnessLevel[] newArray(int size) {
                return (new FitnessLevel[size]);
            }

        };

        protected FitnessLevel(Parcel in) {
            this.level = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public FitnessLevel() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(level);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Role_ implements Parcelable {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("role")
        @Expose
        public String role;
        public final Parcelable.Creator<Role_> CREATOR = new Creator<Role_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Role_ createFromParcel(Parcel in) {
                return new Role_(in);
            }

            public Role_[] newArray(int size) {
                return (new Role_[size]);
            }

        };

        protected Role_(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.role = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Role_() {
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
        public Modality_ modality;
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
            this.modality = ((Modality_) in.readValue((Modality_.class.getClassLoader())));
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




    public class Modality_ implements Parcelable {

        @SerializedName("modality")
        @Expose
        public String modality;
        @SerializedName("price")
        @Expose
        public Long price;
        @SerializedName("id")
        @Expose
        public int id;
        public final Parcelable.Creator<Modality_> CREATOR = new Creator<Modality_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Modality_ createFromParcel(Parcel in) {
                return new Modality_(in);
            }

            public Modality_[] newArray(int size) {
                return (new Modality_[size]);
            }

        };

        protected Modality_(Parcel in) {
            this.modality = ((String) in.readValue((String.class.getClassLoader())));
            this.price = ((Long) in.readValue((Long.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Modality_() {
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