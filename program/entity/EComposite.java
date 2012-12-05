package entity;
import acquaintance.*;
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
    private TreeSet<IAComponent> items = new TreeSet<IAComponent>();

    public EComposite() {}   
    
    @Override
    public boolean add(IAComponent item)
    {
        if(!(item==null)) return items.add(item);
        return false;
    }
    
    @Override
    public IAComponent remove(int barcode)
    {
        return null; // implementeres
    }
    
    @Override
    public int[] getPositions()
    {        
        return null;        
    }

    
}
