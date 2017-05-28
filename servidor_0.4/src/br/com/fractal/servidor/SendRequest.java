package br.com.fractal.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.com.fractal.request_response.Request;
import br.com.fractal.request_response.Response;

public class SendRequest implements Runnable {

	
	private Socket client = null;
	
	private Request request = null;
	
	public SendRequest( Request request, Socket client ){
		this.request = request;
		this.client = client;
	}
	public void run() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream( client.getOutputStream() );
			
			oos.writeObject( this.request );
			
			ObjectInputStream ois = new ObjectInputStream( client.getInputStream() );
			
			Response r = (Response) ois.readObject();
			
			r.open();
			
			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
