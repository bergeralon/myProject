package Algorithm;

import java.util.ArrayList;

/**
 * 
 * here we have the declaration of the 'search' method which gets a domain and returns the solution using a selected algorithm
 *
 */

public interface Searcher {

	public ArrayList<Action> search(SearchDomain domain);	
}
