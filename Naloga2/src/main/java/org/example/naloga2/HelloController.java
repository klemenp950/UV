package org.example.naloga2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

import java.io.*;
import java.lang.reflect.Array;
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
                status.setText("Odprli ste datoteko: " + f.getName() + ". Velikost: " + f.length() + " bytov");
                editortxt.setText(sb.toString() + "\n");
                logi.appendText("Odprli ste datoteko: " + f.getName() + ". Velikost: " + f.length() + " bytov\n");
            } catch (Exception e){
                System.out.println(e.getMessage());
                status.setText("Neuspešno odpiranje datoteke. Napaka: " + e.getMessage());
                logi.appendText("Neuspešno odpiranje datoteke. Napaka: " + e.getMessage() + "\n");
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
                status.setText("Zapisal datoteko "+f.getName()+'\n');
                logi.appendText("Zapisal datoteko "+f.getName()+'\n');
            }catch (Exception e){
                System.out.println(e.getMessage());
                status.setText("Neuspešno shranjevanje datoteke. Napaka: " + e.getMessage());
                logi.appendText("Neuspešno shranjevanje datoteke. Napaka: " + e.getMessage() + "\n");
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
                logi.appendText("Zapisal daltoteko "+f.getName()+'\n');
            }catch (Exception e){
                System.out.println(e.getMessage());
                status.setText("Neuspešno shranjevanje datoteke. Napaka: " + e.getMessage());
                logi.appendText("Neuspešno shranjevanje datoteke. Napaka: " + e.getMessage() + "\n");
            }
        }
    }

    public void zapriSB(ActionEvent actionEvent) {
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
        String besedilo = editor.getHtmlText();
        int kazalec = besedilo.indexOf(poisciTextField.getText());
        editortxt.positionCaret(kazalec);
        editortxt.requestFocus();
        editortxt.selectRange(besedilo.indexOf(poisciTextField.getText()), poisciTextField.getText().length() + besedilo.indexOf(poisciTextField.getText()));
        // TODO: Naredi da ko iščeš po dokumentu se odpre TXT anchorPane.
        status.setText(String.valueOf(kazalec));
    }

    public void najdiVseInZamenjajSB(ActionEvent actionEvent) {
        ArrayList<Integer> seznam = new ArrayList<Integer>();
        int i = 0;
        while (i < editortxt.getLength()){
            String besedilo = editortxt.getText().substring(i);
            int kazalec = besedilo.indexOf(poisciTextField.getText());
            if(kazalec != -1)
                seznam.add(kazalec + i);
            else
                break;

            i += kazalec + poisciTextField.getLength();
        }

        for (Integer integer : seznam) {
            editortxt.replaceText(new IndexRange(integer, integer + poisciTextField.getLength()), zamenjajTextField.getText());
        }
    }

    //TODO: Naredi Najdi vse in zamenjaj.

}