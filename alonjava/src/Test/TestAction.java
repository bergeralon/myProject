package Test;

import java.util.ArrayList;

import Algorithm.AStar;
import Algorithm.Action;
import Algorithm.BFS;
import Algorithm.SearchDomain;
import Algorithm.Searcher;
import Domain.MazeDomain;
import Domain.ParkingLotDomain;

public class TestAction {

	public static void main1(String[] args) {
		
		SearchDomain domain1 = new MazeDomain(6,6,15);
		
		SearchDomain domain2 = new ParkingLotDomain(2, 4, 3);
		
		System.out.println("Staring state is set to upper left corner.\nGoal stste is set to bottom right corner.\n");
//		System.out.println("Maze Layout:\n");
		
//		((MazeDomain)domain1).printMaze();
		
		System.out.println("ParkingLot Layout:\n");
		
		((ParkingLotDomain)domain2).printParkingLot();
		
		int sum = 0;
		
//		take start time:
		long startTime = System.currentTimeMillis();
		
//		summon AStar:
		Searcher s = new AStar();
		
//		summon BFS:
//		Searcher s= new BFS();
		
		ArrayList<Action> actions = s.search(domain2);	// switch here between domain1 and domain2
		
		
		
		if (actions.isEmpty())
			System.out.println("\nSorry bro, you're stuck...:/");
		
		else {
				System.out.println("\nFound you a path!\nTake these steps my friend and good luck :\n");
				
				for(Action n : actions){
					System.out.println(n);
				
					sum += n.getPrice();
				}
						
				System.out.println("\nIt cost you : "+sum);
		}
		
//		end time:
		long endTime = System.currentTimeMillis();
		System.out.println("\nStarted at - "+startTime+"\nEnded at - "+endTime+"\n\nIt took only - "+( endTime - startTime)+" milliSeconds");
	
	}
	
}
