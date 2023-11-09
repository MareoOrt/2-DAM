package ejericicio7_NotifyWait2;

import java.util.Random;

public class ProcesoHijo extends Thread {

	private CadNumero numeros;

	public ProcesoHijo(CadNumero numeros) {
		super();
		this.numeros = numeros;
	}

	@Override
	public void run() {
		super.run();

		synchronized (numeros) {// Dormimos
			try {
				numeros.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int indiceNElegido = (new Random().nextInt(0, numeros.getNumeros().size()));// Cojemos un indice par un
																						// numero aleatorio
			// Imprimimos
			System.out.println("Soy el proceso hijo " + getName() + " y escogi el numero "
					+ numeros.getNumeros().get(indiceNElegido));
			// Borramos el numero de la lista parq que nadie lo pueda cojer
			numeros.getNumeros().remove(indiceNElegido);
		}
	}
}
