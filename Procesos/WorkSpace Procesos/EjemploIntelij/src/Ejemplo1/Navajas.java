package Ejemplo1;

public class Navajas {
	private int unidades;

	public Navajas(int unidades) {
		super();
		this.unidades = unidades;
	}

	public int getUnidades() {
		this.unidades --;
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
}
