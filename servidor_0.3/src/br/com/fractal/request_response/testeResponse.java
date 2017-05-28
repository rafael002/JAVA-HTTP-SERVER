package br.com.fractal.request_response;

@SuppressWarnings("serial")
public class testeResponse implements Response {

	@Override
	public void open() {
		System.out.println("Dados recebidos do servidor.");
		
	}
	
}
