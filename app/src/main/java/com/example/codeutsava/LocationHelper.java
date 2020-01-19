package com.example.codeutsava;

public class LocationHelper  {
    private double Longitute;
    private double Latitude;

    public LocationHelper(double latitude,double longitude) {
        Latitude = latitude;
        Longitute = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitute() {
        return Longitute;
    }

    public void setLongitute(double longitute) {
        Longitute = longitute;
    }
}
