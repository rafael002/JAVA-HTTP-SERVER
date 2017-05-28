package br.com.fractal.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.com.fractal.request_response.Response;
import br.com.fractal.request_response.TestRequest;

public class Client {

	public static void main(String[] args) {
		try {
			Socket client = new Socket( "localhost", 12345 );
			ObjectOutputStream oos = new ObjectOutputStream( client.getOutputStream() );
			
			oos.writeObject(new TestRequest());
			
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
