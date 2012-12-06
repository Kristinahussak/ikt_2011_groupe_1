package entity;
import acquaintance.*;

import java.util.TreeSet;

/**
 * EFacade.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EFacade
{
    private TreeSet<EComponent> orders;
    private static EFacade instance = null;

    public EFacade() {
        this.orders = new TreeSet<EComponent>();
        
        orders.add(new EComponent()));
    }
    
    public static EFacade getInstance()
    {
    	if(instance == null){instance = new EFacade();}
    	return instance;
    }

    public TreeSet<EComponent> viewOrders(int orderState)
    {     	
        return orders;
    }
    
    public boolean processOrder(int orderNo)
    {
        return false;        
    }
    
    public boolean createOrder(String packetInfo)
    {
        return false;
    }
    
    public boolean lookUpStockPosition(int state)
    {
        return false;
    }
    
    public String scanItem()
    {
        return null;
    }
    
    private void verifyItemType(String itemType)
    {        
        
    }  
}
