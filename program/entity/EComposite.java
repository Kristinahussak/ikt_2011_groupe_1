package entity;
import acquaintance.*;

import java.util.ArrayList;
//import entity.EItem;
import java.util.TreeSet;

/**
 * EComposite.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EComposite extends EComponent
{
	private ArrayList<IAComponent> items = new ArrayList<IAComponent>();  
    public EComposite() {}   
}
