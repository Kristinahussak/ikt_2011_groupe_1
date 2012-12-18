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
	private TreeSet<Integer> freePositions = new TreeSet<Integer>();
	private int stockSize = 10000;

    public EStock()
    {
    	for (int i = 0; i < stockSize; i++) 
    	{
    		freePositions.add(i);   
		}
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
    
    public void removeStockPosition(int object)
    {
    	freePositions.remove(object);
    }
    
    @Override
	public ArrayList<String> entityToString() {
		ArrayList<String> info = new ArrayList<String>();
		info.add("I am stock.");    	
    	return info;
    
	}
    
}
