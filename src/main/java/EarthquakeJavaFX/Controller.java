package EarthquakeJavaFX;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;





public class Controller {

    @FXML
    private FlowPane legendPane1;
    @FXML
    private FlowPane legendPane2;
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
    private Label pieChart1NoDataLabel;
    @FXML
    private Label lineChart1NoDataLabel;
    @FXML
    private Button chooseFileButton;
    @FXML
    private CheckBox checkBox1;

    private ArrayList<Boolean> checkBoxState;
    private EarthquakeData data ;
    public void initialize() {

        chooseFileButton.setOnAction(event -> handleChooseFileButton());

        // Det data
        data = new EarthquakeData();
        checkBoxState = new ArrayList<Boolean>();
        for (Node node : checkBoxes.getChildren()) {
            CheckBox checkBox = (CheckBox) node;
            checkBoxState.add(checkBox.isSelected());
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

            legendEntry.getChildren().addAll(colorBox, legendLabel);
            legendPane1.getChildren().add(legendEntry);
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

        // PieChart about types of earthquakes
        ObservableList<PieChart.Data> pieData2 = FXCollections.observableArrayList();
        // Get informations
        for (int index = 0; index < data.getTypes().size(); ++index) {
            pieData2.add(new PieChart.Data(data.getTypes().get(index), data.getTypeFrequency().get(index)));
        }
        // Create pieChart
        pieChart2.setData(pieData2);
        i = 0;
        for (final PieChart.Data data : pieChart2.getData()) {
            data.getNode().getStyleClass().add("section" + (i++));
        }

        // Line chart about earthquakes per centuries
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        // Get informations
        for (int index = 0; index < data.getCenturies().size(); ++index) {
            series1.getData().add(new XYChart.Data<>(data.getCenturies().get(index).toString() + "-"
                    + (data.getCenturies().get(index) + 1), data.getEarthquakePerCentury().get(index)));
        }
        series1.setName("Nombre de séisme par décennie");
        lineChart1.getData().add(series1);

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        for (int index = 0; index < data.getCenturies().size(); ++index) {
            series2.getData().add(new XYChart.Data<>(data.getCenturies().get(index).toString() + "-"
                    + (data.getCenturies().get(index) + 1), data.getEarthquakePerCentury().get(index)));
        }
        series2.setName("Nombre de séisme par décennie");
        barChart1.getData().add(series2);
        checkBox1.setOnAction(event -> {
            if (checkBox1.isSelected()) {
                System.out.println("La checkbox est cochée");
            } else {
                System.out.println("La checkbox est décochée");
            }
        });

        // Sliders param
        startDate.setMin(data.getMinYear());
        startDate.setMax(data.getMaxYear());
        endDate.setMin(data.getMinYear());
        endDate.setMax(data.getMaxYear());
        // Sliders events
        startDate.setOnMouseReleased(event -> {
            System.out.println(startDate.getValue());
        });
        endDate.setOnMouseReleased(event -> {
            System.out.println(endDate.getValue());
        });
    }

    private void handleChooseFileButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier CSV");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv")
        );

        File selectedFile = fileChooser.showOpenDialog(chooseFileButton.getScene().getWindow());

        if (selectedFile != null && isCSVFile(selectedFile)) {
            extractDataFromCSV(selectedFile);
        } else {
        }
    }

    private boolean isCSVFile(File file) {
        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileExtension.equalsIgnoreCase("csv");
    }

    private void extractDataFromCSV(File file) {
    //je te laisse lier les données depuis cette fonction
    }
}
