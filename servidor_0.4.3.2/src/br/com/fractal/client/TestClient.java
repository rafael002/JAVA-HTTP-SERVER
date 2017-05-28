package br.com.fractal.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.fractal.request.TestRequest;
import br.com.fractal.response.Response;

public class TestClient {

	public static void main(String[] args) {
		
		Socket client;
		
		try {
			client = new Socket( "localhost", 12345 );
			
			ExecutorService threadPool = Executors.newFixedThreadPool( 1 );
			
			Callable<Response> task = new SendRequest(  new TestRequest(), client);
			
			Future<Response> response = threadPool.submit( task );
			
			while( ! response.isDone() ){
				Thread.sleep( 1 * 500);
			}
			
			Response r = response.get();
			
			threadPool.shutdown();
			
			r.open();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
