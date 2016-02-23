/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.awt.BorderLayout;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.*;

/**
 *
 * @author FRESHIELD
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        newframe test = new newframe();
        mypanel pan = new mypanel();
        test.getContentPane().add(BorderLayout.NORTH,pan);
        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        test.setLocationRelativeTo(null);
        test.setVisible(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
