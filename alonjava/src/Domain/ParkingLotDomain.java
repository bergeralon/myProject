package Domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

import Algorithm.Action;
import Algorithm.SearchDomain;
import Algorithm.State;

public class ParkingLotDomain implements SearchDomain, Serializable {
	
	private ParkingLotState[][] myParkingLot;
	private int rowsNum;
	private int colsNum;
	
	public ParkingLotDomain(int r, int c, int v){ // v for vehicle  
		this.rowsNum = r;
		this.colsNum = c;
		
		myParkingLot = new ParkingLotState[r][c];
		
		for(int i = 0; i<r; i++)
			for(int j=0; j<c; j++)
				myParkingLot[i][j] = new ParkingLotState(i, j);
		
		initCars(v);

	}
	public String printDomain(){
		return printParkingLot();
	}
	
	public String  printParkingLot() {
				
		String result = "";
		for (int i = 0; i < rowsNum; i++) {
			for (int j = 0; j < colsNum; j++) {
				
				if(myParkingLot[i][j].getIsCar() == true)
					result += " [c] ";
				else result += " [ ] ";
			}
			result += "\n";
		}
		return result;
	}
	
	public int getRegularPrice(){
		return 10;
		
	}

	private void initCars(int v) {
		
		if(v > 0 && v <= (int)((rowsNum*colsNum)/2)){
			
		Random rand = new Random();
		
		for (int i = 0; i < v; i++){
			int temp_i, temp_j;
		
			temp_i = rand.nextInt(rowsNum);
			temp_j = rand.nextInt(colsNum);
			
			if(!myParkingLot[temp_i][temp_j].getIsCar() && !(temp_i == 0 && temp_j == 0) && !(temp_i == rowsNum-1 && temp_j == colsNum-1))
				myParkingLot[temp_i][temp_j].setIsCar(true);
		}
	}	
}
	
	@Override
	public State getStartState() {
		ParkingLotState start = myParkingLot[0][0];
		start.setPrice(0);	
//		start.setfScore(rowsNum+colsNum-2);	// Manhatten distance
		return start;
	}

	@Override
	public ParkingLotState getGoalState() {
		return myParkingLot[rowsNum-1][colsNum-1];
	}

	@Override
	public HashMap<Action, State> getAllPossibleMoves(State current) 
		{
			ParkingLotState curr = (ParkingLotState)current;
			
			HashMap<Action, State> myMap = new HashMap<Action, State>();
				
				// all actions :
			
				// right action
				if((curr.getJ()+1 < colsNum) && (!myParkingLot[curr.getI()][curr.getJ()+1].getIsCar()))
					{
						Action a = new Action("right");
						ParkingLotState nextState = myParkingLot[curr.getI()][curr.getJ()+1];
//						
						myMap.put(a, nextState);
					}
					
				// left action
				if((curr.getJ()-1 >= 0) && (!myParkingLot[curr.getI()][curr.getJ()-1].getIsCar()))
					{
						Action a = new Action("left");
						ParkingLotState nextState = myParkingLot[curr.getI()][curr.getJ() - 1];
						
						myMap.put(a, nextState);
					}
					
				// up action
				if((curr.getI()-1 >= 0) && (!myParkingLot[curr.getI()-1][curr.getJ()].getIsCar()))
					{
						Action a = new Action("up");
						ParkingLotState nextState = myParkingLot[curr.getI()-1][curr.getJ()];
						
						myMap.put(a, nextState);
					}
			
				// down action
				if((curr.getI()+1 < rowsNum) && (!myParkingLot[curr.getI()+1][curr.getJ()].getIsCar()))
					{
						Action a = new Action("down");
						ParkingLotState nextState = myParkingLot[curr.getI()+1][curr.getJ()];

						myMap.put(a, nextState);
					}
			
			return myMap;
		}
	@Override
	public String getDescription()
	{
		return null;
	}
	

}
