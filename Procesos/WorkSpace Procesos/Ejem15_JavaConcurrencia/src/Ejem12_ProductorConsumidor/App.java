package Ejem12_ProductorConsumidor;

import java.util.concurrent.ArrayBlockingQueue;

public class App {

	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> listaCompartida = new ArrayBlockingQueue<Integer>(100);
		
		Productor productor = new Productor(listaCompartida);
		Consumidor consumidor = new Consumidor(listaCompartida);
		productor.start();
		consumidor.start();
	}
}
