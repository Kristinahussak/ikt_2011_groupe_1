package entity;

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
    private int stockPosition;
    private EItemType type;

    public EItem(int stockPosition, String itemType) {
        this.stockPosition = stockPosition;        
    }
    
    @Override
    public boolean add(EComponent item)
    {
        return false;
    }
    
    @Override
    public EComponent remove(String itemType)
    {
        return null;
    }
    
    @Override
    public int[] getPositions()
    {
        return null;        
    }
    
    @Override
    public boolean update()
    {
        return false;
    }
    
    public boolean retrieveItem()
    {
        return false;        
    }
    
    public String scanItem()
    {
        return null;
    }
}
