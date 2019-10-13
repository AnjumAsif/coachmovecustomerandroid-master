package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutUsData implements Parcelable {

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
    public final static Parcelable.Creator<AboutUsData> CREATOR = new Creator<AboutUsData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AboutUsData createFromParcel(Parcel in) {
            return new AboutUsData(in);
        }

        public AboutUsData[] newArray(int size) {
            return (new AboutUsData[size]);
        }

    };

    protected AboutUsData(Parcel in) {
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AboutUsData() {
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

        @SerializedName("aboutus")
        @Expose
        public Aboutus aboutus;
        public final Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


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
            this.aboutus = ((Aboutus) in.readValue((Aboutus.class.getClassLoader())));
        }

        public Data() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(aboutus);
        }

        public int describeContents() {
            return 0;
        }

    }

    public class Aboutus implements Parcelable
    {

        @SerializedName("text")
        @Expose
        public String text;
        @SerializedName("id")
        @Expose
        public Long id;
        public final  Parcelable.Creator<Aboutus> CREATOR = new Creator<Aboutus>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Aboutus createFromParcel(Parcel in) {
                return new Aboutus(in);
            }

            public Aboutus[] newArray(int size) {
                return (new Aboutus[size]);
            }

        }
                ;

        protected Aboutus(Parcel in) {
            this.text = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        }

        public Aboutus() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(text);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }
}