package com.example.kindful;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

public class TransactionModel implements Parcelable {
    private String SenderId;
    private String sendName;
    private String recieverName;
    private String RecieverId;
    private String Date;
    private String Amount;

    protected TransactionModel(Parcel in) {
        SenderId = in.readString();
        RecieverId = in.readString();
        Date = in.readString();
        Amount = in.readString();
        recieverName=in.readString();
        sendName=in.readString();
    }

    public static final Creator<TransactionModel> CREATOR = new Creator<TransactionModel>() {
        @Override
        public TransactionModel createFromParcel(Parcel in) {
            return new TransactionModel(in);
        }

        @Override
        public TransactionModel[] newArray(int size) {
            return new TransactionModel[size];
        }
    };

    public TransactionModel(String senderId, String recieverId, String date, String amount,String sendName,String recieverName) {
        this.SenderId = senderId;
        this.RecieverId = recieverId;
        this.Date = date;
        this.Amount = amount;
        this.sendName=sendName;
        this.recieverName=recieverName;

    }

    public TransactionModel() {
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getSendName() {
        return sendName;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getAmount() {
        return Amount;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setRecieverId(String recieverId) {
        RecieverId = recieverId;
    }

    public void setSenderId(String senderId) {
        SenderId = senderId;
    }

    public String getDate() {
        return Date;
    }

    public String getRecieverId() {
        return RecieverId;
    }

    public String getSenderId() {
        return SenderId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(SenderId);
        parcel.writeString(RecieverId);
        parcel.writeString(Date);
        parcel.writeString(Amount);
        parcel.writeString(sendName);
        parcel.writeString(recieverName);
    }
}
