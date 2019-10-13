package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationData implements Parcelable
{

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
    public final  Parcelable.Creator<NotificationData> CREATOR = new Creator<NotificationData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NotificationData createFromParcel(Parcel in) {
            return new NotificationData(in);
        }

        public NotificationData[] newArray(int size) {
            return (new NotificationData[size]);
        }

    }
            ;

    protected NotificationData(Parcel in) {
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public NotificationData() {
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

    public class Data implements Parcelable
    {

        @SerializedName("notifications")
        @Expose
        public List<Notification> notifications = null;
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

        }
                ;

        protected Data(Parcel in) {
            in.readList(this.notifications, (Notification.class.getClassLoader()));
        }

        public Data() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(notifications);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Notification implements Parcelable
    {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("message")
        @Expose
        public String message;
        @SerializedName("messagePt")
        @Expose
        public String messagePt;
        @SerializedName("tag")
        @Expose
        public String tag;
        @SerializedName("senderId")
        @Expose
        public int senderId;
        @SerializedName("senderName")
        @Expose
        public String senderName;
        @SerializedName("receiverId")
        @Expose
        public int receiverId;
        @SerializedName("targetId")
        @Expose
        public int targetId;
        @SerializedName("createdAt")
        @Expose
        public String createdAt;
        @SerializedName("hasSeen")
        @Expose
        public Boolean hasSeen;
        public final  Parcelable.Creator<Notification> CREATOR = new Creator<Notification>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Notification createFromParcel(Parcel in) {
                return new Notification(in);
            }

            public Notification[] newArray(int size) {
                return (new Notification[size]);
            }

        }
                ;

        protected Notification(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.message = ((String) in.readValue((String.class.getClassLoader())));
            this.messagePt = ((String) in.readValue((String.class.getClassLoader())));
            this.tag = ((String) in.readValue((String.class.getClassLoader())));
            this.senderId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.senderName = ((String) in.readValue((String.class.getClassLoader())));
            this.receiverId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.targetId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.hasSeen = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        }

        public Notification() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(message);
            dest.writeValue(messagePt);
            dest.writeValue(tag);
            dest.writeValue(senderId);
            dest.writeValue(senderName);
            dest.writeValue(receiverId);
            dest.writeValue(targetId);
            dest.writeValue(createdAt);
            dest.writeValue(hasSeen);
        }

        public int describeContents() {
            return 0;
        }

    }
}