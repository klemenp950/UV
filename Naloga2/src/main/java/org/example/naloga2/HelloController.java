package org.example.naloga2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

import java.io.*;
import java.lang.invoke.StringConcatFactory;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HelloController {
    public Label status;
    public HTMLEditor editor;
    public TextArea logi;
    public TextArea editortxt;
    public TextField poisciTextField;
    public TitledPane titlePaneTxt;
    public AnchorPane anchorPaneTxt;
    public TextField zamenjajTextField;
    public Accordion accordion;
    public TitledPane titlePaneHtml;
    @FXML
    private Label welcomeText;

    public boolean txt;

    @FXML

    FileChooser fc;
    File f;

    public void odpriButtonSB(ActionEvent actionEvent) {
        fc = new FileChooser();
        fc.setTitle("Izberi datoteko za branje");
        f = fc.showOpenDialog(null);
        if (f != null){
            StringBuilder sb = new StringBuilder();
            try(BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line;
                while ((line = br.readLine()) != null){
                    sb.append(line + "\n");
                }
                editor.setHtmlText(sb.toString());
                editortxt.setText(sb.toString() + "\n");
                String log = "Odprli ste datoteko: " + f.getName() + ". Velikost: " + f.length() + " bytov.\n";
                status.setText(log);
                logi.appendText(String.format("[%s]: %s", getTime(), log));
            } catch (Exception e){
                System.out.println(e.getMessage());
                String log = "Neuspešno odpiranje datoteke. Napaka: " + e.getMessage() + ".\n";
                status.setText(log);
                logi.appendText(String.format("[%s]: %s", getTime(), log));
            }
        }
    }

    public void shraniButtonSB(ActionEvent actionEvent) {
        if (f!=null){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))){
                if(txt){
                    bw.write(editortxt.getText());
                } else{
                    bw.write(editor.getHtmlText());
                }
                String log = "Zapisal datoteko "+f.getName()+".\n";
                status.setText(log);
                logi.appendText(String.format("[%s]: %s", getTime(), log));
            }catch (Exception e){
                System.out.println(e.getMessage());
                String log = "Neuspešno shranjevanje datoteke. Napaka: " + e.getMessage() + ".\n";
                status.setText(log);
                logi.appendText(String.format("[%s]: %s", getTime(), log));
            }
        }

        if(new File(f.getParentFile().getPath() + "\\" + f.getName().split("\\.")[0] + "-logs.txt").exists()){
            System.out.println("Datoteka že obstaja.");
            File logs = new File(f.getParentFile().getPath() + "\\" + f.getName().split("\\.")[0] + "-logs.txt");
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(logs, true))){
                String log = "Zapisal datoteko "+logs.getName()+".\n";
                status.setText(log);
                logi.appendText(String.format("[%s]: %s", getTime(), log));
                bw.append(logi.getText());
            }catch (Exception e){
                System.out.println(e.getMessage());
                String log = "Neuspešno shranjevanje datoteke. Napaka: " + e.getMessage() + ".\n";
                status.setText(log);
                logi.appendText(String.format("[%s]: %s", getTime(), log));
            }
        } else {
            File logs = new File(f.getParentFile().getPath() + "\\" + f.getName().split("\\.")[0] + "-logs.txt");
            try {
                logs.createNewFile();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(logs))){
                    String log = "Zapisal datoteko "+logs.getName()+".\n";
                    status.setText(log);
                    logi.appendText(String.format("[%s]: %s", getTime(), log));
                    bw.append(logi.getText());
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
                String log = "Neuspešno ustvarjanje datoteke z logi. Napaka: " + e.getMessage() + ".\n";
                status.setText(log);
                logi.appendText(String.format("[%s]: %s", getTime(), log));
            }
        }
    }

    public void shraniKotSB(ActionEvent actionEvent) {
        fc = new FileChooser();
        fc.setTitle("Izberi datoteko za shranjevanje");
        f = fc.showSaveDialog(null);
        if (f!=null){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))){
                if(txt){
                    bw.write(editortxt.getText());
                } else{
                    bw.write(editor.getHtmlText());
                }
                String log = "Zapisal daltoteko "+f.getName()+".\n";
                status.setText(log);
                logi.appendText(String.format("[%s]: %s", getTime(), log));
            }catch (Exception e){
                System.out.println(e.getMessage());
                String log = "Neuspešno shranjevanje datoteke. Napaka: " + e.getMessage() + ".\n";
                status.setText(log);
                logi.appendText(String.format("[%s]: %s", getTime(), log));
            }
        }
        System.out.println(f.getParentFile().getPath() + "\\" + f.getName().split("\\.")[0] + "-logs.txt");
        if(new File((f.getParentFile().getPath() + "\\" + f.getName().split("\\.")[0] + "-logs.txt")).exists()){
            File logs = new File(f.getParentFile().getPath() + "\\" + f.getName().split("\\.")[0] + "-logs.txt");
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(logs))){
                String log = "Zapisal datoteko "+logs.getName()+".\n";
                status.setText(log);
                logi.appendText(String.format("[%s]: %s", getTime(), log));
                bw.append(logi.getText());
            }catch (Exception e){
                System.out.println(e.getMessage());
                String log = "Neuspešno shranjevanje datoteke. Napaka: " + e.getMessage() + ".\n";
                status.setText(log);
                logi.appendText(String.format("[%s]: %s", getTime(), log));
            }
        } else {
            File logs = new File(f.getParentFile().getPath() + "\\" + f.getName().split("\\.")[0] + "-logs.txt");
            try {
                logs.createNewFile();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(logs))){
                    String log = "Zapisal datoteko "+logs.getName()+".\n";
                    status.setText(log);
                    logi.appendText(String.format("[%s]: %s", getTime(), log));
                    bw.append(logi.getText());
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
                String log = "Neuspešno ustvarjanje datoteke z logi. Napaka: " + e.getMessage() + ".\n";
                status.setText(log);
                logi.appendText(String.format("[%s]: %s", getTime(), log));
            }
        }
    }

    public void zapriSB(ActionEvent actionEvent) {
        if(f != null)
            shraniButtonSB(actionEvent); //Shrani spremembe v odprto datoteko pred zapiranjem.
        System.exit(0);
    }

    public void titlePaneTxtSB(MouseEvent mouseEvent) {
        editortxt.setText(editor.getHtmlText());
        txt = true;
    }

    public void titlePaneHTMLSB(MouseEvent mouseEvent) {
        editor.setHtmlText(editortxt.getText());
        txt = false;
    }

    public void najdiSB(ActionEvent actionEvent) {
        String besedilo = editortxt.getText();
        int kazalec = besedilo.indexOf(poisciTextField.getText());
        if (kazalec != -1){
            editortxt.positionCaret(kazalec);
            editortxt.requestFocus();
            editortxt.selectRange(besedilo.indexOf(poisciTextField.getText()), poisciTextField.getText().length() + besedilo.indexOf(poisciTextField.getText()));
            status.setText(String.valueOf(kazalec));
            String log = String.format("Najden niz %s.\n", poisciTextField.getText());
            logi.appendText(String.format("[%s]: %s", getTime(), log));
            status.setText(log);
        } else {
            String log = String.format("Podniza ni v datoteki %s.\n", poisciTextField.getText());
            logi.appendText(String.format("[%s]: %s", getTime(), log));
            status.setText(log);
        }

    }

    public void najdiVseInZamenjajSB(ActionEvent actionEvent) {
        if (anchorPaneTxt.isFocused()) {
            String besedilo = editortxt.getText();
            int kazalec = besedilo.indexOf(poisciTextField.getText());
            while (kazalec >= 0) {
                besedilo = besedilo.replaceAll(poisciTextField.getText(), zamenjajTextField.getText());
                kazalec = besedilo.indexOf(poisciTextField.getText());
            }
            editortxt.setText(besedilo);
            editor.setHtmlText(besedilo);
        } else {
            String besedilo = editor.getHtmlText();
            int kazalec = besedilo.indexOf(poisciTextField.getText());
            while (kazalec >= 0) {
                besedilo = besedilo.replaceAll(poisciTextField.getText(), zamenjajTextField.getText());
                kazalec = besedilo.indexOf(poisciTextField.getText());
            }
            editor.setHtmlText(besedilo);
            editortxt.setText(besedilo);
        }
        String log = String.format("Zamenjali ste %s za %s.\n", poisciTextField.getText(), zamenjajTextField.getText());
        status.setText(log);
        logi.appendText(String.format("[%s]: %s", getTime(), log));
    }

    public static String getTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public void avtorSB(ActionEvent actionEvent) {
        String log = "Avtor: Klemen Parkelj";
        status.setText(log);
        logi.appendText(String.format("[%s]: %s", getTime(), log));
    }
}