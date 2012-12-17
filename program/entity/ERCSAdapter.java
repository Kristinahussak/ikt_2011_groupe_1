package entity;
//
//import gnu.io.CommPortIdentifier;
//import gnu.io.NoSuchPortException;
//import gnu.io.PortInUseException;
//import gnu.io.SerialPort;
//import gnu.io.SerialPortEvent;
//import gnu.io.SerialPortEventListener;
//import gnu.io.UnsupportedCommOperationException;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Enumeration;
//
//
///**
// * ERCSAdapter.java
// * @author 3. Semester Projekt, Gruppe 1
// * Anders Kold, Kristina Hussak,
// * Henning Fich & Nicholaj Rasmussen
// * Created on 03-12-2012
// */
//
public class ERCSAdapter implements IERCS 
{

	ERCSStub RCS;

    public ERCSAdapter() {}    

   
    public boolean retrieveItem(int stockPosition) {
    	
    	String serialMessage = ("retrieveItem:" + new Integer(stockPosition).toString() + "/ ");    	
    	String result = sendMessage(serialMessage);
    	//System.out.println("retrieveItem " + result);
    	return Boolean.valueOf(result);
    }

    

    public String scanItem() {
    	String serialMessage = "scanItem:/ ";
    	String result = sendMessage(serialMessage);
    	System.out.println("scanItem " + result);
        return result;
    }

	
	public boolean storeItem(int stockPosition) {
		String serialMessage = ("storeItem:" + new Integer(stockPosition).toString()+"/ ");    	
		String result = sendMessage(serialMessage);
		//System.out.println("storeItem " + result);    	
        return Boolean.valueOf(result);
	}
	
	public String sendMessage(String serialMessage){
		RCS = new ERCSStub();
    	String response = RCS.message(serialMessage);    	
    	String result = revieveMessage(response);    	
    	return result;		
	}
	
	public String revieveMessage(String message){
		String returnValue = "false";
		//remove "/13" from end of message
		String str = message.substring(0, message.length() - 3);		
		
		String[] parts;
		parts = str.split(":");
		String method = parts[0];
		try{
			returnValue = parts[1];
		}catch (Exception e){
			
		}
		
		return returnValue;
	}
}
