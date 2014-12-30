package Presenter;

import java.util.HashMap;

import Model.Model;

public class UserCommands {

	private HashMap<String, Command> commands = 
			new HashMap<String,Command>();
	
	public UserCommands()
	{
		commands.put("SelectDomain", new SelectDomainCommand());
		commands.put("SelectAlgorithm", new SelectAlgorithmCommand());
		commands.put("SolveDomain", new SolveDomainCommand());
	}
	
	public void doCommand(Model model, String commandName, String args)
	{
		Command command = commands.get(commandName);
		if (command != null)
		{
			command.doCommand(model, args);
		}
		else
		{
			System.out.println("Illegal command!");
		}
	}
	
	public interface Command
	{
		void doCommand(Model model, String args);
	}
	
	private class SelectDomainCommand implements Command
	{
		@Override
		//expecting format "Maze:rows,cols,walls"
		public void doCommand(Model model, String args) {
			model.selectDomain(args);			
		}		
	}
	
	private class SelectAlgorithmCommand implements Command
	{
		@Override
		//expecting name "AStar" etc
		public void doCommand(Model model, String args) {
			model.selectAlgorithm(args);			
		}		
	}
	
	private class SolveDomainCommand implements Command
	{
		@Override
		public void doCommand(Model model, String args) {
			model.solveDomain();			
		}		
	}
}
