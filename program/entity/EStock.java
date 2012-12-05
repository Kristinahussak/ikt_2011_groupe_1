package entity;
import acquaintance.*;
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
    private TreeSet<IAComponent> items = new TreeSet<IAComponent>();

    public EStock() {}   
    
   
    public boolean add(IAComponent item)
    {
        return super.add(item);// skal fjernes hvis ikke yderligere implementation
    }
    
    @Override
    public IAComponent remove(int barcode)
    {
        return super.remove(barcode); // skal fjernes hvis ikke yderligere implementation
    }
    
    @Override
    public int[] getPositions()
    {
        return super.getPositions();        
    }
    
   
    
}
