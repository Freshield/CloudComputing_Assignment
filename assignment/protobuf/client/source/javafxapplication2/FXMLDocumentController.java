/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import javax.swing.*;
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
//import org.json.*;

import com.google.protobuf.InvalidProtocolBufferException;
import com.protobuf.AnimalList;


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
    
    
    
    private DataOutputStream doutput;
    private DataInputStream dinput;
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
            
            game_value = 0;
            game_level = 0;
            show_area.setText("");
            
            //new socket
            s = new Socket(IP_addr,port_num);
            
            dinput = new DataInputStream(s.getInputStream());
            doutput = new DataOutputStream(s.getOutputStream());
            
            Thread readerThread = new Thread(new IncomingReader());
            readerThread.start();
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    @FXML
    private void yesMethod(ActionEvent event) {
        
        switch(game_level){
            
            
            
            case 1:game_value += 16;
            break;         
            
            case 2:game_value += 8;
            break;         
            
            case 3:game_value += 4;
            break;         
            
            case 4:game_value += 2;
            break;         
            
            default:
                break;
        }
        
        game_level += 1;
        try{
            AnimalList.Animal.Builder builder = AnimalList.Animal.newBuilder();
	    builder.setId("client");
	    builder.setName(new Integer(game_value).toString());
	    AnimalList.Animal animal = builder.build();
            byte[] outter = animal.toByteArray();
            doutput.write(outter);
            System.out.println("yes"+animal.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        if(game_level > 1){
        show_area.appendText("YES"+"\n");
        }
        System.out.println("yes"+game_level);
        
    }
    @FXML
    private void noMethod(ActionEvent event) {
        if(game_level != 0){
            game_value += 1;
            game_level += 1;
            
        }
        
        try{
            AnimalList.Animal.Builder builder = AnimalList.Animal.newBuilder();
	    builder.setId("client");
	    builder.setName(new Integer(game_value).toString());
	    AnimalList.Animal animal = builder.build();
	    byte[] outter = animal.toByteArray();
            doutput.write(outter);
            System.out.println("no"+animal.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(game_level == 0){
            
           
           game_level += 1;
            
        }
        System.out.println("no"+game_level);
        if(game_level > 1){
        show_area.appendText("NO"+"\n");
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public class IncomingReader implements Runnable{
        
        public void run(){
            
            String message;
            
            
            try{
                
		System.out.println("thread waitting data");
                
		byte len[] = new byte[1024];
                int count; 
            
		while((count = dinput.read(len)) != 0){
			System.out.println("thread into writting");

	                byte[] temp = new byte[count];
	                
	                for (int i = 0; i < count; i++) {   
	                    
	                        temp[i] = len[i];                              
	                } 
			//change to animal type
			AnimalList.Animal getanimal = AnimalList.Animal.parseFrom(temp);
			//get id
                        
			message = getanimal.getName().toString();
                        show_area.appendText(message+"\n");
			
                        System.out.println("thread server"+game_level);
                    
                        if(game_level >= 5){
                        
                        System.out.println("thread"+message);
                        
                        final_answer test = new final_answer(message);
                        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        test.setLocationRelativeTo(null);
                        test.setVisible(true);
                        show_area.setText("Let's play again~\n");
                        game_level = 0;
                        game_value = 0;
                        
                        
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
        }
    }
    
}
