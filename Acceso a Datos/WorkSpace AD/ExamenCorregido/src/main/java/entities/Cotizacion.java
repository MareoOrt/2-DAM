package entities;

public class Cotizacion {

	private String ciclo;
	private Double actual;
	private Double anterior;
	private String evalua;
	
	public Cotizacion(String ciclo, Double anterior) {
		super();
		this.ciclo = ciclo;
		this.anterior = anterior;
	}

	public Cotizacion(String ciclo, Double actual, Double anterior) {
		super();
		this.ciclo = ciclo;
		this.actual = actual;
		this.anterior = anterior;
	}

	public String getCiclo() {
		return ciclo;
	}
	
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	public Double getActual() {
		return actual;
	}
	
	public void setActual(Double actual) {
		this.actual = actual;
		setEvalua();
	}
	
	public Double getAnterior() {
		return anterior;
	}
	
	public void setAnterior(Double anterior) {
		this.anterior = anterior;
	}
	
	public String getEvalua() {
		return evalua;
	}
	
	public void setEvalua() {
		String evalua="";
		
		if(this.actual!=0.0) {
			if(this.anterior <this.actual) {
				evalua = "Mayor";
			} else if(this.anterior >this.actual) {
				evalua = "Menor";
			}else {
				evalua = "Igual";
			}
		}
		
		this.evalua = evalua;
	}
}
