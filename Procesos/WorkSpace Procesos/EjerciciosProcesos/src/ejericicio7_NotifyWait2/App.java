package ejericicio7_NotifyWait2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

	public static void main(String[] args) {
		CadNumero numeros = new CadNumero();
		List<Integer> lista = new ArrayList<Integer>();

		System.out.print("Se escogieron los numeros ");

		for (int i = 0; i < 5; i++) {
			int rnd = 0;

			do {
				rnd = new Random().nextInt(0, 10);
			} while (comprobarIguales(lista, rnd));

			lista.add(rnd);

			if (i == 4) {
				System.out.print(" y " + rnd + "\n");
			} else if (i == 3) {

				System.out.print(rnd);
			} else {
				System.out.print(rnd + ", ");
			}
		}

		numeros.setNumeros(lista);

		System.out.println("\n");
		
		Thread[] procesosHijos = new Thread[5];
		
		for (int i = 0; i < 5; i++) {
			ProcesoHijo hijo = new ProcesoHijo(numeros);
			hijo.start();
			procesosHijos[i]=hijo;
		}

		ProcesoPadre padre = new ProcesoPadre(numeros, procesosHijos);
		padre.start();
	}

	public static boolean comprobarIguales(List<Integer> lista, int numero) {
		boolean est = false;

		for (Integer integer : lista) {
			if (integer == numero) {
				est = true;
			}
		}

		return est;
	}
}
