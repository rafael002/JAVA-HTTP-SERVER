package br.com.fractal.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.com.fractal.request_response.Request;
import br.com.fractal.request_response.Response;

public class SendRequest implements Runnable {

	private Request request = null;
	
	public SendRequest( Request request ){
		this.request = request;
	}
	public void run() {
		try {
			Socket client = new Socket( "localhost", 12345 );
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
