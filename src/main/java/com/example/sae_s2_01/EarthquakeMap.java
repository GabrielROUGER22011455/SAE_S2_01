package com.example.sae_s2_01;

public class EarthquakeMap {
    private int year;
    private float xPosWGS;
    private float yPosWGS;
    private float magnitude;
    private boolean showable;

    public EarthquakeMap() {

    }

    public EarthquakeMap (int year, float xPosWGS, float yPosWGS, float magnitude, boolean showable){
        this.year = year;
        this.xPosWGS = xPosWGS;
        this.yPosWGS = yPosWGS;
        this.magnitude = magnitude;
        this.showable = showable;
    }

    public int getYear(){
        return year;
    }
    public float getxPosWGS(){
        return  xPosWGS;
    }
    public float getyPosWGS(){
        return  yPosWGS;
    }
    public boolean getShowable(){
        return showable;
    }
    public float getMagnitude(){
        return magnitude;
    }

    public void setShowable(boolean showableState){
        showable = showableState;
    }

    public int setDateToYears(String date){
        String tmp = null;
        for(int index = 0; index < 4; ++index){
            tmp = tmp + date.charAt(index);
        }
        return Integer.parseInt(tmp);
    }
}
