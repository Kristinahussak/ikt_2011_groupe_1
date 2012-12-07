package entity;
import acquaintance.*;

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
   

    public EOrder() {
    	this.state = IAComponent.ORDER_OPEN;
    }
    
    @Override
    public boolean add(IAComponent item)
    {
        return super.add(item);
    }
    
    @Override
    public IAComponent remove(int barcode)
    {
        return super.remove(barcode);
    }
    
    @Override
    public int[] getPositions()
    {
        return null;        
    }
    
    
    // public boolean update() implementeret på EComponent
    
    public boolean processOrder()
    {
        return false;
    }
    
    private void deleteItem(int itemNo)
    {
        
    }
}
