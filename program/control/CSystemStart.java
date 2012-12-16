package control;

import acquaintance.*;
import entity.*;

public class CSystemStart
{
	private IAMBroker broker;
	public void systemStart()
	{
		EFacade.getInstance().setComponentBroker(broker.getBroker());
		
		//Initialise all entities from existing database
	}
}
