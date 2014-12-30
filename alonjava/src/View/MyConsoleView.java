package View;

import java.util.Observable;
import java.util.Scanner;

import Algorithm.Action;
import Model.Solution;

public class MyConsoleView extends Observable implements View {

	private String action;
	
	@Override
	public void start() {
		System.out.println("Welcome to my project :)");
		action = "";
		Scanner scanner = new Scanner(System.in);
		do
		{
			System.out.print("Enter command: ");
			action = scanner.nextLine();
			
			if (!(action.equals("exit")))
			{
				this.setChanged();
				this.notifyObservers();
			}
			
		} while (!(action.equals("exit")));
		scanner.close();

	}

	@Override
	public void displayCurrentState() {
		System.out.println("This is the current view:");
		
	}

	@Override
	public void displaySolution(Solution solution) {
		for(Action a : solution.getActions())
			System.out.println(a);
	}

	@Override
	public String getUserAction() {		
		return action;
	}

	@Override
	public void showMessage(String msg)
	{
		System.out.println(msg);
	}

}
