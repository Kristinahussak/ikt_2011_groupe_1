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
    private ArrayList<EItemType> itemTypes;    
    private static EFacade instance = null;

    public EFacade() {
        this.orders = new ArrayList<EOrder>();  
        this.itemTypes = new ArrayList<EItemType>();  
        
        EItemType itemtype1 = new EItemType("HAGALUND Sovesofa 2 personer", "123456789999",2799);
        EItemType itemtype2 = new EItemType("DAGSTORP Sove 3 antracit", "223456789999",5599);
        EItemType itemtype3 = new EItemType("TIDAFORS Sovesofa mellembrun", "323456789999",5999);        
        itemTypes.add(itemtype1);
        itemTypes.add(itemtype2);
        itemTypes.add(itemtype3);
        
        EOrder order1 = new EOrder("IKEA Göteborg");
        EOrder order2 = new EOrder("IKEA Odense");
        EOrder order3 = new EOrder("IKEA Aarhus");   
        
        EItem item1 = new EItem(1,itemTypes.get(0));
        EItem item2 = new EItem(2,itemTypes.get(0));              
        EItem item3 = new EItem(3,itemTypes.get(1));
        EItem item4 = new EItem(4,itemTypes.get(1));
        EItem item5 = new EItem(5,itemTypes.get(1));
        EItem item6 = new EItem(6,itemTypes.get(1));   
        EItem item7 = new EItem(7,itemTypes.get(2));  
        
        order1.add(item1);
        order1.add(item2);
        order1.add(item7);
        
        order2.add(item3);
        order2.add(item4);
        order2.add(item5);
        
        order3.add(item6);
        
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        
    }
    
    public static EFacade getInstance()
    {
    	if(instance == null){instance = new EFacade();}
    	return instance;
    }

	public ArrayList<ArrayList<String>> viewOrders(int orderState)
	{		
		//FIND THE SPECIFID ORDERLIST VIA param: ORDERSTATE
		ArrayList<EOrder> orderList = new ArrayList<EOrder>();
		for (int x = 0; x < orders.size(); x++)
		{
			if (orders.get(x).getState() == orderState)
			{
				orderList.add(orders.get(x));				
			}
		}
		
		//Return all strings for orders and items
		ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();		
		for (int i = 0; i < orderList.size(); i++)
		{			
			IAComponent currentOrder = orderList.get(i);
			info.add(currentOrder.entityToString());						
		}
		return info;
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
