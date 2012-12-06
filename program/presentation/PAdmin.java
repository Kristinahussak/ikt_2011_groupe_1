package presentation;
import control.*;

/**
 * PAdmin.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class PAdmin
{
	private ICManager controlInterface;

    public PAdmin() {}
    
    public void viewOrders(int orderState)
    {
    	controlInterface.viewOrders(orderState).toString();        
    }    
    
}
