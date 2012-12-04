package entity;

import acquaintance.*;

/**
 * EComponent.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EComponent
{   
    private int OID;
    private int state;
    
    public boolean add(EComponent item)
    {
        return false;
    }
    
    public EComponent remove(String itemType)
    {
        return null;
    }
    
    public int[] getPositions()
    {
        return null;        
    }
    
    public boolean update()
    {
        return false;
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
