package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import Algorithm.Action;
import Algorithm.SearchDomain;
import Domain.MazeDomain;

/**
 * here we have the settings for choosing the desired domain from the command line.
 * In addition, we have a saving solution to a file method as well as a read solution from file method.
 * @author bergeral
 *
 */

public class DomainFactory {

	public static SearchDomain createDomain(String domainName, String domainArgs) {
		if(domainName.equals("Maze")){
			String[] arr = domainArgs.split(",");
			int rows = Integer.parseInt(arr[0]);
			int cols = Integer.parseInt(arr[1]);
			int walls = Integer.parseInt(arr[2]);
			return new MazeDomain(rows,cols,walls);
		}
		//TODO:parking lot
		return null;
	}

	
	public static void main(String[] args)
	{
		saveSolutionsInFile();
	}
	
	private static final String FILE_NAME = "solutions.dat";
	
	public static void saveSolutionsInFile() {
		Solution s = new Solution();
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Action("Hi"));
		s.setActions(actions );
		
		
		FileOutputStream out = null;
		ObjectOutputStream oos = null;
		try {
			out = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(out);
			oos.writeObject(s);

		} catch (IOException e) 
		{
			e.printStackTrace();
		} finally 
		{
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void readSolutionsFromFile() {
		FileInputStream in = null;
		ObjectInputStream ois = null;
		try {
			in = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(in);
			/*solutionsMap = (HashMap<String, Solution>) */ois.readObject();

		} catch (FileNotFoundException e) {
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	
}
