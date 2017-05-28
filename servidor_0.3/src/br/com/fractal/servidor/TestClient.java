package br.com.fractal.servidor;

import br.com.fractal.request_response.TestRequest;

public class TestClient {

	public static void main(String[] args) {
		new Thread(
				new SendRequest( new TestRequest() ) ).start();
	}
}
