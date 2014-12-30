package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class AStar extends CommonSearcher {

	@Override
	public ArrayList<Action> search(SearchDomain domain) {
		
		openList.clear();
		closedList.clear();
		State start = domain.getStartState(); 
		State goal = domain.getGoalState();
		
		openList.add(domain.getStartState());
		domain.getStartState().setPrice(0.0);
		
		double newPathPrice = 0.0;
		
		HashMap<State,Double> gScore = new HashMap<State,Double>();
		gScore.put(start,0.0);
		
		start.setfScore (start.getPrice() + start.getEvaluation(goal));
		
		while(!openList.isEmpty())
		{
			State current = openList.poll();
			
			if (current.equals(goal))
					return generatePathToGoal(goal);
			
			closedList.add(current);
			
			HashMap<Action,State> neighbors = domain.getAllPossibleMoves(current);
			
			//if(neighbors.isEmpty() && openList.isEmpty())
			//	return false;
			
			for(Action a :neighbors.keySet())
				{
					State nextState = neighbors.get(a);
					
					if (closedList.contains(nextState))
						continue;
					
					newPathPrice = (gScore.get(current) + a.getPrice());
					
					if (!gScore.containsKey(nextState))
						gScore.put(nextState, gScore.get(current) + neighbors.get(a).getPrice());
					
					if(!openList.contains(nextState) || newPathPrice < gScore.get(nextState))
					{
						nextState.setCameFrom(current);
						nextState.setCameWithAction(a);
						gScore.put(nextState, newPathPrice);
						nextState.setPrice(nextState.getEvaluation(goal) + newPathPrice);
						
						if(!openList.contains(nextState))
							openList.add(nextState);
						
					}
				}
		}
					
		return new ArrayList<Action>();
	}
}


