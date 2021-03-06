package entity;

import javax.swing.ImageIcon;

import acquaintance.IAEntity;

/**
 * EItemType.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EItemType implements IAEntity
{   
    private int OID = -1;
	private String name;
    private String barcode;
    private double price;

    public EItemType(String name, String barcode, double price) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
    }

    public int getOID(){return OID;}
    
    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

	@Override
	public void setOID(int OID) {
		this.OID = OID;		
	}
    
    
}
