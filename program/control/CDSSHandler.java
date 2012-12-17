package control;

import entity.*;
import mediator.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * CDSSHandler.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */
//test
public class CDSSHandler extends Thread
{	
	private static ServerSocket serverSocket;

	private static final int PORT = 55555;
	public boolean serverRunning = true;

	
    public CDSSHandler() {
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("     - Server Socket was initialized with port: "+PORT);
		} catch (IOException ioEx) {
			System.out.println("     - Unable to set up port on Server Socket: "+PORT);
			System.exit(1);
		}		
	}
    
    public void run()
    {
    	System.out.println("     - Server is now running.");
    	while(serverRunning)
    	{
    		Socket client = null;
    		try {	
    			System.out.println("     - Server is now awaiting clients.");
    			client = serverSocket.accept();    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}

    		System.out.println("     - A new client has been accepted");

    		DSSHandler handler = new DSSHandler(client);
    		handler.setRunning(true);			
    		new Thread(handler).start();  
    	}
    }
    
    public void setServerRunning(boolean state)
    {
    	this.serverRunning = state;
    }
}
    class DSSHandler  implements Runnable {    	
    	private Socket client;
    	private Scanner input;
    	private PrintWriter output;
    	boolean running;    
    	
    	public DSSHandler(Socket socket) {
    		client = socket;

    		try {
    			input = new Scanner(client.getInputStream());
    			output = new PrintWriter(client.getOutputStream(), true);
    		} catch (IOException ioEx) {
    			ioEx.printStackTrace();
    		}
    	}
    public void setRunning(boolean running){
    	this.running = running;
    }
   public void run(){
	   System.out.println("     - Server is awaiting orders from client: "+client.getRemoteSocketAddress().toString());
	   while(running==true)
	   receiveOrder();
    }
    	
    private boolean receiveOrder()
    {
    	String orderString;
    	orderString = input.nextLine();
    	boolean result = EFacade.getInstance().createOrder(orderString);
    	if(result==true){
    		output.println("Order accepted");
    		CObservable.getInstance().notifySubcribers();   		  	
    	}
    	else{
    		output.println("Order not accepted");
    	}
    	return result;    
    }    
    

    }
    
