package Algorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;

import Algorithm.Action;
import Algorithm.State;

/**
 * 
place all common things to all searchers here
 *
 */

public abstract class CommonSearcher implements Searcher  {
		
	protected PriorityQueue<State> openList = new PriorityQueue<State>();
	protected PriorityQueue<State> closedList = new PriorityQueue<State>();
	
	protected ArrayList<Action> generatePathToGoal(State state) {
		
		ArrayList<Action> actions = new ArrayList<Action>();
		
		while (state.cameFrom != null){
		actions.add(0, state.cameWithAction);
		state = state.cameFrom;			
		} 
	
			return actions;

	}
}