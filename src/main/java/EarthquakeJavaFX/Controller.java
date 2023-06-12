package EarthquakeJavaFX;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import java.util.ArrayList;


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
    private ArrayList<Boolean> checkBoxsState = new ArrayList<>();
    private EarthquakeData data = new EarthquakeData();
    public void initialize() {
        data.EarthquakeViewModel();
        checkBoxsState.add(checkBox1.isSelected());
        checkBoxsState.add(checkBox2.isSelected());
        checkBoxsState.add(checkBox3.isSelected());
        checkBoxsState.add(checkBox4.isSelected());
        checkBoxsState.add(checkBox5.isSelected());
        checkBoxsState.add(checkBox6.isSelected());
        checkBoxsState.add(checkBox7.isSelected());
        data.earthquakesPerRegion();
        data.avgEarthquakePerRegion();
        data.mostHitRegions(4);
        data.typesAndTheirFrequency();
        data.earthquakePerDecade();

        ObservableList<PieChart.Data> pieData1 = FXCollections.observableArrayList();
        for (int index = 0; index < data.regionMostHitByEarthquake.size(); ++index) {
            pieData1.add(new PieChart.Data(data.regionMostHitByEarthquake.get(index),
                    data.higherNbrOfEarthquake.get(index)));
        }
        pieChart1.setData(pieData1);
        int i = 0;
        for (final PieChart.Data data : pieChart1.getData()) {
            data.getNode().getStyleClass().add("section" + (i++));
        }

        ObservableList<PieChart.Data> pieData2 = FXCollections.observableArrayList();
        for (int index = 0; index < data.typesOfEarthquake.size(); ++index) {
            pieData2.add(new PieChart.Data(data.typesOfEarthquake.get(index), data.frequencyOfTypes.get(index)));
        }
        pieChart2.setData(pieData2);
        i = 0;
        for (final PieChart.Data data : pieChart2.getData()) {
            data.getNode().getStyleClass().add("section" + (i++));
        }

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        for (int index = 0; index < data.dateInDecade.size(); ++index) {
            series1.getData().add(new XYChart.Data<>(data.dateInDecade.get(index).toString() + "-"
                    + (data.dateInDecade.get(index) + 1), data.nbrEarthquakePerDecade.get(index)));
        }
        series1.setName("Nombre de séisme par décennie");
        lineChart1.getData().add(series1);

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Séries 2");
        series2.getData().add(new XYChart.Data<>("Ville 1", 20));
        series2.getData().add(new XYChart.Data<>("Ville 2", 25));
        series2.getData().add(new XYChart.Data<>("Ville 3", 30));
        lineChart2.getData().add(series2);

        checkBox1.setOnAction(event -> {
            if (checkBox1.isSelected()) {
                System.out.println("La checkbox est cochée");
            } else {
                System.out.println("La checkbox est décochée");
            }
        });
    }
}
