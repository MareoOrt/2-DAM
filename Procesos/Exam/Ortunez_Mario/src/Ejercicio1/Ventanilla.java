package Ejercicio1;

public class Ventanilla extends Thread {

	private Entradas entradas;
	private int contador;

	public Ventanilla(Entradas entradas, String nombre, int prioridad) {
		super();
		setName(nombre);
		setPriority(prioridad);
		this.entradas = entradas;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		while (entradas.hayEntradas()) {
			synchronized (entradas) {
				if (entradas.hayEntradas()) {
					System.out.println("En la " + getName() + " la entrada " + (entradas.getContador()+1));
					contador++;
				}

			}
		}

		System.out.println("En la " + getName() + " se vendieton un total de " + contador);
	}

}
