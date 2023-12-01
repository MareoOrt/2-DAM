package Ejercicio1;

public class App {

	public static void main(String[] args) {

		ThreadGroup estadio= new ThreadGroup("estdio");
		Entradas entradas = new Entradas(5000);
		
		// Ventanillas vip
		Ventanilla ventanilla1 = new Ventanilla(entradas, "Ventanilla 1 (VIP)", 2);
		ventanilla1.start();
		Ventanilla ventanilla2 = new Ventanilla(entradas, "Ventanilla 2 (VIP)", 2);
		ventanilla2.start();
		
		// Resto de ventanillas
		Ventanilla ventanilla3 = new Ventanilla(entradas, "Ventanilla 3", 1);
		ventanilla3.start();
		Ventanilla ventanilla4 = new Ventanilla(entradas, "Ventanilla 4", 1);
		ventanilla4.start();
	}

}
