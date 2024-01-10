package Ejem12_ProductorConsumidor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumidor extends Thread {

	ArrayBlockingQueue<Integer> listaCompartida;

	public Consumidor(ArrayBlockingQueue<Integer> lista) {
		super();
		this.listaCompartida = lista;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		while (true) {
			try {
				System.out.println("Tamaño de la lista compartida: " + listaCompartida.size() + " - Dato recuperado: "
						+ listaCompartida.poll(1000, TimeUnit.MILLISECONDS));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
