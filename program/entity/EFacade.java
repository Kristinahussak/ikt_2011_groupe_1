package entity;
import acquaintance.*;

import java.util.ArrayList;


/**
 * EFacade.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EFacade
{
    private ArrayList<IAComponent> orders;
    private ArrayList<EItemType> itemTypes;    
    private static EFacade instance = null;
    private IAComponent stock = new EStock();
    private IAComponent component = new EComponent();
    public IAComponent item1;

    public EFacade() {
        this.orders = new ArrayList<IAComponent>();  
        this.itemTypes = new ArrayList<EItemType>();  
        
        
        EItemType itemtype1 = new EItemType("HAGALUND Sovesofa 2 personer", "123456789999",2799);
        EItemType itemtype2 = new EItemType("DAGSTORP Sove 3 antracit", "223456789999",5599);
        EItemType itemtype3 = new EItemType("TIDAFORS Sovesofa mellembrun", "323456789999",5999);        
        itemTypes.add(itemtype1);
        itemTypes.add(itemtype2);
        itemTypes.add(itemtype3);
        
        IAComponent order1 = new EOrder("IKEA Göteborg", "2012-12-12");
        IAComponent order2 = new EOrder("IKEA Odense", "2012-12-12");
        IAComponent order3 = new EOrder("IKEA Aarhus", "2012-12-12");   
        
        item1 = new EItem(1,itemTypes.get(0));
        IAComponent item2 = new EItem(2,itemTypes.get(0));              
        IAComponent item3 = new EItem(3,itemTypes.get(1));
        IAComponent item4 = new EItem(4,itemTypes.get(1));
        IAComponent item5 = new EItem(5,itemTypes.get(1));
        IAComponent item6 = new EItem(6,itemTypes.get(1));   
        IAComponent item7 = new EItem(7,itemTypes.get(2));
        //item på stock
        IAComponent item8 = new EItem(8,itemTypes.get(0));
        IAComponent item9 = new EItem(9,itemTypes.get(0)); 
        IAComponent item10 = new EItem(10,itemTypes.get(1)); 
        IAComponent item11 = new EItem(11,itemTypes.get(1)); 
        IAComponent item12 = new EItem(12,itemTypes.get(1));
        IAComponent item13 = new EItem(13,itemTypes.get(2));
        
        
        
        order1.setState(IAComponent.ORDER_CLOSED);
        
        stock.add(item8);
        stock.add(item9);
        stock.add(item10);
        stock.add(item11);
        stock.add(item12);
        stock.add(item13);
        
        order1.add(item1);
        order1.add(item2);
        order1.add(item7);
        order1.add(item1);
        order1.add(item2);
        order1.add(item7);
        order1.add(item1);
        order1.add(item2);
        order1.add(item7);
        order1.add(item1);
        order1.add(item2);
        order1.add(item7);
        
        order2.add(item3);
        order2.add(item4);
        order2.add(item5);
        order2.add(item3);
        order2.add(item4);
        order2.add(item5);
        order2.add(item3);
        order2.add(item4);
        order2.add(item5);        
        
        order3.add(item6);
        order3.add(item6);
        order3.add(item6);
        order3.add(item6);
        order3.add(item6);
        order3.add(item6);
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
    
    public void setComponentBroker(IAMBroker broker)
    {
    	component.setBroker(broker);
    }

	public ArrayList<ArrayList<String>> viewOrders(int orderState)
	{		
		//FIND THE SPECIFID ORDERLIST VIA param: ORDERSTATE
		ArrayList<IAComponent> orderList = new ArrayList<IAComponent>();
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
				currentOrder.update();
			}		
		}		
    	
    	return true;    	
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
    	
    	int x = -1;    	
    	while(!typeExists && x < itemTypes.size())
    	{    
    		x++;
    		if(itemTypes.get(x).getBarcode() == barcode){
    			typeExists = true;
    		}    	
    		
    	}
    	
    	if(!typeExists){x = -1;}
    	
    	return x;        
    }  
    
    public String getItemTypeCanonical(){ 
    	EItemType temp = new EItemType("","",0);
    	return temp.getClass().getCanonicalName();
    }
    
    public String getOrderCanonical(){    	
    	EOrder temp = new EOrder("","");
    	return temp.getClass().getCanonicalName();
    }
    
    public String getItemCanonical(){        	
    	EItem temp = new EItem(0,new EItemType("","",0));
    	return temp.getClass().getCanonicalName();
    }

}
