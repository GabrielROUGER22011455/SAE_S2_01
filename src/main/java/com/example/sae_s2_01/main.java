package com.example.sae_s2_01;

import java.util.List;

public class main {

    public static void main(String[] args) {
        EarthquakeViewModel viewModel = new EarthquakeViewModel();
        viewModel.EarthquakeViewModel();
        List<Earthquake> earthquakeList = viewModel.getEarthquakeList();

        System.out.println("Liste des séismes :");
        for (Earthquake earthquake : earthquakeList) {
            //System.out.println("Ville : " + earthquake.getLocation());
            //System.out.println("Magnitude : " + earthquake.getMagnitude());
            System.out.println();
        }
    }
}
