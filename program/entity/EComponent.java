package entity;

import acquaintance.*;

/**
 * EComponent.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EComponent implements IAComponent
{   
    private int OID;
    private int state;
    private boolean needUpdate = false;
    
    public boolean add(IAComponent item)
    {
        return false;
    }
    
    public IAComponent remove(int barcode)
    {
        return null;
    }
    
    public int[] getPositions()
    {
        return null;        
    }
    
    public boolean isUpdated()
    {
        return needUpdate;
    }

    public int getOID() {
        return OID;
    }

    public int getState() {
        return state;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public void setState(int state) {
        this.state = state;
    }
}
