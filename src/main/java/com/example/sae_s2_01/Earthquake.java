package com.example.sae_s2_01;

public class Earthquake {

    private int id;
    private String date;
    private String heure;
    private String nom;
    private String choc;
    private String xRGF93L93;
    private String yRGF93L93;
    private String longiWGS84;
    private String latiWGS84;
    private String intensite;
    private String qualIntensEpi;

    public Earthquake(int id, String date, String heure, String nom, String choc, String xRGF93L93, String yRGF93L93, String  longiWGS84, String latiWGS84, String intensite, String qualIntensEpi) {
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.nom = nom;
        this.choc = choc;
        this.xRGF93L93 = xRGF93L93;
        this.yRGF93L93 = yRGF93L93;
        this.longiWGS84 = longiWGS84;
        this.latiWGS84 = latiWGS84;
        this.intensite = intensite;
        this.qualIntensEpi = qualIntensEpi;
    }

    public int getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public String getHeure(){
        return heure;
    }
    public String getNom(){
        return nom;
    }
    public String getChoc(){
        return choc;
    }
    public String getxRGF93L93() {return xRGF93L93;}
    public String getyRGF93L93() {return yRGF93L93;}
    public String getLongiWGS84(){return  longiWGS84;}
    public String getLatiWGS84(){return latiWGS84;}
    public String getIntensite(){return intensite;}
    public String getQualIntensEpi(){return qualIntensEpi;}

    @Override
    public String toString(){
        String earthquakeInfo = ("Id : " + id +"\n Date : "+ date + "\n Heure : " + heure + "\n Nom : " + nom +"\n Choc : " + choc + "\n x RGF93L93 : "+xRGF93L93+"\n y RGF93L93"+yRGF93L93+"\n Longitude WSG84 : "+longiWGS84+"\n Latitude WSG94 : "+latiWGS84+"\n Intensité : "+intensite + "\n Qualité intensité épicentre : "+qualIntensEpi+"\n");
        return  earthquakeInfo;
    }

}
