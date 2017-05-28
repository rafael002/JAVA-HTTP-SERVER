package br.com.fractal.servidor;

public class TestServer {

	public static void main(String[] args) {
		new Thread( new Server() ).start();
	}
}
