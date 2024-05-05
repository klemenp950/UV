module com.example.naloga4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.naloga4 to javafx.fxml;
    exports com.example.naloga4;
}