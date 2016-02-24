package probuf_server;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.google.protobuf.InvalidProtocolBufferException;
import com.protobuf.AnimalList;

import java.net.ServerSocket;
import java.net.Socket;
import java.awt.BorderLayout;
import java.io.*;

public class Server {
	
	private HashMap<String, String> map = new HashMap<String, String>() {
	    {
	    	put("0","Does it can fly?");
	    	put("1","Does it eat meat?");
	    	put("2","Does it have soft fur?");
	    	put("3","Does it run fast?");
	    	put("4","Cow");
	    	put("5","Horse");
	    	put("6","Does it has red eye?");
	    	put("7","Sheep");
	    	put("8","Rabbit");
	    	put("9","Does it is a pet?");
	    	put("10","Does it has legs?");
	    	put("11","Snake");
	    	put("12","Lion");
	    	put("13","Does it need walk everyday?");
	    	put("14","Cat");
	    	put("15","Dog");
	    	put("16","Does it eat meat?");
	    	put("17","Does it looked beautiful?");
	    	put("18","Does it drink blood?");
	    	put("19","Fly");
	    	put("20","Mosquito");
	    	put("21","Does it lives around water?");
	    	put("22","Butterfly");
	    	put("23","Dragonfly");
	    	put("24","Does it lives around water?");
	    	put("25","Does it act during the night?");
	    	put("26","Woodpecker");
	    	put("27","Bat");
	    	put("28","Does it big?");
	    	put("29","Duck");
	    	put("30","Goose");
	    }
	};
	


    private JFrame frame;
    private JPanel panel;
    private JTextArea text;
    private DataOutputStream doutput;

	public static void main(String[] args) {
		
		new Server().go();

	}
	
	public void go(){
		
		try{
			//GUI
			frame = new JFrame();
			panel = new JPanel();
			text = new JTextArea(25,40);
			text.setLineWrap(true);
			
			JScrollPane scroller = new JScrollPane(text);
			scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			panel .add(scroller);
			
			frame.getContentPane().add(BorderLayout.CENTER, panel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(600, 600);
			frame.setVisible(true);
			
			//socket
			
			ServerSocket serverSock = new ServerSocket(2333);

			
			while(true){
				//get sock
				Socket clientSocket = serverSock.accept();

				doutput = new DataOutputStream(clientSocket.getOutputStream());
				
				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();

				text.append("connect success\n");
				
				//write first welcome information
				
				AnimalList.Animal.Builder builder = AnimalList.Animal.newBuilder();
				builder.setId("server");
				builder.setName("Welcome to Animal guess game~Are you ready?");
				AnimalList.Animal animal = builder.build();
				byte[] outter = new byte[100];
			    outter = animal.toByteArray();
		        doutput.write(outter);
				System.out.println("go"+animal.toString());
				
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	

	public class ClientHandler implements Runnable{
		
		
		private Socket sock;
	    private DataInputStream dinput;
		
		public ClientHandler(Socket clientSocket){
			
			try{
				//get sock and datainputstream
				sock = clientSocket;
				dinput = new DataInputStream(sock.getInputStream());
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		public void run(){
			
			String message;
			String getResult;
			//DataInputStream dbuffer;
			
			try{
				//when get inputstream
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
					text.append(message+"\n");
					//find the message
					getResult = map.get(message);
					//send back
					AnimalList.Animal.Builder backer = AnimalList.Animal.newBuilder();
					backer.setId("server");
					backer.setName(getResult);
					AnimalList.Animal animalget = backer.build();
					byte[] outter = animalget.toByteArray();
			        doutput.write(outter);
					System.out.println("thread"+animalget.toString());
					//backer.build().writeTo(doutput);
					
				}
				
				
			}catch(Exception ex){
				ex.printStackTrace();
			
			}
		}
		
		
	}
		

}
