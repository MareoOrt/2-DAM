package versionPro;

public class Aplicacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Ventanilla ventanilla=new Ventanilla();
		BancoProductor banco =new BancoProductor(ventanilla);
		for(int i=0;i<5;i++)
		{
			PoliticoConsumidor politico =new PoliticoConsumidor("Politico " + i, ventanilla, banco);
			politico.start();
		}
		banco.start();
		
	}

}
