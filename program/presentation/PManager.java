package presentation;
import control.*;

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
    
    public void viewOrders(int orderState)
    {
    	controlInterface.viewOrders(orderState).toString();   	        
    }
    
    public void processOrder(int orderNo)
    {
        
    }
    
    public void storeItem()
    {
        
    }
    
}
