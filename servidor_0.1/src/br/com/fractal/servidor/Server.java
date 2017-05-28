package br.com.fractal.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	private ServerSocket server;
	private int port = 12345;
	
	public void open(){
		try {
			this.server = new ServerSocket( port );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Server s = new Server();
		
		s.open();
		
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		while( true ){
			try {
				Socket client = s.server.accept();
				
				service.submit( new RequestHandler( client ) );
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
