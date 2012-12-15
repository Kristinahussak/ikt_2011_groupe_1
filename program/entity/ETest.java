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
   /* public ETest()
    {
        super();
    }*/
    
    public static void main ( String[] args )
    {
        ERCSAdapter RCS = new ERCSAdapter();
        RCS.scanItem();
        RCS.storeItem(1234567);
        RCS.retrieveItem(1234567);  
        
        
    }
}
    
    
    /*
    void connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
        	System.out.println("Åbner");
            CommPort commPort = portIdentifier.open("CSS",2000);
            
            
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(19200,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                InputStream in = serialPort.getInputStream();
                OutputStream out =serialPort.getOutputStream();
                
                System.out.println("Sender Hej");
                String message = "Hej /";
                out.write(message.getBytes());
               // in.close();
                //out.close();
                //System.exit(-1);
                
                
                (new Thread(new SerialReader(in))).start();
                (new Thread(new SerialWriter(out))).start();

            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }
    

    public static class SerialReader implements Runnable 
    {
        InputStream in;
        
        public SerialReader ( InputStream in )
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

  
    public static class SerialWriter implements Runnable 
    {
        OutputStream out;
        
        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }
        
        public void run ()
        {
            try
            {                
                int c = 0;
                while ( ( c = System.in.read()) > -1 )
                {
                    this.out.write(c);
                }                
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }            
        }
    }
    
    public static void main ( String[] args )
    {
        try
        {
            (new ETest()).connect("COM4");
        }
        catch ( Exception e )
        {
        	System.out.println("Kan ikke åbne porten");
        	
        	//System.exit(-1);
        }
    }
}*/