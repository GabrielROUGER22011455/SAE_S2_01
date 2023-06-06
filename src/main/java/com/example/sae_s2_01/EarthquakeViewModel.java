package com.example.sae_s2_01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeViewModel {
    private List<Earthquake>  earthquakeList;



        public void EarthquakeViewModel() {
            earthquakeList = new ArrayList<Earthquake>();

            // Load data from the CSV file
            try {
                BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/example/sae_s2_01/SisFrance_seismes_20230604151458.csv"));
                String line;
                reader.readLine();
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    int id = Integer.parseInt(data[0]);
                    String date = data[1];
                    String heure = data[2];
                    String nom = data[3];
                    String region = data[4];
                    String choc = data[5];
                    //System.out.println(data[3]);
                    float xRGF = Float.parseFloat(data[6]);
                    float yRGF = Float.parseFloat(data[7]);
                    float longiWGS84 = Float.parseFloat(data[8]);
                    float latiWSG84 = Float.parseFloat(data[9]);
                    float intensite = Float.parseFloat(data[10]);
                    String qualIntensEpi = data[11];
                    earthquakeList.add(new Earthquake(id, date, heure, nom, region, choc, xRGF, yRGF, longiWGS84, latiWSG84, intensite, qualIntensEpi));
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public List<Earthquake> getEarthquakeList() {
        return earthquakeList;
    }
}