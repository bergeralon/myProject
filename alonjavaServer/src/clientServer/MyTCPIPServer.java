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
						System.out.println("\n* Got new connection");
						
						if (socket != null) {
							ClientHandler handler = new ClientHandler(socket);
							executor.submit(handler);
						}
						System.out.println("\n* Finished executing");
						
					} catch (IOException e) {
						
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
	
	public static void main(String[] args) throws IOException, InterruptedException
	{
		System.out.println("Server started...");
		MyProperties m = new MyProperties();
		MyTCPIPServer server = new MyTCPIPServer(m.port);
		server.startServer(10);
		server.thread.join();
		server.stopServer();
	}
	
}
