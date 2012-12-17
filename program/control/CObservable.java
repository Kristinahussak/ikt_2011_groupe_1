package control;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import entity.EFacade;

public class CObservable extends Observable
{
	private static CObservable instance = null;
	
	public static CObservable getInstance()
    {
    	if(instance == null){instance = new CObservable();}
    	return instance;
    }
	
	public boolean addSubscriber(Observer o)
	{	
		this.addObserver(o);
		return true;
	}
	
	public void notifySubcribers()
	{			
		setChanged();
		notifyObservers();		
	}
	
}
