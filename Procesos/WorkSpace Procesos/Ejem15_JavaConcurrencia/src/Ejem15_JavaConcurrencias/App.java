package Ejem15_JavaConcurrencias;

import java.util.concurrent.atomic.AtomicInteger;

public class App {

	public static void main(String[] args) {

		AtomicInteger contador = new AtomicInteger();

		Proceso p1 = new Proceso("p1", contador);
		Proceso p2 = new Proceso("p2", contador);
		Proceso p3 = new Proceso("p3", contador);
		Proceso p4 = new Proceso("p4", contador);

	}
}
