package Algorithm;

import java.util.HashMap;

// the expected functionality from a search problem 
public interface SearchDomain  {

	State getStartState();
	State getGoalState();
	
	String printDomain();
	HashMap<Action,State> getAllPossibleMoves(State current);	
	
	String getDescription();
}


