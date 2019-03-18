package com.example.earthquakecomplete;

public class MyEarthquakeData {

    private float mag;
    private String place;
    private long time;

    public MyEarthquakeData(float mag, String place, long time) {
        this.mag = mag;
        this.place = place;
        this.time = time;
    }

    public float getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }
}
