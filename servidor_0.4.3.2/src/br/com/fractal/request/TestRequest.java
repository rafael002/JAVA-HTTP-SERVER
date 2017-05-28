package br.com.fractal.request;

import br.com.fractal.response.Response;
import br.com.fractal.response.testeResponse;

@SuppressWarnings("serial")
public class TestRequest implements Request {

	@Override
	public Response handle() {
		System.out.println(" Dados recebidos do cliente. ");
		
		return (Response) new testeResponse();
	}

}
