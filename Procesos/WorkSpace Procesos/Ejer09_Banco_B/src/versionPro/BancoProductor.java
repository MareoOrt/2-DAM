package versionPro;

import java.awt.font.NumericShaper;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class BancoProductor extends Thread {

	private Ventanilla ventanilla;
	private double banca;

	public BancoProductor(Ventanilla ventanilla) {
		this.ventanilla = ventanilla;
		banca = 23000000;
	}

	public synchronized double getBanca() {
		return banca;
	}
	

	public void setBanca(double banca) {
		this.banca = banca;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		do {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			depositarEnVentanilla();
		}while(banca > 0);
	}

	private void depositarEnVentanilla() {
		// TODO Auto-generated method stub
		Random rnd = new Random();
		NumberFormat nf = new DecimalFormat("0.00");
		double dineroQuitar = rnd.nextDouble(1000000, 2000000);
		if (dineroQuitar > banca) {
			dineroQuitar = banca;
			ventanilla.setSeAcaboElChollo(true);
		}
		banca = banca - dineroQuitar;
		ventanilla.setDinero(dineroQuitar);
		System.out.println("Soy el banco y deposite " + nf.format(dineroQuitar) + " € en la ventanilla");
	}

}
