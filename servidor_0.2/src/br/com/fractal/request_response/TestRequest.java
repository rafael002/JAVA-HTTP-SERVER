package br.com.fractal.request_response;

@SuppressWarnings("serial")
public class TestRequest implements Request {

	@Override
	public Response handle() {
		System.out.println(" Dados recebidos do cliente. ");
		
		return (Response) new testeResponse();
	}

}
