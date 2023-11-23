package versionPro;
import java.util.Random;

public class BancoProductor extends Thread {

	private Ventana ventana;
	private double dinero;

	public BancoProductor(Ventana ventana, double dinero) {
		this.ventana = ventana;
		this.dinero = dinero;
	}
	
	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	@Override
	public void run() {

		super.run();

		do{
			double dineroVentana = getDinero(1000000, 2000000);
			ventana.setDInero(dineroVentana);
			System.out.println("Soy el banco y deposite " + dineroVentana + " en la ventana");
			synchronized (ventana) {
				ventana.notify();
			}
		}while(!(this.dinero == 0));
	}
	
	public synchronized double getDinero(double origen, double fin) {

		Random rnd = new Random();
		double dineroQuita = rnd.nextDouble(origen, fin);
		if (dineroQuita >= this.dinero) {
			dineroQuita = this.dinero;
		}
		dinero -= dineroQuita;
		return dineroQuita;
	}
}
