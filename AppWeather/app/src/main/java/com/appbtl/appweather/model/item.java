package com.appbtl.appweather.model;

import java.io.Serializable;

public class item{
    private String weather;
    private String maxTemp;
    private String minTemp;
    private String date;
    private String status;

    public String getWeather() {
        return weather;
    }


    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}