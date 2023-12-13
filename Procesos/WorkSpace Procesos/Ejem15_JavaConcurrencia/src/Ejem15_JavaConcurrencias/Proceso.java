package Ejem15_JavaConcurrencias;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class Proceso implements Runnable {

	AtomicInteger contador;
	int contadorPropio = 0;
	Thread proceso;

	public Proceso(String nombre, AtomicInteger contador) {
		super();
		proceso = new Thread(this, nombre);
		this.contador = contador;
		proceso.start();
	}

	@Override
	public void run() {

		/*
		 * --- Inicializacion del Thread con Runnebale---
		 * 
		 * Thread proceso = new Thread(() ->{});
		 * 
		 * System.out.println("Soy un proceso"); });
		 * 
		 * Thread proceso = new Thread(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * 
		 * } });
		 * 
		 * proceso.start();
		 */

		for (int i = 0; contador.get() < 10000; i++) {
			System.out.println("Proceso:" + Thread.currentThread().getName() + " - " + contador.addAndGet(i));
			contadorPropio++;
		}

		/*
		 * --- Otra opcion ---
		 * 
		 * int valor =0; for(;(valor=contador.addAndGet(i))<10000;){
		 * System.out.println("Proceso:" + Thread.currentThread().getName() + " - " +
		 * valor); contadorPropio++; }
		 *
		 */

		System.out.println("FIN: Proceso: " + Thread.currentThread().getName() + "- contadorpropio");

	}

}
