package Ejemplo1;

public class App {
	public static void main(String[] args) {

		Navajas navajas = new Navajas(1000);

		for (int i = 0; i < 10; i++) {
			if (i <= 6) {
				Cajas c = new Cajas("Vendedor " + (i + 1), navajas, Thread.MAX_PRIORITY);
				c.start();
			} else if (i == 9) {
				Cajas c = new Cajas("Vendedor " + (i + 1), navajas, Thread.MIN_PRIORITY);
				c.start();
			} else {
				Cajas c = new Cajas("Vendedor " + (i + 1), navajas);
				c.start();
			}
		}
	}

}
