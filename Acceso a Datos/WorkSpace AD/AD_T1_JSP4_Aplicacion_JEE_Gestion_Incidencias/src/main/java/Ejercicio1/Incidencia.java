package Ejercicio1;

public class Incidencia {

	private int codigo;
	private String tema;
	private String descripcion;
	
	public Incidencia(int codigo, String tema, String descripcion) {
		super();
		this.codigo = codigo;
		this.tema = tema;
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
}
