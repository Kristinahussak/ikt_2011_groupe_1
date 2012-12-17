package entity;
import acquaintance.*;

import java.util.ArrayList;
//import entity.EItem;
import java.util.TreeSet;

/**
 * EComposite.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EComposite extends EComponent
{	
    public EComposite() {}
    
    public boolean add(IAComponent item)
    {
    	item.setOwnerOID(getOID());    	
        return getItems().add(item);
    }
    
    public IAComponent remove(String barcode)
    {
    	EItem currentItem = null;
    	boolean itemFound = false;

    	for(int i = 0;0<getItems().size() && !itemFound;i++)
    	{
    		
    		currentItem = (EItem) getItems().get(i); 

    		if(barcode.equals(currentItem.getBarcode()))
    		{
    			getItems().remove(i);
    			itemFound = true;
    		}
    	}
        return currentItem;
    }
}
