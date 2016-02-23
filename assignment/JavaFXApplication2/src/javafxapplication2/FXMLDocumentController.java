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
    
    private BufferedReader reader;
    private PrintWriter writer;
    private int game_value = 0;
    private int game_level = 0;
    private Socket s;
    
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
            
            
            s = new Socket(IP_addr,port_num);
            
            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(s.getOutputStream());
            
            Thread readerThread = new Thread(new IncomingReader());
            readerThread.start();
            //String message = reader.readLine().toString();
            //JSONObject obj = new JSONObject(reader.readLine());
            //JSONObject getter = new JSONObject();
           // getter.put("obj",message);
            
            
            
            //show_area.appendText(obj.getString("age")+"\n");
            
            
            
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    @FXML
    private void yesMethod(ActionEvent event) {
        writer.println("0");
        writer.flush();
        show_area.appendText("YES"+"\n");
    }
    @FXML
    private void noMethod(ActionEvent event) {
        System.out.println("You clicked me!");
        show_area.appendText("Hello world\n");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public class IncomingReader implements Runnable{
        
        public void run(){
            
            String message;
            try{
                
                while((message = reader.readLine()) != null){
                    JSONObject obj = new JSONObject(message);
                    show_area.appendText(obj.getString("value")+"\n");
                }
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
}
