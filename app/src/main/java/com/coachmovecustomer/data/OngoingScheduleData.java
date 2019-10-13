package com.coachmovecustomer.data;

public class OngoingScheduleData {


    private String userNameSch, session, address, date, time, ongoing;
    int profileImg;


    public OngoingScheduleData() {
    }

    public String getOngoing() {
        return ongoing;
    }

    public void setOngoing(String ongoing) {
        this.ongoing = ongoing;
    }

    public OngoingScheduleData(String userNameSch, String session, String address, String date, String time, String ongoing, int profileImg) {
        this.userNameSch = userNameSch;
        this.session = session;
        this.address = address;
        this.date = date;
        this.time = time;
        this.ongoing = ongoing;
        this.profileImg = profileImg;
    }


    public String getUserNameSch() {
        return userNameSch;
    }

    public void setUserNameSch(String userNameSch) {
        this.userNameSch = userNameSch;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(int profileImg) {
        this.profileImg = profileImg;
    }


}