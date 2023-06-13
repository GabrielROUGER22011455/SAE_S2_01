package EarthquakeJavaFX;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Controller {
    @FXML
    private PieChart pieChart1;
    @FXML
    private PieChart pieChart2;
    @FXML
    private LineChart<String, Number> lineChart1;
    @FXML
    private LineChart<String, Number> lineChart2;
    @FXML
    private VBox checkBoxes;
    private ArrayList<Boolean> checkBoxState = new ArrayList<>();
    private EarthquakeData data ;
    public void initialize() {
        data = new EarthquakeData();
        for (Node node : checkBoxes.getChildren()) {
            CheckBox checkBox = (CheckBox) node;
            checkBoxState.add(checkBox.isSelected());
        }

        ObservableList<PieChart.Data> pieData1 = FXCollections.observableArrayList();

        // PieChart about most hit regions
        //   Get informations
        ArrayList<String> mostHitRegions = data.getMostHitRegions(4);
        HashMap<String, Integer> earthquakesPerRegion = data.getEarthquakePerRegion();
        //   Fill the values list
        ArrayList<Integer> mostEarthquakes = new ArrayList<Integer>();
        for (String region : mostHitRegions) {
            mostEarthquakes.add(earthquakesPerRegion.get(region));
        }
        //   Create pieChart
        for (int index = 0; index < mostHitRegions.size(); ++index) {
            pieData1.add(new PieChart.Data(mostHitRegions.get(index), mostEarthquakes.get(index)));
        }
        pieChart1.setData(pieData1);
        int i = 0;
        for (final PieChart.Data data : pieChart1.getData()) {
            data.getNode().getStyleClass().add("section" + (i++));
        }

        ObservableList<PieChart.Data> pieData2 = FXCollections.observableArrayList();
        for (int index = 0; index < data.getTypes().size(); ++index) {
            pieData2.add(new PieChart.Data(data.getTypes().get(index), data.getTypeFrequency().get(index)));
        }
        pieChart2.setData(pieData2);
        i = 0;
        for (final PieChart.Data data : pieChart2.getData()) {
            data.getNode().getStyleClass().add("section" + (i++));
        }

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        for (int index = 0; index < data.getDecades().size(); ++index) {
            series1.getData().add(new XYChart.Data<>(data.getDecades().get(index).toString() + "-"
                    + (data.getDecades().get(index) + 1), data.getEarthquakePerDecade().get(index)));
        }
        series1.setName("Nombre de séisme par décennie");
        lineChart1.getData().add(series1);

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Séries 2");
        series2.getData().add(new XYChart.Data<>("Ville 1", 20));
        series2.getData().add(new XYChart.Data<>("Ville 2", 25));
        series2.getData().add(new XYChart.Data<>("Ville 3", 30));
        lineChart2.getData().add(series2);

        CheckBox tmpCheckBox = (CheckBox) checkBoxes.getChildren().get(0);
        tmpCheckBox.setOnAction(event -> {
            if (tmpCheckBox.isSelected()) {
                System.out.println("La checkbox est cochée");
            } else {
                System.out.println("La checkbox est décochée");
            }
        });
    }
}
