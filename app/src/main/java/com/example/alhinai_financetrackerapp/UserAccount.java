package com.example.alhinai_financetrackerapp;

import com.google.firebase.database.Exclude;

public class UserAccount {
    public String accBalance;
    public String accCharity;
    public String accSaving;
    public String accShopping;
    public String accFood;


    public String mKey;

    public UserAccount() {
    }

    public UserAccount(String accBalance, String accCharity, String accSaving, String accShopping, String accFood) {
        this.accBalance = accBalance;
        this.accCharity = accCharity;
        this.accSaving = accSaving;
        this.accShopping = accShopping;
        this.accFood = accFood;
    }

    public String getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(String accBalance) {
        this.accBalance = accBalance;
    }

    public String getAccCharity() {
        return accCharity;
    }

    public void setAccCharity(String accCharity) {
        this.accCharity = accCharity;
    }

    public String getAccSaving() {
        return accSaving;
    }

    public void setAccSaving(String accSaving) {
        this.accSaving = accSaving;
    }

    public String getAccShopping() {
        return accShopping;
    }

    public void setAccShopping(String accShopping) {
        this.accShopping = accShopping;
    }

    public String getAccFood() {
        return accFood;
    }

    public void setAccFood(String accFood) {
        this.accFood = accFood;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String Key) {
        mKey = Key;
    }
}
