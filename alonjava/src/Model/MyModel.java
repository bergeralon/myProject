package Model;

import java.util.ArrayList;
import java.util.Observable;

import Algorithm.Action;
import Algorithm.SearchDomain;
import Algorithm.Searcher;

public class MyModel extends Observable implements Model {
	
	private SearchDomain domain;
	private Searcher algorithm;
	private SearchAlgorithmsFactory algorithmsFactory;
	private Solution solution;
	
	public MyModel()
	{
		algorithmsFactory = new SearchAlgorithmsFactory();
	}

	@Override
	public void selectDomain(String args) {
		// TODO Auto-generated method stub
		String[] arr = args.split(":");
		String domainName = arr[0];
		String domainArgs = arr[1];
		domain = DomainFactory.createDomain(domainName,domainArgs);
	}

	@Override
	public void selectAlgorithm(String algorithmName) {
		// TODO Auto-generated method stub
		algorithm = algorithmsFactory.createAlgorithm(algorithmName);
	}

	@Override
	public void solveDomain() {		
//		sleepSomeTime();
		long startTime = System.currentTimeMillis();
		ArrayList<Action> actions = algorithm.search(domain);
		long endTime = System.currentTimeMillis();
		System.out.println("\nStarted at - "+startTime+"\nEnded at - "+endTime+"\n\nIt took only - "+( endTime - startTime)+" milliSeconds");
		solution = new Solution();
		solution.setActions(actions);
		
		this.setChanged();
		this.notifyObservers();
	}

	public void sleepSomeTime()
	{
		try
		{
			Thread.sleep(10000);
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

}
