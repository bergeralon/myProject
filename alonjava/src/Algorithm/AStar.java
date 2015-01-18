package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * uses a best-first search and finds a least-cost path 
 * from a given initial node to one goal node (out of one or more possible goals). 
 * As A* traverses the graph, it follows a path of the lowest expected total cost or distance, 
 * keeping a sorted priority queue of alternate path segments along the way.

	It uses a knowledge-plus-heuristic cost function of node x (usually denoted f(x)) to 
	determine the order in which the search visits nodes in the tree. The cost function is a sum of two functions:

	the past path-cost function, which is the known distance from the starting node to the current node x (usually denoted g(x))
	a future path-cost function, which is an admissible "heuristic estimate" of the distance from x to the goal (usually denoted h(x)).
 *
 */

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


