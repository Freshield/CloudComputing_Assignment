/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

/**
 *
 * @author FRESHIELD
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label ip_label;
    @FXML
    private Label port_label;
    @FXML
    private TextField ip_text;
    @FXML
    private TextField port_text;
    @FXML
    private TextArea show_area;
    
    @FXML
    private void connectMethod(ActionEvent event) {
        System.out.println("You clicked me!");
        show_area.appendText("\nHello world");
    }
    @FXML
    private void yesMethod(ActionEvent event) {
        System.out.println("You clicked me!");
        show_area.appendText("\nHello world");
    }
    @FXML
    private void noMethod(ActionEvent event) {
        System.out.println("You clicked me!");
        show_area.appendText("\nHello world");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
