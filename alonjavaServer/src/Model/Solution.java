package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Algorithm.Action;

public class Solution implements Serializable{
	private ArrayList<Action> actions;

	public ArrayList<Action> getActions() {
		return actions;
	}

	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}
}
