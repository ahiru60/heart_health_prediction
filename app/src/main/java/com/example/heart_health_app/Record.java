package com.example.heart_health_app;

public class Record {
    private String timeStamp;
    private String heartRate;
    private String prediction;
    private String id;
    public Record(String heartRate, String prediction,String timeStamp,String id) {
        this.heartRate = heartRate;
        this.prediction = prediction;
        this.timeStamp = timeStamp;
        this.id = id;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public String getPrediction() {
        return prediction;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getId(){ return id;}
}

