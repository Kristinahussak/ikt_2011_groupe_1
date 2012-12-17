package control;

import java.util.Observable;
import java.util.Observer;

public class CObservable extends Observable
{
	
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
