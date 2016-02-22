/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package en.crypto.r;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author FRESHIELD
 */
public class EncryptorController implements Initializable {
    
    @FXML
    private Label label03;
    
    @FXML
    private void newMethod(ActionEvent event) {
        label03.setText("This is new Button.");
    }
    
    private void loadMethod(ActionEvent event){
        
    }
    
    private void encodeMethod(ActionEvent event){
        
    }
    
    private void decodeMethod(ActionEvent event){
        
    }
    
    private void clearMethod(ActionEvent event){
        
    }
    
    private void copyMethod(ActionEvent event){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
