package com.coachmovecustomer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddModalitiesData implements Parcelable
{

    @SerializedName("modality")
    @Expose
    public String modality;
    @SerializedName("modalityBr")
    @Expose
    public String modalityBr;
    @SerializedName("price")
    @Expose
    public Float price;
    @SerializedName("id")
    @Expose
    public Integer id;

    public final static Parcelable.Creator<AddModalitiesData> CREATOR = new Creator<AddModalitiesData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AddModalitiesData createFromParcel(Parcel in) {
            return new AddModalitiesData(in);
        }

        public AddModalitiesData[] newArray(int size) {
            return (new AddModalitiesData[size]);
        }

    }
            ;

    protected AddModalitiesData(Parcel in) {
        this.modality = ((String) in.readValue((String.class.getClassLoader())));
        this.modalityBr = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((Float) in.readValue((Float.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public AddModalitiesData() {
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