package com.example.info3;

public class Target {
    public String mName;
    public double mLatitude ;
    public double mLongitude ;
    public float x, y = 0;


    public Target(double newLatitude, double newLongitude, String newName) {
        this. mName = newName;
        this. mLatitude = newLatitude;
        this. mLongitude = newLongitude;
    }

    public String getPoiName() {
        return mName;
    }
    public double getPoiLatitude() {
        return mLatitude;
    }
    public double getPoiLongitude() {
        return mLongitude;
    }
    public void setNewInfo(double newLatitude, double newLongitude){
        this. mLatitude = newLatitude;
        this. mLongitude = newLongitude;
    }
}