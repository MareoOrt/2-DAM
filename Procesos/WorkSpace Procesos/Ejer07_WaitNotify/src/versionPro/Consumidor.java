package versionPro;

public class Consumidor extends Thread {

	private DatoCompartido dato;
	private int contador = 0;

	public Consumidor(String nombre, DatoCompartido dato) {
		setName(nombre);
		this.dato = dato;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		do {

			System.out.println("Soy " + getName() + " y escogi " + dato.getUltimo());
			
			contador++;
		} while (contador < 3);

	}
}
