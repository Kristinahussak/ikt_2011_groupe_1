package presentation;
import java.util.ArrayList;

import control.*;
import entity.EComponent;

/**
 * PManager.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class PManager
{
	private ICManager controlInterface;

    public PManager() {}
    
    public ArrayList<EOrder> viewOrders(int orderState)
    {
    	return controlInterface.viewOrders(orderState);   	        
    }
    
    public boolean processOrder(int orderNo)
    {
    	return controlInterface.processOrder(orderNo);  	        
    }
    
    public void storeItem()
    {
        
    }
    
}
