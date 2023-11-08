package Ejem08;

public class App {

	public static void main(String[] args) {
		Saludo saludo = new Saludo("saludo");
		Proceso proceso = new Proceso(saludo);
		
		proceso.start();		
	}
}
