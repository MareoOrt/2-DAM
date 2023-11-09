package ejericicio7_NotifyWait2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

	public static void main(String[] args) {
		CadNumero numeros = new CadNumero();
		List<Integer> lista = new ArrayList<Integer>();
		
		System.out.print("Se escogieron los numeros ");
		
		// Generacion de aleatorios
		for (int i = 0; i < 5; i++) {
			int rnd = 0;// Numeros aleatorios
			
			do {// Saca un aleatorio hasta que este no este en la lista
				rnd = new Random().nextInt(0,10);
			} while (comprobarIguales(lista, rnd));
			
			// Añado aleatorio a la lista
			lista.add(rnd);
			
			// Imprimimos
			if(i == 4 ) {
				System.out.print(" y " + rnd + "\n");
			}else {				
				System.out.print(" , " + rnd);
			}
		}
		
		numeros.setNumeros(lista);
		
		// Generamos 5 hijos
		for (int i = 0; i < 5; i++) {
			ProcesoHijo hijo = new ProcesoHijo(numeros);
			hijo.start();			
		}
		
		ProcesoPadre padre = new ProcesoPadre(numeros);
		padre.start();
	}
	
	
	// Si el numero se encuentra ya en la lista devuelve true, sino false
	public static boolean comprobarIguales(List<Integer> lista, int numero) {
		boolean est = false;
		
		for (Integer integer : lista) {
			if(integer == numero) {
				est = true;
			}
		}
		
		return est;		
	}
}
