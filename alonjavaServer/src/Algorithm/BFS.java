package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class BFS extends CommonSearcher {

	@Override
	public ArrayList<Action> search(SearchDomain domain) {
		this.openList.add(domain.getStartState());
		
		while (!openList.isEmpty())
		{
			State state = openList.poll();
			closedList.add(state);
			
			if (state.equals(domain.getGoalState()))
			{
				ArrayList<Action> actions = generatePathToGoal(state);
				return actions;
			}
			
			HashMap<Action, State> nextStates = domain.getAllPossibleMoves(state);
			for (Action a : nextStates.keySet())
			{
				State nextState = nextStates.get(a);
				double newPathPrice = state.getPrice() + a.getPrice();
				if (!openList.contains(nextState) && !closedList.contains(nextState))
				{
					nextState.setCameFrom(state);
					nextState.setCameWithAction(a);
					nextState.setPrice(newPathPrice);
										
					openList.add(nextState);
				}
				else
				{					
					if (newPathPrice < nextState.getPrice())
					{
						if (!openList.contains(nextState))
							openList.add(nextState);
						else {
							nextState.setPrice(newPathPrice);
							openList.remove(nextState);
							openList.add(nextState);
						}
					}
				}
			}
		}	
		
		return new ArrayList<Action>();
	}
	
	public ArrayList<Action> generatePathToGoal(State state) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		do
			{
				actions.add(0, state.cameWithAction);
				state = state.cameFrom;			
			} while (state != null);
		
		return actions;
	}
}
