package versionPro;
import java.util.ArrayList;

public class DatoCompartido {
	private ArrayList<Integer> datos;
	
	public DatoCompartido(ArrayList<Integer> datos) {
		super();
		this.datos = datos;
	}
	public DatoCompartido() {
		super();
		datos=new ArrayList<Integer>();
	}

	public ArrayList<Integer> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<Integer> datos) {
		this.datos = datos;
	}
	
	public synchronized Integer getUltimo()
	{
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer dato=datos.get(datos.size());
		datos.remove(datos.size());
		return dato;
	}
	public synchronized void setDato(Integer dato)
	{
		datos.add(dato);
		notify();
	}
	
	
}
