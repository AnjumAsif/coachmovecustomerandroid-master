package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllScheduleData implements Parcelable {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("status")
    @Expose
    public Long status;
    @SerializedName("modality")
    @Expose
    public Modality modality;
    @SerializedName("neighbourhood")
    @Expose
    public Neighbourhood neighbourhood;
    @SerializedName("workoutUsers")
    @Expose
    public List<WorkoutUser> workoutUsers = null;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("prefferedGender")
    @Expose
    public String prefferedGender;
    @SerializedName("timeSlot")
    @Expose
    public TimeSlot timeSlot;
    @SerializedName("requestBy")
    @Expose
    public RequestBy requestBy;
    @SerializedName("requestTo")
    @Expose
    public RequestTo requestTo;
    @SerializedName("timeslotId")
    @Expose
    public Object timeslotId;
    @SerializedName("coachRating")
    @Expose
    public CoachRating coachRating;
    /*  @SerializedName("customerRating")
      @Expose
      public Object customerRating;*/
    @SerializedName("customerRating")
    @Expose
    public CustomerRating customerRating;
    @SerializedName("createdAt")
    @Expose
    public String createdAt;
    @SerializedName("lastUpdatedAt")
    @Expose
    public String lastUpdatedAt;
    public final static Parcelable.Creator<AllScheduleData> CREATOR = new Creator<AllScheduleData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AllScheduleData createFromParcel(Parcel in) {
            return new AllScheduleData(in);
        }

        public AllScheduleData[] newArray(int size) {
            return (new AllScheduleData[size]);
        }

    };

    protected AllScheduleData(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Long) in.readValue((Long.class.getClassLoader())));
        this.modality = ((Modality) in.readValue((Modality.class.getClassLoader())));
        this.neighbourhood = ((Neighbourhood) in.readValue((Neighbourhood.class.getClassLoader())));
        in.readList(this.workoutUsers, (WorkoutUser.class.getClassLoader()));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.prefferedGender = ((String) in.readValue((String.class.getClassLoader())));
        this.timeSlot = ((TimeSlot) in.readValue((TimeSlot.class.getClassLoader())));
        this.requestBy = ((RequestBy) in.readValue((RequestBy.class.getClassLoader())));
        this.requestTo = ((RequestTo) in.readValue((RequestTo.class.getClassLoader())));
        this.timeslotId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.coachRating = ((CoachRating) in.readValue((CoachRating.class.getClassLoader())));
        this.customerRating = ((CustomerRating) in.readValue((CustomerRating.class.getClassLoader())));
//        this.customerRating = ((Object) in.readValue((Object.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AllScheduleData() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(status);
        dest.writeValue(modality);
        dest.writeValue(neighbourhood);
        dest.writeList(workoutUsers);
        dest.writeValue(address);
        dest.writeValue(prefferedGender);
        dest.writeValue(timeSlot);
        dest.writeValue(requestBy);
        dest.writeValue(requestTo);
        dest.writeValue(timeslotId);
        dest.writeValue(coachRating);
        dest.writeValue(customerRating);
//        dest.writeValue(customerRating);
        dest.writeValue(createdAt);
        dest.writeValue(lastUpdatedAt);
    }

    public int describeContents() {
        return 0;
    }

    public class Modality implements Parcelable {

        @SerializedName("modality")
        @Expose
        public String modality;
        @SerializedName("modalityBr")
        @Expose
        public String modalityBr;
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
            this.modalityBr = ((String) in.readValue((String.class.getClassLoader())));
            this.price = ((Long) in.readValue((Long.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Modality() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(modality);
            dest.writeValue(modalityBr);
            dest.writeValue(price);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Neighbourhood implements Parcelable {

        @SerializedName("neighbourhood")
        @Expose
        public String neighbourhood;
        @SerializedName("id")
        @Expose
        public int id;
        public final Parcelable.Creator<Neighbourhood> CREATOR = new Creator<Neighbourhood>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Neighbourhood createFromParcel(Parcel in) {
                return new Neighbourhood(in);
            }

            public Neighbourhood[] newArray(int size) {
                return (new Neighbourhood[size]);
            }

        };

        protected Neighbourhood(Parcel in) {
            this.neighbourhood = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Neighbourhood() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(neighbourhood);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class WorkoutUser implements Parcelable {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("user")
        @Expose
        public User user;
        public final Parcelable.Creator<WorkoutUser> CREATOR = new Creator<WorkoutUser>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public WorkoutUser createFromParcel(Parcel in) {
                return new WorkoutUser(in);
            }

            public WorkoutUser[] newArray(int size) {
                return (new WorkoutUser[size]);
            }

        };

        protected WorkoutUser(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.user = ((User) in.readValue((User.class.getClassLoader())));
        }

        public WorkoutUser() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(user);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class TimeSlot implements Parcelable {

        @SerializedName("start")
        @Expose
        public String start;
        @SerializedName("end")
        @Expose
        public String end;
        @SerializedName("status")
        @Expose
        public Long status;
        @SerializedName("addedBy")
        @Expose
        public AddedBy addedBy;
        @SerializedName("neighbourhood")
        @Expose
        public Neighbourhood_ neighbourhood;
        @SerializedName("id")
        @Expose
        public int id;
        public final Parcelable.Creator<TimeSlot> CREATOR = new Creator<TimeSlot>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public TimeSlot createFromParcel(Parcel in) {
                return new TimeSlot(in);
            }

            public TimeSlot[] newArray(int size) {
                return (new TimeSlot[size]);
            }

        };

        protected TimeSlot(Parcel in) {
            this.start = ((String) in.readValue((String.class.getClassLoader())));
            this.end = ((String) in.readValue((String.class.getClassLoader())));
            this.status = ((Long) in.readValue((Long.class.getClassLoader())));
            this.addedBy = ((AddedBy) in.readValue((AddedBy.class.getClassLoader())));
            this.neighbourhood = ((Neighbourhood_) in.readValue((Neighbourhood_.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public TimeSlot() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(start);
            dest.writeValue(end);
            dest.writeValue(status);
            dest.writeValue(addedBy);
            dest.writeValue(neighbourhood);
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
        public Role__ role;
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
        public Double height;
        @SerializedName("fitnessLevel")
        @Expose
        public FitnessLevel_ fitnessLevel;
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
            this.role = ((Role__) in.readValue((Role__.class.getClassLoader())));
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
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.stripeCustomerId = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
            this.age = ((Long) in.readValue((Long.class.getClassLoader())));
            this.others = ((String) in.readValue((String.class.getClassLoader())));
            this.height = ((Double) in.readValue((Double.class.getClassLoader())));
            this.fitnessLevel = ((FitnessLevel_) in.readValue((FitnessLevel_.class.getClassLoader())));
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
        public Role___ role;
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
        public Object criminalDocPath;
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
        public List<UserModality_> userModalities = null;
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
        public Object stripeCustomerId;
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
            this.role = ((Role___) in.readValue((Role___.class.getClassLoader())));
            this.comments = ((Long) in.readValue((Long.class.getClassLoader())));
            this.otherDocPath = ((Object) in.readValue((Object.class.getClassLoader())));
            this.gender = ((String) in.readValue((String.class.getClassLoader())));
            this.mobile = ((String) in.readValue((String.class.getClassLoader())));
            this.criminalDocPath = ((Object) in.readValue((Object.class.getClassLoader())));
            this.isOnline = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            this.aboutMe = ((String) in.readValue((String.class.getClassLoader())));
            this.crefCrnPath = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.userModalities, (UserModality_.class.getClassLoader()));
            this.accountId = ((String) in.readValue((String.class.getClassLoader())));
            this.firstName = ((String) in.readValue((String.class.getClassLoader())));
            this.profilePicPath = ((String) in.readValue((String.class.getClassLoader())));
            this.dob = ((String) in.readValue((String.class.getClassLoader())));
            this.avgRating = ((Double) in.readValue((Double.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.stripeCustomerId = ((Object) in.readValue((Object.class.getClassLoader())));
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

    public class CoachRating implements Parcelable {

        @SerializedName("rating")
        @Expose
        public double rating;
        @SerializedName("comment")
        @Expose
        public String comment;
        public final Parcelable.Creator<CoachRating> CREATOR = new Creator<CoachRating>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public CoachRating createFromParcel(Parcel in) {
                return new CoachRating(in);
            }

            public CoachRating[] newArray(int size) {
                return (new CoachRating[size]);
            }

        };

        protected CoachRating(Parcel in) {
            this.rating = ((Double) in.readValue((Double.class.getClassLoader())));
            this.comment = ((String) in.readValue((String.class.getClassLoader())));
        }

        public CoachRating() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(rating);
            dest.writeValue(comment);
        }

        public int describeContents() {
            return 0;
        }

    }


    public class CustomerRating implements Parcelable {

        @SerializedName("rating")
        @Expose
        public double rating;
        @SerializedName("comment")
        @Expose
        public String comment;
        public final Parcelable.Creator<CustomerRating> CREATOR = new Creator<CustomerRating>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public CustomerRating createFromParcel(Parcel in) {
                return new CustomerRating(in);
            }

            public CustomerRating[] newArray(int size) {
                return (new CustomerRating[size]);
            }

        };

        protected CustomerRating(Parcel in) {
            this.rating = ((Double) in.readValue((Double.class.getClassLoader())));
            this.comment = ((String) in.readValue((String.class.getClassLoader())));
        }

        public CustomerRating() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(rating);
            dest.writeValue(comment);
        }

        public int describeContents() {
            return 0;
        }

    }



    public class User implements Parcelable {

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
        public Double avgRating;
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
        public Double height;
        @SerializedName("fitnessLevel")
        @Expose
        public FitnessLevel fitnessLevel;
        public final Parcelable.Creator<User> CREATOR = new Creator<User>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public User createFromParcel(Parcel in) {
                return new User(in);
            }

            public User[] newArray(int size) {
                return (new User[size]);
            }

        };

        protected User(Parcel in) {
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
            this.avgRating = ((Double) in.readValue((Double.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.stripeCustomerId = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
            this.age = ((Long) in.readValue((Long.class.getClassLoader())));
            this.others = ((String) in.readValue((String.class.getClassLoader())));
            this.height = ((Double) in.readValue((Double.class.getClassLoader())));
            this.fitnessLevel = ((FitnessLevel) in.readValue((FitnessLevel.class.getClassLoader())));
        }

        public User() {
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

    public class AddedBy implements Parcelable {

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
        public Object otherDocPath;
        @SerializedName("gender")
        @Expose
        public String gender;
        @SerializedName("mobile")
        @Expose
        public String mobile;
        @SerializedName("criminalDocPath")
        @Expose
        public Object criminalDocPath;
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
        public Object stripeCustomerId;
        @SerializedName("cpfNo")
        @Expose
        public String cpfNo;
        @SerializedName("email")
        @Expose
        public String email;
        public final Parcelable.Creator<AddedBy> CREATOR = new Creator<AddedBy>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public AddedBy createFromParcel(Parcel in) {
                return new AddedBy(in);
            }

            public AddedBy[] newArray(int size) {
                return (new AddedBy[size]);
            }

        };

        protected AddedBy(Parcel in) {
            this.lastName = ((String) in.readValue((String.class.getClassLoader())));
            this.universityDocPath = ((String) in.readValue((String.class.getClassLoader())));
            this.address = ((String) in.readValue((String.class.getClassLoader())));
            this.role = ((Role_) in.readValue((Role_.class.getClassLoader())));
            this.comments = ((Long) in.readValue((Long.class.getClassLoader())));
            this.otherDocPath = ((Object) in.readValue((Object.class.getClassLoader())));
            this.gender = ((String) in.readValue((String.class.getClassLoader())));
            this.mobile = ((String) in.readValue((String.class.getClassLoader())));
            this.criminalDocPath = ((Object) in.readValue((Object.class.getClassLoader())));
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
            this.stripeCustomerId = ((Object) in.readValue((Object.class.getClassLoader())));
            this.cpfNo = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
        }

        public AddedBy() {
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

    public class Neighbourhood_ implements Parcelable {

        @SerializedName("neighbourhood")
        @Expose
        public String neighbourhood;
        @SerializedName("id")
        @Expose
        public int id;
        public final Parcelable.Creator<Neighbourhood_> CREATOR = new Creator<Neighbourhood_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Neighbourhood_ createFromParcel(Parcel in) {
                return new Neighbourhood_(in);
            }

            public Neighbourhood_[] newArray(int size) {
                return (new Neighbourhood_[size]);
            }

        };

        protected Neighbourhood_(Parcel in) {
            this.neighbourhood = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Neighbourhood_() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(neighbourhood);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Role__ implements Parcelable {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("role")
        @Expose
        public String role;
        public final Parcelable.Creator<Role__> CREATOR = new Creator<Role__>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Role__ createFromParcel(Parcel in) {
                return new Role__(in);
            }

            public Role__[] newArray(int size) {
                return (new Role__[size]);
            }

        };

        protected Role__(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.role = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Role__() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(role);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class FitnessLevel_ implements Parcelable {

        @SerializedName("level")
        @Expose
        public String level;
        @SerializedName("id")
        @Expose
        public int id;
        public final Parcelable.Creator<FitnessLevel_> CREATOR = new Creator<FitnessLevel_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public FitnessLevel_ createFromParcel(Parcel in) {
                return new FitnessLevel_(in);
            }

            public FitnessLevel_[] newArray(int size) {
                return (new FitnessLevel_[size]);
            }

        };

        protected FitnessLevel_(Parcel in) {
            this.level = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public FitnessLevel_() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(level);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Role___ implements Parcelable {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("role")
        @Expose
        public String role;
        public final Parcelable.Creator<Role___> CREATOR = new Creator<Role___>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Role___ createFromParcel(Parcel in) {
                return new Role___(in);
            }

            public Role___[] newArray(int size) {
                return (new Role___[size]);
            }

        };

        protected Role___(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.role = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Role___() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(role);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class UserModality_ implements Parcelable {

        @SerializedName("modality")
        @Expose
        public Modality__ modality;
        @SerializedName("experience")
        @Expose
        public String experience;
        @SerializedName("id")
        @Expose
        public int id;
        public final Parcelable.Creator<UserModality_> CREATOR = new Creator<UserModality_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public UserModality_ createFromParcel(Parcel in) {
                return new UserModality_(in);
            }

            public UserModality_[] newArray(int size) {
                return (new UserModality_[size]);
            }

        };

        protected UserModality_(Parcel in) {
            this.modality = ((Modality__) in.readValue((Modality__.class.getClassLoader())));
            this.experience = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public UserModality_() {
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

    public class UserModality implements Parcelable {

        @SerializedName("modality")
        @Expose
        public Modality_ modality;
        @SerializedName("experience")
        @Expose
        public String experience;
        @SerializedName("id")
        @Expose
        public int id;
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

    public class Modality__ implements Parcelable {

        @SerializedName("modality")
        @Expose
        public String modality;
        @SerializedName("price")
        @Expose
        public Long price;
        @SerializedName("id")
        @Expose
        public int id;
        public final Parcelable.Creator<Modality__> CREATOR = new Creator<Modality__>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Modality__ createFromParcel(Parcel in) {
                return new Modality__(in);
            }

            public Modality__[] newArray(int size) {
                return (new Modality__[size]);
            }

        };

        protected Modality__(Parcel in) {
            this.modality = ((String) in.readValue((String.class.getClassLoader())));
            this.price = ((Long) in.readValue((Long.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Modality__() {
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