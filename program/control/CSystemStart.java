package control;
import mediator.*;

import acquaintance.*;
import entity.*;

public class CSystemStart
{
	private IAMBroker broker = MBroker.getMBrokerInstance();
	public void systemStart()
	{
		//Set broker in entity
		System.out.println(" - Setting MBroker for Entity-package..");	
		EFacade.getInstance().setComponentBroker(broker.getBroker());		
		
		//Start TCP server for incoming orderhandling
		System.out.println(" - Initializing TCP Server for orderhandling..");	
		CDSSHandler orderHandler = new CDSSHandler();		
		orderHandler.start();
		
		//Initialise all entities from existing database
	}
}
