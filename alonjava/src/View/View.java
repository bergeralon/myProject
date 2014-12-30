package View;

import Model.Solution;

public interface View {
	void start();
	void displayCurrentState();
	void displaySolution(Solution solution);
	String getUserAction();
	
}
