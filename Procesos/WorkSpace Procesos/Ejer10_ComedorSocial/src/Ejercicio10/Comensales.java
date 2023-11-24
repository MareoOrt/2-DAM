package Ejercicio10;

public class Comensales extends Thread {

	private boolean comido;
	private Mesa mesa;

	public Comensales(Mesa mesa, String nombre) {
		super();
		setName(nombre);
		this.mesa = mesa;
		this.comido = false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		if (mesa.isComida()) {
			System.out.println("Soy " + getName() + " y ya comi");
			mesa.alguienCome();
			comido = true;
		}

		System.out.println("Me voy lleno");
	}
}
