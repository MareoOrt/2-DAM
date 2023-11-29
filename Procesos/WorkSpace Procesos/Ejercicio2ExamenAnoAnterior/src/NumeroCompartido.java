

import java.util.Random;

public class NumeroCompartido {

	private int numero;
	private boolean fin;
	private int numerosTotales=0;

	public NumeroCompartido(int personas) {
		this.numero = personas;
		this.fin = false;
	}

	public boolean isFin() {
		return fin;
	}

	public synchronized void setFin(boolean fin) {
		this.fin = fin;
		notify();
	}

	public synchronized void generarNumero() {
		Random random = new Random();
		this.numero = random.nextInt(0, 21);
		numerosTotales++;
		System.out.println("Números totales:"+numerosTotales+":"+this.numero);
		if (numero==10)
		{	
			notify();
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized boolean verificarNumero() {
		//boolean es10 = false;
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!fin)
		{
			// notify();
			return true;
		}else
			return false;
		/*if (this.numero == 10) {
			es10 = true;
		}
		this.numero = 0;
		return es10;*/
		
	}

}
