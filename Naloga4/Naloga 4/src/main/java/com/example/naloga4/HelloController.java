package com.example.naloga4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.net.URI;

public class HelloController {
    public Label status;
    public TitledPane titlePaneIzbiraDestinacije;
    public DatePicker datumPrevzema;
    public DatePicker datumVrnitve;
    public TextField metoPrevzema;
    public TextField mestoVrnitve;
    public HBox Hbox;
    public ComboBox velikost;
    public String Majhen;
    public String Srednji;
    public String Velik;
    public ComboBox menjalnik;
    public String Ročni;
    public String Avtomatski;
    public ComboBox gorivo;
    public String Bencin;
    public String Dizel;
    public String Elektrika;
    public CheckBox zavarovanje;
    public ChoiceBox izbiraAvtomobila;
    public HBox Hbox1;
    public Label cenaNaDan;
    public Label cenaSkupaj;
    public CheckBox placiloSKartico;
    public TitledPane titlePanePlacilo;
    public TextField imePlacnika;
    public TextField ulica;
    public TextField priimekPlacnika;
    public TextField hisnaSt;
    public TextField mestoPlacnika;
    public TextField postnaSt;
    public TextField email;
    public PasswordField ccv;
    public TextField starost;
    public TextField stevilkaVozniske;
    public TextField telefonska;
    public PasswordField stevilkaKartice;
    public Button shraniButton;
    public Button ponastaviVnose;
    public int cenaZavarovanja = 2;
    public int stDni;
    public FileChooser fc;
    public File file;


    public void onSave(ActionEvent actionEvent) {
        
    }


    public void onPonastavi(ActionEvent actionEvent) {
    }

    public void odpri(ActionEvent actionEvent){
        try{
            fc = new FileChooser();
            fc.setTitle("Izberi datoteko za shranjevanje.");
            file = fc.showOpenDialog(null);
            status.setText(String.format("Odprli ste datoteko: %s", file.getName()));
        } catch (Exception e){
            status.setText(String.format("Neuspešno odpiranje datoteke. Napaka: %s", e.getMessage()));
            System.out.print(e.toString());
        }
    }

    public boolean openWebpage(ActionEvent actionEvent) {
        URI uri = URI.create("https://github.com/klemenp950/UV/tree/main/Naloga4");
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}