package Model;

import Algorithm.SearchDomain;

public interface Model {
	void selectDomain(String domainName);
	void selectAlgorithm(String algorithmName);
	void solveDomain();
	Solution getSolution();
	void printCurrentState();
	boolean isCalculated();	
	SearchDomain getDomain();
}
