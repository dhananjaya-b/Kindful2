package com.example.kindful;

public class model {
    private String username;
    private String fullname;
    private String phoneNumber;
    private String userUID;
    private String isDonor;
    private  String details,amount,BankName,bankAcc,IFSCcode,Email; //order and name same as backend
    public model() {
    }
    public model(String currentUid, String usernameS, String fullnameS, String phonenoS, String isDonor,
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

    public String getIFSCcode() { return IFSCcode; }
    public String getUserUID() { return userUID;}
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
    public void setUserUID(String userUID) { this.userUID = userUID; }
}