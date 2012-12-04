package control;
import entity.*;
import mediator.*;

import entity.EItem;
import entity.EOrder;
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
     public TreeSet<EComponent> viewOrders(int orderState) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean processOrder(int orderNo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean storeItem() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
