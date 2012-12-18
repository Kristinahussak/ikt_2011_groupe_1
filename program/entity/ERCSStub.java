package entity;

import java.util.ArrayList;
import java.util.Random;

public class ERCSStub {

	//message format retrieveItem:stockPosition/13
    public String message(String message) {
    	String result = "true";
    	String parametre = "";

    	//removing "/13" from massage
    	String msg = message.substring(0, message.length() - 3);

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
    	String result = "retrieveItem:true/13";
    	//System.out.println("retrieveItem " + message);
    	return result;
    }

    public String storeItem(String message) {
    	String result = "storeItem:true/13";
    	//System.out.println("storeItem " + message);
    	return result;
    }

    public String scanItem() {

    	ArrayList<String> itemTypes = new ArrayList<String>();

    	itemTypes.add("123456789999");
    	itemTypes.add("223456789999");
    	itemTypes.add("323456789999");

    	String barcode = "scanItem:";

    	Random randomGenerator = new Random();

    	int number = randomGenerator.nextInt(3);

     	barcode = barcode + itemTypes.get(number) + "/13";
     	//System.out.println("random: " + new Integer(number).toString()+"/");
    	
    	number = 0;
    	return barcode;
    }

}
