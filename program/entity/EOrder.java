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
    public int[] getPositions()
    {
        return null;        
    }    

    @Override
	public ArrayList<String> entityToString() {	
    	ArrayList<String> info = new ArrayList<String>();    
    	
    	String stringState = "";
    	
    	if(this.getState() == IAComponent.ORDER_OPEN){stringState = "Open";}
    	else if(this.getState() == IAComponent.ORDER_CLOSED){stringState = "Closed";}
    	
    	info.add(this.getOID()+";"+this.storeInfo+";"+this.receivedDate+";"+this.shippingDate+";"+stringState);    	
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
    
    @Override
    public boolean update()
    {
    	return broker.updateEntity(this);      	
    }
    
}
