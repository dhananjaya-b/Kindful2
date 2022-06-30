package com.example.kindful;

public class UserData {
    private String username;
    private String fullname;
    private String phoneNumber;
    private String userUID;
    private String isDonor;

    public UserData() {
    }

    public UserData(String fullName , String isDonor , String phoneNO , String currentUid, String userName) {
        this.username=userName;
        this.fullname=fullName;
        this.phoneNumber=phoneNO;
        this.userUID=currentUid;
        this.isDonor=isDonor;
    }
    public  void setIsDonor(String isDonor){
        this.isDonor=isDonor;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setFullname(String fullname){
        this.fullname=fullname;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public void setUserUID(String userUID){
        this.userUID=userUID;
    }
    public String getUsername(){
        return username;
    }
    public String getFullname(){
        return fullname;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getUserUID(){
        return userUID;
    }
    public String getIsDonor(){
        return isDonor;
    }
}
