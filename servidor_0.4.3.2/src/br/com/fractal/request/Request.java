package br.com.fractal.request;

import java.io.Serializable;

import br.com.fractal.response.Response;


public interface Request extends Serializable {

	Response handle();

}
