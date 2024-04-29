module com.example.naloga4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.naloga4 to javafx.fxml;
    exports com.example.naloga4;
}