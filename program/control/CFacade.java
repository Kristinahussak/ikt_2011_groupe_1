package control;
import entity.*;
import mediator.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * CFacade.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class CFacade implements ICManager, ICAdmin
{
    public CFacade() {}    

    @Override
     public ArrayList<ArrayList<String>> viewOrders(int orderState) {    	
        return EFacade.getInstance().viewOrders(orderState);
    }
    
    @Override
    public boolean processOrder(int orderNo) {
    	
    	if(EFacade.getInstance().processOrder(orderNo)){
    		CObservable.getInstance().notifySubcribers();
    	}        
        return EFacade.getInstance().processOrder(orderNo);
    }

    @Override
    public boolean storeItem() {
    	
    	if(EFacade.getInstance().storeItem()){
    		CObservable.getInstance().notifySubcribers();
    	} 
        return EFacade.getInstance().storeItem();
    }

	@Override
	public boolean addSubscriber(Observer o) {		
		return CObservable.getInstance().addSubscriber(o);
	}

}
