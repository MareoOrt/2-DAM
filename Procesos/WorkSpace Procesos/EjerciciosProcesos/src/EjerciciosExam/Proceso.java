package EjerciciosExam;

import java.util.concurrent.ThreadFactory;

public class Proceso extends ThreadFactory {

	private Contador contador = new Contador(0);
	
	@Override
	public Thread newThread(Runnable r) {
		return null;
	}

	public Contador getContador() {
		return contador;
	}

	public void setContador(Contador contador) {
		this.contador = contador;
	}

	@Override
	public String toString() {
		return "Proceso [contador=" + contador + "]";
	}

	
}
