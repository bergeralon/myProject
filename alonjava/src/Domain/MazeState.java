package Domain;

import Algorithm.State;

/**
 * this  class defines each state in this domain and checks for walls.
 * @author bergeral
 *
 */


public class MazeState extends State {

	private int i,j;
	private boolean isWall;
	
	public MazeState(int i, int j) {
	
		super("["+String.valueOf(i)+","+String.valueOf(j)+"]");
			
		this.i = i;
		this.j = j;
		
		isWall = false;
	
	}

	public boolean getIsWall(){return this.isWall;}
	
	public void setIsWall(boolean w){
		this.isWall = w;
	}
	

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	@Override
	public String toString()
	{
		return "MazeState [i=" + i + ", j=" + j + ", isWall=" + isWall + "]";
	}

	@Override	// Heuristic evaluation
	public double getEvaluation(State goal) {
		MazeState goal1 = (MazeState)goal;
		return (goal1.getJ() - this.j) +  (goal1.getI() - this.i);	// Manhatten distance
	}
}
