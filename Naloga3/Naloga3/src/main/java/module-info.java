module org.example.naloga3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.naloga3 to javafx.fxml;
    exports org.example.naloga3;
}