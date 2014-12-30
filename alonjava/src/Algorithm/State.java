package Algorithm;



public abstract class State implements Comparable<State> {

	// the string representation of the state
	protected String state;
	protected double price;
	protected State cameFrom;
	protected Action cameWithAction;
	protected double fScore; // g + h
	
	public State(String state) {
		this.state=state;
		this.cameFrom = null;
		this.cameWithAction = null;
		//TODO: all other fields with null
	}
	public State getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State cameFrom) {
		this.cameFrom = cameFrom;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	// what is the state's evaluation with respect to the goal
	public abstract double getEvaluation(State goal);

	public String getState(){
		return state;
	}
	
	@Override
	public String toString(){
		return state;
	}
	
	@Override
	public boolean equals(Object o){
		return state.equals(((State)o).getState());
	}
	
	@Override
	public int compareTo(State state)
	{
		if (this.getPrice() > state.getPrice())
			return 1;
		else if (this.getPrice() < state.getPrice())
			return -1;
		return 0;
	}

	public Action getCameWithAction() {
		return cameWithAction;
	}

	public void setCameWithAction(Action cameWithAction) {
		this.cameWithAction = cameWithAction;
	}
	
	public double getfScore (){
		return fScore;
	}
	 
	public void setfScore(double fScore){
		this.fScore = fScore;
	}
	
//	public abstract int HeuristicEvaluation(State goal);
	
	
}
