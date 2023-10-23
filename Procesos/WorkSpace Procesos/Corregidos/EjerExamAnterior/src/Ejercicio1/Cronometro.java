package Ejercicio1;

public class Cronometro extends Thread{
	private int segundos;
	private boolean bandera=true;
	private ContadorGeneral cg;
	
	public Cronometro(ContadorGeneral cg) {
		super();
		this.cg = cg;
	}
	@Override
	public void run() {
		while(bandera) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			segundos++;
		}
	}
	public int getSegundos() {
		return segundos;
	}
	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
	public boolean isBandera() {
		return bandera;
	}
	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

}
