package pojos;

public class Candidatura {

	// Atributos
	private String nombre_elec;
	private String nombre_o;
	private String cod_elec;
	private int cod_comarca;
	private int pobl_derecho_o;
	private int mesas_o;
	private int votantes_o;
	private int abstencion_o;
	private int nulos_o;
	private int blancos_o;
	private int candidatura_o;
	private int electores_o;
	private int validos_o;
	private String n_electos_o;
	private int n_consejeros_o;

	// Constructor
	public Candidaturas(String nombre_elec, String nombre_o, String cod_elec, int cod_comarca, int pobl_derecho_o,
			int mesas_o, int votantes_o, int abstencion_o, int nulos_o, int blancos_o, int candidatura_o,
			int electores_o, int validos_o, String n_electos_o, int n_consejeros_o) {
		super();
		this.nombre_elec = nombre_elec;
		this.nombre_o = nombre_o;
		this.cod_elec = cod_elec;
		this.cod_comarca = cod_comarca;
		this.pobl_derecho_o = pobl_derecho_o;
		this.mesas_o = mesas_o;
		this.votantes_o = votantes_o;
		this.abstencion_o = abstencion_o;
		this.nulos_o = nulos_o;
		this.blancos_o = blancos_o;
		this.candidatura_o = candidatura_o;
		this.electores_o = electores_o;
		this.validos_o = validos_o;
		this.n_electos_o = n_electos_o;
		this.n_consejeros_o = n_consejeros_o;
	}

	// Getters y Setters
	public String getNombre_elec() {
		return nombre_elec;
	}

	public void setNombre_elec(String nombre_elec) {
		this.nombre_elec = nombre_elec;
	}

	public String getNombre_o() {
		return nombre_o;
	}

	public void setNombre_o(String nombre_o) {
		this.nombre_o = nombre_o;
	}

	public String getCod_elec() {
		return cod_elec;
	}

	public void setCod_elec(String cod_elec) {
		this.cod_elec = cod_elec;
	}

	public int getCod_comarca() {
		return cod_comarca;
	}

	public void setCod_comarca(int cod_comarca) {
		this.cod_comarca = cod_comarca;
	}

	public int getPobl_derecho_o() {
		return pobl_derecho_o;
	}

	public void setPobl_derecho_o(int pobl_derecho_o) {
		this.pobl_derecho_o = pobl_derecho_o;
	}

	public int getMesas_o() {
		return mesas_o;
	}

	public void setMesas_o(int mesas_o) {
		this.mesas_o = mesas_o;
	}

	public int getVotantes_o() {
		return votantes_o;
	}

	public void setVotantes_o(int votantes_o) {
		this.votantes_o = votantes_o;
	}

	public int getAbstencion_o() {
		return abstencion_o;
	}

	public void setAbstencion_o(int abstencion_o) {
		this.abstencion_o = abstencion_o;
	}

	public int getNulos_o() {
		return nulos_o;
	}

	public void setNulos_o(int nulos_o) {
		this.nulos_o = nulos_o;
	}

	public int getBlancos_o() {
		return blancos_o;
	}

	public void setBlancos_o(int blancos_o) {
		this.blancos_o = blancos_o;
	}

	public int getCandidatura_() {
		return candidatura_o;
	}

	public void setCandidatura_(int candidatura_o) {
		this.candidatura_o = candidatura_o;
	}

	public int getElectores_o() {
		return electores_o;
	}

	public void setElectores_o(int electores_o) {
		this.electores_o = electores_o;
	}

	public int getValidos_o() {
		return validos_o;
	}

	public void setValidos_o(int validos_o) {
		this.validos_o = validos_o;
	}

	public String getN_electos_o() {
		return n_electos_o;
	}

	public void setN_electos_o(String n_electos_o) {
		this.n_electos_o = n_electos_o;
	}

	public int getN_consejeros_o() {
		return n_consejeros_o;
	}

	public void setN_consejeros_o(int n_consejeros_o) {
		this.n_consejeros_o = n_consejeros_o;
	}

}
