package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Label status;
    public ComboBox combo;
    public Spinner spinner;
    public Button pretvori;
    public ToggleGroup enota;
    public TextField entry;
    public HBox enotica;
    public Button pretvoriSB;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void odpri(ActionEvent actionEvent) {
        status.setText("Zdej bomo pa jebal ježa");
    }

    public void zapri(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if (f!=null){
            status.setText("Status: Datoteka - " + f.getName());
        }
    }

    public void avtorSB(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combo.getItems().addAll("cm -> inč", "inč -> cm");
        combo.getSelectionModel().selectFirst();
        spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, 1000));
        spinner.valueProperty().addListener((obs, ov, nv) -> {
            System.out.println(nv+" ");
        });
    }

    public void pretvoriSB(ActionEvent actionEvent) {
        
    }
}