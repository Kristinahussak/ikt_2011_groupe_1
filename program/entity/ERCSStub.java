package entity;

import java.util.ArrayList;
import java.util.Random;

public class ERCSStub {

	//message format retrieveItem:stockPosition
    public String message(String message) {
    	String result = "true";
    	String parametre = "";

    	String[] parts;
		parts = message.split(":");
		String method = parts[0];
		try{
			parametre = parts[1];
		}catch (Exception e){

		}

		if(method.equals("retrieveItem"))
			result = retrieveItem(parametre);
		if(method.equals("scanItem"))
			result = scanItem();
		if(method.equals("storeItem"))
			result = storeItem(parametre);

    	return result;

    }
    public String retrieveItem(String message) {
    	String result = "true";
    	System.out.println("retrieveItem " + message);
    	return result;
    }

    public String storeItem(String message) {
    	String result = "true";
    	System.out.println("storeItem " + message);
    	return result;
    }

    public String scanItem() {

    	ArrayList<String> itemTypes = new ArrayList<String>();

    	itemTypes.add("123456789999");
    	itemTypes.add("223456789999");
    	itemTypes.add("323456789999");

    	String barcode;

    	Random randomGenerator = new Random();

    	int number = randomGenerator.nextInt(3);

     	barcode = itemTypes.get(number);
     	System.out.println("random: " + new Integer(number).toString());
    	System.out.println("scanItem " + barcode);
    	number = 0;
    	return barcode;
    }

}
