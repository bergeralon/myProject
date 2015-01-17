package Model;

import java.util.Observable;

import Algorithm.SearchDomain;
import clientServer.Client;
import clientServer.MyProperties;

public class MyModel extends Observable implements Model {
	
	private static SearchDomain domain;
	private String algorithm;
	private SearchAlgorithmsFactory algorithmsFactory;
	private Solution solution;
	private boolean calculating = false;
	
	private SolutionManager solutionManager = new SolutionManager();
	
	public MyModel()
	{
		algorithmsFactory = new SearchAlgorithmsFactory();
	}

	@Override
	public void selectDomain(String args) {
		String[] arr = args.split(":");
		String domainName = arr[0];
		String domainArgs = arr[1];
		domain = (DomainFactory.createDomain(domainName,domainArgs));
	}
	
	@Override
	public void selectAlgorithm(String algorithmName) {
		algorithm = algorithmName;
	}

	@Override
	public void solveDomain() {
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					MyProperties m = new MyProperties();
					Client client = new Client();
					solution = client.getSolution(getDomain(), algorithm, m.ip, m.port);
					
//					Moved to server.
//					
//					calculating = true;
//					sleepSomeTime();
//					long startTime = System.currentTimeMillis();
//					ArrayList<Action> actions = algorithm.search(domain);
//					long endTime = System.currentTimeMillis();
//					System.out.println("\nStarted at - "+startTime+"\nEnded at - "+endTime+"\n\nIt took only - "+( endTime - startTime)+" milliSeconds");
//					solution = new Solution();
//					solution.setActions(actions);
//					solutionManager.put(domain.getDescription(), solution);
//					calculating = false;
					
					
					setChanged();
					notifyObservers();
				}
			}).start();
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
		if (solution != null)
			System.out.println(solution.getFirstState());
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
