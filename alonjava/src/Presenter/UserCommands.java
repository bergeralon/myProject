package Presenter;

import java.util.HashMap;

import Model.Model;
import View.View;

/**
 * here we defined the desired type in commands from the user for choosing his game settings.
 * @author Alon
 *
 */

public class UserCommands {

	private HashMap<String, Command> commands = 
			new HashMap<String,Command>();
	
	public UserCommands()
	{
		commands.put("SelectDomain", new SelectDomainCommand());
		commands.put("SelectAlgorithm", new SelectAlgorithmCommand());
		commands.put("SolveDomain", new SolveDomainCommand());
		commands.put("isCalculated", new IsCalculatedCommand());
	}
	
	public void doCommand(Model model, View v, String commandName, String args)
	{
		Command command = commands.get(commandName);
		if (command != null)
		{
			command.doCommand(model, v, args);
		}
		else
		{
			System.out.println("Illegal command!");
		}
	}
	
	public interface Command
	{
		void doCommand(Model model, View v, String args);
	}
	
	private class SelectDomainCommand implements Command
	{
		@Override
		//expecting format "Maze:rows,cols,walls"
		public void doCommand(Model model, View v, String args) {
			model.selectDomain(args);			
		}		
	}
	
	private class SelectAlgorithmCommand implements Command
	{
		@Override
		//expecting name "AStar" etc
		public void doCommand(Model model, View v, String args) {
			model.selectAlgorithm(args);			
		}		
	}
	
	private class SolveDomainCommand implements Command
	{
		@Override
		public void doCommand(Model model, View v, String args) {
			model.solveDomain();			
		}		
	}
	
	private class IsCalculatedCommand implements Command
	{
		@Override
		public void doCommand(Model model, View v, String args) {
			if (model.isCalculated())
			{
				v.showMessage("Calculation is done");
			}
			else
			{
				v.showMessage("Still calculating");
			}
		}		
	}
}
