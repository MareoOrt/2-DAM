package Ejemplos;

public class Ejemplo1 {

	public static void main(String[] args) {
		Runnable runnable = () -> {System.out.println("Lambda runeable running");};
		Thread thread = new Thread(runnable);
		thread.start();
		
		new Thread("Proceso") {
			public void run() {}{
				System.out.println("Thread: " + getName() + " running");
			}
		}.start();
	}
}
