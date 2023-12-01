package Ejercicio2;

import java.util.Random;

public class Agua {
	
	private boolean agua;
	private int tiempoTarda;

	public Agua() {
		super();
		this.agua = true;
	}

	public synchronized boolean cojerAgua() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!agua) {
			notifyAll();
		}
		return agua;
	}

	public synchronized void caeAgua() {
		notify();
		generarTiempo();
		try {
			Thread.sleep(this.tiempoTarda);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isAgua() {
		return agua;
	}

	public void pararAgua() {
		this.agua = false;
	}	
	
	public void generarTiempo() {
		Random rnd = new Random();
		this.tiempoTarda = rnd.nextInt(1,10);
	}
}
