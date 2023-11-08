package Ejem8b;

public class App {
	public static void main(String[] args) {
		DatoCompartido dato = new DatoCompartido();
		
		Consumidor consumidor = new Consumidor(dato);
		consumidor.start();
		
		Productor productor = new Productor(dato);
		productor.start();
		
	}

}
