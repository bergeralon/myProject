package clientServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyTCPIPServer {
	private ServerSocket server;
	private ExecutorService executor;
	private Thread thread;
		
	public Thread getThread()
	{
		return thread;
	}

	public MyTCPIPServer(int port) throws IOException {
		server = new ServerSocket(port);
		server.setSoTimeout(1000000);
	}
	
	public void startServer(int numOfClients) {
		executor = Executors.newFixedThreadPool(numOfClients);
		
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(!Thread.interrupted()) {
					try {
						Socket socket = server.accept();
						System.out.println("Got new connection");
						
						if (socket != null) {
							ClientHandler handler = new ClientHandler(socket);
							executor.submit(handler);
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	catch (Exception e) {
						
					}
				}				
			}			
		});
		thread.start();
	}
	
	public void stopServer() {		
		try {
			executor.shutdownNow();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
