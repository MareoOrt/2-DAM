package Ejem8b;

import java.util.Scanner;

public class Productor extends Thread {

	private DatoCompartido dato;
	
	public Productor(DatoCompartido dato) {
		super();
		this.dato = dato;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Scanner sc = new Scanner(System.in);
		
		/*
		String cadena = sc.nextLine();
		
		do {
			dato.setCadena(cadena);
			synchronized (dato) {
				dato.notify();
			}
			cadena=sc.nextLine();
		}while (!cadena.equals("fin"));
		
		dato.setCadena(cadena);
		
		synchronized (dato) {
			dato.notify();
		}
		*/
		
		String cadena;
		
		do {
			cadena=sc.nextLine();
			dato.setCadena(cadena);
			synchronized (dato) {
				dato.notify();
			}
		}while (!cadena.equals("fin"));
		
	}
}
