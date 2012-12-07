package mediator;
import acquaintance.*;
import entity.*; // Af testhensyn

import foundation.*;

public class Test_MBroker {

	EItem item = new EItem();
	
	public static void main(String[] args){
		
		int errors = 0;
		MBroker broker = MBroker.getMBrokerInstance();
		ADBInfo info = new ADBInfo();
		
		boolean test = broker.setDB(info);
		System.out.println("Test a MBroker færdig med "+errors+" fejl.");
	}
}
