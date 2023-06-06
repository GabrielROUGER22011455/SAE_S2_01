package com.example.sae_s2_01;

public class StringInt {
    // Type used in the separateString function from EarthQuakeViewModel
    // Might be deleted later if we find any better solution
    private String str;
    private int nbr;
    public StringInt (String str, int nbr){
        this.str = str;
        this.nbr = nbr;
    }
    public String getStr() {return str;}
    public int getNbr() {return nbr;}
}
