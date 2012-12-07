package control;
import entity.*;
import mediator.*;

import java.util.ArrayList;
import java.util.TreeSet;


/**
 * ICManager.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public interface ICManager
{  
    public ArrayList<String> viewOrders(int orderState);   
    
    public boolean processOrder(int orderNo);
    
    public boolean storeItem();
    
}

