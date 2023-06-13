package EarthquakeJavaFX;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import java.awt.*;
import java.awt.image.ImageProducer;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.Character.isDigit;

public class Controller {
    @FXML
    private PieChart pieChart1;
    @FXML
    private PieChart pieChart2;
    @FXML
    private LineChart<String, Number> lineChart1;
    @FXML
    private BarChart<String, Number> barChart1;
    @FXML
    private VBox checkBoxes;
    @FXML
    private Slider startDate;
    @FXML
    private Slider endDate;
    @FXML
    private Button chooseFileButton;
    private EarthquakeData data;
    @FXML
    private MapView map;

    public void initialize() {
        createEvents();
    }
    private void refresh() {
        // Refresh the interface

        earthquakeTypesPieChart();
        mostHitRegionsPieChart();
        earthquakesPerCentury();
        barChart();

        startDate.setMin(data.getMinYear());
        startDate.setMax(data.getMaxYear());
        endDate.setMin(data.getMinYear());
        endDate.setMax(data.getMaxYear());

        for(Earthquake earthquake : this.data.getEarthquakeList()){
            MapPoint earthquakeOnMap = new MapPoint(earthquake.getxPosWGS(), earthquake.getyPosWGS());
            map.addLayer(new CustomCircleMarkerLayer(earthquakeOnMap));
        }
    }
    private void barChart(){
        barChart1.getData().clear();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        for (int index = 0; index < data.getCenturies().size(); ++index) {
            series2.getData().add(new XYChart.Data<>(data.getCenturies().get(index).toString() + "-"
                    + (data.getCenturies().get(index) + 1), data.getEarthquakePerCentury().get(index)));
        }
        series2.setName("Nombre de séisme par décennie");
        barChart1.getData().add(series2);
    }
    private void createEvents() {
        chooseFileButton.setOnAction(event -> handleChooseFileButton());
        startDate.setOnMouseReleased(event -> {
            data.yearFilter((int) startDate.getValue(), (int) endDate.getValue());
            if (data != null) refresh();
        });
        endDate.setOnMouseReleased(event -> {
            data.yearFilter((int) startDate.getValue(), (int) endDate.getValue());
            if (data != null) refresh();
        });
        for (Node node : checkBoxes.getChildren()) {
            CheckBox checkbox = (CheckBox) node ;
            String source = checkbox.getText();
            char lastChar = source.charAt(source.length()-1);
            checkbox.selectedProperty().addListener(
                    (ObservableValue<? extends Boolean> observable, Boolean old_val, Boolean new_val) -> {
                        int magnitude;
                        if (isDigit(lastChar)) {magnitude = Character.getNumericValue(lastChar);}
                        else {magnitude = 10;}

                        if (new_val) data.magnitudeFilterChecked(magnitude);
                        else data.magnitudeFilterUnchecked(magnitude);

                        refresh();
                    });

        }
    }
    private void earthquakeTypesPieChart(){
        // PieChart about types of earthquakes
        ObservableList<PieChart.Data> pieData2 = FXCollections.observableArrayList();
        // Get informations
        for (int index = 0; index < data.getTypes().size(); ++index) {
            pieData2.add(new PieChart.Data(data.getTypes().get(index), data.getTypeFrequency().get(index)));
        }
        // Create pieChart
        pieChart2.setData(pieData2);
        int i = 0;
        for (final PieChart.Data data : pieChart2.getData()) {
            data.getNode().getStyleClass().add("section" + (i++));
        }
        FlowPane legendPane2 = new FlowPane();
        legendPane2.getStyleClass().add("legend-pane");
        for (final PieChart.Data data : pieChart2.getData()) {
            HBox legendEntry = new HBox(5);
            legendEntry.getStyleClass().add("legend-entry");

            Rectangle colorBox = new Rectangle(10, 10);
            colorBox.getStyleClass().add("legend-color");
            colorBox.setStyle("-fx-fill: " + data.getNode().getStyle());

            Label legendLabel = new Label(data.getName());
            legendLabel.getStyleClass().add("legend-label");

            legendEntry.getChildren().addAll(colorBox, legendLabel);
            legendPane2.getChildren().add(legendEntry);
        }
    }
    private void mostHitRegionsPieChart() {
        // PieChart about most hit regions
        ObservableList<PieChart.Data> pieData1 = FXCollections.observableArrayList();
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
        FlowPane legendPane1 = new FlowPane();
        legendPane1.getStyleClass().add("legend-pane");
        for (final PieChart.Data data : pieChart1.getData()) {
            HBox legendEntry = new HBox(5);
            legendEntry.getStyleClass().add("legend-entry");
            Rectangle colorBox = new Rectangle(10, 10);
            colorBox.getStyleClass().add("legend-color");
            colorBox.setStyle("-fx-fill: " + data.getNode().getStyle());

            Label legendLabel = new Label(data.getName());
            legendLabel.getStyleClass().add("legend-label");
            // PieChart about types of earthquakes
            ObservableList<PieChart.Data> pieData2 = FXCollections.observableArrayList();
            // Get informations
            for (int index = 0; index < this.data.getTypes().size(); ++index) {
                if(!this.data.getTypes().get(index).equals("null") ) {
                    pieData2.add(new PieChart.Data(this.data.getTypes().get(index), this.data.getTypeFrequency().get(index)));
                }
            }
            // Create pieChart
            pieChart2.setData(pieData2);
            i = 0;
            for (final PieChart.Data pieChartData : pieChart2.getData()) {
                pieChartData.getNode().getStyleClass().add("section" + (i++));
            }

            legendEntry.getChildren().addAll(colorBox, legendLabel);
            legendPane1.getChildren().add(legendEntry);
        }
    }
    private void earthquakesPerCentury() {
        lineChart1.getData().clear();
        // Line chart about earthquakes per centuries
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        // Get informations
        for (int index = 0; index < data.getCenturies().size(); ++index) {
            series1.getData().add(new XYChart.Data<>(data.getCenturies().get(index).toString() + "-"
                    + (data.getCenturies().get(index) + 1), data.getEarthquakePerCentury().get(index)));
        }
        series1.setName("Nombre de séisme enregistrés par centenaire");
        lineChart1.getData().add(series1);
    }
    private void handleChooseFileButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier CSV");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv")
        );

        File selectedFile = fileChooser.showOpenDialog(chooseFileButton.getScene().getWindow());

        if (selectedFile != null && isCSVFile(selectedFile)) {
            data = new EarthquakeData(selectedFile);
            refresh();
        }
    }
    private boolean isCSVFile(File file) {
        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileExtension.equalsIgnoreCase("csv");
    }
}