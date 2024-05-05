package com.example.naloga4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


public class HelloController {
    public Label status;
    public TitledPane titlePaneIzbiraDestinacije;
    public DatePicker datumPrevzema;
    public DatePicker datumVrnitve;
    public ComboBox metoPrevzema;
    public ComboBox mestoVrnitve;
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
    public ComboBox izbiraAvtomobila;
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
    public int cenaZavarovanjaInt = 2;
    public int stDni;
    public int cenaNaDanInt;
    public int cenaSkupajInt;
    public FileChooser fc;
    public File file;
    public String[][] avtomobili = {
            {"Sprinto", "Mala", "Ročni", "Bencin", "1", "147"},
            {"CityCruiser", "Velika", "Avtomatski", "Elektrika", "2", "144"},
            {"PowerGlide", "Srednja", "Ročni", "Bencin", "3", "72"},
            {"Electra", "Mala", "Avtomatski", "Elektrika", "4", "52"},
            {"Thunder", "Velika", "Avtomatski", "Bencin", "5", "54"},
            {"Zoomer", "Srednja", "Ročni", "Elektrika", "6", "102"},
            {"Titanium", "Velika", "Ročni", "Dizel", "7", "98"},
            {"EcoBoost", "Mala", "Avtomatski", "Bencin", "8", "63"},
            {"Maximus", "Srednja", "Avtomatski", "Dizel", "9", "106"},
            {"Voltz", "Velika", "Ročni", "Elektrika", "10", "111"},
            {"Urbanite", "Mala", "Ročni", "Bencin", "11", "55"},
            {"EcoDrive", "Srednja", "Avtomatski", "Dizel", "12", "91"},
            {"Neo", "Velika", "Avtomatski", "Elektrika", "13", "55"},
            {"Swift", "Mala", "Ročni", "Bencin", "14", "142"},
            {"Titan", "Srednja", "Avtomatski", "Bencin", "15", "98"},
            {"EcoRun", "Velika", "Ročni", "Dizel", "16", "107"},
            {"EcoWise", "Mala", "Avtomatski", "Bencin", "17", "109"},
            {"EcoStar", "Srednja", "Ročni", "Elektrika", "18", "115"},
            {"Dyno", "Velika", "Avtomatski", "Bencin", "19", "94"},
            {"EcoPlus", "Mala", "Ročni", "Dizel", "20", "111"},
            {"Cruiser", "Srednja", "Avtomatski", "Bencin", "21", "50"},
            {"EcoMax", "Velika", "Ročni", "Elektrika", "22", "80"},
            {"EcoCharge", "Mala", "Ročni", "Bencin", "23", "86"},
            {"EcoMotive", "Srednja", "Avtomatski", "Dizel", "24", "142"},
            {"EcoTech", "Velika", "Avtomatski", "Bencin", "25", "87"},
            {"EcoSpeed", "Mala", "Ročni", "Elektrika", "26", "112"},
            {"EcoRide", "Srednja", "Ročni", "Bencin", "27", "118"},
            {"EcoDrive", "Velika", "Avtomatski", "Dizel", "28", "108"},
            {"EcoPower", "Mala", "Ročni", "Bencin", "29", "57"},
            {"EcoFusion", "Srednja", "Avtomatski", "Elektrika", "30", "138"},
            {"EcoTrek", "Velika", "Ročni", "Bencin", "31", "150"},
            {"EcoMotion", "Mala", "Ročni", "Dizel", "32", "101"},
            {"EcoPro", "Srednja", "Avtomatski", "Bencin", "33", "141"},
            {"EcoMaster", "Velika", "Ročni", "Elektrika", "34", "99"},
            {"EcoRider", "Mala", "Ročni", "Bencin", "35", "81"},
            {"EcoShift", "Srednja", "Ročni", "Dizel", "36", "135"},
            {"EcoVision", "Velika", "Avtomatski", "Bencin", "37", "90"},
            {"EcoVista", "Mala", "Ročni", "Elektrika", "38", "108"},
            {"EcoDrive", "Srednja", "Ročni", "Bencin", "39", "69"},
            {"EcoForce", "Velika", "Avtomatski", "Dizel", "40", "65"},
            {"EcoTech", "Mala", "Ročni", "Bencin", "41", "124"},
            {"EcoPulse", "Srednja", "Avtomatski", "Elektrika", "42", "120"},
            {"EcoMax", "Velika", "Ročni", "Bencin", "43", "129"},
            {"EcoCharge", "Mala", "Ročni", "Dizel", "44", "84"},
            {"EcoMotive", "Srednja", "Avtomatski", "Bencin", "45", "126"},
            {"EcoTech", "Velika", "Ročni", "Elektrika", "46", "98"},
            {"EcoSpeed", "Mala", "Ročni", "Bencin", "47", "102"},
            {"EcoRide", "Srednja", "Avtomatski", "Dizel", "48", "51"},
            {"EcoDrive", "Velika", "Avtomatski", "Bencin", "49", "89"},
            {"EcoPower", "Mala", "Ročni", "Elektrika", "50", "98"},
            {"EcoFusion", "Srednja", "Avtomatski", "Dizel", "51", "121"},
            {"EcoTrek", "Velika", "Ročni", "Bencin", "52", "144"},
            {"EcoMotion", "Mala", "Avtomatski", "Bencin", "53", "76"},
            {"EcoPro", "Srednja", "Ročni", "Dizel", "54", "138"},
            {"EcoMaster", "Velika", "Avtomatski", "Bencin", "55", "98"},
            {"EcoRider", "Mala", "Ročni", "Elektrika", "56", "144"},
            {"EcoShift", "Srednja", "Avtomatski", "Bencin", "57", "139"},
            {"EcoVision", "Velika", "Ročni", "Dizel", "58", "135"},
            {"EcoVista", "Mala", "Avtomatski", "Bencin", "59", "97"},
            {"EcoDrive", "Srednja", "Avtomatski", "Dizel", "60", "77"},
            {"EcoForce", "Velika", "Ročni", "Bencin", "61", "100"},
            {"EcoTech", "Mala", "Avtomatski", "Elektrika", "62", "84"},
            {"EcoPulse", "Srednja", "Ročni", "Bencin", "63", "121"},
            {"EcoMax", "Velika", "Avtomatski", "Dizel", "64", "61"},
            {"EcoCharge", "Mala", "Ročni", "Bencin", "65", "149"},
            {"EcoMotive", "Srednja", "Avtomatski", "Elektrika", "66", "134"},
            {"EcoTech", "Velika", "Ročni", "Bencin", "67", "50"},
            {"EcoSpeed", "Mala", "Ročni", "Dizel", "68", "145"},
            {"EcoRide", "Srednja", "Avtomatski", "Bencin", "69", "54"},
            {"EcoDrive", "Velika", "Avtomatski", "Elektrika", "70", "112"},
            {"EcoPower", "Mala", "Ročni", "Bencin", "71", "119"},
            {"EcoFusion", "Srednja", "Avtomatski", "Dizel", "72", "67"},
            {"EcoTrek", "Velika", "Ročni", "Bencin", "73", "143"},
            {"EcoMotion", "Mala", "Ročni", "Elektrika", "74", "112"},
            {"EcoPro", "Srednja", "Avtomatski", "Bencin", "75", "124"},
            {"EcoMaster", "Velika", "Ročni", "Dizel", "76", "78"},
            {"EcoRider", "Mala", "Avtomatski", "Bencin", "77", "81"},
            {"EcoShift", "Srednja", "Ročni", "Elektrika", "78", "92"},
            {"EcoVision", "Velika", "Avtomatski", "Bencin", "79", "66"},
            {"EcoVista", "Mala", "Ročni", "Dizel", "80", "61"},
            {"EcoDrive", "Srednja", "Avtomatski", "Bencin", "81", "121"},
            {"EcoForce", "Velika", "Ročni", "Elektrika", "82", "80"},
            {"EcoTech", "Mala", "Ročni", "Bencin", "83", "149"},
            {"EcoPulse", "Srednja", "Avtomatski", "Dizel", "84", "134"},
            {"EcoMax", "Velika", "Avtomatski", "Bencin", "85", "101"},
            {"EcoCharge", "Mala", "Ročni", "Elektrika", "86", "79"},
            {"EcoMotive", "Srednja", "Ročni", "Bencin", "87", "149"},
            {"EcoTech", "Velika", "Avtomatski", "Dizel", "88", "61"},
            {"EcoSpeed", "Mala", "Ročni", "Bencin", "89", "73"},
            {"EcoRide", "Srednja", "Avtomatski", "Elektrika", "90", "69"},
            {"EcoDrive", "Velika", "Ročni", "Bencin", "91", "129"},
            {"EcoPower", "Mala", "Ročni", "Dizel", "92", "98"},
            {"EcoFusion", "Srednja", "Avtomatski", "Bencin", "93", "135"},
            {"EcoTrek", "Velika", "Ročni", "Elektrika", "94", "81"},
            {"EcoMotion", "Mala", "Avtomatski", "Bencin", "95", "149"},
            {"EcoPro", "Srednja", "Ročni", "Dizel", "96", "79"},
            {"EcoMaster", "Velika", "Avtomatski", "Bencin", "97", "54"},
            {"EcoRider", "Mala", "Ročni", "Elektrika", "98", "148"},
            {"EcoShift", "Srednja", "Avtomatski", "Bencin", "99", "100"},
            {"EcoVision", "Velika", "Ročni", "Dizel", "100", "150"}
    };
    public String Ljubljana2;
    public String Maribor2;
    public String Celje2;
    public String Kranj2;
    public String Koper2;
    public String Ptuj2;
    public String NovoMesto2;
    public String Velenje2;
    public String KranjskaGora2;
    public String Ljubljana;
    public String Maribor;
    public String Celje;
    public String Kranj;
    public String Koper;
    public String Ptuj;
    public String NovoMesto;
    public String Velenje;
    public String KranjskaGora;


    public void onSave(ActionEvent actionEvent) {
        if(metoPrevzema.getValue() == null || mestoVrnitve.getValue() == null){
            status.setText("Napaka: Vnesi mesto prevnema in vrnitve avtomobila.");
            return;
        }
        if (datumPrevzema.getValue() == null || datumVrnitve.getValue() == null){
            status.setText("Napaka: Vnesi datum prevezema in vrnitve avtomobila.");
            return;
        }
        if(izbiraAvtomobila.getValue() == null){
            status.setText("Napaka: Izberite vozilo.");
            return;
        }
        if(imePlacnika.getText() == "" || priimekPlacnika.getText() == "" || ulica.getText() == "" || hisnaSt.getText() == "" || mestoPlacnika.getText() == "" || postnaSt.getText() == "" || email.getText() == "" || telefonska.getText() == "" || starost.getText() == "" || stevilkaVozniske.getText() == ""){
            status.setText("Napaka: Izpolnite vse podatke o plačniku.");
            return;
        }
        shraniVDatoteko();
    }

    private void shraniVDatoteko(){
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

    private String narediVnos(){
        StringBuilder sb = new StringBuilder();
        sb.append(metoPrevzema.getValue()).append(",");
        sb.append(mestoVrnitve.getValue()).append(",");
        sb.append(reformatDate(String.valueOf(datumPrevzema.getValue()))).append(",");
        sb.append(reformatDate(String.valueOf(datumVrnitve.getValue()))).append(",");
        sb.append(zavarovanje.isSelected() ? "Zavarovanje" : "Brez zavarovanje").append(",");
        sb.append((String) (izbiraAvtomobila.getValue())).append(",");
        sb.append(cenaNaDan.getText()).append(",");
        sb.append(cenaSkupaj.getText()).append(",");
        sb.append(placiloSKartico.isSelected() ? "Kartica" : "Gotovina").append(",");
        sb.append(imePlacnika.getText()).append(",");
        sb.append(priimekPlacnika.getText()).append(",");
        sb.append(ulica.getText()).append(",");
        sb.append(hisnaSt.getText()).append(",");
        sb.append(mestoPlacnika.getText()).append(",");
        sb.append(postnaSt.getText()).append(",");
        sb.append(email.getText()).append(",");
        sb.append(telefonska.getText()).append(",");
        sb.append(starost.getText()).append(",");
        sb.append(stevilkaVozniske.getText()).append(",");
        sb.append(imePlacnika.getText()).append(",");
        if (stevilkaKartice.isEditable()){
            sb.append(stevilkaKartice.getText()).append(",");
            sb.append(ccv.getText()).append("\n");
        }
        return sb.toString();
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

    public void izracunajStDni(ActionEvent actionEvent){
        if (datumPrevzema.getValue() !=  null && datumVrnitve.getValue() != null){
            LocalDate datumPrevzemaValue = datumPrevzema.getValue();
            LocalDate datumVrnitveValue = datumVrnitve.getValue();
            if(datumPrevzemaValue.isBefore(datumVrnitveValue)){
                if (datumPrevzemaValue.getDayOfYear() > datumVrnitveValue.getDayOfYear()){
                    stDni = datumPrevzemaValue.getDayOfYear() - datumVrnitveValue.getDayOfYear();
                } else if (datumPrevzemaValue.getDayOfYear() < datumVrnitveValue.getDayOfYear()){
                    stDni = datumVrnitveValue.getDayOfYear() - datumPrevzemaValue.getDayOfYear();
                } else {
                    stDni = 1;
                }
            }
        }
    }

    public void onPonastavi(ActionEvent actionEvent) {
        status.setText("");
        datumPrevzema.setValue(null);
        datumVrnitve.setValue(null);
        metoPrevzema.setValue(null);
        mestoVrnitve.setValue(null);
        velikost.setValue(null);
        menjalnik.setValue(null);
        gorivo.setValue(null);
        zavarovanje.setSelected(false);
        izbiraAvtomobila.setValue(null);
        cenaNaDan.setText("0€");
        cenaSkupaj.setText("0€");
        placiloSKartico.setSelected(false);
        imePlacnika.setText("");
        ulica.setText("");
        priimekPlacnika.setText("");
        hisnaSt.setText("");
        mestoPlacnika.setText("");
        postnaSt.setText("");
        email.setText("");
        ccv.setText("");
        starost.setText("");
        stevilkaVozniske.setText("");
        telefonska.setText("");
        stevilkaKartice.setText("");
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

    public void toggleZavarovanje(MouseEvent mouseEvent) {
        if (zavarovanje.isSelected()){
            cenaNaDanInt += cenaZavarovanjaInt;
        } else {
            cenaNaDanInt -= cenaZavarovanjaInt;
        }
        cenaNaDan.setText(cenaNaDanInt + "€");
        cenaSkupajInt = stDni * cenaNaDanInt;
        cenaSkupaj.setText(cenaSkupajInt + "€");
    }

    public void toggleKartica(MouseEvent mouseEvent) {
        stevilkaKartice.setEditable(placiloSKartico.isSelected());
        ccv.setEditable(placiloSKartico.isSelected());
    }

    public void sprememba(ActionEvent ActionEvent){
        ArrayList<String> avti = new ArrayList<>();
        String velikostAvta = (String) velikost.getValue();
        String menjalnikAvta = (String) menjalnik.getValue();
        String gorivoAvta = (String) gorivo.getValue();

        if (velikost.getValue() != null && menjalnik.getValue() != null && gorivo.getValue() != null){
            for (String[] avto : avtomobili) {
                if (avto[1].equals(velikostAvta) && avto[2].equals(menjalnikAvta) && avto[3].equals(gorivoAvta))
                    avti.add(avto[4] + "." + avto[0]);
            }
        } else if (velikost.getValue() != null && menjalnik.getValue() != null) {
            for (String[] avto : avtomobili) {
                if (avto[1].equals(velikostAvta) && avto[2].equals(menjalnikAvta))
                    avti.add(avto[4] + "." + avto[0]);
            }
        } else if (velikost.getValue() != null && gorivo.getValue() != null) {
            for (String[] avto : avtomobili) {
                if (avto[1].equals(velikostAvta) && avto[3].equals(gorivoAvta))
                    avti.add(avto[4] + "." + avto[0]);
            }
        } else if (menjalnik.getValue() != null && gorivo.getValue() != null) {
            for (String[] avto : avtomobili) {
                if (avto[2].equals(menjalnikAvta) && avto[3].equals(gorivoAvta))
                    avti.add(avto[4] + "." + avto[0]);
            }
        } else if (velikost.getValue() != null) {
            for (String[] avto : avtomobili) {
                if (avto[1].equals(velikostAvta))
                    avti.add(avto[4] + "." + avto[0]);
            }
        } else if (menjalnik.getValue() != null) {
            for (String[] avto : avtomobili) {
                if (avto[2].equals(menjalnikAvta))
                    avti.add(avto[4] + "." + avto[0]);
            }
        } else if (gorivo.getValue() != null) {
            for (String[] avto : avtomobili) {
                if (avto[3].equals(gorivoAvta))
                    avti.add(avto[4] + "." + avto[0]);
            }
        } else {
            for (String[] avto : avtomobili) {
                avti.add(avto[4] + "." + avto[0]);
            }
        }
        int st = izbiraAvtomobila.getItems().size();
        for (int i = 0; i < st; i++) {
            izbiraAvtomobila.getItems().remove(0);
        }
        Collections.sort(avti);
        for (String s : avti) {
            izbiraAvtomobila.getItems().add(s);
        }
    }

    public void onSelect(ActionEvent actionEvent) {
        String izbranAvto = (String) izbiraAvtomobila.getValue();
        String[] avto = izbranAvto.split("\\.");
        for (int i = 0; i < avtomobili.length; i++) {
            if (avtomobili[i][4].equals(avto[0])){
                cenaNaDanInt = Integer.parseInt(avtomobili[i][5]);
                updateCene();
            }
        }
    }

    private void updateCene(){
        cenaNaDan.setText(String.valueOf(cenaNaDanInt) + "€");
        cenaSkupaj.setText(String.valueOf(cenaNaDanInt * stDni) + "€");
    }
}