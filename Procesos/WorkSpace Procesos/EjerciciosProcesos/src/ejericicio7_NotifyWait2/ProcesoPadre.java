package ejericicio7_NotifyWait2;

import java.util.ArrayList;
import java.util.List;

public class ProcesoPadre extends Thread {

	private CadNumero numeros;
	private ProcesoHijo[] procesosHijos;

	public ProcesoPadre(CadNumero numeros, ProcesoHijo[] procesosHijos) {
		super();
		this.numeros = numeros;
		this.procesosHijos = procesosHijos;
	}

	@Override
	public void run() {
		super.run();

		List<Integer> lista = new ArrayList<>(numeros.getNumeros());

		for (int i = 0; i < 4; i++) {

			System.out.println("Esta es la vuelta " + (i + 1));

			numeros.setNumeros(new ArrayList<>(lista));

			do {
				synchronized (numeros) {
					for (int j = 0; j < procesosHijos.length; j++) {
						
						if(procesosHijos[i].isElegido()) {
							
							procesosHijos[i].notify();
						}
					}
				}
			} while (numeros.getNumeros().size() > 0);

			System.out.println("\n");

		}

	}
}
