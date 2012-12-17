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
		dbinfo.setPassword("123");		
		broker.setDB(dbinfo);
		
		CGenerateMappers generateMappers = new CGenerateMappers(broker);
		System.out.println("     - Mapping ItemTypes table.");
		generateMappers.mapItemType();
		System.out.println("     - Mapping Items table.");
		generateMappers.mapItem();
		System.out.println("     - Mapping Orders table.");
		generateMappers.mapOrder();
		
		System.out.println(" - Loading previously exsiting entities..");	
//		try {
//			System.out.println("     - Loading Itemtypes from database.");
//            ResultSet itemTypes = broker.queryTable(new EItemType(null, null, 0));
//            while (itemTypes.next()) {                
//
//                String name = itemTypes.getString("ItemtypeName");
//                String barcode = itemTypes.getString("Barcode");
//                double price = itemTypes.getDouble("Price");                
//                EFacade.getInstance().addItemType(name, barcode, price);
//                System.out.println("     - Itemtype");
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
		
		
		
		ResultSet orders = broker.queryTable(new EOrder(null, null));
		ResultSet items = broker.queryTable(new EItem(0, null));
		
		
		
		
		
		//Set broker in entity
		System.out.println(" - Setting MBroker for Entity-package..");	
		EFacade.getInstance().setComponentBroker(broker.getBroker());		
		
		//Start TCP server for incoming orderhandling
//		System.out.println(" - Initializing TCP Server for orderhandling..");	
//		CDSSHandler orderHandler = new CDSSHandler();		
//		orderHandler.start();
		
	}
}
