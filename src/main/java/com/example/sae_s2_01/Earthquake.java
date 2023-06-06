package com.example.sae_s2_01;

public class Earthquake {

    private int id;
    private String date;
    private String time;
    private String name;
    private String region;
    private String type;
    private float xRGF93L93;
    private float yRGF93L93;
    private float longitudeWGS84;
    private float latitudeWGS84;
    private float magnitude;
    private String dataQuality;

    public Earthquake(int id, String date, String time, String name, String state,
                      String type,float xRGF93L93, float yRGF93L93,
                      float  longitudeWGS84, float latitudeWGS84, float magnitude, String dataQuality) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.name = name;
        this.region = region;
        this.type = type;
        this.xRGF93L93 = xRGF93L93;
        this.yRGF93L93 = yRGF93L93;
        this.longitudeWGS84 = longitudeWGS84;
        this.latitudeWGS84 = latitudeWGS84;
        this.magnitude = magnitude;
        this.dataQuality = dataQuality;
    }
    public int getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public String getTime(){
        return time;
    }
    public String getName(){
        return name;
    }
    public String getRegion(){
        return region;
    }
    public String getType(){
        return type;
    }
    public float getxRGF93L93() {return xRGF93L93;}
    public float getyRGF93L93() {return yRGF93L93;}
    public float getLongitudeWGS84(){return  longitudeWGS84;}
    public float getLatitudeWGS84(){return latitudeWGS84;}
    public float getMagnitude(){return magnitude;}
    public String getDataQuality(){return dataQuality;}

    @Override
    public String toString(){
        String earthquakeInfo = ("Id : " + id +"\n Date : "+ date + "\n Time : " + time
                + "\n Name : " + name +"\n Type : " + type + "\n x RGF93L93 : "+xRGF93L93+"\n y RGF93L93"
                + yRGF93L93 + "\n Longitude WSG84 : " + longitudeWGS84+"\n Latitude WSG94 : "
                + latitudeWGS84+"\n Intensité : " + magnitude + "\n Data quality : " + dataQuality+"\n");
        return  earthquakeInfo;
    }

}
