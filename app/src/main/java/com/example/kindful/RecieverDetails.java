package com.example.kindful;

import android.os.Parcel;
import android.os.Parcelable;

public class RecieverDetails implements Parcelable{
    private String username;
    private String fullname;
    private String phoneNumber;
    private String userUID;
    private String isDonor;
    private  String details,amount,BankName,bankAcc,IFSCcode,Email;

    public RecieverDetails() {
    }

    protected RecieverDetails(Parcel in) {
        details = in.readString();
        amount = in.readString();
        BankName = in.readString();
        bankAcc = in.readString();
        IFSCcode = in.readString();
        userUID=in.readString();
        fullname=in.readString();
        phoneNumber=in.readString();
        isDonor=in.readString();
        username=in.readString();


    }
    public RecieverDetails(String currentUid, String usernameS, String fullnameS, String phonenoS, String isDonor,
                           String detailsS, String amountS, String bankNameS, String bankAccS, String ifsCcodeS) {
        this.userUID=currentUid;
        this.details=detailsS;
        this.BankName=bankNameS;
        this.amount=amountS;
        this.IFSCcode=ifsCcodeS;
        this.bankAcc=bankAccS;
        this.fullname=fullnameS;
        this.phoneNumber=phonenoS;
        this.isDonor=isDonor;
        this.username=usernameS;
    }

    public void RecieverDetails(){

    }
    public static final Creator<RecieverDetails> CREATOR = new Creator<RecieverDetails>() {
        @Override
        public RecieverDetails createFromParcel(Parcel in) {
            return new RecieverDetails(in);
        }

        @Override
        public RecieverDetails[] newArray(int size) {
            return new RecieverDetails[size];
        }
    };

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setIFSCcode(String IFSCcode) {
        this.IFSCcode = IFSCcode;
    }

    public String getAmount() {
        return amount;
    }

    public String getBankAcc() {
        return bankAcc;
    }

    public String getBankName() {
        return BankName;
    }

    public String getDetails() {
        return details;
    }

    public String getIFSCcode() {
        return IFSCcode;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setIsDonor(String isDonor) {
        this.isDonor = isDonor;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getIsDonor() {
        return isDonor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(details);
        parcel.writeString(amount);
        parcel.writeString(BankName);
        parcel.writeString(bankAcc);
        parcel.writeString(IFSCcode);
        parcel.writeString(userUID);
        parcel.writeString(fullname);
        parcel.writeString(username);
        parcel.writeString(phoneNumber);
        parcel.writeString(isDonor);
    }
}
