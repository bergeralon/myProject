package Model;

import java.util.HashMap;

import Algorithm.AStar;
import Algorithm.BFS;
import Algorithm.Searcher;

public class SearchAlgorithmsFactory {
	private HashMap<String, AlgorithmCreator> algorithms;
	
	public SearchAlgorithmsFactory()
	{
		algorithms = new HashMap<String, AlgorithmCreator>();
		algorithms.put("BFS", new BFSCreator());
		algorithms.put("AStar", new AStarCreator());
	}
	
	public Searcher createAlgorithm(String algorithmName)
	{
		AlgorithmCreator creator = algorithms.get(algorithmName);
		Searcher searcher = null;
		if (creator != null)  {
			System.out.println("\n* Creating algo name: "+algorithmName);
			searcher = creator.create();			
		}
		else
		{
			System.out.println("Algorithm doesn't exist: "+algorithmName);
		}
		return searcher;
	}
	
	private interface AlgorithmCreator
	{
		Searcher create();
	}
	
	private class BFSCreator implements AlgorithmCreator
	{
		@Override
		public Searcher create() {
			
			return new BFS();
		}		
	}
	
	private class AStarCreator implements AlgorithmCreator
	{
		@Override
		public Searcher create() {
			
			return new AStar();
		}		
	}
}
