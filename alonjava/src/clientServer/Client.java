package clientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Algorithm.SearchDomain;
import Model.Solution;



public class Client {
	
	public Solution getSolution(SearchDomain domain, String algo, String ip, int port) {		
		Socket socket = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		
		try {
			socket = new Socket(ip, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
						
			out.writeObject(domain);			
			out.writeObject(algo);			
			return (Solution)in.readObject();	
								
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
		return null;
	}
}
