package org.example.naloga1;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class HelloController {
    public Label status;
    public CheckMenuItem imeStatus;
    public CheckMenuItem priimekStatus;
    public CheckMenuItem drzavaStatus;
    public MenuItem izpisiVse;
    public Button pobrisiButton;
    public Button odpriButton;
    public Button izhodButton;
    public Button izpisiVseButton;
    public TextField imeTextInput;
    public TextField priimekTextField;
    public TextField drzavaTextField;
    public ToggleGroup akcija;
    public Spinner spinner;
    public RadioButton odstraniPrvegaRadiobutton;
    public RadioButton odstraniIzbranegaradioButton;
    public RadioButton dodajRadioButton;
    public MenuItem oProgramu;
    public Label sporocilo;
    public Button izvediAkcijoButton;
    public ComboBox comboBox;
    public CheckBox dvojnikiDovoljeni;
    public MenuItem odpri;
    public Spinner Kroznicek;
    @FXML
    private Label welcomeText;

    public File datoteka;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void odpriSB(ActionEvent actionEvent){
        FileChooser fs = new FileChooser();
        datoteka = fs.showOpenDialog(null);
        if (datoteka!=null){
            status.setText("Izbrali ste datoteko: " + datoteka.getName());
        }
    }

    public void imeStatusSB(ActionEvent actionEvent) {
        if(imeStatus.isSelected()){
            status.setText("Ime je izbrano");
            imeTextInput.setDisable(false);
        } else {
            status.setText("Ime ni izbrano");
            imeTextInput.setDisable(true);
        }
    }

    public void priimekStatusSB(ActionEvent actionEvent) {
        if(priimekStatus.isSelected()){
            status.setText("Priimek je izbran");
            priimekTextField.setDisable(false);
        } else {
            status.setText("Priimek ni izbran");
            priimekTextField.setDisable(true);
        }
    }

    public void drzavaStatusSB(ActionEvent actionEvent) {
        if(drzavaStatus.isSelected()){
            status.setText("Država je izbrana");
            drzavaTextField.setDisable(false);
        } else {
            status.setText("Država ni izbrana");
            drzavaTextField.setDisable(true);
        }
    }

    public void izpisiVseSB(ActionEvent actionEvent) {
    }

    public void pobrisiSB(ActionEvent actionEvent) {
        status.setText("");
    }

    public void izhodSB(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void izvediAkcijo(ActionEvent actionEvent) {
        if(dodajRadioButton.isSelected()){
            if(!dvojnikiDovoljeni.isSelected()){
                ObservableList items = comboBox.getItems();

                boolean dodam = true;
                for (Object item : items) {
                    if (item.toString().equals(imeTextInput.getText() + "," + priimekTextField.getText() + "," + drzavaTextField.getText()))
                        dodam = false;
                }

                if(dodam)
                    dodaj();
                else
                    status.setText("Element je že v seznamu");

            } else
                dodaj();

        } else if (odstraniIzbranegaradioButton.isSelected()) {
            if(comboBox.getValue() != null){
                comboBox.getItems().remove(comboBox.getValue());
                status.setText("Odstranjujem izbranega");
            }
            else
                status.setText("izberite element.");

        } else if (odstraniPrvegaRadiobutton.isSelected()){
            ObservableList items = comboBox.getItems();
            Object value = comboBox.getValue();
            for (Object item : items) {
                if (item.toString().equals(value)){
                    comboBox.getItems().remove(value);
                    break;
                }
            }
            status.setText("Odstranjujem prvega");
        } else {
            status.setText("Izberite akcijo");
        }
    }

    public void dodaj(){
        String ime = imeTextInput.getText();
        String priimek = priimekTextField.getText();
        String drzava = drzavaTextField.getText();

        comboBox.getItems().add(ime + "," + priimek + "," + drzava);

        status.setText("Dodajam");
    }

    public void spinnerSB(Event keyEvent) {
        boolean obstaja = false;
        for (int i = 0; i < comboBox.getItems().size(); i++) {
            if(spinner.getValue().equals(i)){
                sporocilo.setText(comboBox.getItems().get(i).toString());
                obstaja = true;
            }
        }

        if(!obstaja){
            sporocilo.setText("Ni elementa");
        }
    }
}