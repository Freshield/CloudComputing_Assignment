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
import java.io.*;
import java.net.*;
import org.json.*;

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
        try{
            String IP_addr = ip_text.getText();
            int port_num = 0;
            try{
                port_num = Integer.parseInt(port_text.getText());
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
            
            Socket s = new Socket(IP_addr,port_num);
            
            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            
            //String message = reader.readLine().toString();
            JSONObject obj = new JSONObject(reader.readLine());
            //JSONObject getter = new JSONObject();
           // getter.put("obj",message);
            
            
            
            show_area.appendText("\n"+obj.getString("age"));
            
            
            
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
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
