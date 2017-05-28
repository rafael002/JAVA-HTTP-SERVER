package br.com.fractal.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
	
	private ServerSocket server;
	
	private boolean running = true;
	
	private int port = 12345;
	
	public void open(){
		try {
			this.server = new ServerSocket( port );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			this.server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		
		this.open();
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		
		while( this.running ){
			try {
				Socket client = this.server.accept();
				
				threadPool.submit( new RequestHandler( client ) );
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.close();
		threadPool.shutdownNow();
	}
}
