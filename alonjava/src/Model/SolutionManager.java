package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * this class checks every solution for a problem we had from the hashmap we've created for the solutions and delivers us a cached solution if there is one.
 * @author Alon
 *
 */

public class SolutionManager {

	private HashMap<String, Solution> cache;

	public SolutionManager() {
		cache = new HashMap<String, Solution>();
		try 
		{
			FileInputStream file = new FileInputStream("db");
			cache = (HashMap<String, Solution>) new ObjectInputStream(file).readObject();
			file.close();
		} 
		catch (ClassNotFoundException e) {} 
		catch (IOException e) {}
	}
	
	public void write() {
		try 
		{
			FileOutputStream file = new FileOutputStream("db");
			new ObjectOutputStream(file).writeObject(cache);
			file.close();
		} 
		catch (IOException e) {}
	}

	public void put(String problemDescription, Solution solution) {
		cache.put(problemDescription, solution);
		write();
	}

	public Solution get(String problemDescription) {
		return cache.get(problemDescription);
	}

}
