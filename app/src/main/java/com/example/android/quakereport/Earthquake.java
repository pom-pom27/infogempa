package com.example.android.quakereport;

public class Earthquake {
    private String mPlace, mMag;
    private long timeInMiliSec;

    public Earthquake(String mag, String place, long date) {
        mMag = mag;
        mPlace = place;
        timeInMiliSec = date;
    }

    public String getmMag() {
        return mMag;
    }

    public String getmPlace() {
        return mPlace;
    }

    public long getTimeInMiliSec() {
        return timeInMiliSec;
    }
}
