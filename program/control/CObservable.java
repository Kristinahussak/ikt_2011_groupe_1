package control;

import java.util.Observable;

public class CObservable extends Observable
{
	
	public void notifySubcribers()
	{
		setChanged();
		notifyObservers();
	}
}
