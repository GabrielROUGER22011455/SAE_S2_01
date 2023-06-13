package EarthquakeJavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The MainApplication class is the entry point of the EarthquakeJavaFX application.
 * It extends the JavaFX Application class and sets up the main user interface.
 */
public class MainApplication extends Application {

    /**
     * The start method is called when the JavaFX application is started.
     * It loads the FXML file, creates the main scene, and displays the stage.
     *
     * @param stage the primary stage for the application
     * @throws IOException if an error occurs during loading of the FXML file
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 850);
        stage.setTitle("Rapport des séismes en France");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method is the entry point of the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}