package versionPro;

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
			System.out.println("Soy el " + getName().toLowerCase() + " y robe lo que habia en la ventanilla");
			this.dineroRobado += ventanilla.getDinero();
		} while (ventanilla.isSeAcaboElChollo());
		

	}
}
