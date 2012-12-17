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
			tempOrder.update();
			for (int k = 4; k < tempPacketInfo.length; k = k + 2) {
				int currentNumberOfItems = Integer
						.parseInt(tempPacketInfo[k + 1]);				
				for (int j = 0; j < currentNumberOfItems; j++) {
					try{
						EItem temp = (EItem) stock.remove(tempPacketInfo[k]);		
						tempOrder.add(temp);	
						temp.update();
					}
					catch(IndexOutOfBoundsException e)
					{
						System.out.println("An item was out of stock: " +tempPacketInfo[k]);
					}					
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
    	System.out.println("Jeg er i EFacade");
    	String scannedBarcode = EItem.scanItem();    	
    	EItemType itemType = verifyItemType(scannedBarcode);
    	if(itemType == null){return false;}
    	
    	else
    	{
    		int freePosition = stock.getFirstFreePosition();    		  
    		EItem tempItem = new EItem(freePosition,itemType);
    		stock.add(tempItem);
    		
    		tempItem.update();    		
    		EItem.storeItem(freePosition);    		
    		return true;
    	}   
    }
    
    private EItemType verifyItemType(String barcode)
    {    
    	System.out.println("Jeg er i EFacade.verify");
    	boolean typeExists = false;
    	EItemType temp = null;

    	int x = 0;    	
    	while(!typeExists && x < itemTypes.size())
    	{   
    		if(itemTypes.get(x).getBarcode().equals(barcode)){
    			typeExists = true;
    			temp = itemTypes.get(x);
    		}   
    		x++;    		
    	}

    	System.out.println("Jeg er færdig i EFacade.verify");
    	return temp;        
    }  
    
    public void addItemType(int OID, String name, String barcode, double price)
    {
    	EItemType temp = new EItemType(name, barcode, price);
    	temp.setOID(OID);
    	this.itemTypes.add(temp);
    	
    }
    
    public void addOrder(int OID, String storeInfo, String receivalDate, String shippingDate)
    {
    	EOrder temp = new EOrder(storeInfo, shippingDate);
    	temp.setOID(OID);
    	temp.setReceivedDate(receivalDate);
    	this.orders.add(temp);    	
    }
    
    public void addItem(int OID, int stockPosition, int  itemTypeID, int orderID)
    {
    	boolean foundItemType = false;
    	int itemTypeIndex = 0;
    	for (int i = 0; i < this.itemTypes.size() && !foundItemType; i++) 
    	{
    		if(itemTypes.get(i).getOID() == itemTypeID) 	
    		{
    			foundItemType = true;
    			itemTypeIndex = i;
    		}
		}
    	
    	boolean foundOrder = false;
    	int orderIndex = 0;
    	for (int i = 0; i < this.orders.size() && !foundOrder; i++) 
    	{
    		if(orders.get(i).getOID() == orderID) 	
    		{
    			foundOrder = true;
    			orderIndex = i;
    		}
		}
    	
    	if(orderID == -1){
    		stock.add(new EItem(OID, itemTypes.get(itemTypeIndex)));
    	}
    	else{
    		orders.get(orderIndex).add(new EItem(OID, itemTypes.get(itemTypeIndex)));
    	}
    	
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
    
    public IAComponent getStock()
    {    	
		return this.stock;
    }
    
    public ArrayList<EItemType> getItemTypes()
    {
		return itemTypes;
    	
    }
    
    public void hej()
    {
    	System.out.println("Stock: " +stock.getItems().size());
    }
    
    

}
