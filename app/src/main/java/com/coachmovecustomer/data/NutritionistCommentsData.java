package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NutritionistCommentsData implements Parcelable {

    @SerializedName("commentBy")
    @Expose
    public CommentBy commentBy;
    @SerializedName("commentOn")
    @Expose
    public CommentOn commentOn;
    @SerializedName("rating")
    @Expose
    public Double rating;
    @SerializedName("comment")
    @Expose
    public String comment;
    @SerializedName("createdAt")
    @Expose
    public String createdAt;
    @SerializedName("id")
    @Expose
    public Long id;
    public final static Parcelable.Creator<NutritionistCommentsData> CREATOR = new Creator<NutritionistCommentsData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NutritionistCommentsData createFromParcel(Parcel in) {
            return new NutritionistCommentsData(in);
        }

        public NutritionistCommentsData[] newArray(int size) {
            return (new NutritionistCommentsData[size]);
        }

    };

    protected NutritionistCommentsData(Parcel in) {
        this.commentBy = ((CommentBy) in.readValue((CommentBy.class.getClassLoader())));
        this.commentOn = ((CommentOn) in.readValue((CommentOn.class.getClassLoader())));
        this.rating = ((Double) in.readValue((Double.class.getClassLoader())));
        this.comment = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Long) in.readValue((Long.class.getClassLoader())));
    }

    public NutritionistCommentsData() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(commentBy);
        dest.writeValue(commentOn);
        dest.writeValue(rating);
        dest.writeValue(comment);
        dest.writeValue(createdAt);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }


    public class CommentBy implements Parcelable {

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
        @SerializedName("gender")
        @Expose
        public String gender;
        @SerializedName("mobile")
        @Expose
        public String mobile;
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
        public Long id;
        @SerializedName("cpfNo")
        @Expose
        public String cpfNo;
        @SerializedName("email")
        @Expose
        public String email;
        public final Parcelable.Creator<CommentBy> CREATOR = new Creator<CommentBy>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public CommentBy createFromParcel(Parcel in) {
                return new CommentBy(in);
            }

            public CommentBy[] newArray(int size) {
                return (new CommentBy[size]);
            }

        };

        protected CommentBy(Parcel in) {
            this.lastName = ((String) in.readValue((String.class.getClassLoader())));
            this.universityDocPath = ((String) in.readValue((String.class.getClassLoader())));
            this.address = ((String) in.readValue((String.class.getClassLoader())));
            this.role = ((Role) in.readValue((Role.class.getClassLoader())));
            this.comments = ((Long) in.readValue((Long.class.getClassLoader())));
            this.gender = ((String) in.readValue((String.class.getClassLoader())));
            this.mobile = ((String) in.readValue((String.class.getClassLoader())));
            this.isOnline = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            this.aboutMe = ((String) in.readValue((String.class.getClassLoader())));
            this.crefCrnPath = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.userModalities, (UserModality.class.getClassLoader()));
            this.accountId = ((String) in.readValue((String.class.getClassLoader())));
            this.firstName = ((String) in.readValue((String.class.getClassLoader())));
            this.profilePicPath = ((String) in.readValue((String.class.getClassLoader())));
            this.dob = ((String) in.readValue((String.class.getClassLoader())));
            this.avgRating = ((Double) in.readValue((Double.class.getClassLoader())));
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
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
            dest.writeValue(gender);
            dest.writeValue(mobile);
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
            dest.writeValue(cpfNo);
            dest.writeValue(email);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class CommentOn implements Parcelable {

        @SerializedName("lastName")
        @Expose
        public String lastName;
        @SerializedName("role")
        @Expose
        public Role_ role;
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
        public Double avgRating;
        @SerializedName("id")
        @Expose
        public Long id;
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
        public Double height;
        @SerializedName("fitnessLevel")
        @Expose
        public FitnessLevel fitnessLevel;
        public final Parcelable.Creator<CommentOn> CREATOR = new Creator<CommentOn>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public CommentOn createFromParcel(Parcel in) {
                return new CommentOn(in);
            }

            public CommentOn[] newArray(int size) {
                return (new CommentOn[size]);
            }

        };

        protected CommentOn(Parcel in) {
            this.lastName = ((String) in.readValue((String.class.getClassLoader())));
            this.role = ((Role_) in.readValue((Role_.class.getClassLoader())));
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
            this.avgRating = ((Double) in.readValue((Double.class.getClassLoader())));
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
            this.stripeCustomerId = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
            this.age = ((Long) in.readValue((Long.class.getClassLoader())));
            this.others = ((String) in.readValue((String.class.getClassLoader())));
            this.height = ((Double) in.readValue((Double.class.getClassLoader())));
            this.fitnessLevel = ((FitnessLevel) in.readValue((FitnessLevel.class.getClassLoader())));
        }

        public CommentOn() {
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

    public class Role implements Parcelable {

        @SerializedName("id")
        @Expose
        public Long id;
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

    public class UserModality implements Parcelable {

        @SerializedName("modality")
        @Expose
        public Modality modality;
        @SerializedName("experience")
        @Expose
        public String experience;
        @SerializedName("id")
        @Expose
        public Long id;
        public final Parcelable.Creator<UserModality> CREATOR = new Creator<UserModality>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public UserModality createFromParcel(Parcel in) {
                return new UserModality(in);
            }

            public UserModality[] newArray(int size) {
                return (new UserModality[size]);
            }

        };

        protected UserModality(Parcel in) {
            this.modality = ((Modality) in.readValue((Modality.class.getClassLoader())));
            this.experience = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
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

    public class Role_ implements Parcelable {

        @SerializedName("id")
        @Expose
        public Long id;
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
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
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

    public class FitnessLevel implements Parcelable {

        @SerializedName("level")
        @Expose
        public String level;
        @SerializedName("id")
        @Expose
        public Long id;
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
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
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

    public class Modality implements Parcelable {

        @SerializedName("modality")
        @Expose
        public String modality;
        @SerializedName("price")
        @Expose
        public Long price;
        @SerializedName("id")
        @Expose
        public Long id;
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
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
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

