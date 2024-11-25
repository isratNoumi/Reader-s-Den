package com.example.pocketguard;

import com.google.firebase.database.DatabaseReference;

public class User_helper {
    private String UserImage;
    private String UserName;
    private String Email;
    private String Address;
    private String PhoneNo;

    public User_helper() {
    }

    public User_helper(String userImage, String userName, String email, String address, String phoneNo) {
        UserImage = userImage;
        UserName = userName;
        Email = email;
        Address = address;
        PhoneNo=phoneNo;
    }


    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
