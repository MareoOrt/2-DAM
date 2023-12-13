package Ejem12_ProductorConsumidor;

import java.util.Random;

public class Productor extends Thread {

	Dato datoCompartido;
	
	public Productor(Dato datoCompartido) {
		super();
		this.datoCompartido = datoCompartido;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		Random aleatorios = new Random();
		while(true) {
			datoCompartido.producir(aleatorios.nextInt());
		}
	}
}
