module com.example.sae_s2_01 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sae_s2_01 to javafx.fxml;
    exports com.example.sae_s2_01;
}