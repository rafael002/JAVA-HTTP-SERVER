package br.com.fractal.request_response;

import java.io.Serializable;


public interface Request extends Serializable {

	Response handle();

}
