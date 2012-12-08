package mediator;
import acquaintance.*;
import entity.*; // Af testhensyn

import foundation.*;

public class Test_MBroker {

	
	public static void main(String[] args){
		
		int errors = 0;

		MBroker broker = MBroker.getMBrokerInstance();
		ADBInfo info = new ADBInfo();
		boolean test = broker.setDB(info);
		
		EItem item = new EItem(3042); //HEJ HENNING - EITEM CONSTRUCTOR ER ÆNDRET
									  //DA EFACADE NU HOLDER EN LISTE AF EITEMTYPES
									  //KOMMER TYPEN MED SOM EN CONSTRUCTOR PARAMETER
		item.setOID(32117);
		item.setOwnerOID(240);
		
		IAEntityMapper mapper = broker.getEntityMapper();
		mapper.setEntity( ((Object)item).getClass().getCanonicalName() ); // eller blot "EItem"
		mapper.setSchema("CSS7");
		mapper.setTable("items");
		mapper.setRelation("OID","item_id","int",mapper.EM_NOT_NULL+mapper.EM_AUTO_INCREMENT,mapper.EM_PRIMARY_KEY);
		mapper.setRelation("stockPosition","stock_position","int","",mapper.EM_NO_KEY);
		//mapper.setRelation("halleluja","nudetjul","String","",mapper.EM_NO_KEY);
		//... flere relations her
		
		if(mapper.registerMap())System.out.println("Map Registered");
		
		if(broker.updateEntity(item)) System.out.println("updated test ok");
		System.out.println("Test a MBroker færdig med "+errors+" fejl.");
	}
}
