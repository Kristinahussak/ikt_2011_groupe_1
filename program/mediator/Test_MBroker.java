package mediator;
import acquaintance.*;
import foundation.*;

public class Test_MBroker {

 
	public static void main(String[] args){
		
		MBroker broker = MBroker.getMBrokerInstance();
		ADBInfo info = new ADBInfo();
		
		boolean test = broker.setDB(info);
	}
}
