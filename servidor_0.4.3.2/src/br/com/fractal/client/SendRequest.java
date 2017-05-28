package br.com.fractal.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

import br.com.fractal.request.Request;
import br.com.fractal.response.Response;

public class SendRequest implements Callable<Response>{

	private Socket client = null;
	
	private Request request = null;
	
	public SendRequest( Request request, Socket client ){
		this.request = request;
		this.client = client;
	}
	public Response call() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream( this.client.getOutputStream() );
			
			oos.writeObject( this.request );
			
			ObjectInputStream ois = new ObjectInputStream( this.client.getInputStream() );
			
			Response response = (Response) ois.readObject();
			
			client.close();
			oos.close();
			ois.close();
			
			return response;
			
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
		return null;
	}
}
