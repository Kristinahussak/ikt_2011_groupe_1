package entity;

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
    private TreeSet items;

    public EComposite() {}   
    
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

    
}