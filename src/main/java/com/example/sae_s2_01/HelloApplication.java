package com.example.sae_s2_01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            GridPane root = FXMLLoader.load(getClass().getClassLoader().getResource("view/hello-view.fxml"));
            stage.setTitle("");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }

        EarthquakeViewModel viewModel = new EarthquakeViewModel();
        viewModel.EarthquakeViewModel();
        List<Earthquake> earthquakeList = viewModel.getEarthquakeList();

        System.out.println("Liste des séismes :");
        for (Earthquake earthquake : earthquakeList) {
            System.out.println(earthquake);
        }
        viewModel.getRegionAndNumberOfEarthquake();
        viewModel.getAvgEarthquakePerByRegion();
        System.out.println(viewModel.region);
        System.out.println(viewModel.nbrOfEarthquake);
        System.out.println(viewModel.avgEarthquake);
    }

    public static void main(String[] args) {
        launch();
    }
}