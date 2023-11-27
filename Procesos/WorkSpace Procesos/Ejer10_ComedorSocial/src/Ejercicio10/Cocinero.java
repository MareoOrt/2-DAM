package Ejercicio10;

public class Cocinero extends Thread {

	private Mesa mesa;

	public Cocinero(Mesa mesa) {
		// TODO Auto-generated constructor stub
		this.mesa = mesa;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		for (int i = 0; i < 100; i++) {
			// if (!mesa.isComida()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Marchando un nuevo plato");
				mesa.cocineroSirve();
			//}
		}
		mesa.noHayComida();
		System.out.println("No cocino mas");
	}

}
