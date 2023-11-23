package Ejercicio1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Incidencia {

	private int codigo;
	private String tema;
	private String descripcion;
	private Set<Integer> listaCodigos = new HashSet<Integer>();

	public Incidencia(String tema, String descripcion) {
		super();
		setCodigo();
		this.tema = tema;
		this.descripcion = descripcion;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo() {
		Random r = new Random();
		Integer code = r.nextInt(1, 20);
		boolean comprobarcode = listaCodigos.contains(code);
		do {
			code = r.nextInt(1, 20);
			comprobarcode = listaCodigos.contains(code);
		} while (!comprobarcode);

		listaCodigos.add(code);

	}
}
