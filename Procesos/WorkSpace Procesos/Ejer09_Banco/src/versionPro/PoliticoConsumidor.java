package versionPro;

public class PoliticoConsumidor extends Thread {

	private Ventana ventana;
	private BancoProductor banco;
	private double dineroRobado = 0;

	public PoliticoConsumidor(String nombre, Ventana ventana, BancoProductor banco) {
		setName(nombre);
		this.ventana = ventana;
		this.banco = banco;
	}

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		super.run();
		do {
			if (ventana.getDinero() > 0) {
				System.out.println("Soy el " + getName().toLowerCase() + " y robe lo que habia en la ventana");
				dineroRobado += ventana.getDinero();
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} while (!(banco.getDinero() == 0));
	}
}
