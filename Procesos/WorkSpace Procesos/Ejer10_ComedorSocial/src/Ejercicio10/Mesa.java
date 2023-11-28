package Ejercicio10;

public class Mesa {

	private boolean comida;

	public Mesa() {
		super();
		comida = false;
	}

	public Mesa(boolean comida) {
		super();
		this.comida = comida;
	}

	public synchronized void alguienCome() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[Se comio el plato]");
	}

	public synchronized void cocineroSirve() {
		notify();
	}

	public synchronized void noHayComida() {
		notifyAll();
	}

}
