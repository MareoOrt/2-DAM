package Ejercicio2;

import java.util.Random;

public class Fuente extends Thread {

	private Agua agua;

	public Fuente(Agua agua) {
		super();
		this.agua = agua;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		while (agua.isAgua()) {
			agua.caeAgua();
		}
		
	}

}
