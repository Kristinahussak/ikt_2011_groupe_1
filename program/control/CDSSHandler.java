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

public class CDSSHandler
{
	private static ServerSocket serverSocket;
	//Hvilken port bruger vi?
	private static final int PORT = 12345;

	
    public CDSSHandler() {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException ioEx) {
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}

		do {
			// Wait for client...
			Socket client = null;
			try {
				client = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("\nNew client accepted.\n");

			// Create a thread to handle communication with
			// this client and pass the constructor for this
			// thread a reference to the relevant socket...
			ClientHandler handler = new ClientHandler(client);
			handler.setRunning(true);
			handler.start();// As usual, this method calls run.
		} while (true);
	}
}
    class ClientHandler extends Thread {
    	private Socket client;
    	private Scanner input;
    	private PrintWriter output;
    	boolean running;
    	
  
    	
    	public ClientHandler(Socket socket) {
    		// Set up reference to associated socket...
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

    	
    	
    	EFacade.getInstance().createOrder(orderString);
    	
        return false;
    }    
    

    }
    
