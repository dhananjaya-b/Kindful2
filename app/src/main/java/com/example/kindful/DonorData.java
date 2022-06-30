package com.example.kindful;

import android.os.Parcel;
import android.os.Parcelable;

public class DonorData implements Parcelable{
    private String username;
    private String fullname;
    private String phoneNumber;
    private String userUID;
    private String isDonor;
    public DonorData(String currentUid, String userName, String fullName, String phoneNO,String isDonor) {
        this.username=userName;
        this.fullname=fullName;
        this.phoneNumber=phoneNO;
        this.userUID=currentUid;
        this.isDonor=isDonor;
    }
    public void DonatorData(){

    }
    protected DonorData(Parcel in){
        username=in.readString();
        fullname=in.readString();
        phoneNumber=in.readString();
        userUID=in.readString();
        isDonor=in.readString();
    }
    public static final Parcelable.Creator<DonorData> CREATOR=new Parcelable.Creator<DonorData>(){
        @Override
        public DonorData createFromParcel(Parcel in) {
            return new DonorData(in);
        }

        @Override
        public DonorData[] newArray(int size) {
            return new DonorData[size];
        }
    };
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

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userUID);
        dest.writeString(username);
        dest.writeString(fullname);
        dest.writeString(phoneNumber);
        dest.writeString(isDonor);

    }
}
