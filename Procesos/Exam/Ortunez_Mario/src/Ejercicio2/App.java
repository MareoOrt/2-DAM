package Ejercicio2;

import java.util.Iterator;

public class App {
	
	public static void main(String[] args) {
		
		Agua agua = new Agua();
		
		Fuente fuente = new Fuente(agua);
		fuente.start();
		
		for (int i = 0; i < 4; i++) {
			Botella botella = new Botella(agua, "botella "+ (i+1));
			botella.start();
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		agua.pararAgua();
	}

}
