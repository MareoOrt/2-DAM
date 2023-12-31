package versionPro;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PoliticoConsumidor extends Thread {

	private Ventanilla ventanilla;
	private BancoProductor banco;
	private double dineroRobado;

	public PoliticoConsumidor(String nombre, Ventanilla ventanilla, BancoProductor banco) {
		setName(nombre);
		this.ventanilla = ventanilla;
		this.banco = banco;
		dineroRobado = 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		do {
			if(!ventanilla.ventanillaVacia()) {
			System.out.println("Soy el " + getName().toLowerCase() + " y robe lo que habia en la ventanilla");
			this.dineroRobado += ventanilla.getDinero();
			}
		} while (!ventanilla.isSeAcaboElChollo());
		
		NumberFormat nf = new DecimalFormat("0.00");
		System.out.println("Soy el " + getName().toLowerCase() + " y al final robe " + nf.format(dineroRobado) + " €");

	}
}
