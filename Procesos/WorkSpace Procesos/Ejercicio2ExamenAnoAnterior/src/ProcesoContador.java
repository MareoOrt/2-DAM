

public class ProcesoContador implements Runnable {

	private Thread thread;
	private NumeroCompartido numeroCompartido;

	public ProcesoContador(String nombre, NumeroCompartido numeroCompartido) {
		this.thread = new Thread(this);
		this.numeroCompartido = numeroCompartido;
		this.thread.setName(nombre);
	}

	public Thread getThread() {
		return thread;
	}

	@Override
	public void run() {
		int coincidencias = 0;
		do {
			if (this.numeroCompartido.verificarNumero()) 
				coincidencias++;
			System.out.println("---");
		} while (!this.numeroCompartido.isFin());
		System.out.println("Coincidencias: " + coincidencias);
	}
}
