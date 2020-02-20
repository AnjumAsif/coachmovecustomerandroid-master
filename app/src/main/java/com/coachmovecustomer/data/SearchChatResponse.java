package com.coachmovecustomer.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SearchChatResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public class Role {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("role")
        @Expose
        private String role;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

    }

    public class Role_ {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("role")
        @Expose
        private String role;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

    }

    public class Role__ {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("role")
        @Expose
        private String role;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

    }

    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("accountId")
        @Expose
        private String accountId;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("aboutMe")
        @Expose
        private String aboutMe;
        @SerializedName("cpfNo")
        @Expose
        private String cpfNo;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("height")
        @Expose
        private String height;
        @SerializedName("surgeries")
        @Expose
        private String surgeries;
        @SerializedName("heartDiseases")
        @Expose
        private String heartDiseases;
        @SerializedName("jointPains")
        @Expose
        private String jointPains;
        @SerializedName("medication")
        @Expose
        private String medication;
        @SerializedName("others")
        @Expose
        private String others;
        @SerializedName("profilePicPath")
        @Expose
        private String profilePicPath;
        @SerializedName("crefCrnPath")
        @Expose
        private String crefCrnPath;
        @SerializedName("universityDocPath")
        @Expose
        private String universityDocPath;
        @SerializedName("criminalDocPath")
        @Expose
        private String criminalDocPath;
        @SerializedName("otherDocPath")
        @Expose
        private String otherDocPath;
        @SerializedName("isOnline")
        @Expose
        private String isOnline;
        @SerializedName("enabled")
        @Expose
        private Boolean enabled;
        @SerializedName("lastPasswordResetDate")
        @Expose
        private String lastPasswordResetDate;
        @SerializedName("verified")
        @Expose
        private String verified;
        @SerializedName("stripeCustomerId")
        @Expose
        private String stripeCustomerId;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("avgRating")
        @Expose
        private String avgRating;
        @SerializedName("comments")
        @Expose
        private String comments;
        @SerializedName("panelityCount")
        @Expose
        private String panelityCount;
        @SerializedName("wirecardAccessToken")
        @Expose
        private String wirecardAccessToken;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("bankId")
        @Expose
        private String bankId;
        @SerializedName("street")
        @Expose
        private String street;
        @SerializedName("streetNumber")
        @Expose
        private String streetNumber;
        @SerializedName("district")
        @Expose
        private String district;
        @SerializedName("zipCode")
        @Expose
        private String zipCode;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("deviceId")
        @Expose
        private String deviceId;
        @SerializedName("fitnessLevel")
        @Expose
        private String fitnessLevel;
        @SerializedName("role")
        @Expose
        private Role role;
        @SerializedName("coach")
        @Expose
        private Coach coach;
        @SerializedName("customer")
        @Expose
        private Customer customer;
        @SerializedName("admin")
        @Expose
        private Admin admin;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAboutMe() {
            return aboutMe;
        }

        public void setAboutMe(String aboutMe) {
            this.aboutMe = aboutMe;
        }

        public String getCpfNo() {
            return cpfNo;
        }

        public void setCpfNo(String cpfNo) {
            this.cpfNo = cpfNo;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getSurgeries() {
            return surgeries;
        }

        public void setSurgeries(String surgeries) {
            this.surgeries = surgeries;
        }

        public String getHeartDiseases() {
            return heartDiseases;
        }

        public void setHeartDiseases(String heartDiseases) {
            this.heartDiseases = heartDiseases;
        }

        public String getJointPains() {
            return jointPains;
        }

        public void setJointPains(String jointPains) {
            this.jointPains = jointPains;
        }

        public String getMedication() {
            return medication;
        }

        public void setMedication(String medication) {
            this.medication = medication;
        }

        public String getOthers() {
            return others;
        }

        public void setOthers(String others) {
            this.others = others;
        }

        public String getProfilePicPath() {
            return profilePicPath;
        }

        public void setProfilePicPath(String profilePicPath) {
            this.profilePicPath = profilePicPath;
        }

        public String getCrefCrnPath() {
            return crefCrnPath;
        }

        public void setCrefCrnPath(String crefCrnPath) {
            this.crefCrnPath = crefCrnPath;
        }

        public String getUniversityDocPath() {
            return universityDocPath;
        }

        public void setUniversityDocPath(String universityDocPath) {
            this.universityDocPath = universityDocPath;
        }

        public String getCriminalDocPath() {
            return criminalDocPath;
        }

        public void setCriminalDocPath(String criminalDocPath) {
            this.criminalDocPath = criminalDocPath;
        }

        public String getOtherDocPath() {
            return otherDocPath;
        }

        public void setOtherDocPath(String otherDocPath) {
            this.otherDocPath = otherDocPath;
        }

        public String getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(String isOnline) {
            this.isOnline = isOnline;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public String getLastPasswordResetDate() {
            return lastPasswordResetDate;
        }

        public void setLastPasswordResetDate(String lastPasswordResetDate) {
            this.lastPasswordResetDate = lastPasswordResetDate;
        }

        public String getVerified() {
            return verified;
        }

        public void setVerified(String verified) {
            this.verified = verified;
        }

        public String getStripeCustomerId() {
            return stripeCustomerId;
        }

        public void setStripeCustomerId(String stripeCustomerId) {
            this.stripeCustomerId = stripeCustomerId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(String avgRating) {
            this.avgRating = avgRating;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getPanelityCount() {
            return panelityCount;
        }

        public void setPanelityCount(String panelityCount) {
            this.panelityCount = panelityCount;
        }

        public String getWirecardAccessToken() {
            return wirecardAccessToken;
        }

        public void setWirecardAccessToken(String wirecardAccessToken) {
            this.wirecardAccessToken = wirecardAccessToken;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getBankId() {
            return bankId;
        }

        public void setBankId(String bankId) {
            this.bankId = bankId;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getFitnessLevel() {
            return fitnessLevel;
        }

        public void setFitnessLevel(String fitnessLevel) {
            this.fitnessLevel = fitnessLevel;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Coach getCoach() {
            return coach;
        }

        public void setCoach(Coach coach) {
            this.coach = coach;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public Admin getAdmin() {
            return admin;
        }

        public void setAdmin(Admin admin) {
            this.admin = admin;
        }

    }

    public class Admin {

        @SerializedName("accountId")
        @Expose
        private String accountId;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("profilePicPath")
        @Expose
        private String profilePicPath;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("email")
        @Expose
        private String email;

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getProfilePicPath() {
            return profilePicPath;
        }

        public void setProfilePicPath(String profilePicPath) {
            this.profilePicPath = profilePicPath;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }

    public class Coach {

        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("zipCode")
        @Expose
        private String zipCode;
        @SerializedName("universityDocPath")
        @Expose
        private String universityDocPath;
        @SerializedName("role")
        @Expose
        private Role_ role;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("panelityCount")
        @Expose
        private String panelityCount;
        @SerializedName("isOnline")
        @Expose
        private String isOnline;
        @SerializedName("aboutMe")
        @Expose
        private String aboutMe;
        @SerializedName("userModalities")
        @Expose
        private List<Object> userModalities = null;
        @SerializedName("street")
        @Expose
        private String street;
        @SerializedName("avgRating")
        @Expose
        private String avgRating;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("cpfNo")
        @Expose
        private String cpfNo;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("comments")
        @Expose
        private String comments;
        @SerializedName("otherDocPath")
        @Expose
        private String otherDocPath;
        @SerializedName("streetNumber")
        @Expose
        private String streetNumber;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("criminalDocPath")
        @Expose
        private String criminalDocPath;
        @SerializedName("crefCrnPath")
        @Expose
        private String crefCrnPath;
        @SerializedName("accountId")
        @Expose
        private String accountId;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("profilePicPath")
        @Expose
        private String profilePicPath;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("district")
        @Expose
        private String district;
        @SerializedName("stripeCustomerId")
        @Expose
        private String stripeCustomerId;

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getUniversityDocPath() {
            return universityDocPath;
        }

        public void setUniversityDocPath(String universityDocPath) {
            this.universityDocPath = universityDocPath;
        }

        public Role_ getRole() {
            return role;
        }

        public void setRole(Role_ role) {
            this.role = role;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPanelityCount() {
            return panelityCount;
        }

        public void setPanelityCount(String panelityCount) {
            this.panelityCount = panelityCount;
        }

        public String getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(String isOnline) {
            this.isOnline = isOnline;
        }

        public String getAboutMe() {
            return aboutMe;
        }

        public void setAboutMe(String aboutMe) {
            this.aboutMe = aboutMe;
        }

        public List<Object> getUserModalities() {
            return userModalities;
        }

        public void setUserModalities(List<Object> userModalities) {
            this.userModalities = userModalities;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(String avgRating) {
            this.avgRating = avgRating;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCpfNo() {
            return cpfNo;
        }

        public void setCpfNo(String cpfNo) {
            this.cpfNo = cpfNo;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getOtherDocPath() {
            return otherDocPath;
        }

        public void setOtherDocPath(String otherDocPath) {
            this.otherDocPath = otherDocPath;
        }

        public String getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCriminalDocPath() {
            return criminalDocPath;
        }

        public void setCriminalDocPath(String criminalDocPath) {
            this.criminalDocPath = criminalDocPath;
        }

        public String getCrefCrnPath() {
            return crefCrnPath;
        }

        public void setCrefCrnPath(String crefCrnPath) {
            this.crefCrnPath = crefCrnPath;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getProfilePicPath() {
            return profilePicPath;
        }

        public void setProfilePicPath(String profilePicPath) {
            this.profilePicPath = profilePicPath;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getStripeCustomerId() {
            return stripeCustomerId;
        }

        public void setStripeCustomerId(String stripeCustomerId) {
            this.stripeCustomerId = stripeCustomerId;
        }

    }

    public class Customer {

        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("role")
        @Expose
        private Role__ role;
        @SerializedName("comments")
        @Expose
        private String comments;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("surgeries")
        @Expose
        private String surgeries;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("medication")
        @Expose
        private String medication;
        @SerializedName("accountId")
        @Expose
        private String accountId;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("profilePicPath")
        @Expose
        private String profilePicPath;
        @SerializedName("jointPains")
        @Expose
        private String jointPains;
        @SerializedName("heartDiseases")
        @Expose
        private String heartDiseases;
        @SerializedName("avgRating")
        @Expose
        private String avgRating;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("stripeCustomerId")
        @Expose
        private String stripeCustomerId;
        @SerializedName("cpfNo")
        @Expose
        private String cpfNo;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("others")
        @Expose
        private String others;
        @SerializedName("height")
        @Expose
        private String height;
        @SerializedName("fitnessLevel")
        @Expose
        private String fitnessLevel;

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Role__ getRole() {
            return role;
        }

        public void setRole(Role__ role) {
            this.role = role;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getSurgeries() {
            return surgeries;
        }

        public void setSurgeries(String surgeries) {
            this.surgeries = surgeries;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getMedication() {
            return medication;
        }

        public void setMedication(String medication) {
            this.medication = medication;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getProfilePicPath() {
            return profilePicPath;
        }

        public void setProfilePicPath(String profilePicPath) {
            this.profilePicPath = profilePicPath;
        }

        public String getJointPains() {
            return jointPains;
        }

        public void setJointPains(String jointPains) {
            this.jointPains = jointPains;
        }

        public String getHeartDiseases() {
            return heartDiseases;
        }

        public void setHeartDiseases(String heartDiseases) {
            this.heartDiseases = heartDiseases;
        }

        public String getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(String avgRating) {
            this.avgRating = avgRating;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStripeCustomerId() {
            return stripeCustomerId;
        }

        public void setStripeCustomerId(String stripeCustomerId) {
            this.stripeCustomerId = stripeCustomerId;
        }

        public String getCpfNo() {
            return cpfNo;
        }

        public void setCpfNo(String cpfNo) {
            this.cpfNo = cpfNo;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getOthers() {
            return others;
        }

        public void setOthers(String others) {
            this.others = others;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getFitnessLevel() {
            return fitnessLevel;
        }

        public void setFitnessLevel(String fitnessLevel) {
            this.fitnessLevel = fitnessLevel;
        }

    }

    public class Data {

        @SerializedName("users")
        @Expose
        private List<User> users = null;

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }

    }

}
