package Domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

import Algorithm.Action;
import Algorithm.SearchDomain;
import Algorithm.State;

/**
 * 
 * Implementations of functions that define the maze's layout,
 * possible moves and states.
 *
 */

public class MazeDomain implements SearchDomain, Serializable {
	
	private MazeState[][] myMaze;
	private int rowsNum;
	private int colsNum;
	
	public MazeDomain(int r, int c, int w)  {	
		this.rowsNum = r;
		this.colsNum = c;
		
		myMaze = new MazeState[r][c];
		
		for(int i = 0; i<r; i++)
			for(int j=0; j<c; j++)
				getMaze()[i][j] = new MazeState(i, j);
		
		initWalls(w);
		getMaze()[0][0].setFigure(true);

	}
	public String printDomain(){
		return printMaze();
	}
	
	public String printMaze() {
				
		String result = "";
		for (int i = 0; i < rowsNum; i++) {
			for (int j = 0; j < colsNum; j++) {
				
				if(getMaze()[i][j].getIsWall() == true)
					result += " [x] ";
				else if(getMaze()[i][j].isFigure() == true)
					result += " [o] ";
				else result += " [ ] ";
			}
			result += "\n";
		}
		result += "\n";
		return result;
	}
	
	public int getRegularPrice(){
		return 10;
		
	}

	private void initWalls(int w) {
		
//		myMaze[0][1].setIsWall(true);  // tests i made to check AStar vs. BFS
//		myMaze[3][1].setIsWall(true);
//		myMaze[2][1].setIsWall(true);
//		myMaze[4][3].setIsWall(true);
//		myMaze[3][3].setIsWall(true);
//		myMaze[2][3].setIsWall(true);
		
		if(w > 0 && w <= (int)((rowsNum*colsNum)/2)){
			
		Random rand = new Random();
		
		for (int i = 0; i < w; i++){
			int temp_i, temp_j;
		
			temp_i = rand.nextInt(rowsNum);
			temp_j = rand.nextInt(colsNum);
			
			if(!getMaze()[temp_i][temp_j].getIsWall() && !(temp_i == 0 && temp_j == 0) && 
					!(temp_i == 1 && temp_j == 0) && !(temp_i == 0 && temp_j == 1) && 
						!(temp_i == rowsNum-2 && temp_j == colsNum-2) && !(temp_i == rowsNum-2 && temp_j == colsNum-1) &&
						!(temp_i == rowsNum-1 && temp_j == colsNum-2) && !(temp_i == rowsNum-1 && temp_j == colsNum-1))getMaze()[temp_i][temp_j].setIsWall(true);
		}
	}	
}
	
	@Override
	public State getStartState() {
		MazeState start = getFigureState();
		start.setPrice(0);	
		start.setfScore(rowsNum+colsNum-2);	// Manhatten distance
		return start;
	}

	@Override
	public MazeState getGoalState() {
		return getMaze()[rowsNum-1][colsNum-1];
	}

	@Override
	public HashMap<Action, State> getAllPossibleMoves(State current) 
		{
			MazeState curr = (MazeState)current;
			
			HashMap<Action, State> myMap = new HashMap<Action, State>();
				
				// all actions :
			
				// right action
				if((curr.getJ()+1 < colsNum) && (!getMaze()[curr.getI()][curr.getJ()+1].getIsWall()))
					{
						Action a = new Action("right");
						MazeState nextState = getMaze()[curr.getI()][curr.getJ()+1];
//						
						myMap.put(a, nextState);
					}
					
				// left action
				if((curr.getJ()-1 >= 0) && (!getMaze()[curr.getI()][curr.getJ()-1].getIsWall()))
					{
						Action a = new Action("left");
						MazeState nextState = getMaze()[curr.getI()][curr.getJ() - 1];
						
						myMap.put(a, nextState);
					}
					
				// up action
				if((curr.getI()-1 >= 0) && (!getMaze()[curr.getI()-1][curr.getJ()].getIsWall()))
					{
						Action a = new Action("up");
						MazeState nextState = getMaze()[curr.getI()-1][curr.getJ()];
						
						myMap.put(a, nextState);
					}
			
				// down action
				if((curr.getI()+1 < rowsNum) && (!getMaze()[curr.getI()+1][curr.getJ()].getIsWall()))
					{
						Action a = new Action("down");
						MazeState nextState = getMaze()[curr.getI()+1][curr.getJ()];

						myMap.put(a, nextState);
					}
			
			return myMap;
		}
	@Override
	public String getDescription()
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < rowsNum; i++)
		{
			for (int j = 0; j < colsNum; j++)
			{
				sb.append(getMaze()[i][j].toString());
			}
		}
		return sb.toString();
	}
	public MazeState[][] getMaze() {
		return myMaze;
	}
	
	public MazeState getFigureState()
	{
		for (int i = 0; i < rowsNum; i++) {
			for (int j = 0; j < colsNum; j++) {
				if(myMaze[i][j].isFigure())
					return myMaze[i][j];
			}
		}
		return null;
	}

}
