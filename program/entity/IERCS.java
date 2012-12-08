package entity;

/**
 * IERCS.java
 * @author Nicholaj P. Rasmussen
 * Created on 03-12-2012
 */

public interface IERCS
{    
    public boolean retrieveItem(int stockPosition);
    
    public String scanItem();
	public boolean storeItem(int stockPosition);
    
}

