package Ejem12_ProductorConsumidor;

public class App {

	public static void main(String[] args) {

		Dato dato = new Dato();
		
		Productor productor = new Productor(dato);
		Consumidor consumidor = new Consumidor(dato);
		productor.start();
		consumidor.start();
	}
}
