package Ejem12_ProductorConsumidor;

public class Consumidor extends Thread {

	Dato datoCompartido;
	
	public Consumidor(Dato datoCompartido) {
		super();
		this.datoCompartido = datoCompartido;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		while(true) {
			System.out.println(datoCompartido.consumir());
		}
	}
}
