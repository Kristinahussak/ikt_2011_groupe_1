package entity;

/**
 * ERCSAdapter.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class ERCSAdapter implements IERCS
{

    public ERCSAdapter() {}    

    @Override
    public boolean retrieveItem(int stockPosition) {
        System.out.println("Item has been retrieved from stockposition: "+stockPosition);
        return true;
    }

    @Override
    public String scanItem() {
    	//IMPLEMENT SCANITEM TO BRADLEY ALLEN
    	System.out.println("Item has been scanned (Constant barcode atm.)");
        return "323456789999";
    }

	@Override
	public boolean storeItem(int stockPosition) {
		System.out.println("Item has been stored on stockposition: " +stockPosition);
		return true;
	}
    
}
