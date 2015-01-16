package Domain;

import Algorithm.State;

public class ParkingLotState extends State {

	private int i,j;
	private boolean isCar;
	
	public ParkingLotState(int i, int j) {
	
		super("["+String.valueOf(i)+","+String.valueOf(j)+"]");
			
		this.i = i;
		this.j = j;
		
		isCar = false;
	
	}

	public boolean getIsCar(){return this.isCar;}
	
	public void setIsCar(boolean v){
		this.isCar = v;
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

	
	
	@Override	// Heuristic evaluation
	public double getEvaluation(State goal) {
		ParkingLotState goal1 = (ParkingLotState)goal;
		return (goal1.getJ() - this.j) +  (goal1.getI() - this.i);	// Manhatten distance
	}
}
