package ejericicio7_NotifyWait2;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ProcesoPadre extends Thread {

	private CadNumero numeros;

	public ProcesoPadre(CadNumero numeros) {
		super();
		this.numeros = numeros;
	}

	@Override
	public void run() {
		super.run();
		
		List<Integer> lista = numeros.getNumeros();// Guardo la cadena inicial
		
		for (int i = 0; i < 4; i++) {// Repetimos 3 veces
			
			numeros.setNumeros(lista);// Pongo la cadena inicial 
			
			do {// Repetimos hasta que se cojan todos los numeros de la lista
				synchronized (numeros) {// Despertamos a los proesos
					numeros.notify();
				}
			} while (numeros.getNumeros().size() > 0);
		}

	}
}
