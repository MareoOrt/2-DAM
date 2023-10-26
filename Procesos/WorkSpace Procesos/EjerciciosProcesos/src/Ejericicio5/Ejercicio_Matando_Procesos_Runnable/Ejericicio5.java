package Ejericicio5.Ejercicio_Matando_Procesos_Runnable;

public class Ejericicio5 {
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Soy el proceso padre");
				for (int i = 0; i < 5000; i++) {
					Thread proceso = new Thread(() -> {
						System.out.println("Soy un porceso hijo");
					});
					proceso.interrupt();
				}
			}
		};
	}
}
