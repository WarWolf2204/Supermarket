package com.example.supermarket;

import android.widget.TextClock;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Model implements Serializable {
    private  String giatien;
    private  String tenmon;
    private  String thoigian;
    private  String ID;
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Model() { }
    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getGiatien() {
        return giatien;
    }

    public void setGiatien(String giatien) {
        this.giatien = giatien;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public Model(String mTenmon, String mGiatien , String mThoigian )
    {
        this.thoigian = mThoigian;
        this.tenmon = mTenmon;
        this.giatien = mGiatien;


    }
}
