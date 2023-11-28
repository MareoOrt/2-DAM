package Ejercicio10;

public class App {
	public static void main(String[] args) {

		Mesa mesa = new Mesa();

		Cocinero cocinero = new Cocinero(mesa);
		cocinero.start();

		for (int i = 0; i < 100; i++) {
			Comensales comensal = new Comensales(mesa, "Persona " + (i + 1));
			comensal.start();
		}

	}
}
