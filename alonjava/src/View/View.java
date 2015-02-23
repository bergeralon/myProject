package View;

import Model.Solution;

/**
 * the view layer notifies the observer and the presenter "tells" the model layer what to do. 
 * @author Alon
 *
 */

public interface View {
	void start();
	void displayCurrentState();
	void displaySolution(Solution solution);
	String getUserAction();
	void showMessage(String msg);
	
}
