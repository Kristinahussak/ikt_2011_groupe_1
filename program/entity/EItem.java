package entity;
import acquaintance.*;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * EItem.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EItem extends EComponent
{   
	private static IERCS rcsInterface = new ERCSAdapter();
    private int stockPosition = -1;  
    private EItemType type;

    public EItem(int stockPosition, EItemType type)
    {
        this.stockPosition = stockPosition; 
        this.type = type;
        this.setState(IAComponent.ITEM_AVAILABLE);
    }
      
    @Override
    public int[] getPositions()
    {
    	int[] position = {this.stockPosition};
        return position;        
    }    
    
    // public boolean updated()
    // Bruger EComponents metode. N�r tilstand �ndres OID state mm s�ttes needUpdate = true;
    
    public EItemType getItemType(){return type;}
        
    public static boolean retrieveItem(int stockPosition)
    {
    	rcsInterface.retrieveItem(stockPosition);
        return false;        
    }
    
    public static String scanItem()
    {  
        return rcsInterface.scanItem();
    }
    
    public static boolean storeItem(int stockPosition)
    {  
    	boolean success = false;
    	success = rcsInterface.storeItem(stockPosition);
    	
    	//UPDATE IN DATABASE HERE
    	//BY CALLING UPDATEITEM()
    	
        return success;
    }
    
    @Override
	public ArrayList<IAComponent> getItems() {
    	ArrayList<IAComponent> temp = new ArrayList<IAComponent>();    	
    	temp.add(this);
		return temp;
	}
    
    @Override
	public ArrayList<String> entityToString() {	
    	ArrayList<String> info = new ArrayList<String>();   
    	
    	info.add(getItemType().getName()+";"+
    		     this.stockPosition+";"+
    			 getItemType().getBarcode()+";"+
    			 getItemType().getPrice());
    	
		return info;
	}
    
    
}
