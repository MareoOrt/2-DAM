package versionPro;
import java.util.Random;

public class Productor extends Thread {

	private DatoCompartido dato;
	private Random aleatorio;

	public Productor(DatoCompartido dato) {
		this.dato = dato;
		aleatorio = new Random();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			generarAleatorio();

		}
	}

	private void generarAleatorio() {
		// TODO Auto-generated method stub

			for (int i = 0; i < 5; i++) {
				dato.setDato(aleatorio.nextInt());
							
			}
	}

}
