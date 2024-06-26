package org.example.naloga1;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


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
    public TextArea polje;
    @FXML
    private Label welcomeText;

    public File datoteka;
    private Hyperlink hyperlink = new Hyperlink("https://github.com/klemenp950/UV/blob/main/Naloga1/Navodila.md");
    private HostServices hostServices;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void odpriSB(ActionEvent actionEvent){
        FileChooser fs = new FileChooser();
        datoteka = fs.showOpenDialog(null);
        if (datoteka!=null){
            izpisStatus("Izbrali ste datoteko: " + datoteka.getName());
        }
    }

    public void imeStatusSB(ActionEvent actionEvent) {
        if(imeStatus.isSelected()){
            izpisStatus("Ime je izbrano");
            imeTextInput.setDisable(false);
        } else {
            izpisStatus("Ime ni izbrano");
            imeTextInput.setDisable(true);
        }
    }

    public void priimekStatusSB(ActionEvent actionEvent) {
        if(priimekStatus.isSelected()){
            izpisStatus("Priimek je izbran");
            priimekTextField.setDisable(false);
        } else {
            izpisStatus("Priimek ni izbran");
            priimekTextField.setDisable(true);
        }
    }

    public void drzavaStatusSB(ActionEvent actionEvent) {
        if(drzavaStatus.isSelected()){
            izpisStatus("Država je izbrana");
            drzavaTextField.setDisable(false);
        } else {
            izpisStatus("Država ni izbrana");
            drzavaTextField.setDisable(true);
        }
    }

    public void izpisiVseSB(ActionEvent actionEvent) {
        StringBuilder besedilo = new StringBuilder();
        for (int i = 0; i < comboBox.getItems().size(); i++) {
            besedilo.append(comboBox.getItems().get(i).toString() + "\n");
        }
        polje.setText(besedilo.toString());
    }

    public void pobrisiSB(ActionEvent actionEvent) {
        status.setText("");
        sporocilo.setText("");
        polje.setText("");
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
                comboBox.getItems().remove(comboBox.getSelectionModel().getSelectedIndex());
                izpisStatus("Odstranjujem izbranega");
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
            izpisStatus("Odstranjujem prvega");
        } else {
            status.setText("Izberite akcijo");
        }
    }

    public void dodaj(){
        String ime = imeTextInput.getText();
        String priimek = priimekTextField.getText();
        String drzava = drzavaTextField.getText();

        comboBox.getItems().add(ime + "," + priimek + "," + drzava);

        izpisStatus("Dodajam");

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
    public void izpisStatus(String sporocilo) {
        Platform.runLater(() -> {
            status.setText(sporocilo);
            Timer timer = new Timer();
            int cas = 5000;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        status.setText("");
                    });
                }
            }, cas);
        });
    }
}