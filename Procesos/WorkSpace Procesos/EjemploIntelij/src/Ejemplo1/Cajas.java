package Ejemplo1;

public class Cajas extends Thread {

	private Navajas navajas;
	private int ventas;

	public Cajas(String name, Navajas navajas, int prioridad) {
		super();
		setName(name);
		this.navajas = navajas;
		setPriority(prioridad);
	}
	
	

	public Cajas(String name,Navajas navajas) {
		super();
		setName(name);
		this.navajas = navajas;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		while (!(navajas.getUnidades() <= 0)) {
			synchronized (navajas) {
				accionVender();
			}
		}
		
		System.out.println("Soy " + getName() + " y al final vendi " + ventas);
	}
	
	private void accionVender() {
		ventas++;
		System.out.println("Soy " + getName() + " y acabo de vender una navaja");
	}
}
