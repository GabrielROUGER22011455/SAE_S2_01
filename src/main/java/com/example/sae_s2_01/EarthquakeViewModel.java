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
                //BufferedReader reader = new BufferedReader(new FileReader("earthquakes.csv"));
                BufferedReader reader = new BufferedReader(new FileReader("SisFrance_seismes_20230604151458"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    String location = data[0];
                    double magnitude = Double.parseDouble(data[1]);
                    earthquakeList.add(new Earthquake(location, magnitude));
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