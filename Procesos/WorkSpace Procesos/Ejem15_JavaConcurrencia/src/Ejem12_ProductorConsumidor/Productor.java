package Ejem12_ProductorConsumidor;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Productor extends Thread {

	ArrayBlockingQueue<Integer> listaCompartida;
	
	public Productor(ArrayBlockingQueue<Integer> lista) {
		super();
		this.listaCompartida = lista;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		Random aleatorios = new Random();
		while(true) {
			try {
				listaCompartida.put(aleatorios.nextInt());
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(listaCompartida.size()==100) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
