package entity;
import acquaintance.*;

import java.util.ArrayList;
import java.util.TreeSet;

import presentation.PManager;

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
    private EStock stock = new EStock();

    public EFacade() {
        this.orders = new ArrayList<EOrder>();  
        this.itemTypes = new ArrayList<EItemType>();  
        
        EItemType itemtype1 = new EItemType("HAGALUND Sovesofa 2 personer", "123456789999",2799);
        EItemType itemtype2 = new EItemType("DAGSTORP Sove 3 antracit", "223456789999",5599);
        EItemType itemtype3 = new EItemType("TIDAFORS Sovesofa mellembrun", "323456789999",5999);        
        itemTypes.add(itemtype1);
        itemTypes.add(itemtype2);
        itemTypes.add(itemtype3);
        
        EOrder order1 = new EOrder("IKEA Göteborg", "2012-12-12");
        EOrder order2 = new EOrder("IKEA Odense", "2012-12-12");
        EOrder order3 = new EOrder("IKEA Aarhus", "2012-12-12");   
        
        EItem item1 = new EItem(1,itemTypes.get(0));
        EItem item2 = new EItem(2,itemTypes.get(0));              
        EItem item3 = new EItem(3,itemTypes.get(1));
        EItem item4 = new EItem(4,itemTypes.get(1));
        EItem item5 = new EItem(5,itemTypes.get(1));
        EItem item6 = new EItem(6,itemTypes.get(1));   
        EItem item7 = new EItem(7,itemTypes.get(2));
        //item på stock
        EItem item8 = new EItem(8,itemTypes.get(0));
        EItem item9 = new EItem(9,itemTypes.get(0)); 
        EItem item10 = new EItem(10,itemTypes.get(1)); 
        EItem item11 = new EItem(11,itemTypes.get(1)); 
        EItem item12 = new EItem(12,itemTypes.get(1));
        EItem item13 = new EItem(13,itemTypes.get(2));
        
        stock.add(item8);
        stock.add(item9);
        stock.add(item10);
        stock.add(item11);
        stock.add(item12);
        stock.add(item13);
        
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
    	boolean orderFound = false;
    	IAComponent currentOrder = null;
    	
    	//Get the specific order
    	while(!orderFound)
    	{
    		for (int j = 0; j < orders.size(); j++)
        	{
    			if(orders.get(j).getOID() == orderNo)
    			{
    				currentOrder = orders.get(j);
    				orderFound = true;
    			}
    		}
    	}
    	
    	//Iterate over all items
    	ArrayList<IAComponent> itemList = currentOrder.getItems();
		for (int i = 0; i < itemList.size(); i++)
		{
			IAComponent currentItem = itemList.get(i);			
			if(EItem.retrieveItem(currentItem.getPositions()[0]))
			{
				itemList.remove(currentItem);
				
				//CALL AN UPDATE ORDER HERE
			}
			
						
		}
    	
    	return false;    	
    }
    //Creates a Order from at requestOrder
	public synchronized boolean createOrder(String packetInfo) {
		String[] tempPacketInfo;
		tempPacketInfo = packetInfo.split(";");
		if (tempPacketInfo[0].equals("01")) {
			EOrder tempOrder = new EOrder(tempPacketInfo[1], tempPacketInfo[3]);
			for (int k = 4; k < tempPacketInfo.length; k = k + 2) {
				int currentNumberOfItems = Integer
						.parseInt(tempPacketInfo[k + 1]);
				for (int j = 0; j < currentNumberOfItems; j++) {
					tempOrder.add(stock.remove(tempPacketInfo[k]));
				}
			}
			orders.add(tempOrder);
			return true;
		} else {
			return false;
		}

	}

    public boolean storeItem()
    {    
    	String scannedBarcode = EItem.scanItem();    	
    	int itemtypeIndex = verifyItemType(scannedBarcode);
    	if(itemtypeIndex < 0){return false;}
    	
    	else
    	{
    		int freePosition = stock.getFirstFreePosition();
    		EItemType itemType = itemTypes.get(itemtypeIndex);    		   		
    		stock.add(new EItem(freePosition,itemType));
    		
    		EItem.storeItem(freePosition);
    		
    		return true;
    	}   
    }
    
    private int verifyItemType(String barcode)
    {    
    	boolean typeExists = false;
    	
    	int x = 0;    	
    	while(!typeExists && x < itemTypes.size())
    	{    	
    		if(itemTypes.get(x).getBarcode() == barcode){
    			typeExists = true;
    		}    	
    		x++;
    	}
    	
    	if(!typeExists){System.out.println();x = -1;}
    	
    	return x-1;        
    }  

}
