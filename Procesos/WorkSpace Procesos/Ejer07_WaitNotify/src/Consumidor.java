
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
			synchronized (dato) {
				try {
					dato.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Soy " + getName() + " y escogi " + dato.getUltimo());
			}
			contador ++;
		} while (contador < 3);

	}
}
