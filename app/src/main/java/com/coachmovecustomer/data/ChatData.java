package com.coachmovecustomer.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChatData {
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

    @SerializedName("block")
    @Expose
    public boolean mBlock;

    public class Data {
        @SerializedName("messages")
        @Expose
        public ArrayList<ChatMessage> messagesList = null;
    }

    public class ChatMessage {

        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("sender")
        @Expose
        public Sender senderBy;
        @SerializedName("receiver")
        @Expose
        public Sender receiverBy;
        @SerializedName("message")
        @Expose
        public String message;
        @SerializedName("createdAt")
        @Expose
        public String createdAt;

    }

    public class Sender {
        @SerializedName("lastName")
        @Expose
        public String lastName;
        public String gender;
        @SerializedName("mobile")
        @Expose
        public String mobile;
        @SerializedName("firstName")
        @Expose
        public String firstName;
        @SerializedName("profilePicPath")
        @Expose
        public String profilePicPath;
        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("age")
        @Expose
        public int age;
    }
}