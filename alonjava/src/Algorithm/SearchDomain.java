package Algorithm;

import java.util.HashMap;

// the expected functionality from a search problem 
public interface SearchDomain  {

	State getStartState();
	State getGoalState();
	
	void printDomain();
	HashMap<Action,State> getAllPossibleMoves(State current);	
	
}


