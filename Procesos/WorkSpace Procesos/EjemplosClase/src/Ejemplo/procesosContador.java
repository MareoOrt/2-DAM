package Ejemplo;

public class procesosContador extends Thread{
	
	static int contador = 0;
	
	
	public static int getContador() {
		return contador;
	}
	
	public procesosContador(String nombre, int contador) {
		// TODO Auto-generated constructor stub
		setName(nombre);
		this.contador = contador;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for (int i = 0; i < 10 ; i++) {
			int temp = contadorComun.getConstructor();
			temp++;
			contador = contador+i;
			System.out.println("Proceso hijo " + this.getName() + ": " + i + " - " + this.contador);
		}
		

	}
}

