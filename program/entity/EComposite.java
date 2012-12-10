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
	private ArrayList<IAComponent> items = new ArrayList<IAComponent>();  

    public EComposite() {}   
    
    @Override
    public boolean add(IAComponent item)
    {
        if(!(item==null)) return items.add(item);
        return false;
    }
    
    @Override
    public IAComponent remove(String barcode)
    {
        return null; // implementeres
    }
    
    @Override
    public int[] getPositions()
    {        
        return null;        
    }
    
    @Override
	public ArrayList<String> entityToString() {		
		return null;
	}
    
    @Override
	public ArrayList<IAComponent> getItems() {
		return items;
	}
}
