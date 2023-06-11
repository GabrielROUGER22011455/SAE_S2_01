package com.example.sae_s2_01;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;

public class HelloController {
    @FXML
    private PieChart pieChart1;
    @FXML
    private PieChart pieChart2;
    @FXML
    private LineChart<String, Number> lineChart1;
    @FXML
    private LineChart<String, Number> lineChart2;
    @FXML
    private CheckBox checkBox1;
    @FXML
    private CheckBox checkBox2;
    @FXML
    private CheckBox checkBox3;
    @FXML
    private CheckBox checkBox4;
    @FXML
    private CheckBox checkBox5;
    @FXML
    private CheckBox checkBox6;
    @FXML
    private CheckBox checkBox7;
    IntegerProperty minDate;
    IntegerProperty maxDate;
    ArrayList<Boolean> checkBoxsState;


    public void initialize() {
        checkBoxsState.set(1, checkBox1.isSelected());
        checkBoxsState.set(2, checkBox1.isSelected());
        checkBoxsState.set(3, checkBox1.isSelected());
        checkBoxsState.set(4, checkBox1.isSelected());
        checkBoxsState.set(5, checkBox1.isSelected());
        checkBoxsState.set(6, checkBox1.isSelected());
        checkBoxsState.set(7, checkBox1.isSelected());

        // Remplir le graphique en camembert avec des données de démonstration
        ObservableList<PieChart.Data> pieData1 = FXCollections.observableArrayList(
                new PieChart.Data("region 1", 10),
                new PieChart.Data("region 2", 30),
                new PieChart.Data("region 3", 20),
                new PieChart.Data("region 4", 80)
        );
        pieChart1.setData(pieData1);

        int i = 0;
        for (final PieChart.Data data : pieChart1.getData()) {
            data.getNode().getStyleClass().add("section" + (i++));
        }


        // Remplie le deuxième graphique en camembert avec des données de démonstration
        ObservableList<PieChart.Data> pieData2 = FXCollections.observableArrayList(
                new PieChart.Data("region A", 15),
                new PieChart.Data("region B", 25),
                new PieChart.Data("region C", 35),
                new PieChart.Data("region D", 25)
        );
        pieChart2.setData(pieData2);

        i = 0;
        for (final PieChart.Data data : pieChart2.getData()) {
            data.getNode().getStyleClass().add("section" + (i++));
        }



        // Remplir les graphiques linéaires avec des données de démonstration
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Séries 1");
        series1.getData().add(new XYChart.Data<>("Ville 1", 23));
        series1.getData().add(new XYChart.Data<>("Ville 2", 14));
        series1.getData().add(new XYChart.Data<>("Ville 3", 15));
        lineChart1.getData().add(series1);

        XYChart.Series<String, Number> series2 = new XYChart.Series<>(); // Create new series for lineChart2
        series2.setName("Séries 2");
        series2.getData().add(new XYChart.Data<>("Ville 1", 20));
        series2.getData().add(new XYChart.Data<>("Ville 2", 25));
        series2.getData().add(new XYChart.Data<>("Ville 3", 30));
        lineChart2.getData().add(series2); // Add series to lineChart2

        checkBox1.setOnAction(event -> {
            if (checkBox1.isSelected()) {
                System.out.println("La checkbox est cochée");
            } else {
                System.out.println("La checkbox est décochée");
            }
        });

    }

    public ArrayList<Boolean> getCheckBoxsState(){
        return checkBoxsState;
    }
}
