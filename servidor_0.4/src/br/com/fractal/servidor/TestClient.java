package br.com.fractal.servidor;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import br.com.fractal.request_response.TestRequest;

public class TestClient {

	public static void main(String[] args) {
		Socket client;
		try {
			client = new Socket( "localhost", 12345 );
			
			new Thread(
					new SendRequest( new TestRequest(), client ) ).start();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
