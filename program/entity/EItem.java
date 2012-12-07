package entity;
import acquaintance.*;
import java.util.TreeSet;

/**
 * EItem.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class EItem extends EComponent
{   
    private int stockPosition = -1;  
    private int barcode = -1;
  
    private EItemType type;

    public EItem(int stockPosition) {
        this.stockPosition = stockPosition;  
        state = IAComponent.ITEM_AVAILABLE;
    }
  
    
    @Override
    public int[] getPositions()
    {
        return null;        
    }
    
    
    //  public boolean updated()
    // Bruger EComponents metode. Når tilstand ændres OID state mm sættes needUpdate = true;
    
    public EItemType getItemType(){return type;}
    public boolean setItemType(EItemType type){this.type=type;this.barcode = type.getBarcode();return true;}
    
    public boolean retrieveItem()
    {
        return false;        
    }
    
    public String scanItem()
    {
        return null;
    }
}
