package org.example.naloga3;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController {
    public DatePicker datumOdhoda;
    public DatePicker datumVrnitve;
    public TextField mesto;
    public TextField drzava;
    public Label status;
    public Spinner<Integer> osebePod7;
    public Spinner<Integer> osebePod18;
    public TextArea posebnePotrebe;
    public TextField imePlacnika;
    public TextField ulica;
    public TextField priimekPlacnika;
    public TextField hisnaSt;
    public TextField postnaSt;
    public TextField drzavaPlacnika;
    public DatePicker datumRojstva;
    public TextField imeNaKartici;
    public PasswordField ccv;
    public TextField datumNaKartici;
    public TextField stKartice;
    public TextArea podatkiOPotnikih;
    public TextField mestoPlacnika;
    public ArrayList<String> poljazNapakami;
    public CheckBox prevozSam;
    public CheckBox prevozLetalo;
    public CheckBox prevozBus;
    public CheckBox prevozKolo;
    public CheckBox prevozVlak;
    public TitledPane titlePaneNastanitev;
    public TitledPane titlePanePlacilo;
    public TitledPane titlePanePodatki;
    public TitledPane titlePaneIzbiraDestinacije;
    public TitledPane titlePaneNacinPotovanja;
    public HBox nastanitevHBox;
    public CheckBox nastanitevSoba;
    public CheckBox nastanitevApartma;
    public CheckBox nastanitevMobilnaHiska;
    public CheckBox nastanitevHisa;
    public CheckBox nastanitevGlamping;
    public CheckBox nastanitevHotel;
    public CheckBox nastanitevSam;
    public FileChooser fc;
    public File file;
    public Button shraniButton;
    public VBox nacinPotovanjaVBox;

    @FXML

    public void shrani(ActionEvent actionEvent) {
        nastaviRoboveNaCrno();
        boolean validacija = dataValidation();
        if (validacija){
            status.setText("Popravite vnos v rdečih poljih.");
            System.out.println(poljazNapakami.toString());
        } else {
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                String vrstica = narediVnos();
                bw.write(vrstica);
                bw.close();
            } catch (Exception e){
                status.setText(String.format("Napaka pri shranjevanju v datoteko. Napaka: %s", e.getMessage()));
                System.out.print(e.toString());
            }
            status.setText("Vnos uspešen.");
        }
    }

    public void ponastavi(ActionEvent actionEvent) {
        drzava.setText("");
        mesto.setText("");
        datumVrnitve.setValue(null);
        datumOdhoda.setValue(null);

        ObservableList<Node> ar = nacinPotovanjaVBox.getChildren();
        for (Object o : ar) {
            CheckBox temp = (CheckBox) o;
            temp.setSelected(false);
        }

        ar = nastanitevHBox.getChildren();
        for (int i = 1; i < ar.size(); i++) {
            Object o = ar.get(i);
            CheckBox temp = (CheckBox) o;
            temp.setSelected(false);
        }

        osebePod7.getValueFactory().setValue(0);
        osebePod18.getValueFactory().setValue(0);
        posebnePotrebe.setText("");
        imePlacnika.setText("");
        priimekPlacnika.setText("");
        ulica.setText("");
        hisnaSt.setText("");
        mestoPlacnika.setText("");
        postnaSt.setText("");
        drzavaPlacnika.setText("");
        datumRojstva.setValue(null);
        imeNaKartici.setText("");
        stKartice.setText("");
        datumNaKartici.setText("");
        ccv.setText("");
        podatkiOPotnikih.setText("");
    }

    private String narediVnos(){
        StringBuilder sb = new StringBuilder();
        sb.append(drzava.getText()).append(",");
        sb.append(mesto.getText()).append(",");
        sb.append(reformatDate(String.valueOf(datumVrnitve.getValue()))).append(",");
        sb.append(reformatDate(String.valueOf(datumOdhoda.getValue()))).append(",");

        ObservableList<Node> ar = nacinPotovanjaVBox.getChildren();
        for (Object o : ar) {
            CheckBox temp = (CheckBox) o;
            if (temp.isSelected()) {
                sb.append(temp.getText()).append(";");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(",");

        ar = nastanitevHBox.getChildren();
        for (int i = 1; i < ar.size(); i++) {
            Object o = ar.get(i);
            CheckBox temp = (CheckBox) o;
            if (temp.isSelected()) {
                sb.append(temp.getText()).append(";");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(",");

        sb.append(osebePod7.getValue()).append(",");
        sb.append(osebePod18.getValue()).append(",");
        sb.append(posebnePotrebe.getText()).append(",");
        sb.append(imePlacnika.getText()).append(",");
        sb.append(priimekPlacnika.getText()).append(",");
        sb.append(ulica.getText()).append(",");
        sb.append(hisnaSt.getText()).append(",");
        sb.append(mestoPlacnika.getText()).append(",");
        sb.append(postnaSt.getText()).append(",");
        sb.append(drzavaPlacnika.getText()).append(",");
        sb.append(reformatDate(String.valueOf(datumRojstva.getValue()))).append(",");
        sb.append(imeNaKartici.getText()).append(",");
        sb.append(stKartice.getText()).append(",");
        sb.append(datumNaKartici.getText()).append(",");
        sb.append(ccv.getText()).append(",");
        sb.append(podatkiOPotnikih.getText()).append("\n");

        return sb.toString();
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


    private boolean dataValidation(){
        boolean napaka = false;
        poljazNapakami = new ArrayList<>();
        Pattern regexDrzava = Pattern.compile("[A-Z][a-z]{3,20}");
        Pattern regexDatum = Pattern.compile("(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[1,2])\\.(19|20)\\d{2}");
        Pattern regexIme = Pattern.compile("[A-Z][a-z]{1,30}");
        Pattern regexPriimek = Pattern.compile("[A-Z][a-z]{1,40}");
        Pattern regexUlica = Pattern.compile("([A-Za-z0-9.]{2,10}\\s?){1,4}");
        Pattern regexHisnaSt = Pattern.compile("\\d{1,4}[a-z]?");
        Pattern regexMesto = Pattern.compile("([A-Za-z]{2,30}\\s?){1,4}");
        Pattern regexPostnaSt = Pattern.compile("\\d{4}");
        Pattern regexImePriimek = Pattern.compile("[A-Z][a-z]{1,20}(\\s[A-Z][a-z]{1,20}){1,3}");
        Pattern regexStKartice = Pattern.compile("\\d{16}");
        Pattern regexCCV = Pattern.compile("\\d{3}");
        Pattern regexSt = Pattern.compile("\\d{0,2}");
        Pattern regexDatumKartica = Pattern.compile("\\d{2}/\\d{2}");

        boolean matchFound = preveriRegex(regexDrzava, drzava.getText());
        if (drzava.getText() == null || !matchFound){
            drzava.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(drzava.getId());
            napaka = true;
        }

        if (datumOdhoda.getValue() != null){
            matchFound = preveriRegex(regexDatum, reformatDate(datumOdhoda.getValue().toString()));
            if (datumOdhoda.getValue().toString().isEmpty() || !matchFound){
                datumOdhoda.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
                poljazNapakami.add(datumOdhoda.getId());
                napaka = true;
            }
        } else {
            datumOdhoda.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(datumOdhoda.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexMesto, mesto.getText());
        if (!matchFound || mesto.getText() == null){
            mesto.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(mesto.getId());
            napaka = true;
        }

        if (datumVrnitve.getValue() != null){
            matchFound = preveriRegex(regexDatum, reformatDate(datumVrnitve.getValue().toString()));
            if (datumVrnitve.getValue().toString().isEmpty() || !matchFound){
                datumVrnitve.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
                poljazNapakami.add(datumVrnitve.getId());
                napaka = true;
            }

        } else {
            datumVrnitve.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(datumVrnitve.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexSt, String.valueOf((int)(osebePod7.getValue())));
        if (!matchFound){
            osebePod7.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(osebePod7.getId());
            napaka = true;
        } else {
            osebePod7.getValue();
        }

        matchFound = preveriRegex(regexSt, String.valueOf((int)(osebePod18.getValue())));
        if (!matchFound){
            osebePod18.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(osebePod18.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexIme, imePlacnika.getText());
        if (!matchFound || imePlacnika.getText() == null){
            imePlacnika.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(imePlacnika.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexPriimek, priimekPlacnika.getText());
        if (!matchFound || priimekPlacnika.getText() == null){
            priimekPlacnika.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(priimekPlacnika.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexUlica, ulica.getText());
        if (!matchFound || ulica.getText() == null){
            ulica.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(ulica.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexHisnaSt, hisnaSt.getText());
        if (!matchFound || hisnaSt.getText() == null){
            hisnaSt.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(hisnaSt.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexMesto, mestoPlacnika.getText());
        if (!matchFound || mestoPlacnika.getText() == null){
            mestoPlacnika.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(mestoPlacnika.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexPostnaSt, postnaSt.getText());
        if (!matchFound || postnaSt.getText() == null){
            postnaSt.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(postnaSt.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexDrzava, drzavaPlacnika.getText());
        if (!matchFound || drzavaPlacnika.getText() == null){
            drzavaPlacnika.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(drzavaPlacnika.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexDatum, reformatDate(String.valueOf(datumRojstva.getValue())));
        if (!matchFound){
            datumRojstva.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(datumRojstva.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexImePriimek, imeNaKartici.getText());
        if (!matchFound || imeNaKartici.getText() == null){
            imeNaKartici.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(imeNaKartici.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexStKartice, stKartice.getText());
        if (!matchFound || stKartice.getText() == null){
            stKartice.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(stKartice.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexDatumKartica, datumNaKartici.getText());
        if (!matchFound || datumNaKartici.getText() == null){
            datumNaKartici.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(datumNaKartici.getId());
            napaka = true;
        }

        matchFound = preveriRegex(regexCCV, ccv.getText());
        if (!matchFound || ccv.getText() == null){
            ccv.setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(ccv.getId());
            napaka = true;
        }
        if (!prevozBus.isSelected() && !prevozKolo.isSelected() && !prevozSam.isSelected() && !prevozLetalo.isSelected() && !prevozVlak.isSelected()){
            napaka = true;
            prevozBus.getParent().getParent().getParent().getParent().setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            String x = prevozBus.getParent().getParent().getParent().getParent().getId();
            poljazNapakami.add(x);
        }

        if (!nastanitevApartma.isSelected() && !nastanitevGlamping.isSelected() && !nastanitevHisa.isSelected() && !nastanitevHotel.isSelected() && !nastanitevSam.isSelected() && !nastanitevMobilnaHiska.isSelected() && !nastanitevSoba.isSelected()){
            napaka = true;
            nastanitevSoba.getParent().setStyle("-fx-border-color: red ; -fx-border-radius: 3px ;");
            poljazNapakami.add(nastanitevHBox.getId());
        }

        return napaka;
    }

    private boolean preveriRegex(Pattern pattern, String text){
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    private String reformatDate(String datum){
        try {
            String[] date = datum.split("-");
            StringBuilder sb = new StringBuilder();
            for (int i = date.length - 1; i >= 0 ; i--) {
                sb.append(date[i]);
                sb.append(".");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } catch (NullPointerException npe){
            System.out.println(npe.toString());
            return null;
        }

    }

    private void nastaviRoboveNaCrno(){
        ccv.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        datumNaKartici.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        stKartice.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        imeNaKartici.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        datumRojstva.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        drzavaPlacnika.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        postnaSt.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        mestoPlacnika.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        hisnaSt.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        ulica.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        priimekPlacnika.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        imePlacnika.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        osebePod18.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        osebePod7.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        datumVrnitve.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        datumOdhoda.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        mesto.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        drzava.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        titlePaneNastanitev.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        titlePanePlacilo.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        titlePanePodatki.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        titlePaneIzbiraDestinacije.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        titlePaneNacinPotovanja.setStyle("-fx-border-color: lightgray ; -fx-border-radius: 3px ;");
        nastanitevHBox.setStyle("-fx-border-style: none ;");
    }

    public boolean openWebpage(ActionEvent actionEvent) {
        URI uri = URI.create("https://github.com/klemenp950/UV/tree/main/Naloga3/Naloga3");
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