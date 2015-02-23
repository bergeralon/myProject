package Model;

/**
 * this class holds most of our program's information as well as calculation.
 * the model class gets the information he needs from the user through the presenter (the "middle man").
 */

import Algorithm.SearchDomain;

public interface Model {
	void selectDomain(String domainName);
	void selectAlgorithm(String algorithmName);
	void solveDomain();
	Solution getSolution();
	void printCurrentState();
	boolean isCalculated();	
	void setDomain(SearchDomain domain);
}
