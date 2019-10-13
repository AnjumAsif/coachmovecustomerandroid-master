package com.coachmovecustomer.data;

public class CompleteScheduleData {


    public String header_TV;
    public String userName_TV;
    public String userID_TV;
    public String address_TV;
    public String date_TV;
    public String time_TV;
    public String comment_TV;
    public String calenderdateTV;
    public String calDayTV;
    float ratingBar;
    int profile_Img ;

    public CompleteScheduleData() {
    }


    public CompleteScheduleData(String header_TV, String userName_TV, String userID_TV, String address_TV, String date_TV, String time_TV, String comment_TV, String calenderdateTV, String calDayTV, int profile_Img, float ratingBar) {
        this.header_TV = header_TV;
        this.userName_TV = userName_TV;
        this.userID_TV = userID_TV;
        this.address_TV = address_TV;
        this.date_TV = date_TV;
        this.time_TV = time_TV;
        this.comment_TV = comment_TV;
        this.calenderdateTV = calenderdateTV;
        this.calDayTV = calDayTV;
        this.profile_Img = profile_Img;
        this.ratingBar = ratingBar;
    }


    public String getHeader_TV() {
        return header_TV;
    }

    public void setHeader_TV(String header_TV) {
        this.header_TV = header_TV;
    }

    public String getUserName_TV() {
        return userName_TV;
    }

    public void setUserName_TV(String userName_TV) {
        this.userName_TV = userName_TV;
    }

    public String getUserID_TV() {
        return userID_TV;
    }

    public void setUserID_TV(String userID_TV) {
        this.userID_TV = userID_TV;
    }

    public String getAddress_TV() {
        return address_TV;
    }

    public void setAddress_TV(String address_TV) {
        this.address_TV = address_TV;
    }

    public String getDate_TV() {
        return date_TV;
    }

    public void setDate_TV(String date_TV) {
        this.date_TV = date_TV;
    }

    public String getTime_TV() {
        return time_TV;
    }

    public void setTime_TV(String time_TV) {
        this.time_TV = time_TV;
    }

    public String getComment_TV() {
        return comment_TV;
    }

    public void setComment_TV(String comment_TV) {
        this.comment_TV = comment_TV;
    }

    public int getProfile_Img() {
        return profile_Img;
    }

    public void setProfile_Img(int profile_Img) {
        this.profile_Img = profile_Img;
    }

    public float getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(float ratingBar) {
        this.ratingBar = ratingBar;
    }

    public String getCalenderdateTV() {
        return calenderdateTV;
    }

    public void setCalenderdateTV(String calenderdateTV) {
        this.calenderdateTV = calenderdateTV;
    }

    public String getCalDayTV() {
        return calDayTV;
    }

    public void setCalDayTV(String calDayTV) {
        this.calDayTV = calDayTV;
    }


}