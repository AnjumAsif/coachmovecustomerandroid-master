package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Cards implements Parcelable {

    @SerializedName("cardNo")
    @Expose
    public String cardNo;
    @SerializedName("expiryDate")
    @Expose
    public String expiryDate;
    @SerializedName("cvv")
    @Expose
    public String cvv;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("cardToken")
    @Expose
    public String cardToken;
    /*  @SerializedName("addedBy")
      @Expose
      public AddedBy addedBy;*/
    @SerializedName("createdAt")
    @Expose
    public String createdAt;
    @SerializedName("lastUpdatedAt")
    @Expose
    public String lastUpdatedAt;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    public final static Parcelable.Creator<Cards> CREATOR = new Creator<Cards>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Cards createFromParcel(Parcel in) {
            return new Cards(in);
        }

        public Cards[] newArray(int size) {
            return (new Cards[size]);
        }

    };

    protected Cards(Parcel in) {
        this.cardNo = ((String) in.readValue((String.class.getClassLoader())));
        this.expiryDate = ((String) in.readValue((String.class.getClassLoader())));
        this.cvv = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.cardToken = ((String) in.readValue((String.class.getClassLoader())));
//        this.addedBy = ((AddedBy) in.readValue((AddedBy.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Cards() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cardNo);
        dest.writeValue(expiryDate);
        dest.writeValue(cvv);
        dest.writeValue(type);
        dest.writeValue(cardToken);
//        dest.writeValue(addedBy);
        dest.writeValue(createdAt);
        dest.writeValue(lastUpdatedAt);
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }
}















/*

public class Cards implements Parcelable {

    @SerializedName("cardNo")
    @Expose
    public String cardNo;
    @SerializedName("expiryDate")
    @Expose
    public String expiryDate;
    @SerializedName("cvv")
    @Expose
    public String cvv;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("cardToken")
    @Expose
    public String cardToken;
    @SerializedName("createdAt")
    @Expose
    public String createdAt;
    @SerializedName("lastUpdatedAt")
    @Expose
    public String lastUpdatedAt;
    @SerializedName("id")
    @Expose
    public int id;
    public final static Parcelable.Creator<Cards> CREATOR = new Creator<Cards>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Cards createFromParcel(Parcel in) {
            return new Cards(in);
        }

        public Cards[] newArray(int size) {
            return (new Cards[size]);
        }

    };

    protected Cards(Parcel in) {
        this.cardNo = ((String) in.readValue((String.class.getClassLoader())));
        this.expiryDate = ((String) in.readValue((String.class.getClassLoader())));
        this.cvv = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.cardToken = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Cards() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cardNo);
        dest.writeValue(expiryDate);
        dest.writeValue(cvv);
        dest.writeValue(type);
        dest.writeValue(cardToken);
        dest.writeValue(createdAt);
        dest.writeValue(lastUpdatedAt);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}*/
