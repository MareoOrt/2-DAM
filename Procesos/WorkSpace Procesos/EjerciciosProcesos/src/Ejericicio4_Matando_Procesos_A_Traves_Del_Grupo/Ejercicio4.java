package Ejericicio4_Matando_Procesos_A_Traves_Del_Grupo;

public class Ejercicio4 {
	public static void main(String[] args) {

		new Thread(() -> {
			int i = 0;
			for (; i < 100; i++) {
				System.out.println("Soy el porceso padre");

				Thread procesoH = new Thread(() -> {
					System.out.println("Soy el proceso hijo ");
				});
				procesoH.start();
				
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
