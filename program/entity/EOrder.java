package entity;

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
    private TreeSet items;

    public EOrder() {}
    
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
    
    public boolean processOrder()
    {
        return false;
    }
    
    private void deleteItem(int itemNo)
    {
        
    }
    
    
}
