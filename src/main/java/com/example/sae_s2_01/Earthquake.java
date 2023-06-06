package com.example.sae_s2_01;

public class Earthquake {

    private int id;
    private String date;
    private String time;
    private String name;
    private String type;
    private String xRGF93L93;
    private String yRGF93L93;
    private String longitudeWGS84;
    private String latitudeWGS84;
    private String magnitude;
    private String dataQuality;

    public Earthquake(int id, String date, String time, String name, String type,
                      String xRGF93L93, String yRGF93L93,
                      String  longitudeWGS84, String latiWGS84, String magnitude, String dataQuality) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.name = name;
        this.type = type;
        this.xRGF93L93 = xRGF93L93;
        this.yRGF93L93 = yRGF93L93;
        this.longiWGS84 = longitudeWGS84;
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
    public String getType(){
        return type;
    }
    public String getxRGF93L93() {return xRGF93L93;}
    public String getyRGF93L93() {return yRGF93L93;}
    public String getLongitudeWGS84(){return  longitudeWGS84;}
    public String getLatitudeWGS84(){return latitudeWGS84;}
    public String getMagnitude(){return magnitude;}
    public String getDataQuality(){return dataQuality;}

    @Override
    public String toString(){
        String earthquakeInfo = ("Id : " + id +"\n Date : "+ date + "\n Time : " + time
                + "\n Name : " + name +"\n Type : " + type
                + "\n x RGF93L93 : "+xRGF93L93+"\n y RGF93L93"
                + yRGF93L93 + "\n Longitude WSG84 : " + longiWGS84+"\n Latitude WSG94 : "
                + latiWGS84+"\n Intensité : " + magnitude + "\n Data quality : " + dataQuality+"\n");
        return  earthquakeInfo;
    }

}
