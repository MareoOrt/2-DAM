package Ejercicio10;

public class Comensales extends Thread {

	private Mesa mesa;

	public Comensales(Mesa mesa, String nombre) {
		super();
		setName(nombre);
		this.mesa = mesa;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		mesa.alguienCome();
		System.out.println("Soy " + getName() + " y ya comi");
	}
}
