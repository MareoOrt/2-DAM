package Ejercicio1;

public class Entradas {

	private int contador;
	private boolean advertencia;

	public Entradas(int contador) {
		super();
		this.contador = contador;
		this.advertencia = true;
	}

	public int getContador() {
		contador--;
		return contador;
	}

	public boolean hayEntradas() {
		if (this.contador <= 0) {
			if (advertencia) {
				this.advertencia = false;
				System.out.println("NO HAY MAS ENTRADAS \n");
			}
			return false;
		} else {
			return true;
		}
	}

}
