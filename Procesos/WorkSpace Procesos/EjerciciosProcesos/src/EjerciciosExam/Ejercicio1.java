package EjerciciosExam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Ejercicio1 {

	public static void main(String[] args) {
		
	}
	
	private void contador() {
		// TODO Auto-generated method stub
		Contador contadorGlobal= new Contador(0);
		
		ArrayList<Proceso> procesos = new ArrayList<Proceso>();
		
		for (int i = 0; i < 5; i++) {
			procesos.add(new Proceso());
		}
		
		int contadorTiempo = 0;
		int tiempo = 0;
		
		int limiteContador = 2000;
		int limiteIndividual = 5000;
		
		do {
			synchronized (contadorGlobal) {
				if(contadorGlobal.getContador()<limiteContador) {
					contadorGlobal.setContador(contadorGlobal.getContador()+1);
					System.out.println();
				}
			}
			if(contadorGlobal.getContador()<limiteContador) {
				try {					
					Thread.sleep(1);
					contadorTiempo ++;
					
					if(contadorTiempo >= 1000) {
						tiempo ++;
						contadorTiempo = 0;
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					System.err.print(e.getMessage());
				}
			}
			
		} while (contadorGlobal.getContador() > limiteContador);
	}
}
