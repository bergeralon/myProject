package clientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Algorithm.SearchDomain;
import Model.Model;
import Model.MyModel;
import Model.Solution;

public class ClientHandler implements Runnable {
	private Socket socket;
	
	public ClientHandler(Socket socket)
	{
		this.socket = socket;
	}

	@Override
	public void run() {
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			
			SearchDomain domain = (SearchDomain)in.readObject();
			//System.out.println("Got new problem: " + problem.getDomainName());
			
			Model model = new MyModel();
//			model.selectDomain(domain.getDomainName());
//			model.selectAlgorithm(domain.getAlgorithmName());
			model.solveDomain();
			Solution solution = model.getSolution();
			
			//System.out.println("Found solution: " + solution.getProblemDescription());
			out.writeObject(solution);			
			
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
	}	
}
