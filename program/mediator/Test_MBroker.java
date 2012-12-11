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
		
		EItemType etype = new EItemType("Solsikker","123456789012",145.50);
		EItem item = new EItem(12345,etype); //HEJ HENNING - EITEM CONSTRUCTOR ER ÆNDRET
									  //DA EFACADE NU HOLDER EN LISTE AF EITEMTYPES
									  //KOMMER TYPEN MED SOM EN CONSTRUCTOR PARAMETER
		item.setOID(-1);
		item.setOwnerOID(240);
		
		IAEntityMapper mapper = broker.getEntityMapper();
		mapper.setEntity( ((Object)item).getClass().getCanonicalName() ); // eller blot "EItem"
		mapper.setSchema("CSS5");
		mapper.setTable("items");
		mapper.setRelation("OID","OID","int",mapper.EM_NOT_NULL+mapper.EM_AUTO_INCREMENT,mapper.EM_PRIMARY_KEY);
		mapper.setRelation("stockPosition","stock_position","int","",mapper.EM_NO_KEY);
		mapper.setRelation("ownerOID","owner_id","String","",mapper.EM_NO_KEY);
		//mapper.setRelation("halleluja","nudetjul","String","",mapper.EM_NO_KEY);
		//... flere relations her
		
		if(mapper.registerMap())System.out.println("Map Registered");
		
		if(broker.updateEntity(item)) System.out.println("created test ok");
		item.setOwnerOID(250);
		if(broker.updateEntity(item)) System.out.println("updated test ok");
		item.setOwnerOID(260);
		if(broker.updateEntity(item)) System.out.println("updated test ok");
		System.out.println("Test a MBroker færdig med "+errors+" fejl.");
	}
}
