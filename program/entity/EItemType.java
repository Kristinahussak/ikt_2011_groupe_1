package entity;

import javax.swing.ImageIcon;

/**
 * EItemType.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EItemType
{   
    private String name;
    private int barcode;
    //private ImageIcon picutre;
    private double price;

    public EItemType(String name, int barcode, ImageIcon picutre, double price) {
        this.name = name;
        this.barcode = barcode;
        //this.picutre = picutre;
        this.price = price;
    }
    
    
    
    
    
}
