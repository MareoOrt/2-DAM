package ejericicio7_NotifyWait2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProcesoHijo extends Thread {

	private CadNumero numeros;
	private List<Integer> cadNumElegidos = new ArrayList<Integer>();

	public ProcesoHijo(CadNumero numeros) {
		super();
		this.numeros = numeros;
	}

	@Override
	public void run() {
		super.run();

		do {
			while(!isInterrupted()) {
			synchronized (numeros) {// Sincronizamos para que vayan por orden
				try {
					numeros.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int indiceNElegido = (new Random().nextInt(0, numeros.getNumeros().size()));// Cojemos un indice par un
																							// numero aleatorio

				int n = numeros.getNumeros().get(indiceNElegido);
				// Imprimimos
				System.out.println("Soy el proceso hijo " + getName() + " y escogi el numero " + n);
				// Borramos el numero de la lista parq que nadie lo pueda cojer
				numeros.getNumeros().remove(indiceNElegido);
				cadNumElegidos.add(n);
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}}
		} while (cadNumElegidos.size() > 5);

	}
}
