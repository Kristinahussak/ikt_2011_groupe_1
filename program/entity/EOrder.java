package entity;
import acquaintance.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * EOrder.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EOrder extends EComposite
{    
    private ArrayList<IAComponent> items = new ArrayList<IAComponent>();  
    private String storeInfo;
    private String shippingDate = "Not shipped yet";
    private String receivedDate;  
    

    public EOrder(String storeInfo, String shippingDate) {
    	this.setState(IAComponent.ORDER_OPEN);
    	this.receivedDate = this.getDate();  
    	this.storeInfo = storeInfo;
    	this.shippingDate = shippingDate;
    	
    }
    
    @Override
    public boolean add(IAComponent item)
    {
    	this.items.add(item);
        return true;
    }
    
    @Override
    public IAComponent remove(String barcode)
    {
    	
    	
        return null;
    }
    
    @Override
    public int[] getPositions()
    {
        return null;        
    }
    
    
    // public boolean update() implementeret på EComponent
    
    public boolean processOrder()
    {
        return false;
    }
    
    private void deleteItem(int itemNo)
    {
        
    }
    
    @Override
	public ArrayList<String> entityToString() {	
    	ArrayList<String> info = new ArrayList<String>();    
    	
    	info.add(this.getOID()+";"+this.storeInfo+";"+this.receivedDate+";"+this.shippingDate);    	
    	for (int i = 0; i < this.getItems().size(); i++) 
    	{
    		info.add(items.get(i).entityToString().get(0));    		    					
		}
		return info;
	}
    
    @Override
	public ArrayList<IAComponent> getItems() {
		return this.items;
	}
    
    
}
