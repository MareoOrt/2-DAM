package Ejem12_ProductorConsumidor;

import java.util.concurrent.SynchronousQueue;

public class Dato {

	private SynchronousQueue<Integer> lista = new SynchronousQueue<>();

	public SynchronousQueue<Integer> getLista() {
		return lista;
	}

	public void setLista(SynchronousQueue<Integer> lista) {
		this.lista = lista;
	}

	public int consumir() {
		// TODO Auto-generated method stub
		return lista.poll();
	}

	public void producir(int nextInt) {
		// TODO Auto-generated method stub
		try {
			lista.put(nextInt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
