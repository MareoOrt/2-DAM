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

	public boolean isComida() {
		return comida;
	}

	public synchronized void alguienCome() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.comida = false;
		System.out.println("[Se comio el plato]");
	}
	
	public synchronized void cocineroSirve() {
		//this.comida = true;
		notify();
		//System.out.println("[Se sirvio el plato]");
	}
	
	public synchronized void noHayComida() {
		notifyAll();
	}
	

}
