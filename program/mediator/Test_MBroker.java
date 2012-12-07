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
		
		EItem item = new EItem(3042);
		item.setOID(32117);
		item.setOwnerOID(240);
		
		IAEntityMapper mapper = broker.getEntityMapper();
		mapper.setEntity( ((Object)item).getClass().getCanonicalName() ); // eller blot "EItem"
		mapper.setSchema("CSS4");
		mapper.setTable("items");
		mapper.setRelation("OID","item_id","int",mapper.EM_PRIMARY_KEY);
		//... flere relations her
		
		if(mapper.registerMap())System.out.println("Map Registered");
		
		
		System.out.println("Test a MBroker færdig med "+errors+" fejl.");
	}
}
