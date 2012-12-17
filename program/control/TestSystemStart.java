package control;

import entity.*;

public class TestSystemStart
{
	public static void main(String[] args)
	{
		CSystemStart temp = new CSystemStart();
		temp.systemStart();
		
		EItem tempItem = new EItem(1, new EItemType("","",0));
		tempItem.update();
	}

}
