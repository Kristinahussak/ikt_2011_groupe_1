package entity;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;



import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;


public class ETest
{
    public ETest()
    {
        //super();
    }
    
   public static void main ( String[] args )
    {
        ERCSAdapter RCS = new ERCSAdapter();
        RCS.scanItem();
        RCS.storeItem(1234567);
        RCS.retrieveItem(1234567);  
        
        
    }

    
    /*public static void main ( String[] args )
    {
        try
        {
            (new ETest()).connect("COM4");
        }
        catch ( Exception e )
        {
        	System.out.println("Kan ikke �bne porten");
        	
        	//System.exit(-1);
        }
    } */ 
    

    void connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort port = portIdentifier.open("CSS",2000);
           
            if ( port instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) port;
                serialPort.setSerialPortParams(19200,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                InputStream in = serialPort.getInputStream();
                OutputStream out =serialPort.getOutputStream();
                
                String message = "Hej dav" + "/ ";
            	byte[] test = message.getBytes();
        	    test[test.length-1] = 13;
                out.write(test); 
                
                in.close();
                out.close();
                System.exit(-1);
                
                
                (new Thread(new Reader(in))).start();
                (new Thread(new Writer(out))).start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }
    

    public static class Reader implements Runnable 
    {
        InputStream in;
        
        public Reader ( InputStream in )
        {
            this.in = in;
        }
        
        public void run ()
        {
            byte[] buffer = new byte[1024];
            int len = -1;
            try
            {
                while ( ( len = this.in.read(buffer)) > -1 )
                {
                    System.out.print(new String(buffer,0,len));
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }

  
    public static class Writer implements Runnable 
    {
        OutputStream out;
        
        public Writer ( OutputStream out )
        {
            this.out = out;
        }
        
        public void run ()
        {
            try
            {      
                message = message + "/ ";
            	byte[] test = message.getBytes();
        	    test[test.length-1] = 13;
                this.out.write();                          
                              
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }
    
  
}