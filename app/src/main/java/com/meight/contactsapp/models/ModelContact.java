package com.meight.contactsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelContact implements Parcelable {

    private String name, number;

    public ModelContact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public ModelContact(){}




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
