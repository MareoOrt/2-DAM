package versionPro;

public class Aplicacion {

	public static void main(String[] args) {

		Ventana ventana = new Ventana();

		double dineroTotal = 230000000;

		System.out.println("Hay " + dineroTotal + " en el banco");
		
		BancoProductor banco = new BancoProductor(ventana, dineroTotal);
		for (int i = 0; i < 5; i++) {
			PoliticoConsumidor politico = new PoliticoConsumidor("Politico " + i, ventana, banco);
			politico.start();
		}

		banco.start();
	}

}
