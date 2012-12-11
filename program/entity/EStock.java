package entity;
import acquaintance.*;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * EStock.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EStock extends EComposite
{  
	private ArrayList<IAComponent> items = new ArrayList<IAComponent>();  
	private TreeSet<Integer> freePositions = new TreeSet<Integer>();
	private int stockSize = 100;

    public EStock()
    {
    	for (int i = 0; i < stockSize; i++) 
    	{
    		freePositions.add(i);   
		}
    }   
    
   
    public boolean add(IAComponent item)
    {
    	
        return this.items.add(item);
    }
    
    @Override
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
    
    @Override
    public int[] getPositions()
    {
        return super.getPositions();        
    }
    
    public int getFirstFreePosition()
    {
    	return freePositions.pollFirst();
    }
    
    @Override
	public ArrayList<String> entityToString() {
		ArrayList<String> info = new ArrayList<String>();
		info.add("I am stock.");    	
    	return info;
    
	}
    
}
