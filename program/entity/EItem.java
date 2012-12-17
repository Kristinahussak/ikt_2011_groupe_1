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
    protected int typeOID = -1;
    protected int orderOID = -1;

    public EItem(int stockPosition, EItemType type)
    {
        this.stockPosition = stockPosition; 
        this.type = type;
        this.typeOID = type.getOID();
        this.setState(IAComponent.ITEM_AVAILABLE);
    }
      
    protected  void setOrderOID(int orderOID){this.orderOID = orderOID;}
    
    public int getStockPosition(){return stockPosition;}
    
    @Override
    public int[] getPositions()
    {
    	int[] position = {this.stockPosition};
        return position;        
    }   
    
    public EItemType getItemType(){return type;}
    
    public String getBarcode(){return type.getBarcode();}
        
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
