
public class Proceso implements Runnable {
	private Contador contador;
	private Thread proceso;

	public Proceso(Contador contador, String nombre, ThreadGroup grupo) {
		this.proceso = new Thread(grupo, this, nombre);
		this.contador = contador;
		proceso.start();
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted() && contador.getNumero() < 5000) {
			synchronized (contador) {
				if (contador.getNumero() == 5000) {
					int numHermanos = proceso.getThreadGroup().activeCount();
					Thread[] procesosHermanos = new Thread[numHermanos];
					Thread.enumerate(procesosHermanos);

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					for (Thread p : procesosHermanos) {
						if (p != Thread.currentThread() && p.isAlive()) {
							p.interrupt();
							System.out.println("Mato a " + p.getName() + ".");
						}
					}
					System.out.println("Proceso: " + Thread.currentThread().getName() + " finalizo, que soy el último");
				}
				contador.setNumero(contador.getNumero() + 1);
			}
		}
		if (Thread.currentThread().isInterrupted())
			System.out.println("Proceso: " + Thread.currentThread().getName() + " me han matado");
	}
}
