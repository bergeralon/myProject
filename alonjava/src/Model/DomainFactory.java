package Model;

import Algorithm.SearchDomain;
import Domain.MazeDomain;

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

}
