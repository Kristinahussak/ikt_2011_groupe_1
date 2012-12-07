package entity;
import acquaintance.*;

import java.util.ArrayList;
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
    private ArrayList<EOrder> orders;
    private static EFacade instance = null;

    public EFacade() {
        this.orders = new ArrayList<EOrder>();  
    }
    
    public static EFacade getInstance()
    {
    	if(instance == null){instance = new EFacade();}
    	return instance;
    }

	public ArrayList<EOrder> viewOrders(int orderState)
	{
		ArrayList<EOrder> orderList = new ArrayList<EOrder>();

		for (int x = 0; x < orders.size(); x++)
		{
			if (orders.get(x).getState() == orderState)
			{
				orderList.add(orders.get(x));
			}
		}
		return orderList;
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
