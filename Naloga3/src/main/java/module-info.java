module org.example.naloga3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.naloga3 to javafx.fxml;
    exports org.example.naloga3;
}