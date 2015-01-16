package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Algorithm.Action;

public class Solution implements Serializable{
	private ArrayList<Action> actions;
	private String firstState;
	

	public ArrayList<Action> getActions() {
		return actions;
	}

	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}

	public String getFirstState()
	{
		return firstState;
	}

	public void setFirstState(String firstState)
	{
		this.firstState = firstState;
	}
}
