package ProcesosAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Aplication {

	public static void main(String[] args) {

		// Proceso primera parte
		ProcesoAsincrono procesoAsync = new ProcesoAsincrono();
		
		// Procesos segunda parte
		ProcesoAsincrono procesoAsync2 = new ProcesoAsincrono(5);
		ProcesoAsincrono procesoAsync3 = new ProcesoAsincrono(4);

		System.out.println("Antes de la llamada\n");

		// Ejecutar uno
		ExecutorService executor = Executors.newSingleThreadExecutor();

		// Ejecutar varios
		ExecutorService executor2 = Executors.newFixedThreadPool(2);

		// Ejecutar runnable
		// Future<Integer> resultado = executor.submit(procesoRunnable);

		// Ejecutar callable
		// Ejecutando el proceso de la primera parte
		Future<Integer> resultadoEjecucion = executor.submit(procesoAsync);
		
		// Ejecutando los procesos de la segunda parte
		Future<Integer> resultadoEjecucion2 = executor2.submit(procesoAsync2);
		Future<Integer> resultadoEjecucion3 = executor2.submit(procesoAsync3);

		System.out.println("Proceso iniciado");
		// Con lo anterior ya se empieza a ejecutar

		try {
			// LLamando al call
			// System.out.println("El proceso devolvio: " +procesoAsync.call());

			// Mientras no se haya devuelto un resultado
			while (!resultadoEjecucion.isDone()) {
				Thread.sleep(1000);
				System.out.print(".");
			}	

			// Recuperar el resultado futuro, esperando a que acabe el proceso asincrono
			System.out.println("\nEl proceso devolvio: " + resultadoEjecucion.get());
			
			
			System.out.println("\n------------ Otra parte ------------\n");
			
			do {
				System.out.print(".");
				Thread.sleep(1000);

				if(resultadoEjecucion2.isDone()) {
					System.out.println("\nEl proceso 2 esta devolviendo: " + resultadoEjecucion2.get());
				}
				if(resultadoEjecucion3.isDone()) {
					System.out.println("\nEl proceso 3 esta devolviendo: " + resultadoEjecucion3.get());
				}		
				
			}while(!resultadoEjecucion2.isDone() && !resultadoEjecucion3.isDone());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Despues de la llamada");

		// Apagar ejecutor
		executor.shutdown();
	}

}
