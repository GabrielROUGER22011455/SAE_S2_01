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
                    String time = data[2];
                    String name = data[3];
                    String type = data[4];
                    String xRGF = data[5];
                    String yRGF = data[6];
                    String longitudeWGS84 = data[7];
                    String latitudeWSG84 = data[8];
                    String magnitude = data[9];
                    String dataQuality = data[10];
                    earthquakeList.add(new Earthquake(id, date, time, name, type, xRGF, yRGF, longitudeWGS84, latitudeWSG84, magnitude, dataQuality));
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