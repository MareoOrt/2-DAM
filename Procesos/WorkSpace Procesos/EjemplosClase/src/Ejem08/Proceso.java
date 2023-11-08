package Ejem08;

public class Proceso extends Thread{

	Saludo saludo;

	public Proceso(Saludo saludo) {
		super();
		this.saludo = saludo;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		synchronized (saludo) {
			try {
				saludo.setSaludo(getName());
				saludo.notify();
				saludo.wait();
				System.out.println("Proceso: " + getName() + " " + saludo.getSaludo());
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
}
