package control;
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
		
		
		
		//Set broker in entity
		System.out.println(" - Setting MBroker for Entity-package..");	
		EFacade.getInstance().setComponentBroker(broker.getBroker());		
		
		//Start TCP server for incoming orderhandling
//		System.out.println(" - Initializing TCP Server for orderhandling..");	
//		CDSSHandler orderHandler = new CDSSHandler();		
//		orderHandler.start();
		
	}
}
