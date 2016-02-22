/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package en.crypto.r;

//import java.awt.datatransfer.Clipboard;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 *
 * @author FRESHIELD
 */
public class EncryptorController implements Initializable {
    
    Encrypt e;
    String inputText;
    String outputText;
    
    @FXML
    private Label label03;
    @FXML
    private TextField text01;
    @FXML
    private TextField text02;
    
    public EncryptorController(){
        
        text01 = new TextField();
        text02 = new TextField();
        e = null;
        inputText = "";
        outputText = "";
        
        
    }
    @FXML
    private void newMethod(ActionEvent event) {
        e = new Encrypt();
        label03.setText(String.valueOf(e.getCode()));
        
    }
    @FXML
    private void saveMethod(ActionEvent event){
        try{
            
        if(e == null){
            label03.setText("No Encrypt Object!!!");
            
        }else{
            
            File f = new File("encryptor.txt");
            FileWriter fw = new FileWriter(f);
            fw.write(String.valueOf(e.getCode()));
            fw.close();
            
        }
        label03.setText("Encrypt object is saved");
            
            
        }catch(IOException ex){
            label03.setText("Encrypt object is not saved");
        }finally{
            text02.setText("after Save...");
        }
        
        
    }
    @FXML
    private void loadMethod(ActionEvent event){
        
        try{
            FileReader fr = new FileReader("encryptor.txt");
            BufferedReader r = new BufferedReader(fr);
            String ms = r.readLine();
            e = new Encrypt(ms);
            fr.close();
            label03.setText("Encrypt object is loaded");
            
            
        }catch(IOException ex){
            label03.setText("Encrypt object is not loaded");
        }finally{
            text02.setText("after Load...");
        }
        
    }
    @FXML
    private void encodeMethod(ActionEvent event){
        
        if(e == null){
            label03.setText("No Encrypt Object!!!");
            
        }else{
            
            inputText = text01.getText();
            if(inputText.compareTo("") == 0){
                label03.setText("No input string!!!");
            }else{
                outputText = e.toEncode(inputText);
                text02.setText(outputText);
                label03.setText("The result is above");
            }
        }
        
    }
    @FXML
    private void decodeMethod(ActionEvent event){
        if(e == null){
            label03.setText("No Encrypt Object!!!");
            
        }else{
            
            inputText = text01.getText();
            if(inputText.compareTo("") == 0){
                label03.setText("No input string!!!");
            }else{
                outputText = e.toDecode(inputText);
                text02.setText(outputText);
                label03.setText("The result is abouve");
            }
        }
        
    }
    @FXML
    private void clearMethod(ActionEvent event){
        
        text01.setText("");
        text02.setText("");
        e = null;
        inputText = "";
        outputText = "";
        label03.setText("Cleared");
        
    }
    @FXML
    private void copyMethod(ActionEvent event){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(outputText);
        clipboard.setContent(content);
        label03.setText("Copied");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
