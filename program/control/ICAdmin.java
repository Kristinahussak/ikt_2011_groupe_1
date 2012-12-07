package control;
import entity.*;
import mediator.*;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * ICAdmin.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public interface ICAdmin
{    
     public ArrayList<String> viewOrders(int orderState);         
}

