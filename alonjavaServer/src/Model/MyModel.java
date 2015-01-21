package Model;

import java.util.ArrayList;
import java.util.Observable;

import Algorithm.Action;
import Algorithm.SearchDomain;
import Algorithm.Searcher;

public class MyModel extends Observable implements Model {
	
	private static SearchDomain domain;
	private Searcher algorithm;
	private SearchAlgorithmsFactory algorithmsFactory;
	private Solution solution;
	private boolean calculating = false;
	
	
	public MyModel()
	{
		algorithmsFactory = new SearchAlgorithmsFactory();
	}

	@Override
	public void selectDomain(String args) {
		String[] arr = args.split(":");
		String domainName = arr[0];
		String domainArgs = arr[1];
		domain = DomainFactory.createDomain(domainName,domainArgs);
	}
	
	@Override
	public void selectAlgorithm(String algorithmName) {
		algorithm = algorithmsFactory.createAlgorithm(algorithmName);
	}

	@Override
	public void solveDomain() {
		SolutionManager solutionManager = new SolutionManager();
		Solution sol = solutionManager.get(domain.getDescription());
		if (sol == null)
		{
			calculating = true;
//			sleepSomeTime();
			long startTime = System.currentTimeMillis();
			ArrayList<Action> actions = algorithm.search(domain);
			long endTime = System.currentTimeMillis();
			System.out.println("\nStarted at - "+startTime+"\nEnded at - "+endTime+"\n\nIt took only - "+( endTime - startTime)+" milliSeconds");
			solution = new Solution();
			solution.setActions(actions);
			solutionManager.put(domain.getDescription(), solution);
			calculating = false;
			setChanged();
			notifyObservers();
		}
		else
		{
			System.out.println("\n* FOUND CACHED SOLUTION!");
			solution = sol;
			this.setChanged();
			this.notifyObservers();
		}
		
	}

	public void sleepSomeTime()
	{
		try
		{
			Thread.sleep(15000);
		} catch (InterruptedException e)
		{
		}
	}

	@Override
	public Solution getSolution() {
		return solution;
	}
	@Override
	public void printCurrentState() {
		domain.printDomain();
	}

	@Override
	public boolean isCalculated()
	{
		return !calculating;
	}

	public static SearchDomain getDomain() {
		return domain;
	}


	@Override
	public void setDomain(SearchDomain domain) {
		this.domain = domain;
	}

}
