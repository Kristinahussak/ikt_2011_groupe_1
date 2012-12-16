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
public class CDSSHandler extends Observable
{
	private static ServerSocket serverSocket;

	private static final int PORT = 55555;

	
    public CDSSHandler() {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException ioEx) {
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}

		do {
			Socket client = null;
			try {
				client = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("\nNew client accepted.\n");

			DSSHandler handler = new DSSHandler(client);
			handler.setRunning(true);			
			new Thread(handler).start();
		} while (true);
	}
}
    class DSSHandler extends Observable implements Runnable {
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
    		setChanged();
    		notifyObservers();    		  	
    	}
    	else{
    		output.println("Order not accepted");
    	}
    	return result;
    	
    	
    }    
    

    }
    
