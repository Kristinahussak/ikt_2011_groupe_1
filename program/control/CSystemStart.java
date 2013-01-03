package control;
import java.sql.ResultSet;
import java.sql.SQLException;

import mediator.*;

import acquaintance.*;
import entity.*;

public class CSystemStart
{
	private MBroker broker = MBroker.getMBrokerInstance();
	public void systemStart()
	{		
		//Set databaseinformation
		System.out.println(" - Setting up database..");	
		ADBInfo dbinfo = new ADBInfo();
		dbinfo.setUser("root");
		dbinfo.setPassword("root");		
		broker.setDB(dbinfo);
		
		
		//Set broker in entity
	    System.out.println(" - Setting MBroker for Entity-package..");	
	    EFacade.getInstance().setComponentBroker(broker.getBroker());	
		
		CGenerateMappers generateMappers = new CGenerateMappers(broker);
		System.out.println("     - Mapping ItemTypes table.");
		generateMappers.mapItemType();
		System.out.println("     - Mapping Items table.");
		generateMappers.mapItem();
		System.out.println("     - Mapping Orders table.");
		generateMappers.mapOrder();
		
		System.out.println(" - Loading previously exsiting entities..");	
		broker.putEntity(new EItemType("HAGALUND Sovesofa 2 personer", "123456789999", 2799));
		broker.putEntity(new EItemType("DAGSTORP Sove 3 antracit", "223456789999", 5599));
		broker.putEntity(new EItemType("TIDAFORS Sovesofa mellembrun", "323456789999", 5999));		
		
		
		try {
			System.out.println("     - Loading Itemtypes from database.");
            ResultSet itemTypes = broker.queryTable(EFacade.getInstance().getItemTypeCanonical());
            while (itemTypes.next()) {  
            	
            	int OID = itemTypes.getInt("OID");
                String name = itemTypes.getString("ItemtypeName");
                String barcode = itemTypes.getString("Barcode");
                double price = itemTypes.getDouble("Price");                 
                EFacade.getInstance().addItemType(OID, name, barcode, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		try {
			System.out.println("     - Loading orders from database.");
			ResultSet orders = broker.queryTable(EFacade.getInstance().getOrderCanonical());
            while (orders.next()) {  
            	int OID = orders.getInt("OID");
                String store = orders.getString("Store");
                String receivalDate = orders.getString("ReceivalDate");
                String shippingDate = orders.getString("ShippingDate"); 
              
                EFacade.getInstance().addOrder(OID, store, receivalDate, shippingDate);
            }
        } catch (SQLException e) {            
            e.printStackTrace();
        }
		
		for (int i = 0; i < 100; i++)
		{
			for (int j = 0; j < EFacade.getInstance().getItemTypes().size(); j++) 
			{
				broker.putEntity(new EItem(EFacade.getInstance().getStock().getFirstFreePosition(),
					       EFacade.getInstance().getItemTypes().get(j)));				
			}			
		}
		
		try {
			System.out.println("     - Loading items from database.");
			ResultSet items = broker.queryTable(EFacade.getInstance().getItemCanonical());
            while (items.next()) {  
            	int OID = items.getInt("OID");
            	int stockPosition = items.getInt("StockPosition");
            	int itemTypeID = items.getInt("ItemType");
            	int orderID = items.getInt("OrderID");
              
            	
                EFacade.getInstance().addItem(OID, stockPosition, itemTypeID, orderID);
            }
        } catch (SQLException e) {            
            e.printStackTrace();
        }

		
		//Start TCP server for incoming orderhandling
		System.out.println(" - Initializing TCP Server for orderhandling..");	
		CDSSHandler orderHandler = new CDSSHandler();		
		orderHandler.start();
		
		CObservable.getInstance().notifySubcribers();
		
	}
}
