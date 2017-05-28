package br.com.fractal.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.com.fractal.request.Request;
import br.com.fractal.response.Response;

public class RequestHandler implements Runnable {

	private Socket  client = null;
	
	private Request  request = null;
	
	private Response response = null;
	
	private ObjectInputStream ois = null;
	
	private ObjectOutputStream oos = null;
	
	
	public RequestHandler( Socket client ) {
		this.client = client;
		this.setIOStream();
	}
	
	public void setIOStream(){
		try {
			this.ois = new ObjectInputStream( this.client.getInputStream() );
		} catch (IOException e) {
			System.out.println("Erro ao pegar ObjectInputStream");
			e.printStackTrace();
		}

		try {
			this.oos = new ObjectOutputStream( this.client.getOutputStream() );
		} catch (IOException e) {
			System.out.println("Erro ao pegar ObjectOutputStream");
			e.printStackTrace();
		}
	}
	
	public void close(){
		if( this.ois != null ){
			try {
				this.ois.close();
			} catch (IOException e) {
				System.out.println("Erro ao finalizar ObjectInputStream");
				e.printStackTrace();
			}
		}
		if( this.oos !=null ){
			try {
				this.oos.close();
			} catch (IOException e) {
				System.out.println("Erro ao finalizar ObjectOutputStream");
				e.printStackTrace();
			}
		}
		
		try {
			this.client.close();
		} catch (IOException e) {
			System.out.println("Erro ao fechar o ClientSocket");
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {		
		try {
			
			this.request = (Request) ois.readObject();
			
			this.response = this.request.handle();
			
			oos.writeObject( response );
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close();
	}

}
