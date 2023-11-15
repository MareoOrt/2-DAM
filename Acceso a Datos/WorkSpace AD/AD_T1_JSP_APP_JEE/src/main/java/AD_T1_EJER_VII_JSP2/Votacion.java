package AD_T1_EJER_VII_JSP2;

public class Votacion {

	private String nombre;
	private int nVotacion;

	public Votacion(String nombre, int nVotacion) {
		super();
		this.nombre = nombre;
		this.nVotacion = nVotacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getnVotacion() {
		return nVotacion;
	}

	public void setnVotacion(int nVotacion) {
		this.nVotacion = nVotacion;
	}

}
