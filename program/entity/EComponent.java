package entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import acquaintance.*;

/**
 * EComponent.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EComponent implements IAComponent
{   
    private int OID = -1; // = Ikke sat
    protected int ownerOID = -1;
    private int state = IAComponent.NOT_INITIALIZED;
    private boolean updated = false;
    private ArrayList<IAComponent> items = new ArrayList<IAComponent>();  
    protected IAMBroker broker;
    
    public boolean add(IAComponent item)
    {
    	item.setOwnerOID(getOID());    	
        return this.items.add(item);
    }
    
    public IAComponent remove(String barcode)
    {
    	EItem currentItem = null;
    	boolean itemFound = false;

    	for(int i = 0;0<items.size() && !itemFound;i++)
    	{
    		
    		currentItem = (EItem) items.get(i); 

    		if(barcode.equals(currentItem.getBarcode()))
    		{
    			items.remove(i);
    			itemFound = true;
    		}
    	}
        return currentItem;
    }
    
    public int[] getPositions()
    {
        return null;        
    }
    
    public boolean isUpdated()
    {
        return updated;
    }

    public int getOID() {
        return OID;
    }
 
    public int getOwnerOID() {
        return ownerOID;
    }

    public int getState() {
        return state;
    }

    public void setOID(int OID) {
        this.OID = OID;
        updated = false;
    }

    public void setOwnerOID(int OID) {
        this.ownerOID = OID;
        updated = false;
    }

    public void setState(int state) {
        this.state = state;
        updated = false;
    }
    public void setBroker(IAMBroker broker)
    {
    	this.broker = broker;
    }

	@Override
	public ArrayList<String> entityToString() {		
		return null;
	}
	
	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// getcurrent date time with Date()
		java.util.Date date = new java.util.Date();
		
		return dateFormat.format(date);
	}

	@Override
	public ArrayList<IAComponent> getItems() {
		return items;
	}

	@Override
	public int getFirstFreePosition() {
		return -1;
	}

	@Override
	public boolean update()
    {
		return broker.putEntity(this);    	
    }

}
