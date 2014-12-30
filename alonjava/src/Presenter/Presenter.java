package Presenter;

import java.util.Observable;
import java.util.Observer;

import Model.Model;
import Model.MyModel;
import Model.Solution;
import View.MyConsoleView;
import View.View;

public class Presenter implements Observer {
	private Model model;
	private View view;
	private UserCommands commands;
	
	public Presenter(Model model, View view)
	{
		this.model = model;
		this.view = view;
		commands = new UserCommands();
	}

	@Override
	public void update(Observable observable, Object arg1) {
		if (observable == model)
		{
			Solution solution = model.getSolution();
			model.printCurrentState();
			view.displaySolution(solution);
		}
		else if (observable == view)
		{
			String action = view.getUserAction();
			String[] arr = action.split(" ");
			
			String commandName = arr[0];
			
			String args = null;
			if (arr.length > 1)
				args = arr[1];
			
			commands.doCommand(model, view, commandName, args);
		}
	}
	
	public static void main(String[] args) {
		MyModel model = new MyModel();
		MyConsoleView view = new MyConsoleView();
		Presenter presenter = new Presenter(model, view);
		
		model.addObserver(presenter);
		view.addObserver(presenter);
		
		view.start();
	}
	
}
