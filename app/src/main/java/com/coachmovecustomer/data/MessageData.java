package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageData implements Parcelable {

    @SerializedName("receiver")
    @Expose
    public Receiver receiver;
    @SerializedName("message")
    @Expose
    public Message message;
    public final static Parcelable.Creator<MessageData> CREATOR = new Creator<MessageData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MessageData createFromParcel(Parcel in) {
            return new MessageData(in);
        }

        public MessageData[] newArray(int size) {
            return (new MessageData[size]);
        }

    };

    protected MessageData(Parcel in) {
        this.receiver = ((Receiver) in.readValue((Receiver.class.getClassLoader())));
        this.message = ((Message) in.readValue((Message.class.getClassLoader())));
    }

    public MessageData() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(receiver);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

    public class Receiver implements Parcelable {

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
        public final Parcelable.Creator<Receiver> CREATOR = new Creator<Receiver>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Receiver createFromParcel(Parcel in) {
                return new Receiver(in);
            }

            public Receiver[] newArray(int size) {
                return (new Receiver[size]);
            }

        };

        protected Receiver(Parcel in) {
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
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
            this.stripeCustomerId = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
            this.age = ((Long) in.readValue((Long.class.getClassLoader())));
            this.others = ((String) in.readValue((String.class.getClassLoader())));
            this.height = ((Double) in.readValue((Double.class.getClassLoader())));
            this.fitnessLevel = ((FitnessLevel) in.readValue((FitnessLevel.class.getClassLoader())));
        }

        public Receiver() {
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

    public class Message implements Parcelable {

        @SerializedName("id")
        @Expose
        public Long id;
        @SerializedName("sender")
        @Expose
        public Sender sender;
        @SerializedName("receiver")
        @Expose
        public Receiver_ receiver;
        @SerializedName("message")
        @Expose
        public String message;
        @SerializedName("createdAt")
        @Expose
        public String createdAt;
        public final Parcelable.Creator<Message> CREATOR = new Creator<Message>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Message createFromParcel(Parcel in) {
                return new Message(in);
            }

            public Message[] newArray(int size) {
                return (new Message[size]);
            }

        };

        protected Message(Parcel in) {
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
            this.sender = ((Sender) in.readValue((Sender.class.getClassLoader())));
            this.receiver = ((Receiver_) in.readValue((Receiver_.class.getClassLoader())));
            this.message = ((String) in.readValue((String.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Message() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(sender);
            dest.writeValue(receiver);
            dest.writeValue(message);
            dest.writeValue(createdAt);
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

    public class Sender implements Parcelable {

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
        public FitnessLevel_ fitnessLevel;
        public final Parcelable.Creator<Sender> CREATOR = new Creator<Sender>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Sender createFromParcel(Parcel in) {
                return new Sender(in);
            }

            public Sender[] newArray(int size) {
                return (new Sender[size]);
            }

        };

        protected Sender(Parcel in) {
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
            this.fitnessLevel = ((FitnessLevel_) in.readValue((FitnessLevel_.class.getClassLoader())));
        }

        public Sender() {
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

    public class Receiver_ implements Parcelable {

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
        public FitnessLevel__ fitnessLevel;
        public final Parcelable.Creator<Receiver_> CREATOR = new Creator<Receiver_>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Receiver_ createFromParcel(Parcel in) {
                return new Receiver_(in);
            }

            public Receiver_[] newArray(int size) {
                return (new Receiver_[size]);
            }

        };

        protected Receiver_(Parcel in) {
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
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
            this.stripeCustomerId = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
            this.age = ((Long) in.readValue((Long.class.getClassLoader())));
            this.others = ((String) in.readValue((String.class.getClassLoader())));
            this.height = ((Double) in.readValue((Double.class.getClassLoader())));
            this.fitnessLevel = ((FitnessLevel__) in.readValue((FitnessLevel__.class.getClassLoader())));
        }

        public Receiver_() {
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

    public class FitnessLevel_ implements Parcelable {

        @SerializedName("level")
        @Expose
        public String level;
        @SerializedName("id")
        @Expose
        public Long id;
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
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
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


    public class FitnessLevel__ implements Parcelable {

        @SerializedName("level")
        @Expose
        public String level;
        @SerializedName("id")
        @Expose
        public Long id;
        public final Parcelable.Creator<FitnessLevel__> CREATOR = new Creator<FitnessLevel__>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public FitnessLevel__ createFromParcel(Parcel in) {
                return new FitnessLevel__(in);
            }

            public FitnessLevel__[] newArray(int size) {
                return (new FitnessLevel__[size]);
            }

        };

        protected FitnessLevel__(Parcel in) {
            this.level = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        }

        public FitnessLevel__() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(level);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Role__ implements Parcelable {

        @SerializedName("id")
        @Expose
        public Long id;
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
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
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
}