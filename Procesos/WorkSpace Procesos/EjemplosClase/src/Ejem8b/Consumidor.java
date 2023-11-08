package Ejem8b;

public class Consumidor extends Thread {
	
	private DatoCompartido dato;
	
	public Consumidor(DatoCompartido dato) {
		super();
		this.dato = dato;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		do {
			synchronized (dato) {
				try {
					dato.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Soy el consumidor: " + dato.getCadena());
		}while (!dato.getCadena().equals("fin"));
	}
}
