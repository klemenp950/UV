module org.example.naloga1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.naloga1 to javafx.fxml;
    exports org.example.naloga1;
}