package assignment1;

import java.awt.BorderLayout;
import java.io.*;
import javax.swing.*;
import java.net.*;
import org.json.*;

public class Server {
	
	private String list = "{"
    		+ "'4':'Cow','5':'Horse','7':'Sheep','8':'Rabbit','11':'Snake',"
    		+ "'12':'Lion','14':'Cat','15':'Dog','19':'Fly','20':'Mosquito',"
    		+ "'22':'Butterfly','23':'Dragonfly','26':'Woodpecker','27':'Bat',"
    		+ "'29':'Duck','30':'Goose',"
    		+ "'0':'Does it can fly?','1':'Does it eat meat?','2':'Does it have soft fur?',"
    		+ "'3':'Does it run fast?','6':'Does it has red eye?','9':'Does it is a pet?',"
    		+ "'10':'Does it has legs?','13':'Does it need walk everyday?',"
    		+ "'16':'Does it eat meat?','17':'Does it looked beautiful?',"
    		+ "'18':'Does it drink blood?','21':'Does it lives around water?',"
    		+ "'24':'Does it lives around water?','25':'Does it act during the night?',"
    		+ "'28':'Does it big?'}";
	

    private JSONObject animal_list = new JSONObject(list);
    
    private BufferedReader reader;
    private PrintWriter writer;
    private JFrame frame;
    private JPanel panel;
    private JTextArea text;
    

	public static void main(String[] args) {
		
		new Server().go();

	}
	
	public void go(){
		
		try{
			
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
			
			ServerSocket serverSock = new ServerSocket(2333);

			
			while(true){
				Socket clientSocket = serverSock.accept();

				writer = new PrintWriter(clientSocket.getOutputStream());
				
				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();

				text.append("connect success\n");
				JSONObject buf = new JSONObject();
				buf.put("server", "Welcome to Animal guess game~Are you ready?");

				writer.println(buf);
				writer.flush();
				
				
				
			}
			
			
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public class ClientHandler implements Runnable{
		
		BufferedReader reader;
		Socket sock;
		
		public ClientHandler(Socket clientSocket){
			
			try{
				
				sock = clientSocket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(isReader);
				
				
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		public void run(){
			
			String message;
			JSONObject sender;
			JSONObject getter;
			try{
				
				while((message = reader.readLine()) != null){
					
					getter = new JSONObject(message);
					
					message = getter.getString("client");
					text.append(message+"\n");
					sender = new JSONObject();
					sender.put("server", animal_list.getString(message));

					writer.println(sender);
					writer.flush();
					
				}
				
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		
	}

}
