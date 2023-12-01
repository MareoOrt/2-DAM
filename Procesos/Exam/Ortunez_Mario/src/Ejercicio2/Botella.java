package Ejercicio2;

public class Botella extends Thread {

	private int mililitros;
	private Agua agua;

	public Botella(Agua agua, String nombre) {
		super();
		setName(nombre);
		this.agua = agua;
	}

	public int getMililitros() {
		return mililitros;
	}

	public void setMililitros(int mililitros) {
		this.mililitros = mililitros;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (agua.isAgua()) {
			if (agua.cojerAgua()) {
				System.out.println("La " + getName() + " cogio agua");
				mililitros++;
			}
		}
		System.out.println("En la " + getName() + " al final se lleno con " + mililitros + " ml3 de agua");
	}

}
